package com.trinetbss.portfolio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {

	private Map<String,Integer> portfolios;
	private Map<String,Integer> planPortfolios;

	public Portfolio() {

		// prepare vendor-to-portfolio lookup table
		this.portfolios = new HashMap<>();

		try {
			InputStream in = getClass().getResourceAsStream("/com/trinetbss/portfolio/portfolios.txt");
			Reader fr = new InputStreamReader( in, "utf-8" );
			BufferedReader buff = new BufferedReader( fr );

			for( String dataLine = buff.readLine(); dataLine != null; dataLine = buff.readLine() ) {
				String[] parts = dataLine.split( "," );
				this.portfolios.put( parts[0], Integer.parseInt( parts[1] ) );
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}

		System.out.println( "Portfolio constructor => Built portfolio map with " + this.portfolios.size() + " entries." );


		// prepare plan-to-portfolio override lookup table
		this.planPortfolios = new HashMap<>();

		try {
			InputStream in = getClass().getResourceAsStream("/com/trinetbss/portfolio/planOverrides.txt");
			Reader fr = new InputStreamReader( in, "utf-8" );
			BufferedReader buff = new BufferedReader( fr );

			for( String dataLine = buff.readLine(); dataLine != null; dataLine = buff.readLine() ) {
				String[] parts = dataLine.split( "," );
				planPortfolios.put( parts[0], Integer.parseInt( parts[1] ) );
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}

		System.out.println( "Portfolio constructor => Built plan map with " + planPortfolios.size() + " entries." );
	}

	public Integer lookupPortfolio( String vendorId, String benefitPlan ) {

		Integer portfolioId = this.planPortfolios.get( benefitPlan );
		System.out.println( "Override lookup found " + portfolioId );
		if( portfolioId == null ) {
			portfolioId = this.portfolios.get( vendorId );
		}

		return portfolioId;
	}

	/* main method for testing only */
	public static void main( String[] args ) {
		Portfolio z = new Portfolio();
		System.out.println( "Portfolio.main() => Test lookup for bogus vendor: " + z.lookupPortfolio( "74004", " " ) );
		System.out.println( "Portfolio.main() => Test lookup for KAISER vendor: " + z.lookupPortfolio( "KAISER", " " ) );
		System.out.println( "Portfolio.main() => Test lookup for OPTUM vendor: " + z.lookupPortfolio( "OPTUM", " " ) );
		System.out.println( "Portfolio.main() => Test lookup for override vendor: " + z.lookupPortfolio( "KAISER", "0021DL" ) );
		System.out.println( "Portfolio.main() => Test lookup for EYEMED vendor: " + z.lookupPortfolio( "EYEMEDSOI", " " ) );
		System.out.println( "Portfolio.main() => Test lookup for override EyeMed plan: " + z.lookupPortfolio( "AETNA", "002LXS" ) );

	}

}