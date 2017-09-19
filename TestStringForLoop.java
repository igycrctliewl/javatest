import java.lang.Thread;

public class TestStringForLoop {
   public static void main( String[] args ) {

      String fromCode = "10000";
      String toCode = "120009999";
      int fromInt = Integer.parseInt( fromCode );
      int toInt = Integer.parseInt( toCode.substring( 0, 5 ) );

      for( int a = fromInt; a <= toInt; a++ ) {
         System.out.println( "a:" + a );
      }
   }
}
