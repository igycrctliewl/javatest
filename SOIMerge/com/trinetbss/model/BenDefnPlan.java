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
				.append(QUOTE).append( this.displayPlnSeq ).append(QUOTE)
				.append(COMMA)
				.append( this.minAnnualContrib.toString() )
				.append(COMMA)
				.append( this.maxAnnualContrib.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.waiveCoverage ).append(QUOTE)
				.append(COMMA)
				.append( this.restrictEntryMm.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.eventRulesId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.cobraPlan ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.hipaaPlan ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.collectDepben ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.collectFunds ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.showPlanType ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.handbookUrlId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.depRuleId ).append(QUOTE).toString();
		return csv;
	}

	public String toInsertStatement() {
		final String QUOTE = "'";
		final String COMMA = ",";
		String sql = new StringBuilder()
				.append( "INSERT INTO PS_BEN_DEFN_PLAN (" )
				.append( " BENEFIT_PROGRAM" )
				.append(COMMA).append( " EFFDT" )
				.append(COMMA).append( " PLAN_TYPE" )
				.append(COMMA).append( " DISPLAY_PLN_SEQ" )
				.append(COMMA).append( " MIN_ANNUAL_CONTRIB" )
				.append(COMMA).append( " MAX_ANNUAL_CONTRIB" )
				.append(COMMA).append( " WAIVE_COVERAGE" )
				.append(COMMA).append( " RESTRICT_ENTRY_MM" )
				.append(COMMA).append( " EVENT_RULES_ID" )
				.append(COMMA).append( " COBRA_PLAN" )
				.append(COMMA).append( " HIPAA_PLAN" )
				.append(COMMA).append( " COLLECT_DEPBEN" )
				.append(COMMA).append( " COLLECT_FUNDS" )
				.append(COMMA).append( " SHOW_PLAN_TYPE" )
				.append(COMMA).append( " HANDBOOK_URL_ID" )
				.append(COMMA).append( " DEP_RULE_ID" )
				.append(" ) VALUES ( ")
				.append(QUOTE).append( this.benefitProgram ).append(QUOTE)
				.append(COMMA)
				.append("DATE ").append(QUOTE).append( this.effdt.toString() ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.planType ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.displayPlnSeq ).append(QUOTE)
				.append(COMMA)
				.append( this.minAnnualContrib.toString() )
				.append(COMMA)
				.append( this.maxAnnualContrib.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.waiveCoverage ).append(QUOTE)
				.append(COMMA)
				.append( this.restrictEntryMm.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.eventRulesId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.cobraPlan ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.hipaaPlan ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.collectDepben ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.collectFunds ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.showPlanType ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.handbookUrlId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.depRuleId ).append(QUOTE)
				.append(" );").toString();
		return sql;
	}

	@Override
	public String toString() {
		return super.toString() + " [" + this.planType + "]";
	}

	public static Comparator<BenDefnPlan> PlanComparator = new Comparator<BenDefnPlan>() {
		public int compare( BenDefnPlan obj1, BenDefnPlan obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

}
