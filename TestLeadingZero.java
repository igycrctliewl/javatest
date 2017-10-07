
public class TestLeadingZero {
   public static void main(String[] args) {
      String num = String.valueOf( 35032 );

		System.out.println( "String length:" + num.length() );

		while( num.length() < 5 ) {
			num = "0".concat( num );
		}
		
		System.out.println( "Finished:" + num + ":" );
   }
}