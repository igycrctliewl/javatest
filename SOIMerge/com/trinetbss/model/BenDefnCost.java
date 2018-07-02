package com.trinetbss.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class BenDefnCost implements Comparable<BenDefnCost> {

	public String benefitProgram;
	public java.sql.Date effdt;
	public String planType;
	public BigDecimal optionId;
	public BigDecimal costId;
	public String costType;
	public String erncd;
	public String rateType;
	public String rateTblId;
	public String calcRulesId;


	public int compareTo( BenDefnCost other ) {
		//returns a negative integer, zero, or a positive integer as this 
		//is less than, equal to, or greater than other
		try {
			if( this.benefitProgram.compareTo( other.benefitProgram ) == 0 ) {
				if( this.effdt.compareTo( other.effdt ) == 0 ) {
					if( this.planType.compareTo( other.planType ) == 0 ) {
						if( this.optionId.compareTo( other.optionId ) == 0 ) {
							if( this.costId.compareTo( other.costId ) == 0 ) {
								return 0;
							} else {
								return this.costId.compareTo( other.costId );
							}
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


	public static Comparator<BenDefnCost> CostComparator = new Comparator<BenDefnCost>() {
		public int compare( BenDefnCost obj1, BenDefnCost obj2 ) {
			return obj1.compareTo( obj2 );
		}
	};

}
