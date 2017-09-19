package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlansLocations {
	public static void main( String[] args ) {
		System.out.println( "main method" );
		PlansLocations pbs = new PlansLocations();
		pbs.runQuery( "108", "01-OCT-2017" );

		System.out.println( "display results in caller" );

		try {
			while( pbs.queryResult.next() ) {
				String planType = pbs.queryResult.getString( "PLAN_TYPE" );
				String benefitPlan = pbs.queryResult.getString( "BENEFIT_PLAN" );
				String planName = pbs.queryResult.getString( "PLAN_NAME" );
				String geoLoc = pbs.queryResult.getString( "LOCATION_TBL_ID" );

				System.out.println( "=====>" + planType + " <=> " + benefitPlan + " <=> " + planName + " <=> " + geoLoc );
			}
		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}

		pbs.close();

	}


	private Connection vDatabaseConnection;
	public ResultSet queryResult;

	public PlansLocations() {
		
	}

	public void runQuery( String benefitProgram, String effdtStr ) {
		System.out.println( "PlansLocations.runQuery( " + benefitProgram + ", " + effdtStr + " )" );
		try {

			// Initialize and connect
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );

			String getPlansSQL =
				  "SELECT DISTINCT PLAN_TYPE " +
				  "              , BENEFIT_PLAN " +
				  "              , ( SELECT BP.DESCR " +
				  "                    FROM PS_BENEF_PLAN_TBL BP " +
				  "                   WHERE BP.PLAN_TYPE = OP.PLAN_TYPE " +
				  "                     AND BP.BENEFIT_PLAN = OP.BENEFIT_PLAN " +
				  "                     AND BP.EFFDT = (SELECT MAX( EFFDT ) " +
				  "                                       FROM PS_BENEF_PLAN_TBL B1 " +
				  "                                      WHERE B1.PLAN_TYPE = BP.PLAN_TYPE " +
				  "                                        AND B1.BENEFIT_PLAN = BP.BENEFIT_PLAN " +
				  "                                        AND B1.EFFDT <= OP.EFFDT) ) " +
				  "                   AS PLAN_NAME " +
				  "              , LOCATION_TBL_ID " +
				  "  FROM PS_BEN_DEFN_OPTN OP " +
				  " WHERE BENEFIT_PROGRAM = ? " +
				  "   AND EFFDT = TO_DATE( ?, 'DD-MON-YYYY' ) " +
				  "   AND PLAN_TYPE = '10' " +
				  "   AND OPTION_TYPE = 'O' " +
				  "   AND NOT ( ELIG_RULES_ID IN ('2009', '23GC', '236Q') ) " +
				  "   AND NOT ( LOCATION_TBL_ID = ' ' )";

			// Prepare statement from SQL string
			PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( getPlansSQL );

			// Set values for parameters
			sqlStmt.setString( 1, benefitProgram );
			sqlStmt.setString( 2, effdtStr );

			// run the query
			queryResult = sqlStmt.executeQuery();

/*       while( qResult.next() ) {
				String planType = qResult.getString( "PLAN_TYPE" );
			   String benefitPlan = qResult.getString( "BENEFIT_PLAN" );
				String planName = qResult.getString( "PLAN_NAME" );
				String geoLoc = qResult.getString( "LOCATION_TBL_ID" );

				System.out.println( "=====>" + planType + " <=> " + benefitPlan + " <=> " + planName + " <=> " + geoLoc );
			}  */

		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}
	}

	
	public void close() {
		try {
			System.out.println( "close the database connection" );
			vDatabaseConnection.close();
		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}

	}
}