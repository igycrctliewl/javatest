
public class TestArray {
   public static void main(String[] args) {
      String[] list =  new String[3];
      System.out.println( "list size: " + list.length );

      list[0] = "23";
      list[1] = "30";
      //list[2] = "31";

      for( String planType : list ) {
         System.out.println( "===>  " + planType );
      }

   }
}