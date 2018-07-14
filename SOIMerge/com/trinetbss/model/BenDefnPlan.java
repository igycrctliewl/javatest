package com.trinetbss.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class BenDefnPlan implements Cloneable, Comparable<BenDefnPlan> {

	public String benefitProgram;
	public java.sql.Date effdt;
	public String planType;
	public String displayPlnSeq;
	public BigDecimal minAnnualContrib;
	public BigDecimal maxAnnualContrib;
	public String waiveCoverage;
	public BigDecimal restrictEntryMm;
	public String eventRulesId;
	public String cobraPlan;
	public String hipaaPlan;
	public String collectDepben;
	public String collectFunds;
	public String showPlanType;
	public String handbookUrlId;
	public String depRuleId;


	public int compareTo( BenDefnPlan other ) {
		//returns a negative integer, zero, or a positive integer as this
		//is less than, equal to, or greater than other
		try {
			if( this.benefitProgram.compareTo( other.benefitProgram ) == 0 ) {
				if( this.effdt.compareTo( other.effdt ) == 0 ) {
					if( this.planType.compareTo( other.planType ) == 0 ) {
						return 0;
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
	public BenDefnPlan clone() {
		BenDefnPlan newPlan = new BenDefnPlan();
		newPlan.benefitProgram   = this.benefitProgram;
		newPlan.effdt            = this.effdt;
		newPlan.planType         = this.planType;
		newPlan.displayPlnSeq    = this.displayPlnSeq;
		newPlan.minAnnualContrib = this.minAnnualContrib;
		newPlan.maxAnnualContrib = this.maxAnnualContrib;
		newPlan.waiveCoverage    = this.waiveCoverage;
		newPlan.restrictEntryMm  = this.restrictEntryMm;
		newPlan.eventRulesId     = this.eventRulesId;
		newPlan.cobraPlan        = this.cobraPlan;
		newPlan.hipaaPlan        = this.hipaaPlan;
		newPlan.collectDepben    = this.collectDepben;
		newPlan.collectFunds     = this.collectFunds;
		newPlan.showPlanType     = this.showPlanType;
		newPlan.handbookUrlId    = this.handbookUrlId;
		newPlan.depRuleId        = this.depRuleId;
		return newPlan;
	}


	public static Comparator<BenDefnPlan> PlanComparator = new Comparator<BenDefnPlan>() {
		public int compare( BenDefnPlan obj1, BenDefnPlan obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

}
