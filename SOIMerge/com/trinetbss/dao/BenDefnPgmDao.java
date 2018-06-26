package com.trinetbss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.trinetbss.model.BenDefnPgm;

public class BenDefnPgmDao {

	private static PSConnect psconn = PSConnect.getInstance();
	private static Connection dbConnection = psconn.getConnection();

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
			return psconn.getConnection().prepareStatement( pgmSqlStr );
		} catch( Exception e ) {
			System.out.println( "The PGM statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}


	public static List<BenDefnPgm> getAllPgmRows( String benProg, String effdtStr ) {

		try {
			pgmStmt.setString( 1, benProg );
			pgmStmt.setDate( 2, java.sql.Date.valueOf( effdtStr ) );
			ResultSet queryResult = pgmStmt.executeQuery();
			queryResult.next();
			System.out.println( queryResult.getString( "DESCR" ) );
		} catch( SQLException e ) {
			System.out.println( "BenDefnPgmDao.getAllPgmRows() SQL Exception" );
			e.printStackTrace();
		}

		return null;
	}

	
	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "BenDefnPgmDao.main()" );

		List<BenDefnPgm> pgm = BenDefnPgmDao.getAllPgmRows( "001AAF", "2018-04-01" );

		PSConnect.getInstance().close();
	}

}