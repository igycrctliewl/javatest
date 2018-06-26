package com.trinetbss.main;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OldBenefitProgram {

	private String benProg;
	private java.sql.Date effdt;

	public OldBenefitProgram( String benProg, String effdtStr ) {
		this.benProg = benProg;
		this.effdt = java.sql.Date.valueOf( effdtStr );
	}


	private static final Connection dbConnection = initConnection();
	private static Connection initConnection() {
		try {
			return DriverManager.getConnection( "jdbc:oracle:thin:@dbhrslm06.trinet.com:1521:hrslm06", "sysadm", "mhall510" );
		} catch( Exception e ) {
			System.out.println( "The database connection was not established." );
			e.printStackTrace();
			return null;
		}
	}
	public static Connection getConnection() {
		return dbConnection;
	}

	private static String pgmSqlStr = 
			"SELECT  " +
			"  BENEFIT_PROGRAM      " +
			", EFFDT                " +
			", DESCR                " +
			", PF_CLIENT            " +
			", DESCRSHORT           " +
			", EFF_STATUS           " +
			", PROGRAM_TYPE         " +
			", FSA_RUN_ID           " +
			", FSA_MAX_ANNL_PLDG    " +
			", CURRENCY_CD          " +
			", DFLT_EXPIRATION_DD   " +
			", DFLT_CREDIT_RLLOVR   " +
			", COBRA_SURCHARGE      " +
			", COBRA_DISABL_SURCG   " +
			", FMLA_PLAN_ID         " +
			", SHOW_CREDIT          " +
			", COST_FREQUENCY       " +
			", HANDBOOK_URL_ID      " +
			", INCLD_CNSLSTX        " +
			", COBRA_CONTACT_ID     " +
			", BAS_SHOW_ER_COSTS    " +
			", BAS_SHOW_TAX_IMPCT " +
			"FROM PS_BEN_DEFN_PGM " +
			"WHERE BENEFIT_PROGRAM = ? " +
			"AND EFFDT = ? ";
	private static final PreparedStatement pgmStmt = initPgmStmt();
	private static PreparedStatement initPgmStmt() {
		System.out.println( "prepare statement" );
		try {
			return getConnection().prepareStatement( pgmSqlStr );
		} catch( Exception e ) {
			System.out.println( "The PGM statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}



	public static void main( String[] args ) {

      System.out.println( new java.util.Date() );

		OldBenefitProgram obp = new OldBenefitProgram( "001AAF", "2018-04-01" );
		System.out.println( obp );


      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = OldBenefitProgram.getConnection();

         String getRatesSQL =
                "SELECT NAME " +
                "  FROM V$DATABASE ";




         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( getRatesSQL );

         // Set values for parameters
         // sqlStmt.setLong( 1, 2 );                                         // realmID
         // sqlStmt.setDate( 2, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         // sqlStmt.setLong( 3, 2 );                                         // realmID
         // sqlStmt.setDate( 4, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         // sqlStmt.setString( 5, "Q1" );                                    // OE quarter

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

         while( qResult.next() ) {
       	   String dbName = qResult.getString( "NAME" );
            System.out.println( "=====>" + dbName + " <=> " );
         }


         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

      System.out.println( new java.util.Date() );

   }

}