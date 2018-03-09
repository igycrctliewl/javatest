import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestEmptySet {
   public static void main(String[] args) {

      Set<String> nonePlanTypes = new HashSet<String>();


      for( String planType : nonePlanTypes ) {
         System.out.println( "I'm in the loop for some reason" );
      }

      System.out.println( "End" );


   }
}
