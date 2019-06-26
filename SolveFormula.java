import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;

public class SolveFormula {
	static final String CNST_ENTRY = "CNST";
	static final String BASE_ENTRY = "BASE";

	BnFormula bFn;

	public SolveFormula() {
		this.bFn = this.getTest01AS0000T1();
	}


	public static void main(String[] args) {
		System.out.println( "SolveFormula.main()" );
		SolveFormula solver = new SolveFormula();
		System.out.println( solver.bFn );
		System.out.println( solver.solveFormula( new BigDecimal( "150000.01" ), null, null ) );
	}




   public BigDecimal solveFormula( BigDecimal base, BigDecimal roundUpAmt, BigDecimal roundTo ) {
		BigDecimal calBase = BigDecimal.ZERO;
		BigDecimal calPrem = BigDecimal.ZERO;
		// ported from the Cobol PSPDCOVG

		// MOVE ZERO TO W-CNSTVALUE
		BigDecimal wCnstValue = BigDecimal.ZERO;

		// PERFORM VARYING FRLDEF-IDX FROM 1 BY 1
		//   UNTIL FRLDEF-IDX > FRML-DEF-COUNT OF FRLTB(FRLTB-IDX)
		for( BnFormulaDef def : this.bFn.bnFormulaDefs ) {
			System.out.println( def.toString() );
			System.out.println( def.benOperand );
			switch( def.benOperand ) {
/*          EVALUATE TRUE
            WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = SPACE
            WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '+'
            WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '-'
            WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '/'
            WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '*'
*/			case " ":
			case "+":
			case "-":
			case "/":
			case "*":
				System.out.println( "arithmetic operator" );
            //  IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX)
            //                                             = 'CNST'
				if( CNST_ENTRY.equals( def.bnEntryTyp ) ) {
               // MOVE BN-VALUE OF FRLTB(FRLTB-IDX FRLDEF-IDX)
               //      TO W-CNSTVALUE OF W-WK
					wCnstValue = def.bnValue;
            // END-IF
				}

            // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX)
            //                                             = 'BASE'
				if( BASE_ENTRY.equals( def.bnEntryTyp ) ) {
            //    PERFORM TA000-CALC-COVERAGE

            //    MOVE CALCULATED-BASE OF DARRY(DARRY-IDX)
            //         TO W-CALBASE OF W-WK
					calBase = base;

            //   MOVE PREMIUM-BASE OF DARRY(DARRY-IDX)
            //        TO W-CALPREM OF W-WK
            //  END-IF
				}
				break;
         // WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '('
         // WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = ')'
			case ")":
			case "(":
				System.out.println( "parenthesis" );
            // CONTINUE
				break;
         // WHEN BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'R'
			case "R":
				System.out.println( "rounding operator" );
            // PERFORM SA000-ROUND-COVERAGE
				this.roundCoverage( null, null, null );
            // MOVE ZERO TO W-CNSTVALUE
				wCnstValue = BigDecimal.ZERO;
				break;
			default:
				System.out.println( "other operator:" + def.benOperand + ":" );
         // END-EVALUATE
			}

         // IF BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = SPACE
			if( " ".equals( def.benOperand ) ) {
            // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'CNST'
				if( CNST_ENTRY.equals( def.bnEntryTyp ) ) {
               // MOVE BN-VALUE OF FRLTB(FRLTB-IDX FRLDEF-IDX)
               //         TO W-CALBASE OF W-WK
               //            W-CALPREM OF W-WK
					calBase = def.bnValue;
					calPrem = def.bnValue;
            // END-IF
				}
         // END-IF
			}

         // IF BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '+'
			if( "+".equals( def.benOperand ) ) {
            // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'CNST'
				if( CNST_ENTRY.equals( def.bnEntryTyp ) ) {
               // COMPUTE W-CALBASE OF W-WK =
               //                      W-CALBASE OF W-WK +
               //                      W-CNSTVALUE OF W-WK
					calBase = calBase.add( wCnstValue );
               // COMPUTE W-CALPREM OF W-WK =
               //                      W-CALPREM OF W-WK +
               //                      W-CNSTVALUE OF W-WK
					calPrem = calPrem.add( wCnstValue );
            // ELSE
				} else {
               // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'BASE'
					if( BASE_ENTRY.equals( def.bnEntryTyp ) ) {
               // IF W-CNSTVALUE OF W-WK NOT = ZERO
						if( ! BigDecimal.ZERO.equals( wCnstValue ) ) {
                     // COMPUTE W-CALBASE OF W-WK =
                     //                      W-CALBASE OF W-WK +
                     //                      W-CNSTVALUE OF W-WK
							calBase = calBase.add( wCnstValue );
                     // COMPUTE W-CALPREM OF W-WK =
                     //                      W-CALPREM OF W-WK +
                     //                      W-CNSTVALUE OF W-WK
							calPrem = calPrem.add( wCnstValue );
                  // END-IF
						}
               // END-IF
					}
            // END-IF
				}
         // END-IF
			}

         // IF BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '-'
			if( "-".equals( def.benOperand ) ) {
            // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'CNST'
				if( CNST_ENTRY.equals( def.bnEntryTyp ) ) {
               // COMPUTE W-CALBASE OF W-WK =
               //                      W-CALBASE OF W-WK -
               //                      W-CNSTVALUE OF W-WK
					calBase = calBase.subtract( wCnstValue );
               // COMPUTE W-CALPREM OF W-WK =
               //                      W-CALPREM OF W-WK -
               //                      W-CNSTVALUE OF W-WK
					calPrem = calPrem.subtract( wCnstValue );
            // ELSE
				} else {
               // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'BASE'
					if( BASE_ENTRY.equals( def.bnEntryTyp ) ) {
                  // IF W-CNSTVALUE OF W-WK NOT = ZERO
						if( ! BigDecimal.ZERO.equals( wCnstValue ) ) {
                     // COMPUTE W-CALBASE OF W-WK =
                     //                      W-CNSTVALUE OF W-WK -
                     //                      W-CALBASE OF W-WK
							calBase = calBase.subtract( wCnstValue );
                     // COMPUTE W-CALPREM OF W-WK =
                     //                      W-CNSTVALUE OF W-WK -
                     //                      W-CALPREM OF W-WK
							calPrem = calPrem.subtract( wCnstValue );
                  // END-IF
						}
               // END-IF
					}
            // END-IF
				}
         // END-IF
			}

         // IF BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '*'
			if( "*".equals( def.benOperand ) ) {
            // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'CNST'
				if( CNST_ENTRY.equals( def.bnEntryTyp ) ) {
               // COMPUTE W-CALBASE OF W-WK =
               //                      W-CALBASE OF W-WK *
               //                      W-CNSTVALUE OF W-WK
					calBase = calBase.multiply( wCnstValue );
               // COMPUTE W-CALPREM OF W-WK =
               //                      W-CALPREM OF W-WK *
               //                      W-CNSTVALUE OF W-WK
					calPrem = calPrem.multiply( wCnstValue );
            // ELSE
				} else {
               // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'BASE'
					if( BASE_ENTRY.equals( def.bnEntryTyp ) ) {
                  // IF W-CNSTVALUE OF W-WK NOT = ZERO
						if( ! BigDecimal.ZERO.equals( wCnstValue ) ) {
                     //COMPUTE W-CALBASE OF W-WK =
                     //                     W-CALBASE OF W-WK *
                     //                     W-CNSTVALUE OF W-WK
							calBase = calBase.multiply( wCnstValue );
                     //COMPUTE W-CALPREM OF W-WK =
                     //                     W-CALPREM OF W-WK *
                     //                     W-CNSTVALUE OF W-WK
							calPrem = calPrem.multiply( wCnstValue );
                  // END-IF
						}
               // END-IF
					}
            // END-IF
				}
         // END-IF
			}

         // IF BEN-OPERAND OF FRLTB(FRLTB-IDX FRLDEF-IDX) = '/'
			if( "/".equals( def.benOperand ) ) {
            // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'CNST'
				if( CNST_ENTRY.equals( def.bnEntryTyp ) ) {
               // COMPUTE W-CALBASE OF W-WK =
               //                      W-CALBASE OF W-WK /
               //                      W-CNSTVALUE OF W-WK
					calBase = calBase.divide( wCnstValue, BigDecimal.ROUND_HALF_UP );
               // COMPUTE W-CALPREM OF W-WK =
               //                      W-CALPREM OF W-WK /
               //                      W-CNSTVALUE OF W-WK
					calPrem = calPrem.divide( wCnstValue, BigDecimal.ROUND_HALF_UP );
            // ELSE
				} else {
               // IF BN-ENTRY-TYP OF FRLTB(FRLTB-IDX FRLDEF-IDX) = 'BASE'
					if( BASE_ENTRY.equals( def.bnEntryTyp ) ) {
                  // IF W-CNSTVALUE OF W-WK NOT = ZERO
						if( ! BigDecimal.ZERO.equals( wCnstValue ) ) {
                     // COMPUTE W-CALBASE OF W-WK =
                     //                      W-CNSTVALUE OF W-WK /
                     //                      W-CALBASE OF W-WK
							calBase = wCnstValue.divide( calBase, BigDecimal.ROUND_HALF_UP );
                     // COMPUTE W-CALPREM OF W-WK =
                     //                      W-CNSTVALUE OF W-WK /
                     //                      W-CALPREM OF W-WK
							calPrem = wCnstValue.divide( calPrem, BigDecimal.ROUND_HALF_UP );
                  // END-IF
						}
               // END-IF
					}
            // END-IF
				}
         //END-IF
			}

      // END-PERFORM
		}

      // MOVE W-CALBASE OF W-WK
      //      TO  CALCULATED-BASE OF DARRY(DARRY-IDX)
      // MOVE W-CALPREM OF W-WK
      //      TO PREMIUM-BASE OF DARRY(DARRY-IDX)

		return calBase;
   }

	// SA000-ROUND-COVERAGE SECTION.
	public BigDecimal roundCoverage( BigDecimal base, BigDecimal roundUpAmt, BigDecimal roundTo ) {
/*
       SA000.
      *                                                                *
      ******************************************************************

           COMPUTE WK-ROUND-UNITS OF W-WK
                   =  W-CALBASE OF W-WK
                   /  ROUND-TO OF FRLTB(FRLTB-IDX FRLDEF-IDX)

           IF ROUND-UP-AMT OF FRLTB(FRLTB-IDX FRLDEF-IDX)
                   <=  (W-CALBASE OF W-WK
                            -  (WK-ROUND-UNITS OF W-WK
                            *  ROUND-TO
                                     OF FRLTB(FRLTB-IDX FRLDEF-IDX)))

               ADD 1  TO  WK-ROUND-UNITS OF W-WK
           END-IF

           COMPUTE W-CALBASE OF W-WK
                   =  WK-ROUND-UNITS OF W-WK
                   *  ROUND-TO OF FRLTB(FRLTB-IDX FRLDEF-IDX)

           COMPUTE WK-ROUND-UNITS OF W-WK
                   =  W-CALPREM OF W-WK
                   /  ROUND-TO OF FRLTB(FRLTB-IDX FRLDEF-IDX)

           IF ROUND-UP-AMT OF FRLTB(FRLTB-IDX FRLDEF-IDX)
                   <=  (W-CALPREM OF W-WK
                           -  (WK-ROUND-UNITS OF W-WK
                           *  ROUND-TO
                                  OF FRLTB(FRLTB-IDX FRLDEF-IDX)))

               ADD 1  TO  WK-ROUND-UNITS OF W-WK
           END-IF

           COMPUTE W-CALPREM OF W-WK
                   =  WK-ROUND-UNITS OF W-WK
                   *  ROUND-TO OF FRLTB(FRLTB-IDX FRLDEF-IDX)

           .
       ROUND-COVERAGE-EXIT.
*/

		return null;
	}

//////////////// TESTING METHODS

	private BnFormula getTest03AS0000TB() {
		BnFormula f = new BnFormula( "03AS0000TB", "2019-01-01", "03AS0000TB", "03AS0000TB", "2"
				, "PRIM", "L", 6, 1, "L", 1, 6, 0, "N", "N", "1", 10000, 1000000 );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",10, "(", " ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",20, "(", " ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",30, "(", " ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",40, " ", "BASE",0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",50, ")", " ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",75, "*", "CNST",3,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",80, ")", " ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "03AS0000TB","2019-01-01",110,")", " ",   0,0,0 ) );
		return f;
	}

	// this one has rounding
	private BnFormula getTest01AS0000T1() {
		BnFormula f = new BnFormula( "01AS0000T1","2018-04-01","Supp Life 6X","Supp Life","2","PRIM"
			,"T",4,1,"T",1,4,0,"N","N","1",1000,2000000 );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",10,"("," ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",20,"("," ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",30,"("," ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",40," ","BASE",0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",50,")"," ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",60,"*","CNST",6,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",70,")"," ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",80,")"," ",   0,0,0 ) );
		f.bnFormulaDefs.add( new BnFormulaDef( "01AS0000T1","2018-04-01",90,"R"," ",   0,0.01,1000 ) );
		return f;
	}


	class BnFormula {

		String formulaId;
		java.sql.Date effdt;
		String descr;
		String descrShort;
		String benefitsBase;
		String bnAnnlBenbaseCd;
		String covrgAsOfCd;
		BigDecimal covrgAsOfMM;
		BigDecimal covrgAsOfDD;
		String prevAsOfCd;
		BigDecimal premAsOfDD;
		BigDecimal premAsOfMM;
		BigDecimal maxBenefBase;
		String combineMultSal;
		String evalActv;
		String grpMthd;
		BigDecimal coverageMinimum;
		BigDecimal coverageMaximum;
		List<BnFormulaDef> bnFormulaDefs;

		BnFormula( String formulaId, String effdt, String descr, String descrShort, String benefitsBase
				, String bnAnnlBenbaseCd, String covrgAsOfCd, long covrgAsOfMM, long covrgAsOfDD
				, String premAsOfCd, long premAsOfDD, long premAsOfMM, long maxBenefBase
				, String combineMultSal, String evalActv, String grpMthd
				, long coverageMinimum, long coverageMaximum ) {

			this.formulaId = formulaId;
			this.effdt = java.sql.Date.valueOf( effdt );
			this.descr = descr;
			this.descrShort = descrShort;
			this.benefitsBase = benefitsBase;
			this.bnAnnlBenbaseCd = bnAnnlBenbaseCd;
			this.covrgAsOfCd = covrgAsOfCd;
			this.covrgAsOfMM = BigDecimal.valueOf( covrgAsOfMM );
			this.covrgAsOfDD = BigDecimal.valueOf( covrgAsOfDD );
			this.prevAsOfCd = premAsOfCd;
			this.premAsOfDD = BigDecimal.valueOf( premAsOfDD );
			this.premAsOfMM = BigDecimal.valueOf( premAsOfMM );
			this.maxBenefBase = BigDecimal.valueOf( maxBenefBase );
			this.combineMultSal = combineMultSal;
			this.evalActv = evalActv;
			this.grpMthd = grpMthd;
			this.coverageMinimum = BigDecimal.valueOf( coverageMinimum );
			this.coverageMaximum = BigDecimal.valueOf( coverageMaximum );
			
			this.bnFormulaDefs = new ArrayList<BnFormulaDef>();
		}

		public String toString() {
			return this.formulaId + ":" + this.effdt;
		}
	}


	class BnFormulaDef {
		String bnFormulaId;
		java.sql.Date effdt;
		BigDecimal bnSeqNum;
		String benOperand;
		String bnEntryTyp;
		BigDecimal bnValue;
		BigDecimal roundUpAmt;
		BigDecimal roundTo;

		BnFormulaDef( String bnFormulaId, String effdt, long bnSeqNum, String benOperand, String bnEntryTyp, long bnValue, double roundUpAmt, long roundTo ) {
			this.bnFormulaId = bnFormulaId;
			this.effdt = java.sql.Date.valueOf( effdt );
			this.bnSeqNum = BigDecimal.valueOf( bnSeqNum );
			this.benOperand = benOperand;
			this.bnEntryTyp = bnEntryTyp;
			this.bnValue = BigDecimal.valueOf( bnValue );
			this.roundUpAmt = BigDecimal.valueOf( roundUpAmt );
			this.roundTo = BigDecimal.valueOf( roundTo );
		}

		public String toString() {
			return this.bnFormulaId + ":DEF; step:" + this.bnSeqNum;
		}

	}
	
}

/*
SELECT fm.*
FROM PS_BN_FORMULA FM
   , PS_BN_FORM_DEF DF
WHERE FM.BN_FORMULA_ID = '03AS0000TB'
  AND FM.EFFDT = (
      SELECT MAX(EFFDT)
        FROM PS_BN_FORMULA F1
       WHERE F1.BN_FORMULA_ID = FM.BN_FORMULA_ID )
  AND DF.BN_FORMULA_ID = FM.BN_FORMULA_ID
  AND DF.EFFDT = FM.EFFDT
ORDER BY DF.BN_SEQ_NUM ASC;


SELECT *
FROM PS_SQLSTMT_TBL
WHERE STMT_TEXT LIKE '%BN_FORM_DEF%';


SELECT *
FROM PS_DISBLTY_PLN_TBL
*/