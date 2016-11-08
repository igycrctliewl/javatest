import java.lang.Thread;

public class TestSleep {
   public static void main( String[] args ) {
      try {
         System.out.println( "Started" );
         Thread.sleep(5000);
         System.out.println( "Awake" );
      } catch( Exception e ) {
      }
   }
}
