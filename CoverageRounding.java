import java.math.BigDecimal;
import java.math.RoundingMode;

public class CoverageRounding {

   public static void main(String[] args) {

      BigDecimal base = new BigDecimal( "244000.00" );
      BigDecimal roundUpAmt = new BigDecimal( "0.01" );
      BigDecimal roundTo = new BigDecimal( "1000" );
      System.out.println( "Rounded to " + round( base, roundUpAmt, roundTo ) );

      System.out.println( " " );

      base = new BigDecimal( "244959.41" );
      System.out.println( "Rounded to " + round( base, roundUpAmt, roundTo ) );

      System.out.println( " " );

      base = new BigDecimal( "244000.01" );
      roundUpAmt = new BigDecimal( "0.10" );
      System.out.println( "Rounded to " + round( base, roundUpAmt, roundTo ) );

   }




   public static BigDecimal round( BigDecimal base, BigDecimal roundUpAmt, BigDecimal roundTo ) {

      System.out.println( "base:" + base );
      System.out.println( "roundUpAmt:" + roundUpAmt );
      System.out.println( "roundTo:" + roundTo );

      //COMPUTE WK-ROUND-UNITS  =  W-CALBASE  /  ROUND-TO
      BigDecimal roundUnits = base.divide( roundTo, RoundingMode.HALF_UP ).setScale( 0, RoundingMode.DOWN );
      System.out.println( "step one: roundUnits:" + roundUnits );

      //IF ROUND-UP-AMT  <=  ( W-CALBASE - ( WK-ROUND-UNITS * ROUND-TO ) )
      if( roundUpAmt.compareTo( base.subtract( roundUnits.multiply( roundTo ) )) <= 0 ) {
         //ADD 1  TO  WK-ROUND-UNITS OF W-WK
         System.out.println( "add one" );
         roundUnits = roundUnits.add( BigDecimal.ONE );
      }

      //COMPUTE W-CALBASE OF W-WK =  WK-ROUND-UNITS OF W-WK *  ROUND-TO OF FRLTB(FRLTB-IDX FRLDEF-IDX)
      BigDecimal newBase = roundUnits.multiply( roundTo );

      /* remainder of Cobol code performs same rounding method on the CALPREM amount
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
             *  ROUND-TO OF FRLTB(FRLTB-IDX FRLDEF-IDX)   */



      System.out.println( "roundUnits:" + roundUnits );
      return newBase;

   }
}

