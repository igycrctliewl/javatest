package mb.test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author mikebro
 */
public class DateTest {
   public static void main( String[] args ) {
      Date date = new Date();
      System.out.println( date );

      String[] tz = TimeZone.getAvailableIDs();
      for( String s : tz ) {
         System.out.println( s );
      }

      Calendar instant = Calendar.getInstance( TimeZone.getTimeZone("America/New_York"), Locale.US );

      instant.set( Calendar.HOUR, 0 );
      instant.set( Calendar.HOUR_OF_DAY, 0 );
      instant.set( Calendar.MINUTE, 0 );
      instant.set( Calendar.SECOND, 0 );
      instant.set( Calendar.MILLISECOND, 0 );


      System.out.println( instant );
      System.out.println( instant.getTime() );


      LocalDate today = LocalDate.now();
      System.out.println( today );
      LocalDate dob = LocalDate.of( 1966, Month.JANUARY, 3 );
      System.out.println( dob );
      long days = dob.until( today, ChronoUnit.DAYS );
      System.out.println( days );

      dob = LocalDate.of( 1997, Month.NOVEMBER, 10 );
      System.out.println( dob );
      days = dob.until( today, ChronoUnit.DAYS );
      System.out.println( days );
   }
}
