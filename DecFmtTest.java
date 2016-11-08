import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DecFmtTest {

   public static void main(String[] args) {

      DecimalFormat rateFormat = new DecimalFormat( "###,##0.00" );
      BigDecimal rate = new BigDecimal( "4959.41" );   
      System.out.println(":" + rateFormat.format( rate ) + ":");

      rate = new BigDecimal( "0" );   
      System.out.println(":" + rateFormat.format( rate ) + ":");

   }
}

