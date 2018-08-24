
public class PretendSubmit {

	public void submitDataService( String company ) {
		System.out.println( "PretendSubmit.submitDataService" );
		synchronized( CompanyLock.getLockObj( company ) ) {
			submitData( company );
		}
	}

	public void submitData( String company ) {
		System.out.println( "PretendSubmit.submitData" );
		System.out.println( "Submitted " + company );
	}
	


	public static void main( String[] args ) {
		(new PretendSubmit()).submitDataService( "ABC" );
	}

}