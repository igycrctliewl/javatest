import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class CarTalk {
	public static void main( String[] args ) throws Exception {
		lineInputTest();

	}


	public static void lineInputTest() throws Exception {
		System.out.println( ">>>> CarTalk.lineInputTest" );

		BufferedReader br = new BufferedReader( new FileReader( "C:\\Users\\mikebro\\Documents\\cartalk\\AllEpisodes.txt" ) );

		int i = 0;
		for( String dataLine = br.readLine(); dataLine != null; dataLine = br.readLine() ) {
			System.out.println( i++ + " : " + dataLine );
		}

		br.close();
	}

}