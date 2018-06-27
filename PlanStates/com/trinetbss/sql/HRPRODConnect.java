package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HRPRODConnect extends PSConnect {

	HRPRODConnect() {
		super();
		System.out.println( "HRPRODConnect constructor" );
		try {
			// Initialize and connect
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			String userid = com.trinetbss.main.Password.getInput( "HRPROD userid: " );
			String pswd = com.trinetbss.main.Password.getPassword( "HRPROD password: " );
			super.vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@oxdprod1-scan:1521/hrprodsv", userid, pswd );
			System.out.println( "HRPRODConnect connected" );
		} catch( SQLException e ) {
			System.out.println( "HRPRODConnect SQL exception" );
			e.printStackTrace();
		} catch( Exception e ) {
			System.out.println( "HRPRODConnect generic exception" );
			e.printStackTrace();
		}
	}

}