import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestDPRates {

   public static void main(String[] args) {

      //PLAN TYPE : 15
      //CROSS PLAN : 001EKP	(band code B)
      //DP PLAN : 001EL5	  
      BigDecimal eeRate01 = new BigDecimal( "121.72800000000007" );
      BigDecimal erRate01 = new BigDecimal( "436.49199999999996" );

      BigDecimal eeRate81 = new BigDecimal( "758.0280000000001" );
      BigDecimal erRate81 = new BigDecimal( "436.49199999999996" );

      BigDecimal eeRate82 = new BigDecimal( "1328.8980000000001" );
      BigDecimal erRate82 = new BigDecimal( "436.49199999999996" );


      BigDecimal covrg01Total = eeRate01.add( erRate01 );
      BigDecimal covrg81Total = eeRate81.add( erRate81 );
      BigDecimal covrg82Total = eeRate82.add( erRate82 );
      
      System.out.println("Covrg 01 rate:" + covrg01Total );
      System.out.println("Covrg 81 rate:" + covrg81Total );
      System.out.println("Covrg 82 rate:" + covrg82Total );
      System.out.println("   calculated dp rates" );
      
      BigDecimal covrg83Total = covrg81Total.subtract( covrg01Total ).setScale( 2, BigDecimal.ROUND_HALF_UP );     
      BigDecimal covrg84Total = covrg82Total.subtract( covrg01Total ).setScale( 2, BigDecimal.ROUND_HALF_UP );     
      BigDecimal covrg85Total = covrg82Total.subtract( covrg81Total ).setScale( 2, BigDecimal.ROUND_HALF_UP );     
      BigDecimal covrg86Total = covrg82Total.subtract( covrg82Total ).setScale( 2, BigDecimal.ROUND_HALF_UP );     
      System.out.println("      Covrg 83 rate:" + covrg83Total );
      System.out.println("      Covrg 84 rate:" + covrg84Total );
      System.out.println("      Covrg 85 rate:" + covrg85Total );
      System.out.println("      Covrg 86 rate:" + covrg86Total );
      
      BigDecimal covrg01ErPct = erRate01.divide( covrg01Total, BigDecimal.ROUND_HALF_UP );
      BigDecimal covrg81ErPct = erRate81.divide( covrg81Total, BigDecimal.ROUND_HALF_UP );
      BigDecimal covrg82ErPct = erRate82.divide( covrg82Total, BigDecimal.ROUND_HALF_UP );
      
      System.out.println("Covrg 01 ER pct:" + covrg01ErPct );
      System.out.println("Covrg 81 ER pct:" + covrg81ErPct );
      System.out.println("Covrg 82 ER pct:" + covrg82ErPct );
      
    //  //start from total costs for DP plans
    //  //DP 83 total rate: 636.30
    //  BigDecimal covrg83Total = new BigDecimal( "636.30" );
    //  //DP 84 total rate: 1207.17
    //  BigDecimal covrg84Total = new BigDecimal( "1207.17" );
    //  //DP 85 total rate: 570.87
    //  BigDecimal covrg85Total = new BigDecimal( "570.87" );
    //  //DP 86 total rate: 0
    //  BigDecimal covrg86Total = BigDecimal.ZERO;
      
      //calculate the ER contributions
      BigDecimal erRate83 = covrg83Total.multiply( covrg81ErPct ).setScale( 2, BigDecimal.ROUND_CEILING );
      BigDecimal erRate84 = covrg84Total.multiply( covrg82ErPct ).setScale( 2, BigDecimal.ROUND_CEILING );
      BigDecimal erRate85 = covrg85Total.multiply( covrg82ErPct ).setScale( 2, BigDecimal.ROUND_CEILING );
      BigDecimal erRate86 = covrg86Total.multiply( covrg82ErPct ).setScale( 2, BigDecimal.ROUND_CEILING );
      
      //calculate the EE contributions
      BigDecimal eeRate83 = covrg83Total.subtract( erRate83 );
      BigDecimal eeRate84 = covrg84Total.subtract( erRate84 );
      BigDecimal eeRate85 = covrg85Total.subtract( erRate85 );
      BigDecimal eeRate86 = covrg86Total.subtract( erRate86 );

      System.out.println("\nContributions for DP plan" );
      System.out.println("Covrg Cd 83    company contribution: " + erRate83 );
      System.out.println("              employee contribution: " + eeRate83 );
      System.out.println("Covrg Cd 84    company contribution: " + erRate84 );
      System.out.println("              employee contribution: " + eeRate84 );
      System.out.println("Covrg Cd 85    company contribution: " + erRate85 );
      System.out.println("              employee contribution: " + eeRate85 );
      System.out.println("Covrg Cd 86    company contribution: " + erRate86 );
      System.out.println("              employee contribution: " + eeRate86 );
      
   }
}

