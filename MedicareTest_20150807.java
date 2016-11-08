import java.math.BigDecimal;

public class MedicareTest {

   public static void main(String[] args) {
      Medicare med;
      double wages, tax;
      
      med = new Medicare();
      
/*      System.out.println("2014");
      System.out.println(" ");

      med.CalcConverted( "1234.56" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );

      med.CalcConverted( "1234.56" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );

      med.CalcConverted( "211234.56" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );

      med.CalcConverted( "1234.56" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );

      med.CalcConverted( "2234.56" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
*/
      /*
      med.CalcConverted( 1234.56, 0 );
      System.out.println("F: base: " + med.currStdBase + "   tax: " + med.currStdTax );
      System.out.println("7: base: " + med.currAddlBase + "   tax: " + med.currAddlTax );
      
      med.CalcConverted( 1234.56, 0 );
      System.out.println("F: base: " + med.currStdBase + "   tax: " + med.currStdTax );
      System.out.println("7: base: " + med.currAddlBase + "   tax: " + med.currAddlTax );
      
      med.CalcConverted( 211234.56, 0 );
      System.out.println("F: base: " + med.currStdBase + "   tax: " + med.currStdTax );
      System.out.println("7: base: " + med.currAddlBase + "   tax: " + med.currAddlTax );
      
      med.CalcConverted( 1234.56, 0 );
      System.out.println("F: base: " + med.currStdBase + "   tax: " + med.currStdTax );
      System.out.println("7: base: " + med.currAddlBase + "   tax: " + med.currAddlTax );
      
      med.CalcConverted( 2234.56, 0 );
      System.out.println("F: base: " + med.currStdBase + "   tax: " + med.currStdTax );
      System.out.println("7: base: " + med.currAddlBase + "   tax: " + med.currAddlTax );
      */
      
      System.out.println(" ");
      System.out.println("2015");
      System.out.println(" ");

      /* new year or new employee, create a new Medicare object */
      med = new Medicare();
      
      med.CalcConverted( "7958.70" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

      med.CalcConverted( "225000.00" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

      med.CalcConverted( "18000.00" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

      med.CalcConverted( "8088.70" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

      med.CalcConverted( "7958.70" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

      med.CalcConverted( "8088.70" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

      med.CalcConverted( "7958.70" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

      med.CalcConverted( "8088.70" );
      System.out.println("F:  base: " + med.bdCurrStdBase + "   tax: " + med.bdCurrStdTax + "   YTD tax: " + med.bdYtdStdTax  + "   YTD base: " + med.bdYtdStdBase );
      System.out.println("7:  base: " + med.bdCurrAddlBase + "   tax: " + med.bdCurrAddlTax + "   YTD base: " + med.bdYtdAddlTax  + "   YTD base: " + med.bdYtdAddlBase );
      System.out.println(" ");

          
   }
}
