import java.util.Calendar;
import java.math.BigDecimal;

public class BenAsOfDate {


	public static java.sql.Date buildAsOfDate( java.util.Date effDt, BigDecimal asOfMonth, BigDecimal asOfDay, String asOfCode ) {
		Calendar c = Calendar.getInstance();
		c.setTime( effDt );
		int year;
		int month;
		int day;

		if( "C".equals( asOfCode ) || "P".equals( asOfCode ) ) {
			year  = c.get( Calendar.YEAR );
			month = c.get( Calendar.MONTH );
			month++;
			day   = c.get( Calendar.DAY_OF_MONTH );
		} else {
			// get the current year in order to build the as-of date
			year = c.get( Calendar.YEAR );
			if( "L".equals( asOfCode ) ) {
				year--;
			}
			month = asOfMonth.intValue();
			day = asOfDay.intValue();
		}
		return java.sql.Date.valueOf( "" + year + "-" + month + "-" + day );
	}




	public static void main( String[] args ) {
		java.util.Date effDt = new java.util.Date( 119, 0, 1 );
		System.out.printf( "effDt = %s%n", effDt.toString() );
		System.out.printf( "effDt.getClass() = %s%n", effDt.getClass() );
		BigDecimal mm = new BigDecimal( "04" );
		BigDecimal dd = new BigDecimal( "01" );
		String cd = "L";
		System.out.printf( "mm = %s%n", mm.toString() );
		System.out.printf( "asOf = %s%n", BenAsOfDate.buildAsOfDate( effDt, mm, dd, cd ).toString() );
	}

}