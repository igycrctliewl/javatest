package com.trinetbss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HRSLM06Connect extends PSConnect {

	HRSLM06Connect() {
		super();
		System.out.println( "HRSLM06Connect constructor" );
		try {
			// Initialize and connect
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			super.vDatabaseConnection = DriverManager.getConnection( "jdbc:oracle:thin:@dbhrslm06.trinet.com:1521:hrslm06", "sysadm", "mhall510" );
			System.out.println( "HRSLM06Connect connected" );
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}

}