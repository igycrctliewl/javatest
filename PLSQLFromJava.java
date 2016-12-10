// thanks http://everythingoracle.com/plsqlfrjv.htm

//import java.util.Calendar;
import java.sql.*;

public class PLSQLFromJava {

   public static void main( String[] args ) {

      try {

         // INITIALIZE ORACLE DATABASE DRIVER
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         
         // CONNECT TO DATABASE ( "jdbc:oracle:thin:@<host>:<port>:<sid>", "<schema>", "<schema password>" )
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );
         
         // PREPARE PL/SQL FUNCTION CALL ("?" IS A PLACEHOLDER FOR RETURN VALUES AND PARAMETER VALUES, NUMBERED FROM LEFT TO RIGHT)
         CallableStatement vStatement = vDatabaseConnection.prepareCall( "begin ? := t2_bss.getNextBenProg(); end;" );
         // USE EITHER THE "BLOCK" SYNTAX ABOVE, OR "ANSI 92" SYNTAX BELOW
         //CallableStatement vStatement = vDatabaseConnection.prepareCall( "{ ? = call javatest( ?, ? ) }" );
         
         // SET INPUT PARAMETERS AND DECLARE TYPES FOR RETURN VALUES
         //  not needed for this statement   ----vStatement.registerOutParameter( 1, Types.VARCHAR );   // declare type of function return value "begin ? ..."
         //vStatement.setString( 1, "0016NX" );                  // set value of first function parameter "... javatest( ?, ..."
         //vStatement.setDate( 2, java.sql.Date.valueOf( "2016-12-01" ) );                  // set value of second function parameter 
   
         // initialize variable #1
         vStatement.setString( 1, "      " );                  // set value of first function parameter "... javatest( ?, ..."
         
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