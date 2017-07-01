package ps.hcm;

import java.math.BigDecimal;

/* Java representatio of PeopleSoft JOB record  */

public class PsJob {

   private static int nextEmplid = 1001;
   
   private int emplid;
   private BigDecimal annualRate;
   private BigDecimal abbr;   
   
   
   public static int getNextEmplid() {
      return nextEmplid++;
   }
   
   public PsJob() {
      this.emplid = getNextEmplid();
      this.annualRate = BigDecimal.ZERO;
      this.abbr = BigDecimal.ZERO;
   }
   
   public PsJob( BigDecimal annualRate ) {
      this.emplid = getNextEmplid();
      this.annualRate = annualRate;
      this.abbr = annualRate;
   }
   
   public PsJob( BigDecimal annualRate, BigDecimal abbr ) {
      this.emplid = getNextEmplid();
      this.annualRate = annualRate;
      this.abbr = abbr;
   }
   
   public int getEmplid() {
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