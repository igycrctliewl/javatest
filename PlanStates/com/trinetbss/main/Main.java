package com.trinetbss.main;

import com.trinetbss.json.geocode.AddressComponent;
import com.trinetbss.json.geocode.Geocode;
import com.trinetbss.json.geocode.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		String json = "{ \"results\" : [ { \"address_components\" : [ { \"long_name\" : \"92101\", \"short_name\" : \"92101\", \"types\" : [ \"postal_code\" ] }, { \"long_name\" : \"San Diego\", \"short_name\" : \"San Diego\", \"types\" : [ \"locality\", \"political\" ] }, { \"long_name\" : \"San Diego County\", \"short_name\" : \"San Diego County\", \"types\" : [ \"administrative_area_level_2\", \"political\" ] }, { \"long_name\" : \"California\", \"short_name\" : \"CA\", \"types\" : [ \"administrative_area_level_1\", \"political\" ] }, { \"long_name\" : \"United States\", \"short_name\" : \"US\", \"types\" : [ \"country\", \"political\" ] } ], \"formatted_address\" : \"San Diego, CA 92101, USA\", \"geometry\" : { \"bounds\" : { \"northeast\" : { \"lat\" : 32.742536, \"lng\" : -117.145741 }, \"southwest\" : { \"lat\" : 32.6943429, \"lng\" : -117.2165131 } }, \"location\" : { \"lat\" : 32.7269669, \"lng\" : -117.1647094 }, \"location_type\" : \"APPROXIMATE\", \"viewport\" : { \"northeast\" : { \"lat\" : 32.742536, \"lng\" : -117.145741 }, \"southwest\" : { \"lat\" : 32.6943429, \"lng\" : -117.2165131 } } }, \"place_id\" : \"ChIJK-YIt0JT2YARI5JWWz1ORQk\", \"types\" : [ \"postal_code\" ] } ], \"status\" : \"OK\" }";
		System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();
		Geocode obj = null;
		try {
			obj = mapper.readValue(json, Geocode.class);
		} catch (IOException e) {
			System.out.println("caught IOException");
			e.printStackTrace();
		}
		System.out.println(obj);

		for (Result r : obj.getResults()) {
			System.out.println(r.getFormattedAddress());
			for (AddressComponent ac : r.getAddressComponents()) {
				System.out.println("ac.types[0]:" + ac.getTypes().get(0));
				if ("administrative_area_level_1".equals(ac.getTypes().get(0))) {
					System.out.println("State:" + ac.getShortName());
				}
			}
		}
	}
}
