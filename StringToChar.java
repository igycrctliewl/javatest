
public class StringToChar {

   public static void main( String[] args ) {
      char[] printData = new char[80];

		for( int j = 0; j < printData.length; j++ ) {
			printData[ j ] = '-';
		}

		
		String profession = "Librarian";
		char[] srcData = profession.toCharArray();
		for( int j = 22, k = 0; k < srcData.length; j++, k++ ) {
			printData[ j ] = srcData[ k ];
		}

		String printLine = new String( printData );
		System.out.println( printLine );

	}

}