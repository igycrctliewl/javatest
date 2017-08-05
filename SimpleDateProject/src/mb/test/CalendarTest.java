package mb.test;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {

   public static void main(String[] args) {

      System.out.println( "\n================================================ d1: now\n" );

      Calendar cal = Calendar.getInstance( TimeZone.getTimeZone("America/New_York"), Locale.US );
      System.out.println( cal );
      System.out.println( "\ndate: " + cal.getTime() );



      System.out.println( "\n================================================ d2: 8/3/2017 18:11:23\n" );

      cal.clear();
      cal.set( Calendar.MONTH, Calendar.AUGUST );
      cal.set( Calendar.DAY_OF_MONTH, 3 );
      cal.set( Calendar.YEAR, 2017 );
      cal.set( Calendar.HOUR, 18 );
      cal.set( Calendar.MINUTE, 11 );
      cal.set( Calendar.SECOND, 23 );

      System.out.println( cal );
      System.out.println( "\ndate: " + cal.getTime() );



      System.out.println( "\n================================================ d4: calculate fields\n" );

      Calendar newCal = (java.util.GregorianCalendar) cal.clone();
      newCal.setTime( cal.getTime() );
      System.out.println( newCal );
      System.out.println( "\ndate: " + newCal.getTime() );

      morningTest();

   }


   public static void morningTest() {

      System.out.println( "\n================================================ morning new date\n" );

      Calendar cal = Calendar.getInstance( TimeZone.getTimeZone("America/Vancouver"), Locale.US );
      System.out.println( cal );
      System.out.println( "\ndate: " + cal.getTime() );



      System.out.println( "\n================================================ d2: my birthday\n" );

      cal.clear();
      cal.set( Calendar.MONTH, Calendar.JANUARY );
      cal.set( Calendar.DAY_OF_MONTH, 3 );
      cal.set( Calendar.YEAR, 1966 );
      cal.set( Calendar.HOUR, 8 );
      cal.set( Calendar.MINUTE, 31 );
      cal.set( Calendar.SECOND, 00 );

      System.out.println( cal );
      System.out.println( "\ndate: " + cal.getTime() );



      System.out.println( "\n================================================ calculate fields:\n" );

      cal.setTime( cal.getTime() );
      System.out.println( cal );
      System.out.println( "\ndate: " + cal.getTime() );


   }
}

