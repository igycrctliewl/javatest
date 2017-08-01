package ps.hcm;

import java.math.BigDecimal;

/* Java representation of PeopleSoft JOB record  */

public class PsJob {

   private static int nextEmplid = 1001;

   private String emplid;
   private BigDecimal annualRate;
   private BigDecimal abbr;
   private String benefitProgram;

   // new fields added for insurance rate calculations
   private String sex;
   private int age;
   private String smoker;


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

   public PsJob( String emplid, BigDecimal annualRate, BigDecimal abbr, String benefitProgram ) {
      this.emplid = emplid;
      this.annualRate = annualRate;
      this.abbr = abbr;
      this.benefitProgram = benefitProgram;
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