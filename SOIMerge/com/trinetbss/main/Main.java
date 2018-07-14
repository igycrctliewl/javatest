package com.trinetbss.main;

import com.trinetbss.model.BenDefnCost;
import com.trinetbss.model.BenDefnOptn;
import com.trinetbss.model.OptnMapKey;
import com.trinetbss.dao.BenDefnOptnDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

	public static void main( String[] args ) {

		System.out.println( "Main.main()" );

		long startTime = System.currentTimeMillis();
		System.out.println( "             Start: " + new java.util.Date() );

		System.out.println( "Getting client benefit program options..." );
		List<BenDefnOptn> optn = BenDefnOptnDao.getAllOptnRows( "001AAF", "2018-04-01" );
		System.out.println( "returned rows: " + optn.size() );
		Map<OptnMapKey, BenDefnOptn> newPgmMap = new HashMap<OptnMapKey, BenDefnOptn>();

		{
			long now = System.currentTimeMillis();
			System.out.println( "  Loaded OPTN rows: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}

		// get cost rows for optn rows in the collection
		System.out.println( "Getting matching cost rows..." );
		BenDefnOptnDao.getMatchingCostRows( optn );
		{
			long now = System.currentTimeMillis();
			System.out.println( " Matched COST rows: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}


		// add unique options to the map containing the new merged benefit program
		System.out.println( "Loading map..." );
		for( BenDefnOptn o : optn ) {
			newPgmMap.put( OptnMapKey.getInstance( o.planType, o.optionType, o.benefitPlan, o.covrgCd ), o );
		}
		System.out.println( "Map contains: " + newPgmMap.size() );
		{
			long now = System.currentTimeMillis();
			System.out.println( "Mapped unique keys: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}



		// get the OPTN rows for the clone benefit program
		System.out.println( "Getting clone benefit program options..." );
		List<BenDefnOptn> clone = BenDefnOptnDao.getAllOptnRows( "113", "2018-04-01" );
		System.out.println( "returned rows: " + clone.size() );
		{
			long now = System.currentTimeMillis();
			System.out.println( "Loaded clone OPTNs: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}

		// get the cost rows for the clone benefit program
		System.out.println( "Getting clone benefit cost rows..." );
		BenDefnOptnDao.getMatchingCostRows( clone );
		{
			long now = System.currentTimeMillis();
			System.out.println( "Loaded clone COSTs: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}


		// add new options from the clone benefit program to the map
		System.out.println( "Updating map..." );
		for( BenDefnOptn o : clone ) {
			newPgmMap.put( OptnMapKey.getInstance( o.planType, o.optionType, o.benefitPlan, o.covrgCd ), o );
		}
		System.out.println( "Map contains: " + newPgmMap.size() );
		{
			long now = System.currentTimeMillis();
			System.out.println( "  Merged ben progs: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}


		System.out.println( "Get collection from map..." );
		List<BenDefnOptn> newPgmList = new ArrayList<BenDefnOptn>( newPgmMap.values() );
		// sort rows before re-sequence
		newPgmList.sort( BenDefnOptn.PlanCovrgCdComparator );

		// re-sequence options
		BigDecimal newOptionId = BigDecimal.ONE;
		BigDecimal newCostId = BigDecimal.ONE;
		String prevPlanType = "  ";
		for( BenDefnOptn o : newPgmList ) {
			if( ! prevPlanType.equals( o.planType ) ) {
				prevPlanType = o.planType;
				newOptionId = BigDecimal.ONE;
				newCostId = BigDecimal.ONE;
			}
			o.optionId = newOptionId;
			for( BenDefnCost c : o.cost ) {
				c.optionId = newOptionId;
				c.costId = newCostId;
				newCostId = newCostId.add( BigDecimal.ONE );
			}
			newOptionId = newOptionId.add( BigDecimal.ONE );
		}
		{
			long now = System.currentTimeMillis();
			System.out.println( "  Merged ben progs: " + new java.util.Date() + "  " + ( now - startTime ) + " ms"  );
		}



/*		// print result
		for( BenDefnOptn o : newPgmList ) {
			System.out.println( o.benefitProgram + "<->" + o.effdt + "<->" + o.planType + "<->" + o.benefitPlan + "<->" + o.covrgCd + "<->" + o.optionId );
			for( BenDefnCost c : o.cost ) {
				System.out.println( "         " + c.costId + "<->" + c.rateType + "<->" + c.rateTblId );
			}
		}
*/		System.out.println( "New benefit program contains " + newPgmList.size() + " rows." );

   }

}
