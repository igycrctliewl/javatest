package com.trinetbss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PSConnect {

	//private static final PSConnect conn = new HRPRODConnect();
	private static final PSConnect conn = new HRLITESConnect();

	Connection vDatabaseConnection;

	public PSConnect() {
		
	}

	public Connection getConnection() {
		return this.vDatabaseConnection;
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
}