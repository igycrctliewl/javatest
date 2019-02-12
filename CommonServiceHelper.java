
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonServiceHelper {

	public static void main( String[] args ) {
		//Date d = new Date( 1577491200000L );   //Sat Dec 28 00:00:00 UTC 2019
		//System.out.println( d );
		//System.out.println( d.getTime() );
		formatStringToDateTest();
	}



	public static void formatStringToDateTest() {
		String date = "01-01-2019";
		String format = "dd-MM-yyyy";
		String outFormat = "MM/dd/yyyy";
		String outDate = "01/01/2019";

		Date actualResult = CommonServiceHelper.formatStringToDate(date, format);

		System.out.println( actualResult + " - " + formatDateToString( actualResult, outFormat ) );
		//assertEquals(actualResult.getTime(), actualResult.getTime());

		date = "2019/12/28";
		format = "yyyy/MM/dd";
		outFormat = "MMM dd yyyy";
		outDate = "Dec 28 2019";

		actualResult = CommonServiceHelper.formatStringToDate(date, format);

		System.out.println( actualResult + " - " + formatDateToString( actualResult, outFormat ) );
		//assertEquals(actualResult.getTime(), actualResult.getTime());

		date = "2019/JAN/28";
		format = "yyyy/MM/dd";

		actualResult = CommonServiceHelper.formatStringToDate(date, format);

		System.out.println( actualResult );
		//assertNull(actualResult);
	}



	/**
	 * This method is for formatting date into a specific format.
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date formatStringToDate(String date, String format) {
		Date formattedDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			formattedDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			//CommonUtils.logExceptions(e, logger, "", "");
		}
		return formattedDate;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String formatedString = formatter.format(date);
		return formatedString;
	}


}