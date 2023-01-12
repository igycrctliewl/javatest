import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJava {

	public static void main(String[] args) {

		String myString;

		myString = java.lang.Integer.toString(123);
		System.out.println( "toString:" + myString + ":" );

		myString = java.lang.Integer.toHexString(123);
		System.out.println( "toHexString:" + myString + ":" );

		myString = java.lang.System.getProperty( "java.version" );
		System.out.println( "java version:" + myString + ":" );

		System.out.println( "my class test:" + ClassTest.getMessage() + ":" );

		System.out.println( "java time millis:" + java.lang.System.currentTimeMillis() + ":" );

		Date javaDate = new Date();
		System.out.println( "java current date:" + javaDate.toString() + ":" );

		javaDate = new Date( 1514764800000L );
		System.out.println( "java created date:" + javaDate.toString() + ":" );
		DateFormat dateFormat = new SimpleDateFormat( "MM-dd-yyyy" );
		System.out.println( "java formatted date:" + dateFormat.format( javaDate ) + ":" );

		System.out.println( "\n --- Done --- " );

	}
}