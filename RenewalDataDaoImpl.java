import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenewalDataDaoImpl {

	public static Map<String,List<BenefitPlanHeadCount>> getPlanHeadCountByGroups() throws SQLException {

		// Set values for parameters
		String effdt = "30-SEP-2018";
		String company = "G48";
		Long realmId = 10L;

		// Initialize and connect
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
		Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1521:hrlites", "sysadm", "mhall510" );

		String enrollmentsSQL = 
					"SELECT HB.EMPLID " +
					"     , HB.EMPL_RCD " +
					"     , HB.EFFDT " +
					"     , HB.PLAN_TYPE " +
					"     , HB.BENEFIT_PLAN " +
					"     , HB.COVERAGE_ELECT " +
					"     , CASE HB.COVRG_CD " +
					"          WHEN '1'  THEN  " +
					"             CASE HBD.COVRG_CD " +
					"                WHEN '5'  THEN '2' " +
					"                WHEN '6'  THEN 'C' " +
					"                WHEN '7'  THEN '4' " +
					"                WHEN '8'  THEN '4' " +
					"                ELSE HB.COVRG_CD " +
					"             END " +
					"          WHEN '2'  THEN " +
					"             CASE HBD.COVRG_CD " +
					"                WHEN '6'  THEN '4' " +
					"                ELSE HB.COVRG_CD " +
             	"             END " +
					"          WHEN 'C'  THEN " +
					"             CASE HBD.COVRG_CD " +
					"                WHEN '5'  THEN '4' " +
					"                WHEN '6'  THEN 'C' " +
					"                WHEN '7'  THEN '4' " +
					"                WHEN '8'  THEN '4' " +
					"                ELSE HB.COVRG_CD " +
					"             END           " +
					"          WHEN '4'  THEN '4' " +
					"          ELSE HB.COVRG_CD " +
					"       END AS COVRG_CD " +
					"     , HBD.EFFDT           AS HBD_EFFDT          " +
					"     , HBD.PLAN_TYPE       AS HBD_PLAN_TYPE      " +
					"     , HBD.BENEFIT_PLAN    AS HBD_BENEFIT_PLAN   " +
					"     , HBD.COVERAGE_ELECT  AS HBD_COVERAGE_ELECT " +
					"     , HBD.COVRG_CD        AS HBD_COVRG_CD       " +
					"  FROM PS_JOB J " +
					"     , PS_HEALTH_BENEFIT HB " +
					"       LEFT OUTER JOIN PS_HEALTH_BENEFIT HBD " +
					"               ON HBD.EMPLID = HB.EMPLID " +
					"              AND HBD.EMPL_RCD = HB.EMPL_RCD " +
					"              AND HBD.PLAN_TYPE = ( " +
					"                  CASE HB.PLAN_TYPE " +
					"                     WHEN '10'  THEN '15' " +
					"                     WHEN '11'  THEN '16' " +
					"                     WHEN '14'  THEN '17' " +
					"                     WHEN '1D'  THEN '1E' " +
					"                     WHEN '1V'  THEN '1U' " +
					"                  END ) " +
					"              AND HBD.EFFDT = ( " +
					"                     SELECT MAX(EFFDT) " +
					"                       FROM PS_HEALTH_BENEFIT H2 " +
					"                      WHERE H2.EMPLID = HBD.EMPLID " +
					"                        AND H2.EMPL_RCD = HBD.EMPL_RCD " +
					"                        AND H2.COBRA_EVENT_ID = HBD.COBRA_EVENT_ID " +
					"                        AND H2.PLAN_TYPE = HBD.PLAN_TYPE " +
					"                        AND H2.BENEFIT_NBR = HBD.BENEFIT_NBR " +
					"                        AND H2.EFFDT <= ? ) " +
					"              AND HBD.COVERAGE_ELECT = HB.COVERAGE_ELECT " +
					" WHERE J.COMPANY = ? " +
					"   AND J.EFFDT = ( " +
					"       SELECT MAX( EFFDT ) " +
					"         FROM PS_JOB J1 " +
					"        WHERE J1.EMPLID = J.EMPLID " +
					"          AND J1.EMPL_RCD = J.EMPL_RCD " +
					"          AND J1.EFFDT <= ? ) " +
					"   AND J.EFFSEQ = ( " +
					"       SELECT MAX( EFFSEQ ) " +
					"         FROM PS_JOB J2 " +
					"        WHERE J2.EMPLID = J.EMPLID " +
					"          AND J2.EMPL_RCD = J.EMPL_RCD " +
					"          AND J2.EFFDT = J.EFFDT ) " +
					"   AND HB.EMPLID = J.EMPLID " +
					"   AND HB.EMPL_RCD = J.EMPL_RCD " +
					"   AND HB.EFFDT = ( " +
					"       SELECT MAX( EFFDT ) " +
					"         FROM PS_HEALTH_BENEFIT H1 " +
					"        WHERE H1.EMPLID = HB.EMPLID " +
					"          AND H1.EMPL_RCD = HB.EMPL_RCD " +
					"          AND H1.COBRA_EVENT_ID = HB.COBRA_EVENT_ID " +
					"          AND H1.PLAN_TYPE = HB.PLAN_TYPE " +
					"          AND H1.BENEFIT_NBR = HB.BENEFIT_NBR " +
					"          AND H1.EFFDT <= ? ) " +
					"   AND HB.COVERAGE_ELECT = 'E' " +
					"   AND HB.PLAN_TYPE IN ('10','11','14','1D','1V') ";


		/* Get benefit program from PeopleSoft (in case any employee data is missing in BSS
		 * param 1: company
		 * param 2: effdt
		 * param 3: also effdt
		 */
		String psEmployeeSQL = 
					"SELECT BPP.EMPLID " +
					"     , BPP.EMPL_RCD " +
					"     , BPP.BENEFIT_PROGRAM " +
					"  FROM PS_BEN_PROG_PARTIC BPP " +
					"     , PS_JOB J " +
					" WHERE J.COMPANY = ? " +
					"   AND J.EFFDT = ( " +
					"       SELECT MAX(EFFDT) " +
					"         FROM PS_JOB J1 " +
					"        WHERE J1.EMPLID = J.EMPLID " +
					"          AND J1.EMPL_RCD = J.EMPL_RCD " +
					"          AND J1.EFFDT <= ? ) " +
					"   AND J.EFFSEQ = ( " +
					"       SELECT MAX(EFFSEQ) " +
					"         FROM PS_JOB J2 " +
					"        WHERE J2.EMPLID = J.EMPLID " +
					"          AND J2.EMPL_RCD = J.EMPL_RCD " +
					"          AND J2.EFFDT = J.EFFDT ) " +
					"   AND BPP.EMPLID = J.EMPLID " +
					"   AND BPP.EMPL_RCD = J.EMPL_RCD " +
					"   AND BPP.EFFDT = ( " +
					"       SELECT MAX(EFFDT) " +
					"         FROM PS_BEN_PROG_PARTIC B1 " +
					"        WHERE B1.EMPLID = BPP.EMPLID " +
					"          AND B1.EMPL_RCD = BPP.EMPL_RCD " +
					"          AND B1.COBRA_EVENT_ID = BPP.COBRA_EVENT_ID " +
					"          AND B1.EFFDT <= ? ) ";


		String bssEmployeeSQL = 
					"SELECT XE.EMPLID " +
					"     , XE.EMPL_RCD " +
					"     , XE.COMPANY " +
					"     , COALESCE( XE.UPDATED_BENEFIT_PROGRAM, XE.BENEFIT_PROGRAM ) AS BENEFIT_PROGRAM " +
					"  FROM XBSS_EMPLOYEE@HPDB_HRDB XE " +
					" WHERE XE.COMPANY = ? " +
					"   AND XE.REALM_YEAR_ID = ? ";


		// Map to get the current benefit program for an employee/empl_rcd
		Map<String, Map<Long,String>> emplBenProgMap = new HashMap<String, Map<Long,String>>(); 


		// Prepare and execute PS employee query
		//System.out.println( "PS employee query...." );
		PreparedStatement psEmployeeStmt = vDatabaseConnection.prepareStatement( psEmployeeSQL );
		psEmployeeStmt.setFetchSize( 10000 );
		psEmployeeStmt.setString( 1, company );
		psEmployeeStmt.setString( 2, effdt );
		psEmployeeStmt.setString( 3, effdt );
		ResultSet psEmployeeResult = psEmployeeStmt.executeQuery();

		// load results into map
		while( psEmployeeResult.next() ) {
			String emplid = psEmployeeResult.getString( "EMPLID" );
			Long emplRcd = psEmployeeResult.getLong( "EMPL_RCD" );
			String benefitProgram = psEmployeeResult.getString( "BENEFIT_PROGRAM" );
			//System.out.println( emplid + ":" + emplRcd + ":" + benefitProgram );

			Map<Long,String> innerMap = emplBenProgMap.get( emplid );
			if( innerMap == null ) {
				innerMap = new HashMap<Long,String>();
				emplBenProgMap.put( emplid, innerMap );
			}
			innerMap.put( emplRcd, benefitProgram );
		}


		// Prepare and execute BSS employee query
		//System.out.println( "XBSS employee query...." );
		PreparedStatement employeeStmt = vDatabaseConnection.prepareStatement( bssEmployeeSQL );
		employeeStmt.setFetchSize( 10000 );
		employeeStmt.setString( 1, company );
		employeeStmt.setLong( 2, realmId );
		ResultSet employeeResult = employeeStmt.executeQuery();

		while( employeeResult.next() ) {
			String emplid = employeeResult.getString( "EMPLID" );
			Long emplRcd = employeeResult.getLong( "EMPL_RCD" );
			String benefitProgram = employeeResult.getString( "BENEFIT_PROGRAM" );
			//System.out.println( emplid + ":" + emplRcd + ":" + benefitProgram );

			Map<Long,String> innerMap = emplBenProgMap.get( emplid );
			if( innerMap == null ) {
				innerMap = new HashMap<Long,String>();
				emplBenProgMap.put( emplid, innerMap );
			}
			innerMap.put( emplRcd, benefitProgram );
		}
	
		//System.out.println( "Map contains " + emplBenProgMap.size() );
	
		System.out.println( "---------------------" );

		// Prepare and execute group/enrollment counts query
		PreparedStatement enrollmentsStmt = vDatabaseConnection.prepareStatement( enrollmentsSQL );
		enrollmentsStmt.setFetchSize( 10000 );
		enrollmentsStmt.setString( 1, effdt );
		enrollmentsStmt.setString( 2, company );
		enrollmentsStmt.setString( 3, effdt );
		enrollmentsStmt.setString( 4, effdt );
		ResultSet qResult = enrollmentsStmt.executeQuery();


		/* superMap provides a path from benefitProgram -> benefitPlan -> coverageCd -> headcount */
		Map<String,Map<String,Map<String,Integer>>> superMap = new HashMap<String,Map<String,Map<String,Integer>>>();

		/* benPlanMap provides the plan type for each benefit plan */
		Map<String,String> benPlanMap = new HashMap<String,String>();

		while( qResult.next() ) {
			// obtain result from query and save in temporary variables
			String emplid = qResult.getString( "EMPLID" );
			Long emplRcd = qResult.getLong( "EMPL_RCD" );

			String benefitProgram;
			Map<Long,String> item = emplBenProgMap.get( emplid );
			if( item == null ) {
				benefitProgram = "";
			} else {
				benefitProgram = emplBenProgMap.get( emplid ).get( emplRcd );
			}

			Date enrollmentEffdt = qResult.getDate( "EFFDT" );
			String planType = qResult.getString( "PLAN_TYPE" );
			String benefitPlan = qResult.getString( "BENEFIT_PLAN" );
			String coverageElect = qResult.getString( "COVERAGE_ELECT" );
			String covrgCd = qResult.getString( "COVRG_CD" );
			Date dpEffdt = qResult.getDate( "HBD_EFFDT" );
			String dpPlanType = qResult.getString( "HBD_PLAN_TYPE" );
			String dpBenefitPlan = qResult.getString( "HBD_BENEFIT_PLAN" );
			String dpCoverageElect = qResult.getString( "HBD_COVERAGE_ELECT" );
			String dpCovrgCd = qResult.getString( "HBD_COVRG_CD" );

			System.out.println( emplid + ":" + emplRcd + ":" + benefitProgram + ":" + enrollmentEffdt + ":" + planType + ":" + benefitPlan + ":" + coverageElect + ":" + covrgCd + ":" + dpEffdt + ":" + dpPlanType + ":" + dpBenefitPlan + ":" + dpCoverageElect + ":" + dpCovrgCd );

			// Save the benefit plan and plan type relationship (this will be needed when I build the final result object
			benPlanMap.put( benefitPlan, planType );

			// Update superMap with the count represented by this result row
			Map<String,Map<String,Integer>> benPlanCountMap = superMap.get( benefitProgram );
			if( benPlanCountMap == null ) {
				benPlanCountMap = new HashMap<String,Map<String,Integer>>();
				superMap.put( benefitProgram, benPlanCountMap );
			}

			Map<String,Integer> covrgCdMap = benPlanCountMap.get( benefitPlan );
			if( covrgCdMap == null ) {
				covrgCdMap = new HashMap<String,Integer>();
				benPlanCountMap.put( benefitPlan, covrgCdMap );
			}

			Integer headCount = covrgCdMap.get( covrgCd );
			if( headCount == null ) {
				headCount = Integer.valueOf( 0 );
			}
			covrgCdMap.put( covrgCd, Integer.valueOf( headCount.intValue() + 1 ));
		}

		// CLOSE THE DATABASE CONNECTION
		vDatabaseConnection.close();

/*		System.out.println( "List benPlanMap entries......" );
		for( Map.Entry me : benPlanMap.entrySet() ) {
			System.out.println( me.getKey() + ":" + me.getValue() ) ;
		}
*/

		System.out.println( "\nList superMap entries......" );
		for( Map.Entry<String,Map<String,Map<String,Integer>>> superEntry : superMap.entrySet() ) {
			System.out.println( "bpg:" + superEntry.getKey() );
			for( Map.Entry<String,Map<String,Integer>> planEntry : superEntry.getValue().entrySet() ) {
				System.out.println( "    plan:" + planEntry.getKey() );
				for( Map.Entry<String,Integer> covrgEntry : planEntry.getValue().entrySet() ) {
					System.out.println( "        covrgCd:" + covrgEntry.getKey() + "  count:" + covrgEntry.getValue() );
				}
			}
		}


		// move mapped data to output object (this will be returned by the application method)
		Map<String,List<BenefitPlanHeadCount>> bmap = new HashMap<String,List<BenefitPlanHeadCount>>();
		for( Map.Entry<String,Map<String,Map<String,Integer>>> superEntry : superMap.entrySet() ) {
			bmap.put( superEntry.getKey(), new ArrayList<BenefitPlanHeadCount>() );
			for( Map.Entry<String,Map<String,Integer>> planEntry : superEntry.getValue().entrySet() ) {
				BenefitPlanHeadCount bphc = new BenefitPlanHeadCount();
				bphc.setBenefitPlan( planEntry.getKey() );
				bphc.setPlanType( benPlanMap.get( planEntry.getKey() ));
				bphc.setCoverageLevelHeadCount( new ArrayList<CoverageLevelHeadCount>() );
				bmap.get( superEntry.getKey() ).add( bphc );
				for( Map.Entry<String,Integer> covrgEntry : planEntry.getValue().entrySet() ) {
					CoverageLevelHeadCount clhc = new CoverageLevelHeadCount();
					clhc.setBenefitProgram( superEntry.getKey() );
					clhc.setCoverageLevel( covrgEntry.getKey() );
					clhc.setHeadCount( covrgEntry.getValue() );
					bphc.getCoverageLevelHeadCount().add( clhc );
				}
			}
		}


		return bmap;
	}



	static class CoverageLevelHeadCount {
		private String coverageLevel;
		private int headCount;
		private String benefitProgram;
		
		public String getCoverageLevel() {
			return coverageLevel;
		}
		public void setCoverageLevel(String coverageLevel) {
			this.coverageLevel = coverageLevel;
		}
		public int getHeadCount() {
			return headCount;
		}
		public void setHeadCount(int headCount) {
			this.headCount = headCount;
		}
		public String getBenefitProgram() {
			return benefitProgram;
		}
		public void setBenefitProgram(String benefitProgram) {
			this.benefitProgram = benefitProgram;
		}
	}


	static class BenefitPlanHeadCount {
		private String benefitPlan;
		private String planType;
		private List<CoverageLevelHeadCount> coverageLevelHeadCount;

		public String getBenefitPlan() {
			return benefitPlan;
		}

		public void setBenefitPlan(String benefitPlan) {
			this.benefitPlan = benefitPlan;
		}

		public List<CoverageLevelHeadCount> getCoverageLevelHeadCount() {
			return coverageLevelHeadCount;
		}

		public void setCoverageLevelHeadCount(List<CoverageLevelHeadCount> coverageLevelHeadCount) {
			this.coverageLevelHeadCount = coverageLevelHeadCount;
		}

		public String getPlanType() {
			return planType;
		}

		public void setPlanType(String planType) {
			this.planType = planType;
		}
	}


	public static void main( String[] args ) {

		try {
			Map<String,List<BenefitPlanHeadCount>> hcMap = RenewalDataDaoImpl.getPlanHeadCountByGroups();
			System.out.println( "\nPrinting result of method call...." );
			for( Map.Entry<String,List<BenefitPlanHeadCount>> entry : hcMap.entrySet() ) {
				System.out.println( entry.getKey() );
				for( BenefitPlanHeadCount phc : entry.getValue() ) {
					System.out.println( "   " + phc.getBenefitPlan() + ":" + phc.getPlanType() );
					for( CoverageLevelHeadCount clhc : phc.getCoverageLevelHeadCount() ) {
						System.out.println( "      " + clhc.getBenefitProgram() + ":" + clhc.getCoverageLevel() + ":" + clhc.getHeadCount() );
					}
				}
			}
		} catch( SQLException e ) {
			System.out.println( e.toString() );
		}
	}

}
