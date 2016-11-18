import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarTest {

   public static void main(String[] args) {

      SimpleDateFormat fmt = new SimpleDateFormat( "MM/dd/yyyy  hh:mm:ss  z" );

      Calendar cal = Calendar.getInstance();   
      cal.clear();
      System.out.println( "d1:" + cal + ":\n" );

      cal.set( Calendar.MONTH, Calendar.OCTOBER );
      cal.set( Calendar.DAY_OF_MONTH, 1 );
      cal.set( Calendar.YEAR, 2016 );
      
      System.out.println( "d2:" + cal + ":\n" );
      System.out.println( "date:" + cal.getTime() + ":\n" );
      System.out.println( "date:" + fmt.format( cal.getTime() ) + ":\n" );
      
      System.out.println( "________________________________________________\n\n" );
      
//germanyTime.setTimeInMillis(localTime.getTimeInMillis());

      TimeZone tz = TimeZone.getTimeZone("UTC");
      System.out.println( tz.getID() );
      cal.setTimeZone( tz );
      System.out.println( "d3:" + cal + ":\n" );
      System.out.println( "date:" + fmt.format( cal.getTime() ) + ":\n" );
     
   }
}

