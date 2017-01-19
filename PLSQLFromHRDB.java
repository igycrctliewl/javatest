// thanks http://everythingoracle.com/plsqlfrjv.htm

//import java.util.Calendar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class PLSQLFromHRDB {

   public static void main( String[] args ) {

      try {

         // INITIALIZE ORACLE DATABASE DRIVER
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );

         // CONNECT TO DATABASE ( "jdbc:oracle:thin:@<host>:<port>:<sid>", "<schema>", "<schema password>" )
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhplites.trinet.com:1749:hplites", "hrdb", "starfish510400" );

         // PREPARE PL/SQL FUNCTION CALL ("?" IS A PLACEHOLDER FOR RETURN VALUES AND PARAMETER VALUES, NUMBERED FROM LEFT TO RIGHT)
         CallableStatement vStatement = vDatabaseConnection.prepareCall( "begin createBenProg.createBenefitProgram( ?, ?, ?, ?, ? ); end;" );

         // initialize out variables
         //vStatement.registerOutParameter( 1, Types.VARCHAR );              // declare the type of the parameter
         //vStatement.registerOutParameter( 2, Types.VARCHAR );              // declare the type of the parameter
         //vStatement.registerOutParameter( 3, Types.NUMBER );               // declare the type of the parameter
         //vStatement.registerOutParameter( 4, Types.DATE );                 // declare the type of the parameter
         //vStatement.registerOutParameter( 4, Types.DATE );                 // declare the type of the parameter

         vStatement.setString( 1, "0018FL" );
         vStatement.setString( 2, "G48" );
         vStatement.setLong( 3, 2 );
         vStatement.setDate( 4, java.sql.Date.valueOf( "2017-01-01" ) );
         vStatement.setDate( 5, java.sql.Date.valueOf( "2017-01-17" ) );


         // CALL THE PL/SQL FUNCTION
         vStatement.execute();

         //String newBenProg = vStatement.getString( 1 );
         //System.out.println( "New benefit program:" + newBenProg );

         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

   }

}