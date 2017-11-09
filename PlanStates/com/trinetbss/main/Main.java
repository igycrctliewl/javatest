package com.trinetbss.main;

import com.trinetbss.sql.GeoLocations;
import com.trinetbss.sql.GeoLocRange;
import com.trinetbss.sql.PlansLocations;
import com.trinetbss.sql.PSConnect;
import com.trinetbss.zipcode.Zipcode;
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

	private static final Zipcode zip = new Zipcode();

	public static void main( String[] args ) {
      System.out.println( "Main.main()" );

		String benefitProgram = "101";
		String effdtStr = "01-JAN-2018";

      Map< String, PSGeogLocn > geoLocationStates =  new HashMap<>();
		PlansLocations pbs = new PlansLocations();

      pbs.runQuery( benefitProgram, effdtStr );
		try {
			while( pbs.queryResult.next() ) {
				String planType = pbs.queryResult.getString( "PLAN_TYPE" );
				String benefitPlan = pbs.queryResult.getString( "BENEFIT_PLAN" );
				String planName = pbs.queryResult.getString( "PLAN_NAME" );
				String geoLoc = pbs.queryResult.getString( "LOCATION_TBL_ID" );

				// if this geo location is already in the map, just reuse it
				// if not, add it to the map along with the related object
				if( !geoLocationStates.containsKey( geoLoc ) ) {
					//System.out.println( "********** getting geo location definition **********" );
					PSGeogLocn psGeogLocn = new PSGeogLocn( geoLoc, effdtStr );
					// System.out.println( "********** get geo location ranges **********" );
					Main.getGeogLocnRangeStates( psGeogLocn );
					// System.out.println( " -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* states so far: " + psGeogLocn.states );
					geoLocationStates.put( geoLoc, psGeogLocn );
 				}

				// System.out.println( "********** associate plan with states **********" );
				for( String state : geoLocationStates.get( geoLoc ).states ) {
					System.out.println( planType + "," + benefitPlan + "," + planName + "," + state );
				}
			}
		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}

		System.out.println( "Geo location map contained " + geoLocationStates.size() + " entries." );

		PSConnect.getInstance().close();
   }

	/**
	 * This method returns no value but it has the side-effect of updating the passed
	 * PSGeogLocn parameter with the list of states found in the associated list of
	 * zip codes for this geo location
	 */
	private static void getGeogLocnRangeStates( PSGeogLocn psGeogLocn ) {
		GeoLocRange ranges = new GeoLocRange();
		ranges.runQuery( psGeogLocn.geogLocnId, psGeogLocn.effdt );
		try {
			while( ranges.queryResult.next() ) {
				String fromZip = ranges.queryResult.getString( "LOCN_FROM" );
				String toZip = ranges.queryResult.getString( "LOCN_TO" );

				// System.out.println( "=====>" + fromZip + " <=> " + toZip + " <=" );

				int fromInt = Integer.parseInt( fromZip );
				int toInt = Integer.parseInt( toZip.substring( 0, 5 ) );
				for( int a = fromInt; a <= toInt; a++ ) {
					// System.out.println( "     a:" + a );
					String zipCode = String.valueOf( a );
					while( zipCode.length() < 5 ) {
						zipCode = "0".concat( zipCode );
					}
					String state = Main.callGeoApi( zipCode );
					if( state != null ) {
						psGeogLocn.states.add( state );
					}
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
		return zip.lookupZip( zipCode );
	}

}
