package com.trinetbss.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BSSTableData {

	private BufferedWriter plyrPlanTbl;
	private BufferedWriter rlRegionPlansTbl;

	public BSSTableData() {
		try {
			this.plyrPlanTbl = BSSTableData.openFile( "REALM_PLYR_PLAN.txt" );
			this.rlRegionPlansTbl = BSSTableData.openFile( "RL_REGION_PLANS.txt" );
		} catch( IOException e ) {
			System.out.println( "BSSTableData constructor FATAL error" );
			System.out.println( "     Could not open BSS output files" );
			e.printStackTrace();
			System.exit( 1 );
		}

	}

	public void testWrite() throws IOException {
		plyrPlanTbl.write("something else");
		plyrPlanTbl.newLine();
		rlRegionPlansTbl.write("something else");
		rlRegionPlansTbl.newLine();
	}

	public void close() throws IOException {
		plyrPlanTbl.close();
		rlRegionPlansTbl.close();
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
			bss.testWrite();
			bss.close();

		} catch( IOException e ) {
			System.out.println( "BSSTableData.main IO Exception" );
			e.printStackTrace();
		}
	}
}
