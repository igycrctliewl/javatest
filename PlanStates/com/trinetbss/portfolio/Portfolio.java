package com.trinetbss.portfolio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {

	private Map<String,Integer> portfolios;

	public Portfolio() {

		this.portfolios = new HashMap<>();

		try {
			InputStream in = getClass().getResourceAsStream("/com/trinetbss/portfolio/portfolios.txt");
			Reader fr = new InputStreamReader( in, "utf-8" );
			BufferedReader buff = new BufferedReader( fr );

			for( String dataLine = buff.readLine(); dataLine != null; dataLine = buff.readLine() ) {
				String[] parts = dataLine.split( "," );
				portfolios.put( parts[0], Integer.parseInt( parts[1] ) );
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}

		System.out.println( "Portfolio constructor => Built map with " + portfolios.size() + " entries." );
	}

	public Integer lookupPortfolio( String vendorId ) {
		return portfolios.get( vendorId );
	}

	/* main method for testing only */
	public static void main( String[] args ) {
		Portfolio z = new Portfolio();
		System.out.println( "Portfolio.main() => Test lookup for bogus vendor: " + z.lookupPortfolio( "74004" ) );
		System.out.println( "Portfolio.main() => Test lookup for KAISER vendor: " + z.lookupPortfolio( "KAISER" ) );
		System.out.println( "Portfolio.main() => Test lookup for OPTUM vendor: " + z.lookupPortfolio( "OPTUM" ) );
	}

}