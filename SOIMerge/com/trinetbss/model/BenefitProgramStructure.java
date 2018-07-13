package com.trinetbss.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trinetbss.dao.BenDefnPgmDao;
import com.trinetbss.dao.BenDefnPlanDao;
import com.trinetbss.dao.BenDefnOptnDao;
import com.trinetbss.dao.BenDefnCostDao;

public class BenefitProgramStructure {
	private BenDefnPgm pgm;
	private List<BenDefnPlan> plans;
	private List<BenDefnOptn> optns;
	private List<BenDefnCost> costs;
	
	public BenefitProgramStructure( String benefitProgram, String effdtStr ) {
		this.pgm = new BenDefnPgm();
		this.pgm.benefitProgram = benefitProgram;
		this.pgm.effdt = java.sql.Date.valueOf( effdtStr );
		this.pgm = BenDefnPgmDao.getPgmRow( this.pgm ); 
		this.plans = BenDefnPlanDao.getAllPlanRows( this.pgm );
		this.optns = BenDefnOptnDao.getAllOptnRows( this.pgm );
		this.costs = BenDefnCostDao.getAllCostRows( this.pgm );
	}

	/**
	 * This method will pair the COST rows in the benefit program structure with the
	 * corresponding OPTN rows.  The OPTN row object will be updated with the List
	 * of COST row objects.
	 * <p>
	 * No parameters are required since the method will act on the List objects already constructed
	 */
	public void matchCostWithOptn() {
		Map<Long,List<BenDefnCost>> costMap = new HashMap<Long,List<BenDefnCost>>();
		for( BenDefnCost c : costs ) {
			if( costMap.containsKey( c.optionId.longValue() ) ) {
				costMap.get( c.optionId.longValue() ).add( c );
			} else {
				costMap.put( c.optionId.longValue(), new ArrayList<BenDefnCost>() );
				costMap.get( c.optionId.longValue() ).add( c );
			}
		}
	}
	
	public String toString() {
		return super.toString() + "-" + this.pgm.benefitProgram;
	}
	
	
	public static void main( String[] args ) {
		BenefitProgramStructure struc = new BenefitProgramStructure( "003", "2018-01-01" );
		System.out.println( struc );
		struc.matchCostWithOptn();
	}
}
