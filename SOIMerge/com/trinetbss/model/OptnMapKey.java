package com.trinetbss.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a structure for building a unique OPTN row key.  The combination of PLAN_TYPE,
 * OPTION_TYPE, BENEFIT_PLAN and COVRG_CD constitute a unique option.  This key can be used as the
 * key to a Map of option rows.
 * @author mbrothers
 *
 */
public class OptnMapKey {

	private String planType;
	private String optionType;
	private String benefitPlan;
	private String covrgCd;

	@SuppressWarnings("rawtypes")
	private static Map<String,Map> planTypeMap = new HashMap<String,Map>();

	/**
	 * This static method ensures that one and only one OptnMapKey object will be created for any
	 * given combination of key values.  The object returned by this method can be used as a key
	 * to a Map of OptnMapKey keys to BenDefnOptn values.
	 * @param planType
	 * @param optionType
	 * @param benefitPlan
	 * @param covrgCd
	 * @return the previously created instance of OptnMapKey matching this combination of keys, or a new
	 * instance of OptnMapKey if this combination of keys was never used before.
	 */
	public static OptnMapKey getInstance( String planType, String optionType, String benefitPlan, String covrgCd ) {
		if( OptnMapKey.planTypeMap.containsKey( planType ) ) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Map<String,Map> optionMap = OptnMapKey.planTypeMap.get( planType );
			if( optionMap.containsKey( optionType ) ) {
				@SuppressWarnings({ "unchecked", "rawtypes" })
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
				@SuppressWarnings("rawtypes")
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
			@SuppressWarnings("rawtypes")
			Map<String,Map> covrgCdMap = new HashMap<String,Map>();
			covrgCdMap.put( covrgCd, benefPlanMap );
			@SuppressWarnings("rawtypes")
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
