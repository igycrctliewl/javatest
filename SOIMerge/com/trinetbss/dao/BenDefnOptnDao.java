package com.trinetbss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trinetbss.model.BenDefnOptn;

public class BenDefnOptnDao {

	private static PSConnect psconn = PSConnect.getInstance();
	private static Connection dbConnection = psconn.getConnection();

	private static String optnSqlStr =
			"SELECT  " +
			"  BENEFIT_PROGRAM    " +
			", EFFDT              " +
			", PLAN_TYPE          " +
			", OPTION_ID          " +
			", DISPLAY_OPT_SEQ    " +
			", OPTION_TYPE        " +
			", BENEFIT_PLAN       " +
			", COVRG_CD           " +
			", OPTION_CD          " +
			", OPTION_LVL         " +
			", DEDCD              " +
			", DFLT_OPTION_IND    " +
			", ELIG_RULES_ID      " +
			", LOCATION_TBL_ID    " +
			", CROSS_PLAN_TYPE    " +
			", CROSS_BENEF_PLAN   " +
			", COVERAGE_LIMIT_PCT " +
			", CROSS_PLN_DPND_CHK " +
			"FROM PS_BEN_DEFN_OPTN " +
			"WHERE BENEFIT_PROGRAM = ? " +
			"AND EFFDT = ? ";
	private static final PreparedStatement optnStmt = initOptnStmt();
	private static PreparedStatement initOptnStmt() {
		System.out.println( "prepare OPTN statement" );
		try {
			return psconn.getConnection().prepareStatement( optnSqlStr );
		} catch( Exception e ) {
			System.out.println( "The OPTN statement was not prepared." );
			e.printStackTrace();
			return null;
		}
	}


	public static List<BenDefnOptn> getAllOptnRows( String benProg, String effdtStr ) {

		List<BenDefnOptn> result = new ArrayList<BenDefnOptn>();
		try {
			optnStmt.setString( 1, benProg );
			optnStmt.setDate( 2, java.sql.Date.valueOf( effdtStr ) );
			ResultSet queryResult = optnStmt.executeQuery();

			while( queryResult.next() ) {
				BenDefnOptn row = getRowObj( queryResult );
				result.add( row );
			}
		} catch( SQLException e ) {
			System.out.println( "BenDefnOptnDao.getAllOptnRows() SQL Exception" );
			e.printStackTrace();
		}

		return result;
	}


	public static void getMatchingCostRows( BenDefnOptn optn ) {
		// lookup the cost rows and add to the BenDefnOptn object
		optn.cost = BenDefnCostDao.getCostRowsForOptn( optn );
	}


	public static void getMatchingCostRows( List<BenDefnOptn> optns ) {
		// for each row in optn, lookup the cost rows and add to the collection
		for( BenDefnOptn op : optns ) {
			getMatchingCostRows( op );
		}
	}


	private static BenDefnOptn getRowObj( ResultSet dbRow ) throws SQLException {
		BenDefnOptn obj = new BenDefnOptn();
		obj.benefitProgram = dbRow.getString( "BENEFIT_PROGRAM" );
		obj.effdt = dbRow.getDate( "EFFDT" );
		obj.planType = dbRow.getString( "PLAN_TYPE" );
		obj.optionId = dbRow.getBigDecimal( "OPTION_ID" );
		obj.displayOptSeq = dbRow.getBigDecimal( "DISPLAY_OPT_SEQ" );
		obj.optionType = dbRow.getString( "OPTION_TYPE" );
		obj.benefitPlan = dbRow.getString( "BENEFIT_PLAN" );
		obj.covrgCd = dbRow.getString( "COVRG_CD" );
		obj.optionCd = dbRow.getString( "OPTION_CD" );
		obj.optionLvl = dbRow.getBigDecimal( "OPTION_LVL" );
		obj.dedcd = dbRow.getString( "DEDCD" );
		obj.dfltOptionInd = dbRow.getString( "DFLT_OPTION_IND" );
		obj.eligRulesId = dbRow.getString( "ELIG_RULES_ID" );
		obj.locationTblId = dbRow.getString( "LOCATION_TBL_ID" );
		obj.crossPlanType = dbRow.getString( "CROSS_PLAN_TYPE" );
		obj.crossBenefPlan = dbRow.getString( "CROSS_BENEF_PLAN" );
		obj.coverageLimitPct = dbRow.getBigDecimal( "COVERAGE_LIMIT_PCT" );
		obj.crossPlnDpndChk = dbRow.getString( "CROSS_PLN_DPND_CHK" );
		return obj;
	}


	/* main method for testing only */
	public static void main( String[] args ) {
		System.out.println( "BenDefnOptnDao.main()" );

		List<BenDefnOptn> optn = BenDefnOptnDao.getAllOptnRows( "001AAF", "2018-04-01" );
		System.out.println( "returned rows: " + optn.size() );

		// test comparator and print sorted result
		optn.sort( BenDefnOptn.PlanCovrgCdComparator );
		for( BenDefnOptn o : optn ) {
			System.out.println( o.benefitProgram + "<->" + o.effdt + "<->" + o.planType + "<->" + o.benefitPlan + "<->" + o.covrgCd );
		}

	}

}