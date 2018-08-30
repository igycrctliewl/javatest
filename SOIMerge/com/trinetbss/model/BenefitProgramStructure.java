package com.trinetbss.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trinetbss.dao.BenDefnPgmDao;
import com.trinetbss.dao.BenDefnPlanDao;
import com.trinetbss.dao.BenDefnOptnDao;
import com.trinetbss.dao.BenDefnCostDao;
import com.trinetbss.io.FileWriter;

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
	public BenefitProgramStructure merge( BenefitProgramStructure mergeSrc ) {
		System.out.println( "BenefitProgramStructure.merge()" );
		// merge PLAN
		// first, create a map of the PLAN rows for this object and load from this instance
		Map<String,BenDefnPlan> planMap = new HashMap<String,BenDefnPlan>();
		for( BenDefnPlan p : this.plans ) {
			planMap.put( p.planType, p );
		}

		// second, add rows from the merge source that are not found in this benefit program structure instance
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

		// third, get Collection of values from map and replace PLAN rows in this instance
		this.plans = new ArrayList<BenDefnPlan>( planMap.values() );

		// merge OPTN
		// first, create a map of the OPTN rows for this object and load from this instance
		Map<OptnMapKey,BenDefnOptn> optnMap = new HashMap<OptnMapKey,BenDefnOptn>();
		for( BenDefnOptn o : this.optns ) {
			optnMap.putIfAbsent( OptnMapKey.getInstance( o.planType, o.optionType, o.benefitPlan, o.covrgCd ), o );
		}

		// second, add rows from the merge source that are not found in this benefit program structure instance
		for( BenDefnOptn o : mergeSrc.optns ) {
			if( optnMap.containsKey( OptnMapKey.getInstance( o.planType, o.optionType, o.benefitPlan, o.covrgCd ) ) ) {
				// leave the existing row alone
			} else {
				BenDefnOptn newRow = o.clone();
				newRow.benefitProgram = this.pgm.benefitProgram;
				newRow.effdt = this.pgm.effdt;
				for( BenDefnCost c : newRow.cost ) {
					c.benefitProgram = this.pgm.benefitProgram;
					c.effdt = this.pgm.effdt;
				}
				optnMap.put( OptnMapKey.getInstance( newRow.planType, newRow.optionType, newRow.benefitPlan, newRow.covrgCd ), newRow );
			}
		}

		// third, get Collection of values from map and replace OPTN rows in this instance
		this.optns = new ArrayList<BenDefnOptn>( optnMap.values() );


		// rebuild List<COST> from OPTN collection
		this.costs = new ArrayList<BenDefnCost>();
		for( BenDefnOptn o : this.optns ) {
			for( BenDefnCost c : o.cost ) {
				this.costs.add( c );
			}
		}
		
		
		return this;
	}


	public void writeBenProgStrucToFile() {
		try {
			FileWriter fw = new FileWriter( "C:\\temp\\" + this.pgm.benefitProgram + ".txt" );
			fw.writeLine( this.pgm.toCsvOutput() );
			//for( BenDefnPlan p : this.plans ) {
			//	fw.writeLine( p.toCsvOutput() );
			//}
			//for( BenDefnOptn o : this.optns ) {
			//	fw.writeLine( o.toCsvOutput() );
			//}
			//for( BenDefnCost c : this.costs ) {
			//	fw.writeLine( c.toCsvOutput() );
			//}
			fw.flush();
			fw.close();
		} catch( IOException e ) {
			System.out.println( "An output file was requested, but could not be produced.  See Java messages for details." );
			e.printStackTrace();
		}
	}


	public void writeSQLInsertStatements() {
		try {
			FileWriter fw = new FileWriter( "C:\\temp\\pgmPlan.sql" );
			fw.writeLine( this.pgm.toInsertStatement() );
			for( BenDefnPlan p : this.plans ) {
				fw.writeLine( p.toInsertStatement() );
			}
			//for( BenDefnOptn o : this.optns ) {
			//	fw.writeLine( o.toCsvOutput() );
			//}
			//for( BenDefnCost c : this.costs ) {
			//	fw.writeLine( c.toCsvOutput() );
			//}
			fw.flush();
			fw.close();
		} catch( IOException e ) {
			System.out.println( "An output file was requested, but could not be produced.  See Java messages for details." );
			e.printStackTrace();
		}
	}

	
	
	public String toString() {
		return super.toString() + " [" + this.pgm.benefitProgram + "]";
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
		struc.writeSQLInsertStatements();
		
		System.out.println( "BenefitProgramStructure.main finished" );
	}
}
