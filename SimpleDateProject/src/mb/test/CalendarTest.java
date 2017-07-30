package mb.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class CalendarTest {

   public static void main(String[] args) {

      System.out.println( "\n================================================\n" );

      SimpleDateFormat fmt = new SimpleDateFormat( "MM/dd/yyyy  hh:mm:ss  z" );
      SimpleDateFormat dateFmt = new SimpleDateFormat( "MM/dd/yyyy" );

      Calendar cal = Calendar.getInstance();
      cal.clear();
      System.out.println( "d1:\n" + cal + "\n" );

      cal.set( Calendar.MONTH, Calendar.JANUARY );
      cal.set( Calendar.DAY_OF_MONTH, 3 );
      cal.set( Calendar.YEAR, 1966 );

      System.out.println( "d2:\n" + cal + "\n" );
      System.out.println( "date:" + cal.getTime() + "\n" );
      System.out.println( "date:" + fmt.format( cal.getTime() ) + "\n" );
      System.out.println( "just date:" + dateFmt.format( cal.getTime() ) + "\n" );

      System.out.println( "________________________________________________\n\n" );

      TimeZone tz = TimeZone.getTimeZone("UTC");
      Calendar utcTime = Calendar.getInstance( tz );
      utcTime.setTimeInMillis(  cal.getTimeInMillis() );

      System.out.println( "d3:\n" + utcTime + "\n" );
      System.out.println( "date:" + fmt.format( utcTime.getTime() ) + "\n" );
      System.out.println( "pattern:" + fmt.toLocalizedPattern() + "\n" );

   }
}

