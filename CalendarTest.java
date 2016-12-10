import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarTest {

   public static void main(String[] args) {

      System.out.println( "\n================================================\n" );

      SimpleDateFormat fmt = new SimpleDateFormat( "MM/dd/yyyy  hh:mm:ss  z" );

      Calendar cal = Calendar.getInstance();   
      //cal.clear();
      System.out.println( "d1:\n" + cal + "\n" );

      //cal.set( Calendar.MONTH, Calendar.OCTOBER );
      //cal.set( Calendar.DAY_OF_MONTH, 1 );
      //cal.set( Calendar.HOUR_OF_DAY, 12 );
      //cal.set( Calendar.MINUTE, 12 );
      //cal.set( Calendar.YEAR, 2016 );
       
      System.out.println( "d2:\n" + cal + "\n" );
      //System.out.println( "date:" + cal.getTime() + "\n" );
      //System.out.println( "date:" + fmt.format( cal.getTime() ) + "\n" );
      
      System.out.println( "________________________________________________\n\n" );
      
      TimeZone tz = TimeZone.getTimeZone("UTC");
      Calendar utcTime = Calendar.getInstance( tz );
      utcTime.setTimeInMillis(  cal.getTimeInMillis() );

      System.out.println( "d3:\n" + utcTime + "\n" );
      System.out.println( "date:" + fmt.format( utcTime.getTime() ) + "\n" );
      System.out.println( "pattern:" + fmt.toLocalizedPattern() + "\n" );
     
   }
}

