package com.mb.sickleave;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mb.sickleave.json.Data;
import com.mb.sickleave.json.Response;

public class Main {

	public static void main( String[] args ) throws Exception {
	
		ObjectMapper mapper = new ObjectMapper();
		Response obj = null;
		String jsonString = "{ \"data\": {\"increamentSickLeaveBalancePslMandateConfig\": {\"city\": null,\"minimumEmployees\": 5,\"stateCd\": \"CA\"},\"mandateAccrualHours\": 1.7777777777777777,\"pslIncrementSickLeaveBalanceConfig\": {\"accrualRate\": 0.04444444444444444,\"capInHours\": 40,\"daysAfterAccrualBegins\": 0},\"pslResetAccrualConfig\": {\"capInHours\": 40,\"carryOverAllowed\": true,\"resetDay\": 31,\"resetMonth\": \"DECEMBER\"},\"resetAccrualPslMandateConfig\": {\"city\": null,\"minimumEmployees\": 5,\"stateCd\": \"CA\"}},\"_requestId\": \"dc201afe-585b-4a02-aed1-b1a4f430e407\",\"_statusCode\": \"200\",\"_statusText\": \"OK\",\"_statusMessage\": \"Success\"}";
		obj = mapper.readValue( jsonString, Response.class );
		System.out.println(obj);

		Data data = obj.getData();
		System.out.println( data );

		BigDecimal balance = data.getMandateAccrualHours();
		System.out.println( balance );

	/*   
		for (Result r : obj.getResults()) {
			System.out.println(r.getFormattedAddress());
			for (AddressComponent ac : r.getAddressComponents()) {
				parseAC( ac, addr );
			}
			System.out.println( "     " + addr );
		}
		catch ( MalformedURLException mal ) {
		System.out.println("bad url");
		mal.printStackTrace();
		catch ( IOException e ) {
		System.out.println("caught IOException:" + e.getClass().getName() );
		e.printStackTrace();
		
		
		f( "US".equals( addr.country ) ) {
		//System.out.println( "Determined state is " + addr.state );
		return addr.state;
		else {
		System.out.println( "zipCode " + zipCode + " returned no state value; addr.country = " + addr.country );
	*/
	}
}
