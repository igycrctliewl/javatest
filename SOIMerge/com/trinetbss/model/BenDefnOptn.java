package com.trinetbss.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BenDefnOptn implements Cloneable, Comparable<BenDefnOptn> {

	public String benefitProgram;
	public java.sql.Date effdt;
	public String planType;
	public BigDecimal optionId;
	public BigDecimal displayOptSeq;
	public String optionType;
	public String benefitPlan;
	public String covrgCd;
	public String optionCd;
	public BigDecimal optionLvl;
	public String dedcd;
	public String dfltOptionInd;
	public String eligRulesId;
	public String locationTblId;
	public String crossPlanType;
	public String crossBenefPlan;
	public BigDecimal coverageLimitPct;
	public String crossPlnDpndChk;
	public List<BenDefnCost> cost = new ArrayList<BenDefnCost>();


	public int compareTo( BenDefnOptn other ) {
		//returns a negative integer, zero, or a positive integer as this 
		//is less than, equal to, or greater than other
		try {
			if( this.benefitProgram.compareTo( other.benefitProgram ) == 0 ) {
				if( this.effdt.compareTo( other.effdt ) == 0 ) {
					if( this.planType.compareTo( other.planType ) == 0 ) {
						if( this.optionId.compareTo( other.optionId ) == 0 ) {
							return 0;
						} else {
							return this.optionId.compareTo( other.optionId );
						}
					} else {
						return this.planType.compareTo( other.planType );
					}
				} else {
					return this.effdt.compareTo( other.effdt );
				}
			} else {
				return this.benefitProgram.compareTo( other.benefitProgram );
			}
		} catch( Exception e ) {
			return 0;
		}
	}

	@Override
	public BenDefnOptn clone() {
		BenDefnOptn newOptn = new BenDefnOptn();
		newOptn.benefitProgram   = this.benefitProgram;
		newOptn.effdt            = this.effdt;
		newOptn.planType         = this.planType;
		newOptn.optionId         = this.optionId;
		newOptn.displayOptSeq    = this.displayOptSeq;
		newOptn.optionType       = this.optionType;
		newOptn.benefitPlan      = this.benefitPlan;
		newOptn.covrgCd          = this.covrgCd;
		newOptn.optionCd         = this.optionCd;
		newOptn.optionLvl        = this.optionLvl;
		newOptn.dedcd            = this.dedcd;
		newOptn.dfltOptionInd    = this.dfltOptionInd;
		newOptn.eligRulesId      = this.eligRulesId;
		newOptn.locationTblId    = this.locationTblId;
		newOptn.crossPlanType    = this.crossPlanType;
		newOptn.crossBenefPlan   = this.crossBenefPlan;
		newOptn.coverageLimitPct = this.coverageLimitPct;
		newOptn.crossPlnDpndChk  = this.crossPlnDpndChk;
		newOptn.cost = new ArrayList<BenDefnCost>();
		for( BenDefnCost c : this.cost ) {
			newOptn.cost.add( c.clone() );
		}
		return newOptn;
	}

	public String toCsvOutput() {
		final String QUOTE = "\"";
		final String COMMA = ",";
		String csv = new StringBuilder()
				.append(QUOTE).append( this.benefitProgram ).append(QUOTE)
				.append(COMMA)
				.append( this.effdt.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.planType ).append(QUOTE)
				.append(COMMA)
				.append( this.optionId.toString() )
				.append(COMMA)
				.append( this.displayOptSeq.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.optionType ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.benefitPlan ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.covrgCd ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.optionCd ).append(QUOTE)
				.append(COMMA)
				.append( this.optionLvl.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.dedcd ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.dfltOptionInd ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.eligRulesId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.locationTblId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.crossPlanType ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.crossBenefPlan ).append(QUOTE)
				.append(COMMA)
				.append( this.coverageLimitPct.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.crossPlnDpndChk ).append(QUOTE).toString();
		return csv;
	}

	@Override
	public String toString() {
		return super.toString() + " [" + this.optionType + "-" + this.benefitPlan + "-" + this.covrgCd + "]";
	}

	
	
	private static int getCovrgCdEq( String covrgCd ) {
		if( "1".equals( covrgCd ) )
			return 1;
		else if( "2".equals( covrgCd ) )
			return 2;
		else if( "3".equals( covrgCd ) )
			return 3;
		else if( "C".equals( covrgCd ) )
			return 3;
		else if( "4".equals( covrgCd ) )
			return 4;
		else if( "F".equals( covrgCd ) )
			return 4;
		else if( "5".equals( covrgCd ) )
			return 5;
		else if( "6".equals( covrgCd ) )
			return 6;
		else if( "7".equals( covrgCd ) )
			return 7;
		else if( "8".equals( covrgCd ) )
			return 8;
		else if( "9".equals( covrgCd ) )
			return 9;
		else if( "12".equals( covrgCd ) )
			return 12;
		else if( "14".equals( covrgCd ) )
			return 14;
		else if( "20".equals( covrgCd ) )
			return 20;
		else if( "21".equals( covrgCd ) )
			return 21;
		else if( "22".equals( covrgCd ) )
			return 22;
		else if( "23".equals( covrgCd ) )
			return 23;
		else if( "24".equals( covrgCd ) )
			return 24;
		else if( "25".equals( covrgCd ) )
			return 25;
		else if( "26".equals( covrgCd ) )
			return 26;
		else if( "81".equals( covrgCd ) )
			return 81;
		else if( "82".equals( covrgCd ) )
			return 82;
		else if( "83".equals( covrgCd ) )
			return 83;
		else if( "84".equals( covrgCd ) )
			return 84;
		else if( "85".equals( covrgCd ) )
			return 85;
		else if( "86".equals( covrgCd ) )
			return 86;

		return 0;
	}


	public static Comparator<BenDefnOptn> OptnComparator = new Comparator<BenDefnOptn>() {
		public int compare( BenDefnOptn obj1, BenDefnOptn obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

	public static Comparator<BenDefnOptn> PlanCovrgCdComparator = new Comparator<BenDefnOptn>() {
		public int compare( BenDefnOptn obj1, BenDefnOptn obj2 ) {
			//returns a negative integer, zero, or a positive integer as obj1 
			//is less than, equal to, or greater than obj2
			try {
				if( obj1.planType.compareTo( obj2.planType ) == 0 ) {
					if( obj1.benefitPlan.compareTo( obj2.benefitPlan ) == 0 ) {
						if( obj1.covrgCd.compareTo( obj2.covrgCd ) == 0 ) {
							return 0;
						} else {
							return BenDefnOptn.getCovrgCdEq( obj1.covrgCd ) - BenDefnOptn.getCovrgCdEq( obj2.covrgCd );
						}
					} else {
						return obj1.benefitPlan.compareTo( obj2.benefitPlan );
					}
				} else {
					return obj1.planType.compareTo( obj2.planType );
				}
			} catch( Exception e ) {
				return 0;
			}
		}
	};

}
