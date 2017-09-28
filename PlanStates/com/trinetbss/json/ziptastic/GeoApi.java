package com.trinetbss.json.ziptastic;

import com.trinetbss.json.ziptastic.Geocode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GeoApi {

	public static void main( String[] args ) {
		System.out.println( "State:" + callGeoApi( "94044" ) );
	}



	/**
	 * @return the state derived from the input zipCode
	 */
	private static String callGeoApi( String zipCode ) {

		try {
			URL geogApi = new URL( "http://ziptasticapi.com/" + zipCode );

			ObjectMapper mapper = new ObjectMapper();
			Geocode obj = null;

			//obj = mapper.readValue( "{\"country\":\"US\",\"state\":\"CA\",\"city\":\"PACIFICA\"}", Geocode.class );
			
			// usage of this API from within Java is apparently prohibited
			//obj = mapper.readValue( geogApi, Geocode.class );

			System.out.println(obj);

			System.out.println( obj.getState() );

		} catch ( MalformedURLException mal ) {
			System.out.println("bad url");
			mal.printStackTrace();
		} catch ( IOException e ) {
			System.out.println("caught IOException:" + e.getClass().getName() );
			e.printStackTrace();
		}

		return "devel";
	}
}