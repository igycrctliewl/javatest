import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestArrayList {
   public static void main(String[] args) {

      List<String> planTypeList =  new ArrayList<String>();

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