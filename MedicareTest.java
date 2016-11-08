import java.math.BigDecimal;

public class MedicareTest {

   static Medicare med;
   static MBDecimalFormat fmt1;

   public static void main(String[] args) {

      String mask = "##,###,###.00";
      fmt1 = new MBDecimalFormat( mask );

      /* begin year with fresh Medicare object
      System.out.println(" ");
      System.out.println("2014");
      System.out.println(" ");

      med = new Medicare();

      MedicareTest.calcTax( "1234.56" );
      MedicareTest.calcTax( "1234.56" );
      MedicareTest.calcTax( "211234.56" );
      MedicareTest.calcTax( "1234.56" );
      MedicareTest.calcTax( "2234.56" );
      */

      System.out.println(" ");
      System.out.println("2016 - first EE");
      System.out.println(" ");

      /* new year or new employee, create a new Medicare object */
      med = new Medicare();

      MedicareTest.calcTax( "4959.41" );      /*  1 */
      MedicareTest.calcTax( "91183" );        /*  3 */
      MedicareTest.calcTax( "5310.41" );      /*  5 */
      MedicareTest.calcTax( "5310.41" );      /*  7 */
      MedicareTest.calcTax( "28020" );        /*  9 */


      System.out.println(" ");
      System.out.println("2016 - second EE");
      System.out.println(" ");

      /* new year or new employee, create a new Medicare object */
      med = new Medicare();

      MedicareTest.calcTax( "3750" );         /*  2 */
      MedicareTest.calcTax( "60790" );        /*  4 */
      MedicareTest.calcTax( "3750" );         /*  6 */
      MedicareTest.calcTax( "3750" );         /*  8 */
      MedicareTest.calcTax( "18680" );        /* 10 */


      System.out.println(" ");
      System.out.println("2016 - 00001527619");
      System.out.println(" ");

      /* new year or new employee, create a new Medicare object */
      med = new Medicare();

      MedicareTest.calcTax( "8151.27" );
      MedicareTest.calcTax( "148354" );
      MedicareTest.calcTax( "8277.77" );
      MedicareTest.calcTax( "8277.77" );
      MedicareTest.calcTax( "28029" );

   }

   static void calcTax( String amount ) {
      med.calcConverted( amount );
      System.out.println("F: base: " + fmt1.format( med.bdCurrStdBase ) + "  tax: " + fmt1.format( med.bdCurrStdTax ) + " YTD base: " + fmt1.format( med.bdYtdStdBase ) + "  YTD tax: " + fmt1.format( med.bdYtdStdTax ) );
      System.out.println("7: base: " + fmt1.format( med.bdCurrAddlBase ) + "  tax: " + fmt1.format( med.bdCurrAddlTax ) + " YTD base: " + fmt1.format( med.bdYtdAddlBase ) + "  YTD tax: " + fmt1.format( med.bdYtdAddlTax ) );
      System.out.println(" ");
   }
}

