
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BenefitClassServiceImpl {

	// save a map of company codes to saved data objects
	private Map< String, CompanyClass > savedCompanyClass;

	public BenefitClassServiceImpl() {
		this.savedCompanyClass = new HashMap< String, CompanyClass >();
	}


	public String generateClassCode( String company, String group ) {
		System.out.println( "BenefitClassServiceImpl.generateClassCode" );

		CompanyClass cc = this.savedCompanyClass.get( company );
		if( cc == null ) {
			cc = new CompanyClass();
			this.savedCompanyClass.put( company, cc );
		}

		String newEligConfig1 = "DUMY";
		// see if ELIG_CONFIG1 was saved previously, either in this saved map...
		newEligConfig1 = cc.savedMappings.get( group );
		if( newEligConfig1 == null ) {
			// ...or in the database
			newEligConfig1 = "DBDB";
		}

		cc.savedMappings.put( group, newEligConfig1 );
		return newEligConfig1;
	}


	public List<String> generateAllClassCodes( String company, List<String> groups) {
		// determine the first character of the new class code
		char indexChar = 0;
		return groups;
	}


	class CompanyClass {
		// save a map of BenefitPrograms to Benefit Class codes (elig-config1)
		Map< String, String > savedMappings;
		Character lastMaxChar;

		CompanyClass() {
			System.out.println( "CompanyClass constructor" );
			savedMappings = new HashMap< String, String >();
			lastMaxChar = null;
		}
	}




   public static void main( String[] args ) {
		BenefitClassServiceImpl impl = new BenefitClassServiceImpl();
		System.out.println( "Call method:" + impl.generateClassCode( "G9P", "UV6" ) );
	}

}
