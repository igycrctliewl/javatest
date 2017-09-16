import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeoLocations {
   public static void main( String[] args ) {

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );

         String sqlString =
              "WITH PARENT_TABLE " +
              "  AS ( SELECT * " +
              "         FROM PS_GEOG_LOCN_TBL LOC " +
              "        WHERE LOC.LOCATION_TBL_ID = 'Q124' " +
              "          AND LOC.EFFDT = (  " +
              "              SELECT MAX( EFFDT ) " +
              "                FROM PS_GEOG_LOCN_TBL L1 " +
              "               WHERE L1.LOCATION_TBL_ID = LOC.LOCATION_TBL_ID " +
              "                 AND L1.EFFDT <= DATE '2018-01-01' )) " +
              "SELECT LOCN_FROM AS POSTAL_CODE " +
              "  FROM PS_GEOG_LOCN_RANGE  " +
              " WHERE ( LOCATION_TBL_ID, EFFDT ) IN ( SELECT LOCATION_TBL_ID, EFFDT FROM PARENT_TABLE ) " +
              "   UNION " +
              "SELECT SUBSTR( LOCN_TO, 1, 5 ) " +
              "  FROM PS_GEOG_LOCN_RANGE  " +
              " WHERE ( LOCATION_TBL_ID, EFFDT ) IN ( SELECT LOCATION_TBL_ID, EFFDT FROM PARENT_TABLE )";


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( sqlString );

         // Set values for parameters
         //sqlStmt.setLong( 1, 2 );                                         // realmID
         //sqlStmt.setDate( 2, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         //sqlStmt.setLong( 3, 2 );                                         // realmID
         //sqlStmt.setDate( 4, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
         //sqlStmt.setString( 5, "Q1" );                                    // OE quarter

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

         while( qResult.next() ) {
       	   String postCode = qResult.getString( "POSTAL_CODE" );

            System.out.println( "=====>" + postCode + "<=" );
         }


         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

   }
}