package com.trinetbss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PSConnect {

	// Select the desired environment by un-commenting ONLY ONE of the following lines:
	//private static final PSConnect conn = new HRPRODConnect();
	private static final PSConnect conn = new HRSLM06Connect();

	Connection vDatabaseConnection;

	public PSConnect() {
		
	}

	public Connection getConnection() {
		return this.vDatabaseConnection;
	}


	public void testConnection() {
		try {
			String sqlString = "SELECT NAME FROM V$DATABASE";
			PreparedStatement sqlStmt = this.vDatabaseConnection.prepareStatement( sqlString );
			ResultSet rs = sqlStmt.executeQuery();
			System.out.println( "Database connection complete" );
		} catch( SQLException e ) {
			System.out.println( "Database connection failed" );
		}
	}


	public void close() {
		System.out.println( "PSConnect.close()" );
		try {
			this.vDatabaseConnection.close();
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}

	public static PSConnect getInstance() {
		return PSConnect.conn;
	}


	public static void main( String[] args ) {
		PSConnect psconn = PSConnect.getInstance();
		psconn.testConnection();

	}

}