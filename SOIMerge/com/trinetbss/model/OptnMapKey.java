package com.trinetbss.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class OptnMapKey implements Comparable<OptnMapKey> {

	private String planType;
	private String optionType;
	private String benefitPlan;
	private String covrgCd;

	public OptnMapKey( String planType, String optionType, String benefitPlan, String covrgCd ) {
		this.planType    = planType;
		this.optionType  = optionType;
      this.benefitPlan = benefitPlan;
      this.covrgCd     = covrgCd;
	}

	public String toString() {
		return this.planType + "-" + this.optionType + "-" + this.benefitPlan + "-" + this.covrgCd;
	}

	public int compareTo( OptnMapKey other ) {
		//returns a negative integer, zero, or a positive integer as this 
		//is less than, equal to, or greater than other
		try {
			if( this.planType.compareTo( other.planType ) == 0 ) {
				if( this.optionType.compareTo( other.optionType ) == 0 ) {
					if( this.benefitPlan.compareTo( other.benefitPlan ) == 0 ) {
						if( this.covrgCd.compareTo( other.covrgCd ) == 0 ) {
							return 0;
						} else {
							return this.covrgCd.compareTo( other.covrgCd );
						}
					} else {
						return this.benefitPlan.compareTo( other.benefitPlan );
					}
				} else {
					return this.optionType.compareTo( other.optionType );
				}
			} else {
				return this.planType.compareTo( other.planType );
			}
		} catch( Exception e ) {
			return -1;
		}
	}


	public static void main( String[] args ) {
		OptnMapKey key1 = new OptnMapKey( "10", "W", " ", " " );
		System.out.println( key1 );
		OptnMapKey key2 = new OptnMapKey( "10", "W", " ", " " );
		System.out.println( key1.compareTo( key2 ) );
	}
}
