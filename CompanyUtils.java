

public class CompanyUtils {

   private static String lastCompany;

	public static void setLastCompany( String lastCompany ) {
		CompanyUtils.lastCompany = lastCompany;
	}

	public static String getNextCompany() {
		int value = Integer.parseInt( lastCompany, 36 );
		value++;
		CompanyUtils.lastCompany = Integer.toString( value, 36 ).toUpperCase();
		return CompanyUtils.lastCompany;
	}

	public static void main( String[] args ) {
		CompanyUtils.setLastCompany( args[0] );
		System.out.println( "next company is: " + CompanyUtils.getNextCompany() );
		System.out.println( "next company is: " + CompanyUtils.getNextCompany() );
		System.out.println( "next company is: " + CompanyUtils.getNextCompany() );
		}
	}
}