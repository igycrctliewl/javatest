import java.text.*;
import java.math.*;

/**
 * MBDecimalFormat is an extension to DecimalFormat to produce formatted
 * numbers with a length equal to the pattern mask.  These work better
 * for columns of numbers.
 */
public class MBDecimalFormat extends DecimalFormat {

   private int maskLen;

   /**
    * Creates the MBDecimalFormat object by specifying the pattern mask.
    *
    * @param  pattern  A string containing a pattern mask according to 
    *                  the specification for the DecimalFormat object
    */
   public MBDecimalFormat ( String pattern ) {
      super( pattern );
      this.maskLen = pattern.length();
   }

   /**
    * Formats the BigDecimal passed to the method according to the pattern
    * used to create the object
    *
    * @param  number  a BigDecimal to be formatted
    * @return         a string containing the formatted number, sized accordingly
    */
   public String format( BigDecimal number ) {
      String s = super.format( number.doubleValue() );

      String pad = "";
      for( int i = 1; i <= this.maskLen - s.length(); i++ ) {
         pad = pad + " ";
      }

      return pad + s;      
   }
}
