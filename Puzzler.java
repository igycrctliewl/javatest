
public class Puzzler {

	/**
	 * Solve the CarTalk puzzler from episode #803, the one with palindromic odometer readings
	 * The solution to the puzzler is written to the console
	 * @param args
	 */
	public static void main( String[] args ) {
		for( int j = 1; j < 999999; j++ ) {
			if( isPalindrome( formatNumber( j ).substring( 2, 6 ) )) {
				if( isPalindrome( formatNumber( j + 1 ).substring( 1, 6 ) )) {
					if( isPalindrome( formatNumber( j + 2 ).substring( 1, 5 ) )) {
						if( isPalindrome( formatNumber( j + 3 ).substring( 0, 6 ) )) {
							System.out.println( formatNumber( j ) + " " + formatNumber( j + 1 ) + " " + formatNumber( j + 2 ) + " " + formatNumber( j + 3 ) );
						}
					}
				}
			}
		}
		System.out.println( "done" );
	}


	/**
	 * Determine whether the argument is a palindrome.
	 * @param str
	 * @return false if the String is null or has zero length,
	 * true if the string reads the same forwards or backward, false otherwise
	 */
	private static boolean isPalindrome( String str ) {
		if( str == null || str.length() == 0 ) {
			return false;
		}
		if( str.equals( reverse( str ) )) {
			return true;
		} else {
			return false;
		}		
	}

	
	/**
	 * A utility to reverse the order of characters in a string.
	 * @param s a String to reverse. The parameter will not be altered.
	 * @return a new String that is the reverse of the parameter
	 */
	private static String reverse( String s ) {
		byte[] strBytes = s.getBytes();
		byte[] newBytes = new byte[ strBytes.length ];
		for( int j = 0; j < strBytes.length; j++ ) {
			newBytes[j] = strBytes[ strBytes.length - 1 - j ];
		}
		return new String( newBytes );
	}


	/**
	 * Formats an integer as a fixed-length string, padding on the left with zeros as needed
	 * @param i integer to be formatted
	 * @return string representation of the argument
	 */
	private static String formatNumber( int i ) {
		String fmt = Integer.toString( i );
		for( ; fmt.length() < 6; fmt = "0" + fmt );
		return fmt;
	}
	
	
}