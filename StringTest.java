
public class StringTest {

   public static void main( String[] args ) {
      String str = "mikebro    ";
      System.out.println( ":" + str.trim() + ":" );
 
      testSubstring();
   }


   private static void testSubstring() {
      String str = "abcdefghijklmnopqrstuvwxyz";
      //            12345678901234567890
      String str2 = str.substring(0,20);
      System.out.println( str2 );

      String c = "abc".substring(2,3);
      System.out.println( "c:" + c + ":" );

      String geog = "123459999".substring( 0, 5 );
      System.out.println( "substring test:" + geog + ":" );

      String dataLine = "* this is a comment line";
      System.out.println( "substring:" + dataLine.substring( 0, 1 ) + ":" );
   }
}