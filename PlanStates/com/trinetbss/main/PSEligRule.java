package com.trinetbss.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.trinetbss.sql.BasEligRules;
import com.trinetbss.sql.BasEligState;

public class PSEligRule {

	public String eligRulesId;
	public String effdtStr;
	public boolean eligUseState;
	public String eligFlagState;
	public Set<String> states;

	public PSEligRule( String eligRulesId, String effdtStr ) {
		BasEligRules rules = new BasEligRules();
		rules.runQuery( eligRulesId, effdtStr );

		try {
			while( rules.queryResult.next() ) {
				this.eligRulesId = rules.queryResult.getString( "ELIG_RULES_ID" );
				this.effdtStr = rules.queryResult.getString( "EFFDT_STR" );
				this.eligFlagState = rules.queryResult.getString( "ELIG_FLG_STATE" );
				this.eligUseState = "Y".equals( rules.queryResult.getString( "ELIG_USE_STATE" ) );
			}
		} catch( Exception e ) {
			System.out.println( "PSEligRule constructor => general exception when reading BAS_ELIG_RULES rows" );
			e.printStackTrace();
		}

		if( this.eligUseState ) {
			// Depending on the eligFlagState value, the state list is Eligible or Ineligible states.
			// If Ineligible, create a set of all states (ineligible ones will be removed).
			// If Eligible, create an empty set (eligible states will be added).
			if( "I".equals( this.eligFlagState ) ) {
				this.states = new HashSet<>(Arrays.asList( Constants.ALL_STATE_VALUES ));
			} else {
				this.states = new HashSet<>();
			}
			BasEligState basEligState = new BasEligState();
			basEligState.runQuery( this.eligRulesId, this.effdtStr );

			try {
				while( basEligState.queryResult.next() ) {
					// if the flag was Ineligible, remove this state from the list of all states
					if( "I".equals( this.eligFlagState ) ) {
						this.states.remove( basEligState.queryResult.getString( "STATE" ) );
					} else {
					// otherwise, add this state to the eligible list
						this.states.add( basEligState.queryResult.getString( "STATE" ) );
					}
				}
			} catch( Exception e ) {
				System.out.println( "PSEligRule constructor => general exception when reading BAS_ELIG_STATE rows" );
				e.printStackTrace();
			}
			System.out.println( "PSEligRule constructor => state criteria contains " + this.states.size() + " rows." );
		}

	}




	/* main method for testing only */
	public static void main( String[] args ) {
		PSEligRule elig = new PSEligRule( "2124", "12-JAN-2018" );
		System.out.println( "PSEligRule.main => object:eligRulesId:" + elig.eligRulesId );
		System.out.println( "PSEligRule.main => object:effdtStr:" + elig.effdtStr );
		System.out.println( "PSEligRule.main => object:eligFlagState:" + elig.eligFlagState );
		System.out.println( "PSEligRule.main => object:eligUseState:" + elig.eligUseState );
		System.out.println( "PSEligRule.main => object:states:" + elig.states );
	}

}