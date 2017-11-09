package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HRLITESConnect extends PSConnect {

	HRLITESConnect() {
		super();
		System.out.println( "HRLITESConnect constructor" );
		try {
			// Initialize and connect
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			super.vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1725:hrlites", "sysadm", "mhall510" );
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}

}