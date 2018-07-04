package com.trinetbss.main;

import com.trinetbss.model.BenDefnOptn;
import com.trinetbss.dao.BenDefnOptnDao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main( String[] args ) {

		System.out.println( "Main.main()" );

		List<BenDefnOptn> optn = BenDefnOptnDao.getAllOptnRows( "001AAF", "2018-04-01" );
		System.out.println( "returned rows: " + optn.size() );

		// test comparator and print sorted result
		optn.sort( BenDefnOptn.PlanCovrgCdComparator );
		for( BenDefnOptn o : optn ) {
			System.out.println( o.benefitProgram + "<->" + o.effdt + "<->" + o.planType + "<->" + o.benefitPlan + "<->" + o.covrgCd );
		}

   }

}