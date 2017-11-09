package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlansLocations {

	public ResultSet queryResult;
	public PlansLocations() {
	}

	public void runQuery( String benefitProgram, String effdtStr ) {
		System.out.println( "PlansLocations.runQuery( " + benefitProgram + ", " + effdtStr + " )" );
		try {

			// Initialize and connect
			PSConnect psconn = PSConnect.getInstance();
			Connection vDatabaseConnection = psconn.getConnection();

			String sqlString =
					"SELECT DISTINCT " +
					"       OP.PLAN_TYPE " +
					"     , OP.BENEFIT_PLAN " +
					"     , ( SELECT BP.DESCR " +
					"           FROM PS_BENEF_PLAN_TBL BP " +
					"          WHERE BP.PLAN_TYPE = OP.PLAN_TYPE " +
					"            AND BP.BENEFIT_PLAN = OP.BENEFIT_PLAN " +
					"            AND BP.EFFDT = ( " +
					"                SELECT MAX( EFFDT ) " +
					"                  FROM PS_BENEF_PLAN_TBL B1 " +
					"                 WHERE B1.PLAN_TYPE = BP.PLAN_TYPE " +
					"                   AND B1.BENEFIT_PLAN = BP.BENEFIT_PLAN " +
					"                   AND B1.EFFDT <= OP.EFFDT ) ) " +
					"          AS PLAN_NAME " +
					"     , OP.LOCATION_TBL_ID " +
					"     , OP.ELIG_RULES_ID " +
					"  FROM PS_BEN_DEFN_OPTN OP " +
					" WHERE OP.BENEFIT_PROGRAM = ? " +
					"   AND OP.EFFDT = TO_DATE( ?, 'DD-MON-YYYY' ) " +
					"   AND OP.PLAN_TYPE IN ( '10','11','14','1D','1V','23','30','31' ) " +
					"   AND OP.OPTION_TYPE = 'O' " +
					"   AND NOT ( OP.ELIG_RULES_ID IN ('2009', '23GC', '236Q') ) " +
					"   --EXCLUDE THE INTERNATIONAL PLAN " +
					"   AND NOT ( OP.BENEFIT_PLAN = '0003M3' ) " +
					"   --EXCLUDE MIRROR PLANS " +
					"   AND NOT EXISTS ( " +
					"          SELECT 'X' " +
					"            FROM PS_T2_ACA_MIRR_TBL MIR " +
					"           WHERE MIR.T2_MIRR_BEN_PLAN = OP.BENEFIT_PLAN ) ";

			// Prepare statement from SQL string
			PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( sqlString );

			// Set values for parameters
			sqlStmt.setString( 1, benefitProgram );
			sqlStmt.setString( 2, effdtStr );

			// run the query
			this.queryResult = sqlStmt.executeQuery();

		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}
	}

	
	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "PlansLocations.main()" );
		PlansLocations pbs = new PlansLocations();
		pbs.runQuery( "101", "01-JAN-2018" );

		try {
			while( pbs.queryResult.next() ) {
				String planType = pbs.queryResult.getString( "PLAN_TYPE" );
				String benefitPlan = pbs.queryResult.getString( "BENEFIT_PLAN" );
				String planName = pbs.queryResult.getString( "PLAN_NAME" );
				String geoLoc = pbs.queryResult.getString( "LOCATION_TBL_ID" );
				String eligRule = pbs.queryResult.getString( "ELIG_RULES_ID" );

				System.out.println( "====>" + planType + ":" + benefitPlan + ":" + planName + ":" + geoLoc + ":" + eligRule );
			}
		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}

		PSConnect.getInstance().close();
	}

}