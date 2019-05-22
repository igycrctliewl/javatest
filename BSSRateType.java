import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BSSRateType {
   
	MEDICAL( "10" ),
	DP_MEDICAL( "15" ),
	OTHER( "OTHER" ),
	WAIVER_ALLOWANCE( "WAIVE" ),
	BEN_SUPP_EE( "BSUP1", "1", "BS_EE" ),
	BEN_SUPP_SP( "BSUP2", "2", "BS_SP" ),
	BEN_SUPP_DEP( "BSUPC", "C", "BS_DEP" ),
	BEN_SUPP_FAM( "BSUP4", "4", "BS_FAM" ),
	BEN_SUPP_NQ_ADULT( "BSUP5", "5", "BS_NQ5" ),
	BEN_SUPP_NQ_CHILD( "BSUP6", "6", "BS_NQ6" ),
	BEN_SUPP_NQ_ADULT_CHILD( "BSUP7", "7", "BS_NQ7" ),
	BEN_SUPP_FAM_NQ_ADULT( "BSUP8", "8", "BS_NQ8" );

	private final String rateIdType;
	private final String covrgCd;
	private final String rateDescr;

	BSSRateType( String rateIdType, String covrgCd, String rateDescr ) {
		this.rateIdType = rateIdType;
		this.covrgCd = covrgCd;
		this.rateDescr = rateDescr;
	}

	BSSRateType( String rateIdType ) {
		this( rateIdType, null, null );
	}

	public String rateIdType() {
		return this.rateIdType;
	}

	public String covrgCd() {
		return this.covrgCd;
	}

	public String rateDescr() {
		return this.rateDescr;
	}

	public String toString() {
		return this.rateIdType();
	}

	public static final EnumSet<BSSRateType> BenefitSupplementSet = 
		EnumSet.of( BEN_SUPP_EE, BEN_SUPP_SP, BEN_SUPP_DEP, BEN_SUPP_FAM, 
			BEN_SUPP_NQ_ADULT, BEN_SUPP_NQ_CHILD, BEN_SUPP_NQ_ADULT_CHILD, BEN_SUPP_FAM_NQ_ADULT );



	public static void main( String[] args ) {
		System.out.println( "BSSRateType.main()" );
		for( BSSRateType b : BSSRateType.values() ) {
			System.out.println( b.rateIdType() + " : " + b.covrgCd() );
		}
		System.out.println( " " );
		for( BSSRateType b : BSSRateType.BenefitSupplementSet ) {
			System.out.println( b.rateIdType() + " : " + b.covrgCd() + " : " + b.rateDescr() );
		}

		System.out.printf( "%ncreate map%n" );
		Map<String,BSSRateType> map = new HashMap<String,BSSRateType>();
		for( BSSRateType b : BSSRateType.BenefitSupplementSet ) {
			map.put( b.covrgCd(), b );
		}
		System.out.printf( "Map contains %d entries%n", map.size() );
		System.out.printf( "Lookup value for covrg cd 5: %s%n", map.get( "5" ) );
	}
}