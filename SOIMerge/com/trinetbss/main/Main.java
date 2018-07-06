package com.trinetbss.main;

import com.trinetbss.model.BenDefnCost;
import com.trinetbss.model.BenDefnOptn;
import com.trinetbss.model.OptnMapKey;
import com.trinetbss.dao.BenDefnOptnDao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

	public static void main( String[] args ) {

		System.out.println( "Main.main()" );

		System.out.println( "Getting client benefit program options..." );
		List<BenDefnOptn> optn = BenDefnOptnDao.getAllOptnRows( "001AAF", "2018-04-01" );
		System.out.println( "returned rows: " + optn.size() );
		Map<OptnMapKey, BenDefnOptn> newPgmMap = new HashMap<OptnMapKey, BenDefnOptn>();

		// get cost rows for optn rows in the collection
		System.out.println( "Getting matching cost rows..." );
		BenDefnOptnDao.getMatchingCostRows( optn );


		// add unique options to the map containing the new merged benefit program
		System.out.println( "Loading map..." );
		for( BenDefnOptn o : optn ) {
			newPgmMap.put( OptnMapKey.getInstance( o.planType, o.optionType, o.benefitPlan, o.covrgCd ), o );
		}
		System.out.println( "Map contains: " + newPgmMap.size() );



		// get the OPTN rows for the clone benefit program
		System.out.println( "Getting clone benefit program options..." );
		List<BenDefnOptn> clone = BenDefnOptnDao.getAllOptnRows( "113", "2018-04-01" );
		System.out.println( "returned rows: " + clone.size() );

		// get the cost rows for the clone benefit program
		System.out.println( "Getting clone benefit cost rows..." );
		BenDefnOptnDao.getMatchingCostRows( clone );


		// add new options from the clone benefit program to the map
		System.out.println( "Updating map..." );
		for( BenDefnOptn o : clone ) {
			newPgmMap.put( OptnMapKey.getInstance( o.planType, o.optionType, o.benefitPlan, o.covrgCd ), o );
		}
		System.out.println( "Map contains: " + newPgmMap.size() );


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



		// print result
		for( BenDefnOptn o : newPgmList ) {
			System.out.println( o.benefitProgram + "<->" + o.effdt + "<->" + o.planType + "<->" + o.benefitPlan + "<->" + o.covrgCd + "<->" + o.optionId );
			for( BenDefnCost c : o.cost ) {
				System.out.println( "         " + c.costId + "<->" + c.rateType + "<->" + c.rateTblId );
			}
		}
		System.out.println( "New benefit program contains " + newPgmList.size() + " rows." );

   }

}
