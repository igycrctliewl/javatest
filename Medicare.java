import java.lang.*;
import java.math.*;

public class Medicare {

   private static final BigDecimal THRESHOLD = new BigDecimal("200000");
   private static final BigDecimal STD_MED_RATE = new BigDecimal("0.0145");
   private static final BigDecimal ADDL_MED_RATE = new BigDecimal("0.009");

   public BigDecimal bdYtdStdBase;
   public BigDecimal bdYtdStdTax;
   public BigDecimal bdYtdAddlBase;
   public BigDecimal bdYtdAddlTax;
   public BigDecimal bdCurrStdBase;
   public BigDecimal bdCurrStdTax;
   public BigDecimal bdCurrAddlBase;
   public BigDecimal bdCurrAddlTax;

   public double ytdStdBase;
   public double ytdAddlBase;
   public double ytdStdTax;
   public double ytdAddlTax;
   public double currStdBase;
   public double currAddlBase;
   public double currStdTax;
   public double currAddlTax;

   Medicare () {
      this.bdYtdStdBase  = new BigDecimal("0").setScale(2);
      this.bdYtdStdTax   = new BigDecimal("0").setScale(2);
      this.bdYtdAddlBase  = new BigDecimal("0").setScale(2);
      this.bdYtdAddlTax   = new BigDecimal("0").setScale(2);
      this.bdCurrStdBase  = new BigDecimal("0").setScale(2);
      this.bdCurrStdTax  = new BigDecimal("0").setScale(2);
      this.bdCurrAddlBase = new BigDecimal("0").setScale(2);
      this.bdCurrAddlTax = new BigDecimal("0").setScale(2);

      this.ytdStdBase = 0;
      this.ytdAddlBase = 0;
      this.currStdBase = 0;
      this.currAddlBase = 0;
      this.currStdTax = 0;
      this.currAddlTax = 0;
   }

   public void calcConverted( String currBase ) {
      this.bdCurrStdBase = new BigDecimal( currBase ).setScale(2);

      // first step is always to calculate standard Medicare
      this.bdCurrStdTax = this.bdCurrStdBase.multiply( Medicare.STD_MED_RATE ).setScale(2, BigDecimal.ROUND_HALF_UP);

      if( this.bdCurrStdBase.compareTo( BigDecimal.ZERO ) >= 0 ) {
         // current transaction amount is positive
         if( this.bdYtdStdBase.add( this.bdCurrStdBase ).compareTo( Medicare.THRESHOLD ) > 0 ) {
            //System.out.println( " over threshold " );
      
            // then calculate additional medicare
            this.bdCurrAddlBase = this.bdYtdStdBase.add( this.bdCurrStdBase ).subtract( Medicare.THRESHOLD );
            if( this.bdCurrAddlBase.compareTo( this.bdCurrStdBase ) > 0 ) {
               this.bdCurrAddlBase = this.bdCurrStdBase.setScale(2);
            }
            this.bdCurrAddlTax = this.bdCurrAddlBase.multiply( Medicare.ADDL_MED_RATE ).setScale(2, BigDecimal.ROUND_HALF_UP);
         } else {
            //System.out.println(" under threshold ");
            // under 200000, no additional medicare calculation required
            this.bdCurrAddlBase = BigDecimal.ZERO.abs();
            this.bdCurrAddlTax = BigDecimal.ZERO.abs();
         }
      } else {
         /* the current transaction amount is negative
         /*  process as reversal and take care to handle additional medicare
         /**/

         if( this.bdYtdStdBase.compareTo( Medicare.THRESHOLD ) > 0 ) {
            //System.out.println(" reversal over threshold before");
            // employee was over threshold before reversal; calculate addl medicare reversal
            if( this.bdYtdStdBase.add( this.bdCurrStdBase ).compareTo( Medicare.THRESHOLD ) > 0 ) {
               //System.out.println(" reversal over threshold after");
               // employee is still over threshold after reversal; reverse at full additional medicare rate
               this.bdCurrAddlBase = this.bdCurrStdBase.setScale(2);
               this.bdCurrAddlTax = this.bdCurrAddlBase.multiply( Medicare.ADDL_MED_RATE ).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
               //System.out.println(" reversal under threshold after");
               // employee is back under threshold.  calculate the additional base and tax
               this.bdCurrAddlBase = this.bdCurrStdBase.add( Medicare.THRESHOLD.subtract( this.bdYtdStdBase.add( this.bdCurrStdBase )));
               this.bdCurrAddlTax = this.bdCurrAddlBase.multiply( Medicare.ADDL_MED_RATE ).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
         } else {
            //System.out.println(" reversal under threshold before");
            // employee was under threshold before reversal; no addl medicare
            this.bdCurrAddlBase = BigDecimal.ZERO.abs();
            this.bdCurrAddlTax = BigDecimal.ZERO.abs();
         }
      }

      this.bdYtdStdBase = this.bdYtdStdBase.add( this.bdCurrStdBase );
      this.bdYtdStdTax  = this.bdYtdStdTax.add( this.bdCurrStdTax );
      this.bdYtdAddlBase = this.bdYtdAddlBase.add( this.bdCurrAddlBase );
      this.bdYtdAddlTax  = this.bdYtdAddlTax.add( this.bdCurrAddlTax );

      /* test YTD amounts to determine whether any adjustments are required */
      BigDecimal adj = this.bdYtdStdBase.multiply( Medicare.STD_MED_RATE ).setScale(2, BigDecimal.ROUND_HALF_UP).subtract( this.bdYtdStdTax );
      //System.out.println("                   adjustment:" + adj );
      this.bdYtdStdTax  = this.bdYtdStdTax.add( adj );
      this.bdCurrStdTax = this.bdCurrStdTax.add( adj );

      adj = this.bdYtdAddlBase.multiply( Medicare.ADDL_MED_RATE ).setScale(2, BigDecimal.ROUND_HALF_UP).subtract( this.bdYtdAddlTax );
      //System.out.println("                   adjustment:" + adj );
      this.bdYtdAddlTax  = this.bdYtdAddlTax.add( adj );
      this.bdCurrAddlTax = this.bdCurrAddlTax.add( adj );
   }

   /**
   * @deprecated
   * this method replaced by calcConverted( String currBase ) which uses BigDecimal rather than double
   */
   @Deprecated   
   public void calcConverted( double currBase, double currTax ) {
      if( this.ytdStdBase + currBase > 200000 ) {
      // first calculate standard Medicare
         this.currStdBase = Round( currBase );
         this.currStdTax = Round( this.currStdBase * 0.0145 );

      // then calculate additional medicare
         this.currAddlBase = Round( this.ytdStdBase + currBase - 200000 );
         if( this.currAddlBase > currBase ) {
            this.currAddlBase = Round( currBase );
         }
         this.currAddlTax = Round( this.currAddlBase * 0.009 );
      } else {
      // under 200000, just calculate standard Medicare
         this.currStdBase = Round( currBase );
         this.currStdTax = Round( this.currStdBase * 0.0145 );
         this.currAddlBase = 0;
         this.currAddlTax = 0;
      }

      this.ytdStdBase = Round( this.ytdStdBase + this.currStdBase );
      this.ytdAddlBase = Round( this.ytdAddlBase + this.currAddlBase );
   }

   /**
   * @deprecated
   * this method is only called by deprecated version of <code>calcConverted</code>
   */
   @Deprecated
   public static double Round( double dblValue ) {
      double d = ( dblValue * 100 ) + .5;
      int hold = new Double( d ).intValue();
      return new Integer( hold ).doubleValue() / 100;
   }
}
