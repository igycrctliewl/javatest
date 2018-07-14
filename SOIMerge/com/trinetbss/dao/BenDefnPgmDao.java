package com.trinetbss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trinetbss.model.BenDefnPgm;

public class BenDefnPgmDao {

	private static PSConnect psconn = PSConnect.getInstance();

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
		System.out.println( "prepare PGM statement" );
		try {
			return psconn.getConnection().prepareStatement( pgmSqlStr );
		} catch( Exception e ) {
			System.out.println( "The PGM statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}


	public static List<BenDefnPgm> getAllPgmRows( String benProg, String effdtStr ) {

		List<BenDefnPgm> result = new ArrayList<BenDefnPgm>();
		try {
			pgmStmt.setString( 1, benProg );
			pgmStmt.setDate( 2, java.sql.Date.valueOf( effdtStr ) );
			ResultSet queryResult = pgmStmt.executeQuery();

			while( queryResult.next() ) {
				BenDefnPgm row = getRowObj( queryResult );
				result.add( row );
			}
		} catch( SQLException e ) {
			System.out.println( "BenDefnPgmDao.getAllPgmRows() SQL Exception" );
			System.out.println( e.getMessage() );
			e.printStackTrace();
		}

		return result;
	}


	public static BenDefnPgm getPgmRow( BenDefnPgm pgm ) {

		try {
			pgmStmt.setString( 1, pgm.benefitProgram );
			pgmStmt.setDate( 2, pgm.effdt );
			ResultSet queryResult = pgmStmt.executeQuery();

			while( queryResult.next() ) {
				pgm = getRowObj( queryResult );
			}
		} catch( SQLException e ) {
			System.out.println( "BenDefnPgmDao.getPgmRow() SQL Exception" );
			System.out.println( e.getMessage() );
			e.printStackTrace();
		}
		return pgm;
	}



	private static BenDefnPgm getRowObj( ResultSet dbRow ) throws SQLException {
		BenDefnPgm obj = new BenDefnPgm();
		obj.benefitProgram = dbRow.getString( "BENEFIT_PROGRAM" );
		obj.effdt = dbRow.getDate( "EFFDT" );
		obj.descr = dbRow.getString( "DESCR" );
		obj.pfClient = dbRow.getString( "PF_CLIENT" );
		obj.descrShort = dbRow.getString( "DESCRSHORT" );
		obj.effStatus = dbRow.getString( "EFF_STATUS" );
		obj.programType = dbRow.getString( "PROGRAM_TYPE" );
		obj.fsaRunId = dbRow.getString( "FSA_RUN_ID" );
		obj.fsaMaxAnnlPldg = dbRow.getBigDecimal( "FSA_MAX_ANNL_PLDG" );
		obj.currencyCd = dbRow.getString( "CURRENCY_CD" );
		obj.dfltExpirationDd = dbRow.getBigDecimal( "DFLT_EXPIRATION_DD" );
		obj.dfltCreditRllovr = dbRow.getString( "DFLT_CREDIT_RLLOVR" );
		obj.cobraSurcharge = dbRow.getBigDecimal( "COBRA_SURCHARGE" );
		obj.cobraDisablSurcg = dbRow.getBigDecimal( "COBRA_DISABL_SURCG" );
		obj.fmlaPlanId = dbRow.getString( "FMLA_PLAN_ID" );
		obj.showCredit = dbRow.getString( "SHOW_CREDIT" );
		obj.costFrequency = dbRow.getString( "COST_FREQUENCY" );
		obj.HandbookUrlId = dbRow.getString( "HANDBOOK_URL_ID" );
		obj.incldCnslstx = dbRow.getString( "INCLD_CNSLSTX" );
		obj.cobraContactId = dbRow.getBigDecimal( "COBRA_CONTACT_ID" );
		obj.basShowErCosts = dbRow.getString( "BAS_SHOW_ER_COSTS" );
		obj.basShowTaxImpct = dbRow.getString( "BAS_SHOW_TAX_IMPCT" );
		return obj;
	}


	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "BenDefnPgmDao.main()" );

		List<BenDefnPgm> pgm = BenDefnPgmDao.getAllPgmRows( "001AAF", "2018-04-01" );
		System.out.println( "returned rows: " + pgm.size() );

	}

}