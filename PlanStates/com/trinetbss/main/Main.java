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
					System.out.println( "********** getting geo location definition **********" );
					PSGeogLocn psGeogLocn = new PSGeogLocn( geoLoc, effdtStr );
					System.out.println( "********** get geo location ranges **********" );
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

		System.out.println( "Geo location map contained " + geoLocationStates.size() + " entries." );

		PSConnect.getInstance().close();
   }

	
	private static void getGeogLocnRangeStates( PSGeogLocn psGeogLocn ) {
		GeoLocRange ranges = new GeoLocRange();
		ranges.runQuery( psGeogLocn.geogLocnId, psGeogLocn.effdt );
		try {
			while( ranges.queryResult.next() ) {
				String fromZip = ranges.queryResult.getString( "LOCN_FROM" );
				String toZip = ranges.queryResult.getString( "LOCN_TO" );

				System.out.println( "=====>" + fromZip + " <=> " + toZip + " <=" );

				int fromInt = Integer.parseInt( fromZip );
				int toInt = Integer.parseInt( toZip.substring( 0, 5 ) );
				for( int a = fromInt; a <= toInt; a++ ) {
					System.out.println( "     a:" + a );
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
		return zip.lookupZip( zipCode );
	}

}
