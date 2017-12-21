
public class TestCharArray {

   public static void main(String[] args) {

      char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		char[] quickAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ9876543210".toCharArray();

		System.out.println( "1:" + alphabet.length );
		System.out.println( "2:" + quickAlpha.length );

		for( int a = 0; a < 26; a++ ) {
			System.out.println( ">" + alphabet[ a ] + "<>" + quickAlpha[ a ] + "<" );
		}


		String numbers = "K";
		System.out.println( "String length is " + numbers.length() );

		char c = 0;
		if( numbers != null && numbers.length() > 0 ) {
			c = numbers.charAt( 0 );
		}

		System.out.println( "result of 'charAt(0)' is >" + c + "<" );

		char x = 'G';
		char newX = 0;
		try {
			newX = getNext( x );
		} catch( Exception e ) {
			System.out.println( "ERROR during getNext call" );
			e.printStackTrace();
		}
		System.out.println( "Find next index after 'G' : " + newX );

		x = '0';
		try {
			newX = getNext( x );
		} catch( Exception e ) {
			System.out.println( "ERROR during getNext call" );
			e.printStackTrace();
		}
		System.out.println( "Find next index after '0' : " + newX );

   }





	
	private static final char[] INDEX_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ9876543210".toCharArray();
	private static char getNext( char indexChar ) throws Exception {
		int nextIndex = 0;
		for( int a = 0; a < INDEX_CHARACTERS.length; a++ ) {
			System.out.println( a );
			if( indexChar == INDEX_CHARACTERS[ a ] ) {
				System.out.println( "Found hit on index " + a );
				nextIndex = a + 1;
				break;
			}
		}
		if( nextIndex < INDEX_CHARACTERS.length ) {
			return INDEX_CHARACTERS[ nextIndex ];
		} else {
			throw new Exception( "A new benefit class could not be assigned to this benefit group.  Too many classes have been assigned." );
		}
	}



}