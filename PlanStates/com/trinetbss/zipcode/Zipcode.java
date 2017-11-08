package com.trinetbss.zipcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Zipcode {

	private Map<String,String> zipCodes;

	public Zipcode() {

		this.zipCodes = new HashMap<>();

		try {
			InputStream in = getClass().getResourceAsStream("/com/trinetbss/zipcode/zipcodes.txt");
			Reader fr = new InputStreamReader( in, "utf-8" );
			BufferedReader buff = new BufferedReader( fr );

			for( String dataLine = buff.readLine(); dataLine != null; dataLine = buff.readLine() ) {
				String[] parts = dataLine.split( "," );
				zipCodes.put( parts[0], parts[1] );
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}

		//System.out.println( "Build map with " + zipCodes.size() + " entries." );
	}

	public String lookupZip( String zip ) {
		return zipCodes.get( zip );
	}

	/* main method for testing only */
	private static void main( String[] args ) {
		Zipcode z = new Zipcode();
		System.out.println( "Test lookup for my zip code: " + z.lookupZip( "74004" ) );
	}

}