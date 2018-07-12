
import java.util.HashMap;
import java.util.Map;

public class CompanyLock {
	private String companyCode;

	private CompanyLock( String companyCode ) {
		this.companyCode = companyCode;
	}

	public String toString() {
		return super.toString() + "-" + this.companyCode;
	}


	private static Map<String,CompanyLock> lockObjMap = new HashMap<>();

	public static CompanyLock getLockObj( String companyCode ) {
		if( lockObjMap.containsKey( companyCode ) ) {
			System.out.println( "return lock object for " + companyCode );
			return lockObjMap.get( companyCode );
		} else {
			System.out.println( "create and return lock object for " + companyCode );
			CompanyLock cLock = new CompanyLock( companyCode );
			lockObjMap.put( companyCode, cLock );
			return cLock;
		}
	}


	public static void main( String[] args ) {
		CompanyLock lock = CompanyLock.getLockObj( "G9P" );
		System.out.println( lock );
		lock = CompanyLock.getLockObj( "4M7" );
		System.out.println( lock );
		lock = CompanyLock.getLockObj( "G49" );
		System.out.println( lock );
		lock = CompanyLock.getLockObj( "G9P" );
		System.out.println( lock );
		lock = CompanyLock.getLockObj( "OVO" );
		System.out.println( lock );
		lock = CompanyLock.getLockObj( "G49" );
		System.out.println( lock );
	}


	public static void mainBad( String[] args ) {
		CompanyLock lock = new CompanyLock( "G9P" );
		System.out.println( lock );
		lock = new CompanyLock( "4M7" );
		System.out.println( lock );
		lock = new CompanyLock( "G49" );
		System.out.println( lock );
		lock = new CompanyLock( "G9P" );
		System.out.println( lock );
		lock = new CompanyLock( "OVO" );
		System.out.println( lock );
		lock = new CompanyLock( "G49" );
		System.out.println( lock );
	}
}
