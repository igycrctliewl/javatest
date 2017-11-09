package com.trinetbss.main;

import java.io.Console;

public class Password {

	/**
	 * Uses a standard prompt to get input from console
	 */
	public static String getInput() throws Exception {
		Console console = System.console();
		if (console == null) {
			throw new Exception( "Couldn't get Console instance" );
		}
		return console.readLine( "Enter your user name: " );
	}


	/**
	 * Uses a user-supplied prompt to get input from console
	 */
	public static String getInput( String prompt ) throws Exception {
		Console console = System.console();
		if (console == null) {
			throw new Exception( "Couldn't get Console instance" );
		}
		return console.readLine( prompt );
	}


	/**
	 * Uses a standard prompt to get a password without echoing input
	 */
	public static String getPassword() throws Exception {
		Console console = System.console();
		if (console == null) {
			throw new Exception( "Couldn't get Console instance" );
		}
		char passwordArray[] = console.readPassword( "Enter your password: " );
		return new String( passwordArray );
	}


	/**
	 * Uses a user-supplied prompt to get a password without echoing input
	 */
	public static String getPassword( String prompt ) throws Exception {
		Console console = System.console();
		if (console == null) {
			throw new Exception( "Couldn't get Console instance" );
		}
		char passwordArray[] = console.readPassword( prompt );
		return new String( passwordArray );
	}


	public static void main(String[] args) throws Exception {
		System.out.println( "Userid is " + Password.getInput( "Enter your userid for HRLITES: " ) );
		System.out.println( "Password is " + Password.getPassword( "Enter your password for HRLITES: " ) );
	}
}
