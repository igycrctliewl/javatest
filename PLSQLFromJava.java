// thanks http://everythingoracle.com/plsqlfrjv.htm

//import java.util.Calendar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class PLSQLFromJava {

   public static void main( String[] args ) {

      try {

         // INITIALIZE ORACLE DATABASE DRIVER
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         
         // CONNECT TO DATABASE ( "jdbc:oracle:thin:@<host>:<port>:<sid>", "<schema>", "<schema password>" )
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );
         
         // PREPARE PL/SQL FUNCTION CALL ("?" IS A PLACEHOLDER FOR RETURN VALUES AND PARAMETER VALUES, NUMBERED FROM LEFT TO RIGHT)
         CallableStatement vStatement = vDatabaseConnection.prepareCall( "begin ? := t2_bss.getNextBenProg(); end;" );

   
         // initialize variable #1
         vStatement.registerOutParameter( 1, Types.VARCHAR );                 // declare the type of the output parameter
         
         // CALL THE PL/SQL FUNCTION
         vStatement.execute();
         
         String newBenProg = vStatement.getString( 1 );
         System.out.println( "New benefit program:" + newBenProg );
         
         // CLOSE THE DATABASE CONNECTION
         vDatabaseConnection.close();
      
      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }
 
   }

}