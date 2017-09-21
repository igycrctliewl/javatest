package com.trinetbss.main;

import com.trinetbss.json.geocode.AddressComponent;
import com.trinetbss.json.geocode.Geocode;
import com.trinetbss.json.geocode.Result;
import com.trinetbss.sql.GeoLocations;
import com.trinetbss.sql.GeoLocRange;
import com.trinetbss.sql.PlansLocations;
import com.trinetbss.sql.PSConnect;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Main {
	public static void main( String[] args ) {
		System.out.println( "Main.main()" );

		String benefitProgram = "108";
		String effdtStr = "01-OCT-2017";

		Map< String, PSGeogLocn > geoLocationStates =  new HashMap<>();
		PlansLocations pbs = new PlansLocations();

		pbs.runQuery( benefitProgram, effdtStr );
		try {
			while( pbs.queryResult.next() ) {
				String planType = pbs.queryResult.getString( "PLAN_TYPE" );
				String benefitPlan = pbs.queryResult.getString( "BENEFIT_PLAN" );
				String planName = pbs.queryResult.getString( "PLAN_NAME" );
				String geoLoc = pbs.queryResult.getString( "LOCATION_TBL_ID" );

				if( !geoLocationStates.containsKey( geoLoc ) ) {
					//System.out.println( "********** getting geo location definition **********" );
					PSGeogLocn psGeogLocn = new PSGeogLocn( geoLoc, effdtStr );
					//System.out.println( "********** get geo location ranges **********" );
					Main.getGeogLocnRangeStates( psGeogLocn );
					System.out.println( " -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* states so far: " + psGeogLocn.states );
					geoLocationStates.put( geoLoc, psGeogLocn );
				}

				System.out.println( "********** associate plan with states **********" );
				System.out.println( "=====>" + planType + " <=> " + benefitPlan + " <=> " + planName + " <=> " + geoLoc );
			}
		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}

		//System.out.println( "Geo location map contained " + geoLocationStates.size() + " entries." );

		PSConnect.getInstance().close();
	}

	
	private static void getGeogLocnRangeStates( PSGeogLocn psGeogLocn ) {
		GeoLocRange ranges = new GeoLocRange();
		ranges.runQuery( psGeogLocn.geogLocnId, psGeogLocn.effdt );
		try {
			while( ranges.queryResult.next() ) {
				String fromZip = ranges.queryResult.getString( "LOCN_FROM" );
				String toZip = ranges.queryResult.getString( "LOCN_TO" );

				//System.out.println( "=====>" + fromZip + " <=> " + toZip + " <=" );

				int fromInt = Integer.parseInt( fromZip );
				int toInt = Integer.parseInt( toZip.substring( 0, 5 ) );
				for( int a = fromInt; a <= toInt; a++ ) {
					//System.out.println( "     a:" + a );
					String zipCode = String.valueOf( a );
					while( zipCode.length() < 5 ) {
						zipCode = "0".concat( zipCode );
					}
					psGeogLocn.states.add( Main.callGeoApi( zipCode ) );
				}
			}
		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}

	}

	/**
	 * @return the state derived from the input zipCode
	 */
	private static String callGeoApi( String zipCode ) {

		Address addr = new Address();

		try {
			URL geogApi = new URL( "http://maps.googleapis.com/maps/api/geocode/json?address=US&components=postal_code:" + zipCode );

			ObjectMapper mapper = new ObjectMapper();
			Geocode obj = null;
			obj = mapper.readValue( geogApi, Geocode.class );
			System.out.println(obj);

			for (Result r : obj.getResults()) {
				System.out.println(r.getFormattedAddress());
				for (AddressComponent ac : r.getAddressComponents()) {
					parseAC( ac, addr );
				}
				System.out.println( "     " + addr );
			}
		} catch ( MalformedURLException mal ) {
			System.out.println("bad url");
			mal.printStackTrace();
		} catch ( IOException e ) {
			System.out.println("caught IOException:" + e.getClass().getName() );
			e.printStackTrace();
		}

		if( "US".equals( addr.country ) ) {
			//System.out.println( "Determined state is " + addr.state );
			return addr.state;
		} else {
			System.out.println( "zipCode " + zipCode + " returned no state value; addr.country = " + addr.country );
			return "";
		}
	}

	private static void parseAC( AddressComponent adr, Address a ) {
		String longName = adr.getLongName();
		String shortName = adr.getShortName();
		List<String> types = adr.getTypes();

		System.out.println( ">>>> parseAC" );
		System.out.println( "     longName:" + longName );
		System.out.println( "     shortName:" + shortName );

		if( types.contains( "postal_code" ) ) {
			System.out.println( "     zip code is " + shortName );
			a.zip = shortName;
		} else if( types.contains( "locality" ) ) {
			System.out.println( "     city is " + shortName );
			a.city = shortName;
		} else if( types.contains( "administrative_area_level_2" ) ) {
			System.out.println( "     county is " + shortName );
		} else if( types.contains( "administrative_area_level_1" ) ) {
			System.out.println( "     state is " + shortName );
			a.state = shortName;
		} else if( types.contains( "country" ) ) {
			System.out.println( "     country is " + shortName );
			a.country = shortName;
		}

	}

	static class Address {
		String city;
		String state;
		String zip;
		String country;

		public String toString() {
			return this.getClass().getName() + ":" + this.city + ", " + this.state + "   " + this.zip + "  " + this.country;
		}
	}
}
