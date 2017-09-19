import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlansByState {
   public static void main( String[] args ) {

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );

         String getPlansSQL =
              "SELECT DISTINCT PLAN_TYPE " +
              "              , BENEFIT_PLAN " +
              "              , ( SELECT BP.DESCR " +
              "                    FROM PS_BENEF_PLAN_TBL BP " +
              "                   WHERE BP.PLAN_TYPE = OP.PLAN_TYPE " +
              "                     AND BP.BENEFIT_PLAN = OP.BENEFIT_PLAN " +
              "                     AND BP.EFFDT = (SELECT MAX( EFFDT ) " +
              "                                       FROM PS_BENEF_PLAN_TBL B1 " +
              "                                      WHERE B1.PLAN_TYPE = BP.PLAN_TYPE " +
              "                                        AND B1.BENEFIT_PLAN = BP.BENEFIT_PLAN " +
              "                                        AND B1.EFFDT <= OP.EFFDT) ) " +
              "                   AS PLAN_NAME " +
              "              , LOCATION_TBL_ID " +
              "  FROM PS_BEN_DEFN_OPTN OP " +
              " WHERE BENEFIT_PROGRAM = '101' " +
              "   AND EFFDT = DATE '2018-01-01' " +
              "   AND PLAN_TYPE = '10' " +
              "   AND OPTION_TYPE = 'O' " +
              "   AND NOT ( ELIG_RULES_ID IN ('2009', '23GC', '236Q') ) " +
              "   AND NOT ( LOCATION_TBL_ID = ' ' )";

         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( getPlansSQL );

         // Set values for parameters
         //sqlStmt.setLong( 1, 2 );                                         // realmID
         //sqlStmt.setDate( 2, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         //sqlStmt.setLong( 3, 2 );                                         // realmID
         //sqlStmt.setDate( 4, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         //sqlStmt.setString( 5, "Q1" );                                    // OE quarter

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

         while( qResult.next() ) {
       	   String planType = qResult.getString( "PLAN_TYPE" );
      	   String benefitPlan = qResult.getString( "BENEFIT_PLAN" );
     	      String planName = qResult.getString( "PLAN_NAME" );
            String geoLoc = qResult.getString( "LOCATION_TBL_ID" );

            System.out.println( "=====>" + planType + " <=> " + benefitPlan + " <=> " + planName + " <=> " + geoLoc );
         }


         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

   }
}