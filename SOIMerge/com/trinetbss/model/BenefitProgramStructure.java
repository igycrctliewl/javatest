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
		System.out.println( "Loading " + benefitProgram + " PGM rows" );
		this.pgm = BenDefnPgmDao.getPgmRow( this.pgm );
		System.out.println( "Loading " + benefitProgram + " PLAN rows" );
		this.plans = BenDefnPlanDao.getAllPlanRows( this.pgm );
		System.out.println( "Loading " + benefitProgram + " OPTN rows" );
		this.optns = BenDefnOptnDao.getAllOptnRows( this.pgm );
		System.out.println( "Loading " + benefitProgram + " COST rows" );
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
		// create Map of lists of cost rows with OPTION_ID as key
		Map<Long,List<BenDefnCost>> costMap = new HashMap<Long,List<BenDefnCost>>();
		for( BenDefnCost c : costs ) {
			if( costMap.containsKey( c.optionId.longValue() ) ) {
				costMap.get( c.optionId.longValue() ).add( c );
			} else {
				costMap.put( c.optionId.longValue(), new ArrayList<BenDefnCost>() );
				costMap.get( c.optionId.longValue() ).add( c );
			}
		}

		// for each OPTN row, find the matching list of COST rows in the map and link to OPTN object
		for( BenDefnOptn o : optns ) {
			if( costMap.containsKey( o.optionId.longValue() ) ) {
				o.cost = costMap.get( o.optionId.longValue() );
			}
		}
	}

	/**
	 * Merge the source benefit structure into this benefit structure
	 * @param mergeSrc the source benefit structure to be merged into this object
	 */
	public void merge( BenefitProgramStructure mergeSrc ) {
		// merge PLAN
		// first, create a map of the PLAN rows for this object
		Map<String,BenDefnPlan> planMap = new HashMap<String,BenDefnPlan>();
		for( BenDefnPlan p : this.plans ) {
			planMap.put( p.planType, p );
		}

		// second, add rows from the source that are not found in this benefit program structure
		for( BenDefnPlan p : mergeSrc.plans ) {
			if( planMap.containsKey( p.planType ) ) {
				// leave the existing row alone
			} else {
				BenDefnPlan newRow = p.clone();
				newRow.benefitProgram = this.pgm.benefitProgram;
				newRow.effdt = this.pgm.effdt;
				planMap.put( newRow.planType, newRow );
			}
		}

		// third, get Collection of values from map and replace this collection of PLAN rows


		//merge OPTN
		//if desired, rebuild List<COST> from OPTN collection
	}

	public String toString() {
		return super.toString() + "-" + this.pgm.benefitProgram;
	}


	public static void main( String[] args ) {
		/* this test was for a benefit program where each OPTN row had two or more COST rows
		BenefitProgramStructure struc = new BenefitProgramStructure( "003", "2018-01-01" );  */

		BenefitProgramStructure struc = new BenefitProgramStructure( "001AAF", "2018-04-01" );
		System.out.println( struc );
		struc.matchCostWithOptn();

		BenefitProgramStructure clone = new BenefitProgramStructure( "113", "2018-04-01" );
		System.out.println( clone );
		clone.matchCostWithOptn();

		struc.merge( clone );
	}
}
