
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ACAMirrorPlan {

	private Company company;
	private BenefitGroup group;
	private String effdt;
	private Map<String, MirrorPlanData> mirrorPlansMap = new HashMap<>();

	public ACAMirrorPlan( Company company, BenefitGroup group, String effdt  ) {
		this.company = company;
		this.group = group;
		this.effdt = effdt;
		this.getMirrorPlans();
	}

	public MirrorPlanData get( String planType, String benefitPlan ) {
		return mirrorPlansMap.get( planType + benefitPlan );
	}

	@Override
	public String toString() {
		return this.getClass() + ":[" + this.company.getCode() + "][" + this.group.getBenefitProgram() + "]";
	}


   private void getMirrorPlans() {

      System.out.println( new java.util.Date() );

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );

         String mirrorPlanSQL =
					"SELECT MIRT.PLAN_TYPE, MIRT.BENEFIT_PLAN, MIRT.T2_MIRR_BEN_PLAN, MIRT.COVRG_CD " +
					", MIRT.T2_CALCULATE_VALUE " +
					", MTR.T2_COVRG_AMT_1, MTR.T2_PROV_COVRG_AMT1 " +

					"  FROM PS_T2_ACA_MIRR_TBL MIRT " +
					"     , PS_T2_MTR_RATE_TBL MTR " +

					" WHERE ( MIRT.T2_OE_QUARTER, MIRT.T2_OPTION_TYPE, MIRT.EFFDT ) IN ( " +
					"       SELECT MIRF.T2_OE_QUARTER, 'B', MIRF.EFFDT " +
					"         FROM PS_T2_ACA_MIRR_EFF MIRF " +
					"        WHERE MIRF.T2_OE_QUARTER = ? " +
					"          AND MIRF.T2_OPTION_TYPE = 'B' " +
					"          AND MIRF.EFFDT = ( " +
					"              SELECT MAX(EFFDT) " +
					"                FROM PS_T2_ACA_MIRR_EFF MF1 " +
					"               WHERE MF1.T2_OE_QUARTER = MIRF.T2_OE_QUARTER " +
					"                 AND MF1.T2_OPTION_TYPE = MIRF.T2_OPTION_TYPE " +
					"                 AND MF1.EFFDT <= TO_DATE( ?, 'DD-MON-YYYY' ) ) " +
					"  ) " +
					"AND MTR.PLAN_TYPE = MIRT.PLAN_TYPE " +
					"AND MTR.BENEFIT_PLAN = MIRT.T2_MIRR_BEN_PLAN " +
					"AND MTR.T2_BAND_CD = getBandCode( MIRT.PLAN_TYPE, MIRT.BENEFIT_PLAN, TO_DATE( ?, 'DD-MON-YYYY' ), ?, ?, ? ) " +
					"AND MTR.T2_OE_QUARTER = ? " +
					"AND MTR.EFFDT = ( " +
					"    SELECT MAX(EFFDT) " +
					"      FROM PS_T2_MTR_RATE_TBL MT1 " +
					"     WHERE MT1.PLAN_TYPE = MTR.PLAN_TYPE " +
					"       AND MT1.BENEFIT_PLAN = MTR.BENEFIT_PLAN " +
					"       AND MT1.T2_BAND_CD = MTR.T2_BAND_CD " +
					"       AND MT1.T2_OE_QUARTER = MTR.T2_OE_QUARTER " +
					"       AND MT1.EFFDT <= TO_DATE( ?, 'DD-MON-YYYY' ) ) " ;

         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( mirrorPlanSQL );

			/* SQL parameters
			1: oeQuarter
			2: effdtStr
			3: effdtStr
			4:	company
			5: pfClient
			6: benefitProgram
			7: oeQuarter
			8: effdtStr
			*/

         // Set values for parameters
         sqlStmt.setString( 1, this.company.getQuater() );
         sqlStmt.setString( 2, this.effdt );
         sqlStmt.setString( 3, this.effdt );
         sqlStmt.setString( 4, this.company.getCode() );
         sqlStmt.setString( 5, this.company.getPfClient() );
         sqlStmt.setString( 6, this.group.getBenefitProgram() );
         sqlStmt.setString( 7, this.company.getQuater() );
         sqlStmt.setString( 8, this.effdt );

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

         while( qResult.next() ) {
       	   String planType = qResult.getString( "PLAN_TYPE" );
      	   String benPlan = qResult.getString( "BENEFIT_PLAN" );
     	      String mirrPlan = qResult.getString( "T2_MIRR_BEN_PLAN" );
            String covrgCd = qResult.getString( "COVRG_CD" );
		      BigDecimal eeCost = qResult.getBigDecimal( "T2_CALCULATE_VALUE" );
		      BigDecimal planCost = qResult.getBigDecimal( "T2_COVRG_AMT_1" );
		      BigDecimal providerRate = qResult.getBigDecimal( "T2_PROV_COVRG_AMT1" );

				MirrorPlanData mpd = new MirrorPlanData( planType, benPlan, mirrPlan
							, covrgCd, eeCost, planCost, providerRate );
				mirrorPlansMap.put( mpd.getMapKey(), mpd );

            System.out.println( "=====>" + planType + " <=> " + benPlan + " <=> " + mirrPlan + " <=> " + covrgCd + " <=> " + eeCost + " <=> " + planCost + " <=> " + providerRate );
         }

			System.out.println( "Mirror plans map contains " + mirrorPlansMap.size() + " keys." );

         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
			e.printStackTrace();
      }

      System.out.println( new java.util.Date() );

   }

}