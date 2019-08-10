import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestArrayList {
   public static void main(String[] args) {

      List<String> planTypeList =  new ArrayList<String>();
		for( String s : planTypeList ) {
			System.out.println( "inside loop of empty list" );
		}
		System.out.println( "after loop of empty list" );


      planTypeList.add( "11" );
      planTypeList.add( "11" );
      planTypeList.add( "11" );
      planTypeList.add( "14" );
      planTypeList.add( "16" );
      planTypeList.add( "16" );
      planTypeList.add( "16" );
      planTypeList.add( "16" );

      for( String planType : planTypeList ) {
         System.out.println( "===>  " + planType );
      }

      System.out.println( " " );


      HashSet<String> planTypeSet =  new HashSet<String>();

      planTypeSet.add( "11" );
      planTypeSet.add( "11" );
      planTypeSet.add( "11" );
      planTypeSet.add( "14" );
      planTypeSet.add( "16" );
      planTypeSet.add( "16" );
      planTypeSet.add( "16" );
      planTypeSet.add( "16" );

      for( String planType : planTypeSet ) {
         System.out.println( "===>  " + planType );
      }

   }
}
