
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CompanyLock {
	private String companyCode;

	private CompanyLock( String companyCode ) {
		this.companyCode = companyCode;
	}

	public String toString() {
		return super.toString() + "-" + this.companyCode;
	}


	private static Map<String,CompanyLock> lockObjMap = new HashMap<>();

	public synchronized static CompanyLock getLockObj( String companyCode ) {
		// is there already a String key that is equals() to this key?
		Set<String> keys = lockObjMap.keySet();
		for( String s : keys ) {
			if( s.equals( companyCode ) ) {
				return lockObjMap.get( s );
			}
		}
		// there was no match so create a new lock object and add to the map
		System.out.println( "create and return lock object for " + companyCode );
		CompanyLock cLock = new CompanyLock( companyCode );
		lockObjMap.put( companyCode, cLock );
		return cLock;
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


}
