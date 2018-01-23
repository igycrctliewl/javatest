

public class CollatingSeq {

	public static void main( String[] args ) {

		char a = '0';
		char b = '9';
		char c = 'A';

		char d = ( a > b ) ? a : b;
		System.out.println( "First compare: " + d );

		d = ( c > d ) ? c : d;
		System.out.println( "Second compare: " + d );
	}
}