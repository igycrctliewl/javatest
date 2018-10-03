package com.trinetbss.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BSSTableData {

	private static final String COMMA = ",";
	private static final String QUOTE = "\"";

	private BufferedWriter plyrPlanTbl;
	private BufferedWriter rlRegionPlansTbl;
	private int plyrPlanId;

	public BSSTableData() {

		this.plyrPlanId = 14000;

		try {
			this.plyrPlanTbl = BSSTableData.openFile( "REALM_PLYR_PLAN.txt" );
			this.plyrPlanTbl.write( "ID,REALM_YEAR_ID,PLAN_TYPE,BENEFIT_PLAN,PORTFOLIO_ID,SITUS" );
			this.plyrPlanTbl.newLine();
			this.rlRegionPlansTbl = BSSTableData.openFile( "RL_REGION_PLANS.txt" );
			this.rlRegionPlansTbl.write( "REALM_PLYR_PLAN_ID,REGION,MANDATORY_FLAG,SUB_REGION" );
			this.rlRegionPlansTbl.newLine();
		} catch( IOException e ) {
			System.out.println( "BSSTableData constructor FATAL error" );
			System.out.println( "     Could not open BSS output files" );
			e.printStackTrace();
			System.exit( 1 );
		}

	}


	public void writePlanData( int realmYearId, String planType, String benefitPlan, int portfolioId, String situs, String[] states, String subRegion ) throws IOException {
		this.plyrPlanId++;
		this.plyrPlanTbl.write( "" + this.plyrPlanId );
		this.plyrPlanTbl.write( COMMA );
		this.plyrPlanTbl.write( "" + realmYearId );
		this.plyrPlanTbl.write( COMMA );
		this.plyrPlanTbl.write( QUOTE + planType + QUOTE );
		this.plyrPlanTbl.write( COMMA );
		this.plyrPlanTbl.write( QUOTE + benefitPlan + QUOTE );
		this.plyrPlanTbl.write( COMMA );
		/*if( portfolioId != null )*/ this.plyrPlanTbl.write( "" + portfolioId );
		this.plyrPlanTbl.write( COMMA );
		if( situs != null ) this.plyrPlanTbl.write( QUOTE + situs + QUOTE );
		this.plyrPlanTbl.newLine();

		for( String state : states ) {
			this.rlRegionPlansTbl.write( "" + this.plyrPlanId );
			this.rlRegionPlansTbl.write( COMMA );
			this.rlRegionPlansTbl.write( QUOTE + state + QUOTE );
			this.rlRegionPlansTbl.write( COMMA );
			this.rlRegionPlansTbl.write( "0" );
			this.rlRegionPlansTbl.write( COMMA );
			this.rlRegionPlansTbl.write( QUOTE + subRegion + QUOTE );
			this.rlRegionPlansTbl.newLine();
		}
	}

	public void close() {
		try {
			plyrPlanTbl.close();
			rlRegionPlansTbl.close();
		} catch( IOException e ) {
			System.out.println( "BSSTableData.close IO Exception" );
			e.printStackTrace();
		}
	}


	private static BufferedWriter openFile( String fileName ) throws IOException {
		return new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream( 
				new File( fileName ) ) ) );
	}

	public static void main( String[] args ) {

		try {
			BSSTableData bss = new BSSTableData();
			bss.writePlanData( 12,"10","000SR2",5,null, new String[] {"CA","NV"}, "" );
			bss.close();

		} catch( IOException e ) {
			System.out.println( "BSSTableData.main IO Exception" );
			e.printStackTrace();
		}
	}
}
