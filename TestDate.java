
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main( String[] args ) {
      Date d = new Date( 1514764800000L );
      System.out.println( d );
		long l = System.currentTimeMillis();
		System.out.println( l );
   }

   public static void main1(String[] args) {
      Date date = new Date();
      System.out.println( date );
      System.out.println( "other formatted:" + new SimpleDateFormat( "dd-MMM-yyyy" ).format( date ) );

      Date oraDate;

      try {
         oraDate = new SimpleDateFormat( "dd-MMM-yyyy" ).parse( "13-JUN-2016" );
         System.out.println( "ORA DATE >" + new SimpleDateFormat( "MM/dd/yyyy" ).format( oraDate ) + "<" );
      } catch( Exception e ) {
         e.printStackTrace();
         oraDate = new Date();
      }


      Date d1;

      try {
         d1 = new SimpleDateFormat( "MM-dd-yyyy" ).parse( "05-13-2016" );
         System.out.println( "Date2 >" + new SimpleDateFormat( "MM/dd/yyyy" ).format( d1 ) + "<" );
      } catch( Exception e ) {
         e.printStackTrace();
         d1 = new Date();
      }


      Date d2;

      try {
         d2 = new SimpleDateFormat( "MM-dd-yyyy" ).parse( "05-13-2016" );
         System.out.println( "Date2 >" + new SimpleDateFormat( "MM/dd/yyyy" ).format( d2 ) + "<" );
      } catch( Exception e ) {
         e.printStackTrace();
         d2 = new Date();
      }


      Date d3;

      try {
         d3 = new SimpleDateFormat( "MM-dd-yyyy" ).parse( "01-03-2016" );
         System.out.println( "Date3 >" + new SimpleDateFormat( "MM/dd/yyyy" ).format( d3 ) + "<" );
      } catch( Exception e ) {
         e.printStackTrace();
         d3 = new Date();
      }

      Date dNull = null;

      System.out.println( "compare d2 with null:" + d2.compareTo( dNull ) );
      if( d2.compareTo( dNull ) > 0 )    // if  d2 > null
         System.out.println( "    true condition" );

      System.out.println( "compare d2 with d3:" + d2.compareTo( d3 ) );
      if( d2.compareTo( d3 ) > 0 )    // if  d2 > d3
         System.out.println( "    true condition" );

      System.out.println( "compare d3 with d2:" + d3.compareTo( d2 ) );
      if( d3.compareTo( d2 ) < 0 )    // if  d3 < d2
         System.out.println( "    true condition" );

      System.out.println( "compare d1 with d2:" + d1.compareTo( d2 ) );
      if( d1.compareTo( d2 ) == 0 )    // if  d1 = d2
         System.out.println( "    true condition" );

      if( d3.compareTo( d2 ) <= 0 )    // if  d3 <= d2
         System.out.println( "    " + d3 + " is less than or equal to " + d2 );

      if( d2.compareTo( d2 ) <= 0 )    // if  d2 <= d2
         System.out.println( "    " + d2 + " is less than or equal to " + d2 );

      if( d2.compareTo( d3 ) <= 0 )    // if  d2 <= d3
         System.out.println( "    " + d2 + " is less than or equal to " + d3 );
      else
         System.out.println( "    " + d2 + " is NOT less than or equal to " + d3 );


   }
}