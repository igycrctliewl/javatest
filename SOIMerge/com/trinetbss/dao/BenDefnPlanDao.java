package com.trinetbss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trinetbss.model.BenDefnPlan;

public class BenDefnPlanDao {

	private static PSConnect psconn = PSConnect.getInstance();
	private static Connection dbConnection = psconn.getConnection();

	private static String planSqlStr =
			"SELECT  " +
			"  BENEFIT_PROGRAM    " +
			", EFFDT              " +
			", PLAN_TYPE          " +
			", DISPLAY_PLN_SEQ    " +
			", MIN_ANNUAL_CONTRIB " +
			", MAX_ANNUAL_CONTRIB " +
			", WAIVE_COVERAGE     " +
			", RESTRICT_ENTRY_MM  " +
			", EVENT_RULES_ID     " +
			", COBRA_PLAN         " +
			", HIPAA_PLAN         " +
			", COLLECT_DEPBEN     " +
			", COLLECT_FUNDS      " +
			", SHOW_PLAN_TYPE     " +
			", HANDBOOK_URL_ID    " +
			", DEP_RULE_ID        " +
			"FROM PS_BEN_DEFN_PLAN " +
			"WHERE BENEFIT_PROGRAM = ? " +
			"AND EFFDT = ? ";
	private static final PreparedStatement planStmt = initPlanStmt();
	private static PreparedStatement initPlanStmt() {
		System.out.println( "prepare statement" );
		try {
			return psconn.getConnection().prepareStatement( planSqlStr );
		} catch( Exception e ) {
			System.out.println( "The PLAN statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}


	public static List<BenDefnPlan> getAllPlanRows( String benProg, String effdtStr ) {

		List<BenDefnPlan> result = new ArrayList<BenDefnPlan>();
		try {
			planStmt.setString( 1, benProg );
			planStmt.setDate( 2, java.sql.Date.valueOf( effdtStr ) );
			ResultSet queryResult = planStmt.executeQuery();

			while( queryResult.next() ) {
				BenDefnPlan row = getRowObj( queryResult );
				result.add( row );
			}
		} catch( SQLException e ) {
			System.out.println( "BenDefnPlanDao.getAllPlanRows() SQL Exception" );
			e.printStackTrace();
		}

		return result;
	}



	private static BenDefnPlan getRowObj( ResultSet dbRow ) throws SQLException {
		BenDefnPlan obj = new BenDefnPlan();
		obj.benefitProgram = dbRow.getString( "BENEFIT_PROGRAM" );
		obj.effdt = dbRow.getDate( "EFFDT" );
		obj.planType = dbRow.getString( "PLAN_TYPE" );
		obj.displayPlnSeq = dbRow.getString( "DISPLAY_PLN_SEQ" );
		obj.minAnnualContrib = dbRow.getBigDecimal( "MIN_ANNUAL_CONTRIB" );
		obj.maxAnnualContrib = dbRow.getBigDecimal( "MAX_ANNUAL_CONTRIB" );
		obj.waiveCoverage = dbRow.getString( "WAIVE_COVERAGE" );
		obj.restrictEntryMm = dbRow.getBigDecimal( "RESTRICT_ENTRY_MM" );
		obj.eventRulesId = dbRow.getString( "EVENT_RULES_ID" );
		obj.cobraPlan = dbRow.getString( "COBRA_PLAN" );
		obj.hipaaPlan = dbRow.getString( "HIPAA_PLAN" );
		obj.collectDepben = dbRow.getString( "COLLECT_DEPBEN" );
		obj.collectFunds = dbRow.getString( "COLLECT_FUNDS" );
		obj.showPlanType = dbRow.getString( "SHOW_PLAN_TYPE" );
		obj.handbookUrlId = dbRow.getString( "HANDBOOK_URL_ID" );
		obj.depRuleId = dbRow.getString( "DEP_RULE_ID" );
		return obj;
	}


	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "BenDefnPlanDao.main()" );

		List<BenDefnPlan> plan = BenDefnPlanDao.getAllPlanRows( "001AAF", "2018-04-01" );
		System.out.println( "returned rows: " + plan.size() );

		// test comparator and print sorted result
		plan.sort( BenDefnPlan.PlanComparator );
		for( BenDefnPlan p : plan ) {
			System.out.println( p.benefitProgram + "<->" + p.effdt + "<->" + p.planType );
		}
		PSConnect.getInstance().close();
	}

}