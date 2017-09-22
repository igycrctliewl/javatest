import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CarTalk {
	public static void main( String[] args ) throws Exception {

		URL test = new URL( "https://play.podtrac.com/npr-510208/npr.mc.tritondigital.com/NPR_510208/media/anon.npr-podcasts/podcast/510208/9930418/npr_9930418.mp3?orgId=1&amp;p=510208&amp;story=9930418&amp;t=podcast&amp;e=9930418&amp;siteplayer=true&amp;dl=1" );
		System.out.println( test.getFile() );

		String path = test.getPath();
		System.out.println( path.substring( path.lastIndexOf( "/" ) + 1, path.length() )); //



		lineInputTest();

	}


	public static void lineInputTest() throws Exception {
		System.out.println( ">>>> CarTalk.lineInputTest" );

		BufferedReader br = new BufferedReader( new FileReader( "C:\\Users\\mikebro\\Documents\\cartalk\\AllEpisodes.txt" ) );

		int i = 0;
		for( String dataLine = br.readLine(); dataLine != null; dataLine = br.readLine() ) {
			//System.out.println( i++ + " : " + dataLine );
			String podcast = testRegex( dataLine );
			if( ! "".equals( podcast ) ) {
				System.out.println( ">" + podcast + "<" );
				URL url = new URL( podcast );
				String path = url.getPath();
				String localFileName = path.substring( path.lastIndexOf( "/" ) + 1, path.length() );
				System.out.println( localFileName );
			}
			//System.out.println( "URL.getFile():" + url.getFile() );
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

}
