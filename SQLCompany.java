// thanks http://everythingoracle.com/plsqlfrjv.htm

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLCompany {

   public static void main( String[] args ) {

		long startTime = System.currentTimeMillis();
      System.out.println( "             Start: " + new java.util.Date() );

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrcab.trinet.com:1521:hrcab", "sysadm", "tfitd775" );

         String sqlString = "SELECT COMPANY FROM PS_T2_CLIENT_OPTN2 WHERE COMPANY = ? ";


         // System.out.println( ">>>>>>\n" + sqlString );


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( sqlString );
			sqlStmt.setFetchSize( 10000 );

			{
				long now = System.currentTimeMillis();
				System.out.println( "Statement prepared: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
			}

         // Set values for parameters
			sqlStmt.setString( 1, "G9P" );

         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

			{
				long now = System.currentTimeMillis();
				System.out.println( "    Query executed: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
			}

			long rows = 0;
         while( qResult.next() ) {
				String s = qResult.getString( "COMPANY" );
				rows++;

				System.out.println( "=====>" + s + " - " + s.hashCode() );
			}

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
