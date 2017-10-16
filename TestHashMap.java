import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestHashMap {
   public static void main(String[] args) {

		Set<String> s = new HashSet<>();
		if( s instanceof java.util.Collection ) {
			System.out.println( "Set is a Collection");
		}

      String[] selections = { "002A5Z","002A50","002A51","002A52" };
      int j = 0;
      List<String> planTypeList =  new ArrayList<String>();
      Map< String, HashSet< String >> planTypesForUpdate =  new HashMap< String, HashSet< String >>();

      planTypeList.add( "11" );
      planTypeList.add( "11" );
      planTypeList.add( "11" );
      planTypeList.add( "14" );
      planTypeList.add( "16" );
      planTypeList.add( "30" );
      planTypeList.add( "30" );
      planTypeList.add( "30" );
      planTypeList.add( "67" );

      for( String planType : planTypeList ) {
         if( !planTypesForUpdate.containsKey( planType ) ) {
            planTypesForUpdate.put( planType, new HashSet< String >() );
         }
         if( "30".equals( planType ) ) {
            planTypesForUpdate.get( planType ).add( selections[ j++ ] );
         }
      }

      System.out.println( "size of STD plan list: " + planTypesForUpdate.get( "30" ).size() );


      Set<String> k = planTypesForUpdate.keySet();
      for( String x : k ) {
         System.out.println( "HashSet key value " + x );
         for( String plan : planTypesForUpdate.get( x ) ) {
            System.out.println( "     " + plan );
         }
      }

   }
}
