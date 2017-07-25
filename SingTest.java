public class SingTest {
   
   public static void main( String[] args ) {
      Sing s = Sing.getInstance();
      Sing t = Sing.getInstance();

      System.out.println( "s: " + s.getInstanceId() );
      System.out.println( "t: " + t.getInstanceId() );
   }
   
}