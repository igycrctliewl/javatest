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


	public static Comparator<BenDefnPgm> PgmComparator = new Comparator<BenDefnPgm>() {
		public int compare( BenDefnPgm obj1, BenDefnPgm obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

}
