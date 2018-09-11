package com.trinetbss.region;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Region {

	private Map<String,String> regions;


	public Region() {
		// prepare location-to-subRegion lookup table
		this.regions = new HashMap<String,String>();

		try {
			InputStream in = getClass().getResourceAsStream("/com/trinetbss/region/subRegions.txt");
			Reader fr = new InputStreamReader( in, "utf-8" );
			BufferedReader buff = new BufferedReader( fr );

			for( String dataLine = buff.readLine(); dataLine != null; dataLine = buff.readLine() ) {
				String[] parts = dataLine.split( "," );
				this.regions.put( parts[0], parts[1] );
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}

		System.out.println( "Region constructor => Built sub region map with " + this.regions.size() + " entries." );
	}

	public String lookupSubRegion( String location ) {
		return regions.get( location );
	}

	
	/* main method for testing only */
	public static void main( String[] args ) {
		Region z = new Region();
		System.out.println( z );
		System.out.println( "Region.main() => Test lookup for Ambrose location: " + z.lookupSubRegion( "AM08" ) );
		System.out.println( "Region.main() => Test lookup for Accord location: " + z.lookupSubRegion( "AC27" ) );
		System.out.println( "Region.main() => Test lookup for SOI location: " + z.lookupSubRegion( "SY72" ) );

	}

}