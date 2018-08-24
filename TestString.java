
public class TestString {

	public static void main( String[] args ) {
		TestString.substrTest( args );
	}

	public static void substrTest( String[] args ) {
		String descrShort = "fifty-three thousand";
		System.out.println( "Length of descrShort: " + descrShort.length() );
		int offset = Math.min( descrShort.length(), 11 );
		String newDescr = descrShort.substring( 0, offset );
		System.out.println( newDescr );
	}


	public static void splitTest( String[] args ) {
		String longString = "G9P,4M7,G49,G9P,OVO,G49";
		String[] strings;
		strings = longString.split(",");
		for( String s : strings ) {
			System.out.println( s + " - " + s.hashCode() );
		}
	}


}
