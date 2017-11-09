package com.trinetbss.main;

import com.trinetbss.sql.BasEligRules;
import com.trinetbss.sql.BasEligState;
import java.sql.Date;
import java.util.HashSet;

public class PSEligRule {

	public String eligRulesId;
	public String effdtStr;
	public boolean eligUseState;
	public String eligFlagState;
	public HashSet<String> states;

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
			System.out.println( "general exception when reading BAS_ELIG_RULES rows" );
			e.printStackTrace();
		}

		if( this.eligUseState ) {
			this.states = new HashSet<>();
			BasEligState basEligState = new BasEligState();
			basEligState.runQuery( this.eligRulesId, this.effdtStr );

			try {
				while( basEligState.queryResult.next() ) {
					this.states.add( basEligState.queryResult.getString( "STATE" ) );
				}
			} catch( Exception e ) {
				System.out.println( "general exception when reading BAS_ELIG_STATE rows" );
				e.printStackTrace();
			}
			System.out.println( "state criteria contains " + this.states.size() + " rows." );
		}

	}




	/* main method for testing only */
	public static void main( String[] args ) {
		PSEligRule elig = new PSEligRule( "2123", "12-JAN-2018" );
		System.out.println( "object:eligRulesId:" + elig.eligRulesId );
		System.out.println( "object:effdtStr:" + elig.effdtStr );
		System.out.println( "object:eligFlagState:" + elig.eligFlagState );
		System.out.println( "object:eligUseState:" + elig.eligUseState );
		System.out.println( "object:states:" + elig.states );
	}

}