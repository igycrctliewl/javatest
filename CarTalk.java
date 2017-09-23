import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CarTalk {
	public static final int ONEMEG = 1048576;
	public static void main( String[] args ) throws Exception {
		downloadAll();
	}


	public static void downloadAll() throws Exception {
		System.out.println( ">>>> CarTalk.downloadAll" );

		BufferedReader br = new BufferedReader( new FileReader( "C:\\Users\\mikebro\\Documents\\cartalk\\AllEpisodes.txt" ) );
		String outputPath = "Z:\\Shared Music\\CarTalk\\";

		int i = 0;
		for( String dataLine = br.readLine(); dataLine != null; dataLine = br.readLine() ) {
			//System.out.println( i++ + " : " + dataLine );
			String podcast = testRegex( dataLine );
			if( ! "".equals( podcast ) ) {
				//System.out.println( ">" + podcast + "<" );
				URL url = new URL( podcast );
				String path = url.getPath();
				String localFileName = path.substring( path.lastIndexOf( "/" ) + 1, path.length() );
				System.out.print( "Downloading " + localFileName );
				
				//download episode to local file
				byte[] bStream = new byte[ONEMEG];
				int progress = 0;
				InputStream input = url.openStream();
				FileOutputStream output = new FileOutputStream( outputPath + localFileName );
				for( int readBytes = input.read( bStream ); readBytes > 0; readBytes = input.read( bStream ) ) {
					progress += readBytes;
					if( progress > ONEMEG ) {
						System.out.print( "." );
						progress = 0;
					}
					output.write( bStream, 0, readBytes );
				}
		
				input.close();
				output.close();
				
				
				System.out.println( "done" );
			}
		}

		br.close();
	}


	public static String testRegex( String html ) {
		//String html = "	Line 22:                 <li class=\"audio-tool audio-tool-download\"><a href = \"https://play.podtrac.com/npr-510208/npr.mc.tritondigital.com/NPR_510208/media/anon.npr-podcasts/podcast/510208/536186784/npr_536186784.mp3?orgId=1&amp;d=3277&amp;p=510208&amp;story=536186784&amp;t=podcast&amp;e=536186784&amp;siteplayer=true&amp;dl=1\" data-metrics=\"{&quot;category&quot;:&quot;audio actions&quot;,&quot;action&quot;:&quot;download audio&quot;,&quot;label&quot;:&quot;536186784&quot;,&quot;noninteraction&quot;:true}\"><b class=\"icn-download\"></b><b class=\"audio-tool-label\">Download</b></a>";
		//System.out.println( html );
		Pattern ptn = Pattern.compile( "(?<=href\\s?=\\s?\").+(?<=\")" );
		Matcher mtch = ptn.matcher( html );
		if( mtch.find() ) {
			String result = mtch.group();
			return result.substring( 0, result.indexOf("\"") );
		} else {
			return "";
		}
	}

	
	private static void testDownload() throws Exception {
		URL test = new URL( "https://play.podtrac.com/npr-510208/npr.mc.tritondigital.com/NPR_510208/media/anon.npr-podcasts/podcast/510208/328869101/npr_328869101.mp3?orgId=1&amp;p=510208&amp;story=328869101&amp;t=podcast&amp;e=328869101&amp;siteplayer=true&amp;dl=1" );
		System.out.println( test.getFile() );

		String path = test.getPath();
		String localFileName = path.substring( path.lastIndexOf( "/" ) + 1, path.length() );
		System.out.println( localFileName );

		byte[] bStream = new byte[1048576];
		InputStream input = test.openStream();
		FileOutputStream output = new FileOutputStream( localFileName );
		System.out.print( "Downloading..." );
		for( int readBytes = input.read( bStream ); readBytes > 0; readBytes = input.read( bStream ) ) {
			output.write( bStream, 0, readBytes );
		}
		System.out.println( "done." );

		input.close();
		output.close();

	}
	
	
}
