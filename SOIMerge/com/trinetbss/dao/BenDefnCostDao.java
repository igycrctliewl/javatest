package com.trinetbss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trinetbss.model.BenDefnCost;

public class BenDefnCostDao {

	private static PSConnect psconn = PSConnect.getInstance();
	private static Connection dbConnection = psconn.getConnection();

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
		System.out.println( "prepare statement" );
		try {
			return psconn.getConnection().prepareStatement( costSqlStr );
		} catch( Exception e ) {
			System.out.println( "The COST statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}


	public static List<BenDefnCost> getAllCostRows( String benProg, String effdtStr ) {

		List<BenDefnCost> result = new ArrayList<BenDefnCost>();
		try {
			costStmt.setString( 1, benProg );
			costStmt.setDate( 2, java.sql.Date.valueOf( effdtStr ) );
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
		PSConnect.getInstance().close();
	}

}