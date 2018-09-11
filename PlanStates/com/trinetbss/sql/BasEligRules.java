package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasEligRules {

	public ResultSet queryResult;
	public BasEligRules() {
	}

	public void runQuery( String eligRulesId, String effdtStr ) {
		System.out.println( "BasEligRules.runQuery( " + eligRulesId + ", " + effdtStr + " )" );
		try {

			// Initialize and connect
			PSConnect psconn = PSConnect.getInstance();
			Connection vDatabaseConnection = psconn.getConnection();

			String sqlString =
					"SELECT RL.ELIG_RULES_ID " +
					"     , TO_CHAR( RL.EFFDT, 'DD-MON-YYYY' ) EFFDT_STR " +
					"     , RL.ELIG_FLG_STATE " +
					"     , RL.ELIG_USE_STATE " +
					"     , RL.GRP_MTHD_STATE " +
					"     , RL.EVAL_MTHD_STATE " +
					"     , RL.EVAL_ACTV_STATE " +
					"  FROM PS_BAS_ELIG_RULES RL " +
					" WHERE RL.ELIG_RULES_ID = ? " +
					"   AND RL.EFFDT = ( " +
					"       SELECT MAX(EFFDT) " +
					"         FROM PS_BAS_ELIG_RULES R1 " +
					"        WHERE R1.ELIG_RULES_ID = RL.ELIG_RULES_ID " +
					"          AND R1.EFFDT <= TO_DATE( ?, 'DD-MON-YYYY' ) ) ";

			// Prepare statement from SQL string
			PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( sqlString );

			// Set values for parameters
			sqlStmt.setString( 1, eligRulesId );
			sqlStmt.setString( 2, effdtStr );

			// run the query
			this.queryResult = sqlStmt.executeQuery();

		} catch( SQLException e ) {
			System.out.println( "BasEligRules.runQuery SQL Exception" );
			e.printStackTrace();
		}
	}

	
	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "BasEligRules.main()" );
		BasEligRules pbs = new BasEligRules();
		pbs.runQuery( "0200", "01-JAN-2018" );

		try {
			while( pbs.queryResult.next() ) {
				String eligRulesId = pbs.queryResult.getString( "ELIG_RULES_ID" );
				String effdtStr = pbs.queryResult.getString( "EFFDT_STR" );
				String eligFlgState = pbs.queryResult.getString( "ELIG_FLG_STATE" );
				String eligUseState = pbs.queryResult.getString( "ELIG_USE_STATE" );
				String grpMthdState = pbs.queryResult.getString( "GRP_MTHD_STATE" );
				String evalMthdState = pbs.queryResult.getString( "EVAL_MTHD_STATE" );
				String evalActvState = pbs.queryResult.getString( "EVAL_ACTV_STATE" );

				System.out.println( "BasEligRules.main() =>" + eligRulesId  + ":" + effdtStr  + ":" + eligFlgState  + ":" + eligUseState  + ":" + grpMthdState  + ":" + evalMthdState + ":" + evalActvState );
			}
		} catch( SQLException e ) {
			System.out.println( "BasEligRules.runQuery SQL Exception" );
			e.printStackTrace();
		}

		PSConnect.getInstance().close();
	}

}