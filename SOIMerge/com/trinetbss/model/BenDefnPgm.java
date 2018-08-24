package com.trinetbss.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class BenDefnPgm implements Comparable<BenDefnPgm> {

	public String benefitProgram;
	public java.sql.Date effdt;
	public String descr;
	public String pfClient;
	public String descrShort;
	public String effStatus;
	public String programType;
	public String fsaRunId;
	public BigDecimal fsaMaxAnnlPldg;
	public String currencyCd;
	public BigDecimal dfltExpirationDd;
	public String dfltCreditRllovr;
	public BigDecimal cobraSurcharge;
	public BigDecimal cobraDisablSurcg;
	public String fmlaPlanId;
	public String showCredit;
	public String costFrequency;
	public String HandbookUrlId;
	public String incldCnslstx;
	public BigDecimal cobraContactId;
	public String basShowErCosts;
	public String basShowTaxImpct;


	public int compareTo( BenDefnPgm other ) {
		//returns a negative integer, zero, or a positive integer as this 
		//is less than, equal to, or greater than other
		try {
			if( this.benefitProgram.compareTo( other.benefitProgram ) == 0 ) {
				if( this.effdt.compareTo( other.effdt ) == 0 ) {
					return 0;
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

	public String toCsvOutput() {
		final String QUOTE = "\"";
		final String COMMA = ",";
		String csv = new StringBuilder()
				.append(QUOTE).append( this.benefitProgram ).append(QUOTE)
				.append(COMMA)
				.append( effdt.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.descr ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.pfClient ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.descrShort ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.effStatus ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.programType ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.fsaRunId ).append(QUOTE)
				.append(COMMA)
				.append( fsaMaxAnnlPldg.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.currencyCd ).append(QUOTE)
				.append(COMMA)
				.append( dfltExpirationDd.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.dfltCreditRllovr ).append(QUOTE)
				.append(COMMA)
				.append( cobraSurcharge.toString() )
				.append(COMMA)
				.append( cobraDisablSurcg.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.fmlaPlanId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.showCredit ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.costFrequency ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.HandbookUrlId ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.incldCnslstx ).append(QUOTE)
				.append(COMMA)
				.append( cobraContactId.toString() )
				.append(COMMA)
				.append(QUOTE).append( this.basShowErCosts ).append(QUOTE)
				.append(COMMA)
				.append(QUOTE).append( this.basShowTaxImpct ).append(QUOTE).toString();
		return csv;
	}

	public static Comparator<BenDefnPgm> PgmComparator = new Comparator<BenDefnPgm>() {
		public int compare( BenDefnPgm obj1, BenDefnPgm obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

}
