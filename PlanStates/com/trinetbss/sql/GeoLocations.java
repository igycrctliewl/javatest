package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GeoLocations {

	public ResultSet queryResult;
	public GeoLocations() {
	}

   public void runQuery( String geoLoc, String effdtStr ) {

      try {

         // Initialize and connect
			PSConnect psconn = PSConnect.getInstance();
         Connection vDatabaseConnection = psconn.getConnection();

         String sqlString =
              "SELECT * " +
				  "  FROM PS_GEOG_LOCN_TBL LOC " +
				  " WHERE LOC.LOCATION_TBL_ID = ? " +
				  "   AND LOC.EFFDT = ( " +
				  "       SELECT MAX( EFFDT ) " +
				  "         FROM PS_GEOG_LOCN_TBL L1 " +
				  "        WHERE L1.LOCATION_TBL_ID = LOC.LOCATION_TBL_ID " +
				  "          AND L1.EFFDT <= TO_DATE( ?, 'DD-MON-YYYY' ) ) ";


         // Prepare statement from SQL string
         PreparedStatement sqlStmt = vDatabaseConnection.prepareStatement( sqlString );

         // Set values for parameters
         sqlStmt.setString( 1, geoLoc );
         sqlStmt.setString( 2, effdtStr );

         // run the query
         queryResult = sqlStmt.executeQuery();

      } catch( SQLException e ) {
         System.out.println( "GeoLocations.runQuery() SQL Exception" );
			e.printStackTrace();
      }

   }



	/* main method for testing */
	public static void main( String[] args ) {
		System.out.println( "GeoLocations.main()" );
		GeoLocations geo = new GeoLocations();
		geo.runQuery( "Q124", "01-JAN-2018" );

		System.out.println( "GeoLocations.main() => display results in caller" );

		try {
			while( geo.queryResult.next() ) {
				String geoLoc = geo.queryResult.getString( "LOCATION_TBL_ID" );
				Date effdt = geo.queryResult.getDate( "EFFDT" );
				String eligFlag = geo.queryResult.getString( "ELIG_FLG_GEO" );

				System.out.println( "GeoLocations.main() =>" + geoLoc + " <=> " + effdt + " <=> " + eligFlag + " <=" );
			}
		} catch( SQLException e ) {
         System.out.println( "GeoLocations.main() SQL Exception" );
			e.printStackTrace();
		}

		PSConnect.getInstance().close();
	}



}