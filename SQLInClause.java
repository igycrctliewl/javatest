import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Array;
import java.util.List;

public class SQLInClause {

   public static void main( String[] args ) {

      System.out.println( new java.util.Date() );

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );

         String getCompsSQL =
                " SELECT EFFDT, DESCR " +
                "   FROM PS_COMPANY_TBL " +
                "  WHERE COMPANY IN ( ? ) ";


         System.out.println( ">>>>>>\n" + getCompsSQL );


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( getCompsSQL );

         String[] list =  new String[3];
         list[0] = "G9P";
         list[1] = "OUG";
         list[2] = "G48";

         Array companies = vDatabaseConnection.createArrayOf("VARCHAR2",list);

         // Set values for parameters
         sqlStmt.setArray( 1, companies );    // list of COMPANY

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

         while( qResult.next() ) {
            java.sql.Date effdt = qResult.getDate( "EFFDT" );
       	   String descr = qResult.getString( "DESCR" );

            System.out.println( "=====>" + effdt + " <=> " + descr );
         }



         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

      System.out.println( new java.util.Date() );

   }

}