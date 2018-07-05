package com.trinetbss.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class OptnMapKey {

	private String planType;
	private String optionType;
	private String benefitPlan;
	private String covrgCd;

	private static Map<String,Map> planTypeMap = new HashMap<String,Map>();

	/* This nested map structure will make sure that a key object is only created once for a particular
      set of values.  This way, the OptnMapKey can be used as a key for the map of OPTN rows*/
	public static OptnMapKey getInstance( String planType, String optionType, String benefitPlan, String covrgCd ) {
		if( OptnMapKey.planTypeMap.containsKey( planType ) ) {
			@SuppressWarnings("unchecked")
			Map<String,Map> optionMap = OptnMapKey.planTypeMap.get( planType );
			if( optionMap.containsKey( optionType ) ) {
				@SuppressWarnings("unchecked")
				Map<String,Map> covrgCdMap = optionMap.get( optionType );
				if( covrgCdMap.containsKey( covrgCd ) ) {
					@SuppressWarnings("unchecked")
					Map<String,OptnMapKey> benefPlanMap = covrgCdMap.get( covrgCd );
					if( benefPlanMap.containsKey( benefitPlan ) ) {
						return benefPlanMap.get( benefitPlan );
					} else {
						OptnMapKey newKey = new OptnMapKey( planType, optionType, benefitPlan, covrgCd );
						benefPlanMap.put( benefitPlan, newKey );
						covrgCdMap.put( covrgCd, benefPlanMap );
						optionMap.put( optionType, covrgCdMap );
						OptnMapKey.planTypeMap.put( planType, optionMap );
						return newKey;
					}
				} else {
					OptnMapKey newKey = new OptnMapKey( planType, optionType, benefitPlan, covrgCd );
					Map<String,OptnMapKey> benefPlanMap = new HashMap<String,OptnMapKey>();
					benefPlanMap.put( benefitPlan, newKey );
					covrgCdMap.put( covrgCd, benefPlanMap );
					optionMap.put( optionType, covrgCdMap );
					OptnMapKey.planTypeMap.put( planType, optionMap );
					return newKey;
				}
			} else {
				OptnMapKey newKey = new OptnMapKey( planType, optionType, benefitPlan, covrgCd );
				Map<String,OptnMapKey> benefPlanMap = new HashMap<String,OptnMapKey>();
				benefPlanMap.put( benefitPlan, newKey );
				Map<String,Map> covrgCdMap = new HashMap<String,Map>();
				covrgCdMap.put( covrgCd, benefPlanMap );
				optionMap.put( optionType, covrgCdMap );
				OptnMapKey.planTypeMap.put( planType, optionMap );
				return newKey;
			}
		} else {
			OptnMapKey newKey = new OptnMapKey( planType, optionType, benefitPlan, covrgCd );
			Map<String,OptnMapKey> benefPlanMap = new HashMap<String,OptnMapKey>();
			benefPlanMap.put( benefitPlan, newKey );
			Map<String,Map> covrgCdMap = new HashMap<String,Map>();
			covrgCdMap.put( covrgCd, benefPlanMap );
			Map<String,Map> optionMap = new HashMap<String,Map>();
			optionMap.put( optionType, covrgCdMap );
			OptnMapKey.planTypeMap.put( planType, optionMap );
			return newKey;
		}
	}


	private OptnMapKey( String planType, String optionType, String benefitPlan, String covrgCd ) {
		this.planType    = planType;
		this.optionType  = optionType;
		this.benefitPlan = benefitPlan;
		this.covrgCd     = covrgCd;
	}

	public String toString() {
		return this.planType + "-" + this.optionType + "-" + this.benefitPlan + "-" + this.covrgCd;
	}



	public static void main( String[] args ) {
		OptnMapKey key1 = OptnMapKey.getInstance( "10", "W", " ", " " );
		System.out.println( key1 );
		OptnMapKey key2 = OptnMapKey.getInstance( "10", "W", " ", " " );
		System.out.println( key2 );
		System.out.println( key1 == key2 );
	}
}
