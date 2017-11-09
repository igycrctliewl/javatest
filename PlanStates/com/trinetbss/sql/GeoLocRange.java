package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GeoLocRange {

	public ResultSet queryResult;
	public GeoLocRange() {
	}

   public void runQuery( String geoLoc, Date effdt ) {

      try {

         // Initialize and connect
			PSConnect psconn = PSConnect.getInstance();
         Connection vDatabaseConnection = psconn.getConnection();

         String sqlString =
				  "SELECT LOCN_FROM, LOCN_TO " +
				  "  FROM PS_GEOG_LOCN_RANGE LOC " +
				  " WHERE LOC.LOCATION_TBL_ID = ? " +
				  "   AND LOC.EFFDT = ? ";

         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( sqlString );

         // Set values for parameters
         sqlStmt.setString( 1, geoLoc );
         sqlStmt.setDate( 2, effdt );

         // run the query
         queryResult = sqlStmt.executeQuery();

      } catch( SQLException e ) {
         System.out.println( e.toString() );
      }

   }



	/* main method for testing */
   private static void main( String[] args ) {
		System.out.println( "GeoLocRange.main()" );
		GeoLocRange geo = new GeoLocRange();
		//Date effdt = new Date( 117,0,1 ); //deprecated constructor
		Date effdt = new Date( 1483257600000L ); //cludge 2017-01-01
		System.out.println( "GeoLocRange.main() => Date:" + effdt.getTime() );
		System.out.println( "GeoLocRange.main() => Date:" + effdt.toString() );
		geo.runQuery( "Q124", effdt );

		System.out.println( "GeoLocRange.main() => display results in caller" );

		try {
			while( geo.queryResult.next() ) {
				String fromZip = geo.queryResult.getString( "LOCN_FROM" );
				String toZip = geo.queryResult.getString( "LOCN_TO" );

				System.out.println( "GeoLocRange.main() =>" + fromZip + " <=> " + toZip + " <=" );
			}
		} catch( SQLException e ) {
			System.out.println( "GeoLocRange.main() SQL Exception" );
			e.printStackTrace();
		}

		PSConnect.getInstance().close();
	}
}