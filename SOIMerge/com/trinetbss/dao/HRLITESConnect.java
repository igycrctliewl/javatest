package com.trinetbss.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class HRLITESConnect extends PSConnect {

	HRLITESConnect() {
		super();
		System.out.println( "HRLITESConnect constructor" );
		try {
			// Initialize and connect
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			super.vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrlites.trinet.com:1521:hrlites", "sysadm", "mhall510" );
			System.out.println( "HRLITESConnect connected" );
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}

}