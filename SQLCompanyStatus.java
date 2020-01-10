// thanks http://everythingoracle.com/plsqlfrjv.htm

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLCompanyStatus {

   public static void main( String[] args ) {

		long startTime = System.currentTimeMillis();
      System.out.println( "             Start: " + new java.util.Date() );

      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhpcab.trinet.com:1521:hpcab", "hrdb", "tarpon775895" );

         String companyStatusSQL =
					"WITH COMPANY_STATUS_REALM_YEAR AS ( " +
					"SELECT  " +
					"  ID " +
					", COMPANY " +
					", CONFIRMATION_NUMBER " +
					", CREATE_TIME " +
					", EMAIL_SENT " +
					", ERROR_MSG " +
					", STATUS " +
					", STRATEGY_ID " +
					", USER_ID " +
					", REALM_YEAR_ID " +
					", SERVICE_ORDER         " +
					"FROM XBIS_SUBMIT_STATUS SS " +
					"WHERE ( SS.COMPANY, SS.REALM_YEAR_ID ) IN ( " +
					"    SELECT CODE, REALM_YEAR_ID " +
					"    FROM XBSS_COMPANY " +
					"    WHERE REALM_YEAR_ID = 25 ) " +
					") " +
					"SELECT * " +
					"  FROM COMPANY_STATUS_REALM_YEAR CSRY " +
					" WHERE CSRY.ID = ( " +
					"       SELECT MAX(ID) " +
					"         FROM COMPANY_STATUS_REALM_YEAR C1 " +
					"        WHERE C1.COMPANY = CSRY.COMPANY ) ";


         // System.out.println( ">>>>>>\n" + companyStatusSQL );


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( companyStatusSQL );
			sqlStmt.setFetchSize( 10000 );

			{
				long now = System.currentTimeMillis();
				System.out.println( "Statement prepared: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
			}

         // Set values for parameters
/*			sqlStmt.setLong( 1, 2 );                                         // realmID
			sqlStmt.setDate( 2, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
			sqlStmt.setLong( 3, 2 );                                         // realmID
			sqlStmt.setDate( 4, java.sql.Date.valueOf( "2017-01-01" ) );     // effdt
			sqlStmt.setString( 5, "Q1" );                                    // OE quarter
*/
         // run the query
         ResultSet qResult = sqlStmt.executeQuery();

			{
				long now = System.currentTimeMillis();
				System.out.println( "    Query executed: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
			}

			long rows = 0;
         while( qResult.next() ) {
				BigDecimal id = qResult.getBigDecimal( "ID" );
				String company = qResult.getString( "COMPANY" );
				String confirm = qResult.getString( "CONFIRMATION_NUMBER" );
				rows++;

				System.out.println( "=====>" + id + " <=> " + company + " <=> " + confirm );
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
