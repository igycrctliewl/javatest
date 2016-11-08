import java.util.ArrayList;
import java.util.List;

public class TestList {
   public static void main(String[] args) {
      List<String> list = new ArrayList<String>();

      list.add( "23" );
      list.add( "30" );
      list.add( "31" );
      list.add( "A3" );

      for( String planType : list ) {
         System.out.println( "===>  " + planType );
      }

   }
}