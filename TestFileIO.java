import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class TestFileIO {
	public static void main( String[] args ) throws Exception {
		lineInputTest();

	}


	public static void lineInputTest() throws Exception {
		System.out.println( ">>>> TestFileIO.lineInputTest" );

		BufferedReader br = new BufferedReader( new FileReader( "TestFileIO.java" ) );

		int i = 0;
		for( String dataLine = br.readLine(); dataLine != null; dataLine = br.readLine() ) {
			System.out.println( i++ + " : " + dataLine );
		}

		br.close();
	}


	public static void inputTest() throws Exception {
		System.out.println( ">>>> TestFileIO.inputTest" );

		FileReader fr = new FileReader( "fwOutput.txt" );
		char[] inputBuffer = new char[10];

		fr.mark( 50 );
		fr.read( inputBuffer );
		System.out.println( new String( inputBuffer ) );
		fr.reset();

		for( int dataRead = fr.read( inputBuffer ); dataRead != -1; dataRead = fr.read( inputBuffer ) ) {
			String dataLine = new String( inputBuffer );
			System.out.println( dataRead + " : " + dataLine );
		}

		fr.close();
	}


	public static void outputTest() throws Exception {
		System.out.println( ">>>> TestFileIO.outputTest" );

		FileWriter fw = new FileWriter( "fwOutput.txt" );
		fw.write( "Victory - The City Goes Wild\n" );
		fw.write( "The city of San Francisco celebrated a victory\n" );
		fw.write( "The 49ers brought the city its first championship\n" );
		fw.flush();
		fw.close();


	}

}