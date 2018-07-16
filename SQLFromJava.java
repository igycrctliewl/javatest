// thanks http://everythingoracle.com/plsqlfrjv.htm

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLFromJava {

   public static void main( String[] args ) {

		long startTime = System.currentTimeMillis();
      System.out.println( "             Start: " + new java.util.Date() );

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrcab.trinet.com:1521:hrcab", "sysadm", "tfitd775" );

         String getRatesSQL =
					"SELECT MST.T2_BAND_CD BANDCODE " +
					"     , OPT.BENEFIT_PLAN BENEFITPLAN " +
					"     , OPT.COVRG_CD COVERAGECODE " +
					"     , OPT.PLAN_TYPE PLANTYPE " +
					"     , CASE " +
					"          WHEN OPT.COVRG_CD = '1' " +
					"          THEN " +
					"             MST.T2_COVRG_AMT_1 " +
					"          WHEN OPT.COVRG_CD = '2' " +
					"          THEN " +
					"             MST.T2_COVRG_AMT_2 " +
					"          WHEN OPT.COVRG_CD = '81' " +
					"          THEN " +
					"             MST.T2_COVRG_AMT_2 " +
					"          WHEN OPT.COVRG_CD = 'C' " +
					"          THEN " +
					"             MST.T2_COVRG_AMT_C " +
					"          WHEN OPT.COVRG_CD = '82' " +
					"          THEN " +
					"             MST.T2_COVRG_AMT_C " +
					"          WHEN OPT.COVRG_CD = '4' " +
					"          THEN " +
					"             MST.T2_COVRG_AMT_4 " +
					"          ELSE " +
					"             ( SELECT ROUND( AVG( RTD.BN_EMPLR_RATE ), 2 ) " +
					"                 FROM PS_BN_RATE_DATA RTD " +
					"                WHERE RTD.RATE_TBL_ID = MST.T2_COVRG1_FLAT_RT " +
					"                  AND RTD.EFFDT = (SELECT MAX( EFFDT ) " +
					"                                     FROM PS_BN_RATE_DATA R1 " +
					"                                    WHERE R1.RATE_TBL_ID = RTD.RATE_TBL_ID) ) " +
					"       END " +
					"          AS EMPLOYERCOST " +
					"     , PGM.EFFDT " +
					"  FROM PS_BEN_DEFN_PGM PGM " +
					"     , PS_BEN_DEFN_OPTN OPT " +
					"     , PS_BENEF_PLAN_TBL BP " +
					"     , PS_T2_MTR_RATE_TBL MST " +
					" WHERE PGM.BENEFIT_PROGRAM = '098' " +
					"   AND MST.T2_OE_QUARTER = 'Q4' " +
					"   AND PGM.EFFDT = (SELECT MAX( EFFDT ) " +
					"                      FROM PS_BEN_DEFN_PGM P1 " +
					"                     WHERE P1.BENEFIT_PROGRAM = PGM.BENEFIT_PROGRAM " +
					"                       AND P1.EFFDT <= DATE '2019-09-30') " +
					"   AND OPT.BENEFIT_PROGRAM = PGM.BENEFIT_PROGRAM " +
					"   AND OPT.EFFDT = PGM.EFFDT " +
					"   AND OPT.PLAN_TYPE IN ( '10', '11', '14', '1D', '1V', '21', '23', '25', '27', '2Y', '30', '31' ) " +
					"   AND OPT.OPTION_TYPE <> 'W' " +
					"   AND NOT ( OPT.ELIG_RULES_ID IN ('2009', '236Q', '23GC') ) " +
					"   AND BP.BENEFIT_PLAN = OPT.BENEFIT_PLAN " +
					"   AND BP.EFFDT = (SELECT MAX( EFFDT ) " +
					"                     FROM PS_BENEF_PLAN_TBL B1 " +
					"                    WHERE B1.PLAN_TYPE = BP.PLAN_TYPE " +
					"                      AND B1.BENEFIT_PLAN = BP.BENEFIT_PLAN) " +
					"   AND MST.PLAN_TYPE = OPT.PLAN_TYPE " +
					"   AND MST.BENEFIT_PLAN = OPT.BENEFIT_PLAN " +
					"   AND MST.EFFDT = (SELECT MAX( EFFDT ) " +
					"                      FROM PS_T2_MTR_RATE_TBL M1 " +
					"                     WHERE M1.PLAN_TYPE = MST.PLAN_TYPE " +
					"                       AND M1.BENEFIT_PLAN = MST.BENEFIT_PLAN " +
					"                       AND M1.T2_BAND_CD = MST.T2_BAND_CD " +
					"                       AND M1.T2_OE_QUARTER = MST.T2_OE_QUARTER " +
					"                       AND M1.EFFDT <= DATE '2019-09-30') " +
					"   AND MST.T2_BAND_CD IN ( '22', ' ' , '46', '2', '3', '19', '20', 'N' ) " ;


         // System.out.println( ">>>>>>\n" + getRatesSQL );


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( getRatesSQL );
			sqlStmt.setFetchSize( 10000 );

			{
				long now = System.currentTimeMillis();
				System.out.println( "Statement prepared: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
			}

         // Set values for parameters
/*			sqlStmt.setLong( 1, 2 );                                         // realmID
			sqlStmt.setDate( 2, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
			sqlStmt.setLong( 3, 2 );                                         // realmID
			sqlStmt.setDate( 4, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
			sqlStmt.setString( 5, "Q1" );                                    // OE quarter
*/
         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

			{
				long now = System.currentTimeMillis();
				System.out.println( "    Query executed: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
			}

			long rows = 0;
         while( qResult.next() ) {
				String bandCode = qResult.getString( "BANDCODE" );
				rows++;
/*				String benPlan = qResult.getString( "BENEFITPLAN" );
				String covrgCd = qResult.getString( "COVERAGECODE" );
				String planType = qResult.getString( "PLANTYPE" );
				BigDecimal erCost = qResult.getBigDecimal( "EMPLOYERCOST" );
				java.sql.Date effdt = qResult.getDate( "EFFDT" );

				System.out.println( "=====>" + bandCode + " <=> " + benPlan + " <=> " + covrgCd + " <=> " + planType + " <=> " + erCost + " <=> " + effdt );
*/			}

			{
				long now = System.currentTimeMillis();
				System.out.println( "  Output processed: " + new java.util.Date() + "  " + ( now - startTime ) + " ms    " + rows + " rows"  );
			}

         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

		{
			long now = System.currentTimeMillis();
			System.out.println( "               End: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}

   }

}
