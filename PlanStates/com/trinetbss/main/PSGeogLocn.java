package com.trinetbss.main;

import com.trinetbss.sql.GeoLocations;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class PSGeogLocn {

	public String geogLocnId;
	public Date effdt;
	public String eligFlag;
	public Set<String> states;

	public PSGeogLocn( String geogLocnId, String effdtStr ) {
		GeoLocations geo = new GeoLocations();
		geo.runQuery( geogLocnId, effdtStr );

		try {
			while( geo.queryResult.next() ) {
				this.geogLocnId = geo.queryResult.getString( "LOCATION_TBL_ID" );
				this.effdt = geo.queryResult.getDate( "EFFDT" );
				this.eligFlag = geo.queryResult.getString( "ELIG_FLG_GEO" );
				this.states = new HashSet<>();
			}
		} catch( Exception e ) {
			System.out.println( "PSGeogLocn constructor => general exception " );
			e.printStackTrace();
		}

	}




	/* main method for testing only */
	private static void main( String[] args ) {
		PSGeogLocn psg = new PSGeogLocn( "Q124", "12-JAN-2018" );
		System.out.println( "PSGeogLocn.main => object:geogLocnId:" + psg.geogLocnId );
		System.out.println( "PSGeogLocn.main => object:effdt:" + psg.effdt );
		System.out.println( "PSGeogLocn.main => object:eligFlag:" + psg.eligFlag );
		System.out.println( "PSGeogLocn.main => object:states.size:" + psg.states.size() );
	}

}