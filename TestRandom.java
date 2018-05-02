

public class TestRandom {
	public static void main( String[] args ) {
		String binaryNumeral = "";
		for( int i = 0; i < 128; i++ ) {
			long l = System.currentTimeMillis();
			String dig = ( isEven( l ) ? "1" : "0" );
			System.out.println( "" + l + "  " + dig );
			binaryNumeral = new StringBuilder().append( binaryNumeral ).append( dig ).toString();
		}
		System.out.println( binaryNumeral );
		long decimalNumeral = Long.parseLong( binaryNumeral, 2 );
		System.out.println( decimalNumeral );
	}

	private static boolean isEven( long num ) {
		return ( num % 2 ) == 0;
	}
}