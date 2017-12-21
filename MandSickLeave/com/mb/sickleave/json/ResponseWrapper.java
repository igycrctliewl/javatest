package com.mb.sickleave.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseWrapper {

   private Response response;

   public ResponseWrapper( ObjectMapper mapper, String jsonString ) {
		try {
			this.response = mapper.readValue( jsonString, Response.class );
		} catch( Exception e ) {
			System.out.println( "Error creating instance of ResponseWrapper" );
			e.printStackTrace();
		}
   }


	public Data getData() {
		return response.getData();
	}
}