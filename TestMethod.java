import java.util.List;
import java.util.ArrayList;

public class TestMethod {

   public static void main(String[] args) {
       TestMethod m = new TestMethod();
       List<String> myList = m.getAssignmentAddresses( "G9P" );
       for( String j : myList ) {
           System.out.println( j );
       }
       System.out.println( myList.get(0) );
   }
   
   
   public List<String> getAssignmentAddresses( String company ) {
       List<String> s = new ArrayList<String>();
       s.add( "addr1" );
       s.add( "addr2" );
       return s;
   }
}