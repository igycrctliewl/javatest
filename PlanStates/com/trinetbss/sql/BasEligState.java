package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasEligState {

	public ResultSet queryResult;
	public BasEligState() {
	}

	public void runQuery( String eligRulesId, String effdtStr ) {
		System.out.println( "BasEligState.runQuery( " + eligRulesId + ", " + effdtStr + " )" );
		try {

			// Initialize and connect
			PSConnect psconn = PSConnect.getInstance();
			Connection vDatabaseConnection = psconn.getConnection();

			String sqlString =
					"SELECT ELIG_RULES_ID " +
					"     , TO_CHAR( EFFDT, 'DD-MON-YYYY' ) EFFDT_STR " +
					"     , COUNTRY " +
					"     , STATE " +
					"  FROM PS_BAS_ELIG_STATE " +
					" WHERE ELIG_RULES_ID = ? " +
					"   AND EFFDT = TO_DATE( ?, 'DD-MON-YYYY' ) ";

			// Prepare statement from SQL string
			PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( sqlString );

			// Set values for parameters
			sqlStmt.setString( 1, eligRulesId );
			sqlStmt.setString( 2, effdtStr );

			// run the query
			this.queryResult = sqlStmt.executeQuery();

		} catch( SQLException e ) {
			System.out.println( "BasEligState.runQuery SQL Exception" );
			e.printStackTrace();
		}
	}

	
	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "BasEligState.main()" );
		BasEligState pbs = new BasEligState();
		pbs.runQuery( "2123", "01-JAN-2017" );

		try {
			while( pbs.queryResult.next() ) {
				String eligRulesId = pbs.queryResult.getString( "ELIG_RULES_ID" );
				String effdtStr = pbs.queryResult.getString( "EFFDT_STR" );
				String country = pbs.queryResult.getString( "COUNTRY" );
				String state = pbs.queryResult.getString( "STATE" );

				System.out.println( "BasEligState.main() =>" + eligRulesId + ":" + effdtStr + ":" + country + ":" + state );
			}
		} catch( SQLException e ) {
			System.out.println( "BasEligState.main SQL Exception" );
			e.printStackTrace();
		}

		PSConnect.getInstance().close();
	}

}