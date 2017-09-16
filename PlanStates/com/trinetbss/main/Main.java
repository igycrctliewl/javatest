package com.trinetbss.main;

import com.trinetbss.json.geocode.AddressComponent;
import com.trinetbss.json.geocode.Geocode;
import com.trinetbss.json.geocode.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
	public static void main(String[] args) {

      // hard-coded test
		//String json = "{ \"results\" : [ { \"address_components\" : [ { \"long_name\" : \"92101\", \"short_name\" : \"92101\", \"types\" : [ \"postal_code\" ] }, { \"long_name\" : \"San Diego\", \"short_name\" : \"San Diego\", \"types\" : [ \"locality\", \"political\" ] }, { \"long_name\" : \"San Diego County\", \"short_name\" : \"San Diego County\", \"types\" : [ \"administrative_area_level_2\", \"political\" ] }, { \"long_name\" : \"California\", \"short_name\" : \"CA\", \"types\" : [ \"administrative_area_level_1\", \"political\" ] }, { \"long_name\" : \"United States\", \"short_name\" : \"US\", \"types\" : [ \"country\", \"political\" ] } ], \"formatted_address\" : \"San Diego, CA 92101, USA\", \"geometry\" : { \"bounds\" : { \"northeast\" : { \"lat\" : 32.742536, \"lng\" : -117.145741 }, \"southwest\" : { \"lat\" : 32.6943429, \"lng\" : -117.2165131 } }, \"location\" : { \"lat\" : 32.7269669, \"lng\" : -117.1647094 }, \"location_type\" : \"APPROXIMATE\", \"viewport\" : { \"northeast\" : { \"lat\" : 32.742536, \"lng\" : -117.145741 }, \"southwest\" : { \"lat\" : 32.6943429, \"lng\" : -117.2165131 } } }, \"place_id\" : \"ChIJK-YIt0JT2YARI5JWWz1ORQk\", \"types\" : [ \"postal_code\" ] } ], \"status\" : \"OK\" }";
		//System.out.println(json);

      Address addr = new Address();

      try {
         URL geogApi = new URL( "http://maps.googleapis.com/maps/api/geocode/json?address=US&components=postal_code:" + args[0] );

         ObjectMapper mapper = new ObjectMapper();
         Geocode obj = null;
         obj = mapper.readValue( geogApi, Geocode.class);
         System.out.println(obj);

         for (Result r : obj.getResults()) {
            System.out.println(r.getFormattedAddress());
            for (AddressComponent ac : r.getAddressComponents()) {
               parseAC( ac, addr );
               //System.out.println("ac.types[0]:" + ac.getTypes().get(0));
               //if ("administrative_area_level_1".equals(ac.getTypes().get(0))) {
               //   System.out.println("State:" + ac.getShortName());
               //}
            }
         }
      } catch ( MalformedURLException mal ) {
         System.out.println("bad url");
         mal.printStackTrace();
      } catch ( IOException e ) {
         System.out.println("caught IOException:" + e.getClass().getName() );
         e.printStackTrace();
      }

      if( "US".equals( addr.country ) ) {
         System.out.println( "Determined state is " + addr.state );
      }
	}

   private static void parseAC( AddressComponent adr, Address a ) {
      String longName = adr.getLongName();
      String shortName = adr.getShortName();
      List<String> types = adr.getTypes();

      System.out.println( ">>>> parseAC" );
      //System.out.println( "     longName:" + longName );
      //System.out.println( "     shortName:" + shortName );

      if( types.contains( "postal_code" ) ) {
         System.out.println( "     zip code is " + shortName );
         a.zip = shortName;
      } else if( types.contains( "locality" ) ) {
         System.out.println( "     city is " + shortName );
         a.city = shortName;
      } else if( types.contains( "administrative_area_level_2" ) ) {
         System.out.println( "     county is " + shortName );
      } else if( types.contains( "administrative_area_level_1" ) ) {
         System.out.println( "     state is " + shortName );
         a.state = shortName;
      } else if( types.contains( "country" ) ) {
         System.out.println( "     country is " + shortName );
         a.country = shortName;
      }

      System.out.println( "     " + a );
   }

   static class Address {
      String city;
      String state;
      String zip;
      String country;

      public String toString() {
         return this.getClass().getName() + ":" + this.city + ", " + this.state + "   " + this.zip + "  " + this.country;
      }
   }
}
