
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OldBenefitProgram {

	private String benProg;
	private java.sql.Date effdt;

	public OldBenefitProgram( String benProg, String effdtStr ) {
		this.benProg = benProg;
		this.effdt = java.sql.Date.valueOf( effdtStr );
	}


	private static final Connection dbConnection = initConnection();
	private static Connection initConnection() {
		try {
			return DriverManager.getConnection( "jdbc:oracle:thin:@dbhrslm06.trinet.com:1521:hrslm06", "sysadm", "mhall510" );
		} catch( Exception e ) {
			return null;
		}
	}
	public static Connection getConnection() {
		return dbConnection;
	}


   public static void main( String[] args ) {

      System.out.println( new java.util.Date() );

		OldBenefitProgram obp = new OldBenefitProgram( "001AAF", "2018-04-01" );
		System.out.println( obp );


      try {

         // Initialize and connect
         DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
         Connection vDatabaseConnection = OldBenefitProgram.getConnection();

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