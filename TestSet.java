import java.util.HashSet;
import java.util.Set;

public class TestSet {
   public static void main(String[] args) {

		Set<String> s = new HashSet<>();

      s.add( "11" );
      s.add( "11" );
      s.add( "11" );
      s.add( "14" );
      s.add( "16" );
      s.add( "30" );
      s.add( "30" );
      s.add( "30" );
      s.add( "67" );

      System.out.println( "print set as a string: " + s.toString() );

   }
}
