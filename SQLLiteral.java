import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Array;
import java.util.List;

public class SQLLiteral {

   public static void main( String[] args ) {

      System.out.println( new java.util.Date() );

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );

         String createSQL = "SELECT COLUMN_VALUE AS COMPANY FROM TABLE(SYS.ODCIVARCHAR2LIST( 'A', 'B', 'C' ))";

         System.out.println( ">>>>>>\n" + createSQL );

         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( createSQL );

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();


         /* this is friggin evil!  it won't let me create an array, it won't let me get an array
            it's like this damn thing doesn't even accept the concept of arrays!  Come on man!!!  */

         qResult.next();
         Array companies = qResult.getArray( "COMPANY" );
         System.out.println( "companies is class " + companies.getClass() );


         while( qResult.next() ) {
       	   String descr = qResult.getString( "COMPANY" );

            System.out.println( "=====>" +  " <=> " + descr );
         }



         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         e.printStackTrace();
      }

      System.out.println( new java.util.Date() );

   }

}