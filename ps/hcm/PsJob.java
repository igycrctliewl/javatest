package ps.hcm;

import java.math.BigDecimal;

/* Java representatio of PeopleSoft JOB record  */

public class PsJob {

   private static int nextEmplid = 1001;

   private String emplid;
   private BigDecimal annualRate;
   private BigDecimal abbr;
   private String benefitProgram;


   public static int getNextEmplid() {
      return nextEmplid++;
   }

   public PsJob() {
      this.emplid = Integer.toString( getNextEmplid() );
      this.annualRate = BigDecimal.ZERO;
      this.abbr = BigDecimal.ZERO;
      this.benefitProgram = "";
   }

   public PsJob( BigDecimal annualRate ) {
      this.emplid = Integer.toString( getNextEmplid() );
      this.annualRate = annualRate;
      this.abbr = annualRate;
   }

   public PsJob( BigDecimal annualRate, BigDecimal abbr ) {
      this.emplid = Integer.toString( getNextEmplid() );
      this.annualRate = annualRate;
      this.abbr = abbr;
   }

   public String getEmplid() {
      return this.emplid;
   }

   public BigDecimal getAnnualRate() {
      return this.annualRate;
   }

   public BigDecimal getAbbr() {
      return this.abbr;
   }

   public String toString() {
      return "ee:" + this.getEmplid() +
             ":annualrt:" + this.getAnnualRate().toString() +
             ":abbr:" + this.getAbbr().toString();
   }
}