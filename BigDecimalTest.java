import java.math.BigDecimal;

public class BigDecimalTest {

   public static void main(String[] args) {

      BigDecimal rate = new BigDecimal( "4959.41" );   
      BigDecimal covrg82Total = new BigDecimal( "9876.54" );   

      System.out.println( "raw BD output:" + rate + ":" );
      System.out.println( "hash:" + rate.hashCode() + ":" );

      rate = BigDecimal.ZERO;   
      System.out.println( "raw BD output:" + rate + ":" );
      System.out.println( "hash:" + rate.hashCode() + ":" );

      rate = covrg82Total;
      System.out.println( "raw BD output:" + rate + ":" );
      System.out.println( "hash:" + rate.hashCode() + ":" );

      BigDecimal employerCost = new BigDecimal( "1234.10" );
      BigDecimal employeePlusSpouseCost = new BigDecimal( "123.32" );
      BigDecimal employeePlusSpousePercent = employerCost.setScale(9).divide(employeePlusSpouseCost, BigDecimal.ROUND_HALF_UP );
      System.out.println( "employerCost:" + employerCost + ":" );
      System.out.println( "employeePlusSpouseCost:" + employeePlusSpouseCost + ":" );
      System.out.println( "employeePlusSpousePercent:" + employeePlusSpousePercent + ":" );

      BigDecimal testPercent = new BigDecimal( "0.75" ).setScale(9);
      BigDecimal disPercent = testPercent.multiply( BigDecimal.valueOf(100) );
      System.out.println( "disPercent:" + disPercent + ":" );

   }
}

