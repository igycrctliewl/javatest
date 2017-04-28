import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestEligMap {

   public static void main( String[] args ) {

      System.out.println( new java.util.Date() );
      Map<String, String> eligPlanMap = getEligPlanMap( "SY" );

      String waitPeriod = "F30D";
      String planType = "10";
      String benefitPlan = "002AXU";

      String newElig = eligPlanMap.get( waitPeriod + planType + benefitPlan );
      if( newElig == null ) {
         newElig = eligPlanMap.get( waitPeriod + planType );
      }

      System.out.println( "Found elig rule " + newElig );

      System.out.println( new java.util.Date() );

   }


   public static Map<String, String> getEligPlanMap( String oeQuarter ) {

      Map<String, String> waitPlanEligMap = new HashMap<String, String>();

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );

         String getCompsSQL =
                " SELECT T2_NEW_WAIT_PERIOD, PLAN_TYPE, BENEFIT_PLAN, T2_NEW_ELIG_RULEID " +
                "   FROM PS_T2_WAITPER_ELIG " +
                "  WHERE T2_OE_QUARTER = ? " +
                "    AND EFF_STATUS = 'A' " +
                " ORDER BY 1,2,3 ";



         System.out.println( ">>>>>>\n" + getCompsSQL );


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( getCompsSQL );

         // Set values for parameters
         sqlStmt.setString( 1, oeQuarter );    // OE quarter

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

         while( qResult.next() ) {
       	   String waitPer = qResult.getString( "T2_NEW_WAIT_PERIOD" );
       	   String planType = qResult.getString( "PLAN_TYPE" );
       	   String benefitPlan = qResult.getString( "BENEFIT_PLAN" );
       	   String eligRule = qResult.getString( "T2_NEW_ELIG_RULEID" );

            if( " ".equals( benefitPlan ) ) {
               waitPlanEligMap.put( waitPer + planType, eligRule );
            } else {
               waitPlanEligMap.put( waitPer + planType + benefitPlan, eligRule );
            }

            System.out.println( "=====>" + waitPer + " <=> " + eligRule );
         }

         System.out.println( " " );
         System.out.println( "Plan map contains " + waitPlanEligMap.size() + " entries" );


         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

      return waitPlanEligMap;
   }

}


/*
      List<String> list = new ArrayList<String>();

      list.add( "23" );
      list.add( "30" );
      list.add( "31" );
      list.add( "A3" );

      for( String planType : list ) {
         System.out.println( "===>  " + planType );
      }

   }
}
*/