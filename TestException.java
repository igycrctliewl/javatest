
public class TestException {
   public static void main(String[] args) {
      String[] list =  new String[3];
      System.out.println( "list size: " + list.length );

      try {

         list[0] = "23";
         list[1] = "30";
         //list[2] = "31";


         if( list[0] == "23" ) {
            throw( new Exception( "mike forced error" ) );
         }
      
         for( String planType : list ) {
            System.out.println( "===>  " + planType );
         }
      } catch ( Exception e ) {
         e.printStackTrace();
      }
   }
}