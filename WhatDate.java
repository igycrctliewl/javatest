import java.sql.Date;

public class WhatDate {

	public static void main( String[] args ) {
		if( args.length != 1 ) {
			System.out.println( "Usage: WhatDate [Long]\n   where [Long] is a number of milliseconds representing a date" );
			System.exit( 0 );
		}

		try {
			Long l = Long.valueOf( args[0] );
			//System.out.println( "Long is " + l );
			Date d = new Date( l );
			System.out.printf( "%s translates to Date %s%n", l, d );
		} catch( NumberFormatException nfe ) {
			System.out.println( "Usage: WhatDate [Long]\n   where [Long] is a number of milliseconds representing a date" );
			System.out.println( "   ERROR: the argument provided could not be transformed to a Long integer." );
			System.exit( 12 );
		}
	}
}
