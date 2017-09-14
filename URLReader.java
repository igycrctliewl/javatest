import java.net.*;
import java.io.*;

public class URLReader {
	public static void main(String[] args) throws Exception {

		URL geogApi = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=94044");
		BufferedReader in = new BufferedReader(new InputStreamReader(geogApi.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);

		in.close();
	}
}