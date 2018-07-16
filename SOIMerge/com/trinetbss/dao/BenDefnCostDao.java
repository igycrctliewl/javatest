package com.trinetbss.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trinetbss.model.BenDefnCost;
import com.trinetbss.model.BenDefnOptn;
import com.trinetbss.model.BenDefnPgm;

public class BenDefnCostDao {

	private static PSConnect psconn = PSConnect.getInstance();

	private static String costSqlStr =
			"SELECT  " +
			"  BENEFIT_PROGRAM " +
			", EFFDT           " +
			", PLAN_TYPE       " +
			", OPTION_ID       " +
			", COST_ID         " +
			", COST_TYPE       " +
			", ERNCD           " +
			", RATE_TYPE       " +
			", RATE_TBL_ID     " +
			", CALC_RULES_ID   " +
			"FROM PS_BEN_DEFN_COST " +
			"WHERE BENEFIT_PROGRAM = ? " +
			"AND EFFDT = ? ";
	private static final PreparedStatement costStmt = initCostStmt();
	private static PreparedStatement initCostStmt() {
		System.out.println( "prepare COST statement" );
		try {
			PreparedStatement st = psconn.getConnection().prepareStatement( costSqlStr );
			st.setFetchSize( 10000 );
			return st;
		} catch( Exception e ) {
			System.out.println( "The COST statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}


	private static String costFromOptnSqlStr =
			"SELECT  " +
			"  BENEFIT_PROGRAM " +
			", EFFDT           " +
			", PLAN_TYPE       " +
			", OPTION_ID       " +
			", COST_ID         " +
			", COST_TYPE       " +
			", ERNCD           " +
			", RATE_TYPE       " +
			", RATE_TBL_ID     " +
			", CALC_RULES_ID   " +
			"FROM PS_BEN_DEFN_COST " +
			"WHERE BENEFIT_PROGRAM = ? " +
			"AND EFFDT = ? " +
			"AND PLAN_TYPE = ? " +
			"AND OPTION_ID = ? ";
	private static final PreparedStatement costFromOptnStmt = initCostFromOptnStmt();
	private static PreparedStatement initCostFromOptnStmt() {
		System.out.println( "prepare COST FROM OPTN statement" );
		try {
			return psconn.getConnection().prepareStatement( costFromOptnSqlStr );
		} catch( Exception e ) {
			System.out.println( "The COST FROM OPTN statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}


	public static List<BenDefnCost> getAllCostRows( String benProg, String effdtStr ) {
		BenDefnPgm pgm = new BenDefnPgm();
		pgm.benefitProgram = benProg;
		pgm.effdt = java.sql.Date.valueOf( effdtStr );
		return getAllCostRows( pgm );
	}


	public static List<BenDefnCost> getAllCostRows( BenDefnPgm pgm ) {

		List<BenDefnCost> result = new ArrayList<BenDefnCost>();
		try {
			costStmt.setString( 1, pgm.benefitProgram );
			costStmt.setDate( 2, pgm.effdt );
			ResultSet queryResult = costStmt.executeQuery();

			while( queryResult.next() ) {
				BenDefnCost row = getRowObj( queryResult );
				result.add( row );
			}
		} catch( SQLException e ) {
			System.out.println( "BenDefnCostDao.getAllCostRows() SQL Exception" );
			e.printStackTrace();
		}

		return result;
	}



	public static List<BenDefnCost> getCostRowsForOptn( BenDefnOptn optn ) {

		List<BenDefnCost> result = new ArrayList<BenDefnCost>();
		try {
			costFromOptnStmt.setString( 1, optn.benefitProgram );
			costFromOptnStmt.setDate( 2, optn.effdt );
			costFromOptnStmt.setString( 3, optn.planType );
			costFromOptnStmt.setInt( 4, optn.optionId.intValue() );
			ResultSet queryResult = costFromOptnStmt.executeQuery();

			while( queryResult.next() ) {
				BenDefnCost row = getRowObj( queryResult );
				result.add( row );
			}
		} catch( SQLException e ) {
			System.out.println( "BenDefnCostDao.getCostRowsForOptn() SQL Exception" );
			e.printStackTrace();
		}

		return result;
	}



	private static BenDefnCost getRowObj( ResultSet dbRow ) throws SQLException {
		BenDefnCost obj = new BenDefnCost();
		obj.benefitProgram = dbRow.getString( "BENEFIT_PROGRAM" );
		obj.effdt = dbRow.getDate( "EFFDT" );
		obj.planType = dbRow.getString( "PLAN_TYPE" );
		obj.optionId = dbRow.getBigDecimal( "OPTION_ID" );
		obj.costId = dbRow.getBigDecimal( "COST_ID" );
		obj.costType = dbRow.getString( "COST_TYPE" );
		obj.erncd = dbRow.getString( "ERNCD" );
		obj.rateType = dbRow.getString( "RATE_TYPE" );
		obj.rateTblId = dbRow.getString( "RATE_TBL_ID" );
		obj.calcRulesId = dbRow.getString( "CALC_RULES_ID" );
		return obj;
	}


	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "BenDefnCostDao.main()" );

		List<BenDefnCost> cost = BenDefnCostDao.getAllCostRows( "001AAF", "2018-04-01" );
		System.out.println( "returned rows: " + cost.size() );

		System.out.println( "Specific OPTN-COST row test..." );
		BenDefnOptn optn = new BenDefnOptn();
		optn.benefitProgram = "001AAF";
		optn.effdt = java.sql.Date.valueOf( "2018-04-01" );
		optn.planType = "23";
		optn.optionId = new BigDecimal( "4585" );
		List<BenDefnCost> cost2 = BenDefnCostDao.getCostRowsForOptn( optn );
		for( BenDefnCost c : cost2 ) {
			System.out.println( c.benefitProgram + "<->" + c.effdt + "<->" + c.planType + "<->" + c.optionId + "<->" + c.costId );
		}
	}

}
