
public class TestCharArray {

   public static void main(String[] args) {

      char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		char[] quickAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ9876543210".toCharArray();

		System.out.println( "1:" + alphabet.length );
		System.out.println( "2:" + quickAlpha.length );

		for( int a = 0; a < 26; a++ ) {
			System.out.println( ">" + alphabet[ a ] + "<>" + quickAlpha[ a ] + "<" );
		}
   }
}