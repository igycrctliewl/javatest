
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OldBenefitProgram {

   public static void main( String[] args ) {

      System.out.println( new java.util.Date() );

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrslm06.trinet.com:1521:hrslm06", "sysadm", "mhall510" );

         String getRatesSQL =
                "SELECT NAME " +
                "  FROM V$DATABASE ";

         System.out.println( ">>>>>>\n" + getRatesSQL );


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( getRatesSQL );

         // Set values for parameters
         // sqlStmt.setLong( 1, 2 );                                         // realmID
         // sqlStmt.setDate( 2, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         // sqlStmt.setLong( 3, 2 );                                         // realmID
         // sqlStmt.setDate( 4, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         // sqlStmt.setString( 5, "Q1" );                                    // OE quarter

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

         while( qResult.next() ) {
       	   String dbName = qResult.getString( "NAME" );
            System.out.println( "=====>" + dbName + " <=> " );
         }


         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

      System.out.println( new java.util.Date() );

   }

}