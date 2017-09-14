
public class StringTest {

   public static void main( String[] args ) {
      String eligRule = "E";
      if( ! "I".equals( eligRule ) ) {
         System.out.println( "not equal" );
      } else {
         System.out.println( "else condition" );
      }
   }


   public static void main1( String[] args ) {
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
   }
}