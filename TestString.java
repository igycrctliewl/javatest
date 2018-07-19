
public class TestString {

	public static void main( String[] args ) {
		String longString = "G9P,4M7,G49,G9P,OVO,G49";
		String[] strings;
		strings = longString.split(",");
		for( String s : strings ) {
			System.out.println( s + " - " + s.hashCode() );
		}
	}


}
