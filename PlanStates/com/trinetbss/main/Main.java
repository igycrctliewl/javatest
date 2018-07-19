package com.trinetbss.main;

import com.trinetbss.output.BSSTableData;
import com.trinetbss.portfolio.Portfolio;
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
import java.util.Set;


public class Main {

	private static final Zipcode zip = new Zipcode();
	private static final Portfolio port = new Portfolio();

	public static void main( String[] args ) {
      System.out.println( "Main.main()" );

		int realmYearId = 17;
		String benefitProgram = "110";
		String effdtStr = "01-JAN-2019";

		// prepare output object
		BSSTableData bss = new BSSTableData();

      Map< String, PSGeogLocn > geoLocationStates =  new HashMap<>();
      Map< String, PSEligRule > eligRulesStates =  new HashMap<>();
		PlansLocations pbs = new PlansLocations();

      pbs.runQuery( benefitProgram, effdtStr );
		try {
			while( pbs.queryResult.next() ) {
				String planType = pbs.queryResult.getString( "PLAN_TYPE" );
				String benefitPlan = pbs.queryResult.getString( "BENEFIT_PLAN" );
				String planName = pbs.queryResult.getString( "PLAN_NAME" );
				String vendor = pbs.queryResult.getString( "VENDOR_ID" );
				String geoLoc = pbs.queryResult.getString( "LOCATION_TBL_ID" );
				String eligRulesId = pbs.queryResult.getString( "ELIG_RULES_ID" );
				Set<String> eligibleStates = new HashSet<>();

				// if this geo location is already in the map, just reuse it
				// if not, add it to the map along with the related object
				if( !geoLocationStates.containsKey( geoLoc ) ) {
					// getting geo location code definition
					PSGeogLocn psGeogLocn = new PSGeogLocn( geoLoc, effdtStr );
					// get states from geo location zip code ranges
					Main.getGeogLocnRangeStates( psGeogLocn );
					// add updated geo location object to map
					geoLocationStates.put( geoLoc, psGeogLocn );
 				}

				// add the states for this geo location to the complete list for this benefit plan
				// as requested by Ramakrishna, state eligibility should only be produced for plan type 10
				if( "10".equals( planType ) && geoLocationStates.get( geoLoc ).states != null ) {
					eligibleStates.addAll( geoLocationStates.get( geoLoc ).states );
				}

				// evaluate the elig rule state criteria
				if( !eligRulesStates.containsKey( eligRulesId ) ) {
					// get elig rule definition
					PSEligRule psEligRule = new PSEligRule( eligRulesId, effdtStr );
					// add updated geo location object to map
					eligRulesStates.put( eligRulesId, psEligRule );
 				}

				// add the states for this elig rule to the complete list for this benefit plan
				// as requested by Ramakrishna, state eligibility should only be produced for plan type 10
				if( "10".equals( planType ) && eligRulesStates.get( eligRulesId ).states != null ) {
					eligibleStates.addAll( eligRulesStates.get( eligRulesId ).states );
				}


				// associate plan with states
				System.out.println( "Main.main() => " + planType + "," + benefitPlan + "," + planName + "  (" + vendor + ")" );
				for( String state : eligibleStates ) {
					System.out.println( "Main.main() => " + planType + "," + benefitPlan + "," + planName + "," + state );
				}

				// write output to load files
				bss.writePlanData( realmYearId, planType, benefitPlan, port.lookupPortfolio( vendor, benefitPlan ), null, eligibleStates.toArray( new String[0] ) );

			}
		} catch( SQLException e ) {
			System.out.println( "Main.main() SQL Exception" );
			e.printStackTrace();
		} catch( IOException e ) {
			System.out.println( "Main.main() IO Exception - check status of csv output files" );
			e.printStackTrace();
		}

		PSConnect.getInstance().close();
		bss.close();
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

				// System.out.println( "Main.getGeogLocnRangeStates() => " + fromZip + " <=> " + toZip + " <=" );

				int fromInt = Integer.parseInt( fromZip );
				int toInt = Integer.parseInt( toZip.substring( 0, 5 ) );
				for( int a = fromInt; a <= toInt; a++ ) {
					// System.out.println( "Main.getGeogLocnRangeStates() =>  a:" + a );
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
			System.out.println( "Main.getGeogLocnRangeStates SQL Exception" );
			e.printStackTrace();
		}
	}

	/**
	 * @return the state derived from the input zipCode
	 */
	private static String callGeoApi( String zipCode ) {
		return zip.lookupZip( zipCode );
	}

}
