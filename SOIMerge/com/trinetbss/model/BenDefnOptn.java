package com.trinetbss.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class BenDefnOptn implements Comparable<BenDefnOptn> {

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


	public static Comparator<BenDefnOptn> OptnComparator = new Comparator<BenDefnOptn>() {
		public int compare( BenDefnOptn obj1, BenDefnOptn obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

	public static Comparator<BenDefnOptn> PlanCovrgCdComparator = new Comparator<BenDefnOptn>() {
		public int compare( BenDefnOptn obj1, BenDefnOptn obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

}
