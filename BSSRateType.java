import java.util.EnumSet;

public enum BSSRateType {
   
	MEDICAL( "10", null ),
	DP_MEDICAL( "15", null ),
	OTHER( "OTHER", null ),
	WAIVER_ALLOWANCE( "WAIVE", null ),
	BEN_SUPP_EE( "BSUP1", "1" ),
	BEN_SUPP_SP( "BSUP2", "2" ),
	BEN_SUPP_DEP( "BSUPC", "C" ),
	BEN_SUPP_FAM( "BSUP4", "4" ),
	BEN_SUPP_NQ_ADULT( "BSUP5", "5" ),
	BEN_SUPP_NQ_CHILD( "BSUP6", "6" ),
	BEN_SUPP_NQ_ADULT_CHILD( "BSUP7", "7" ),
	BEN_SUPP_FAM_NQ_ADULT( "BSUP8", "8" );

	private final String rateIdType;
	private final String covrgCd;

	BSSRateType( String rateIdType, String covrgCd ) {
		this.rateIdType = rateIdType;
		this.covrgCd = covrgCd;
	}

	public String rateIdType() {
		return this.rateIdType;
	}

	public String covrgCd() {
		return this.covrgCd;
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
			System.out.println( b.rateIdType() + " : " + b.covrgCd() );
		}
   }   
}