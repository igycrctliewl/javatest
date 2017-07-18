package ps.benefits;

public class LifeInsuranceBenefit extends Benefit {

   @Deprecated
   private String rateTblId;

   private BenefitRate rate;

   public LifeInsuranceBenefit( String planType, String benefitPlan ) {
      super( planType, benefitPlan );
      System.out.println( "LifeInsuranceBenefit constructor" );
   }

   @Deprecated
   public String getRateTblId() {
      return this.rateTblId;
   }

   @Deprecated
   public void setRateTblId( String newRateTblId ) {
      this.rateTblId = newRateTblId;
   }



   public BenefitRate getRate() {
      return this.rate;
   }

   public void setRate( String newRateTblId ) {
      this.rate = new BenefitRate( newRateTblId );
   }



   public String toString() {
      String s = super.toString();
      if( this.rate == null ) {
      // continue
      } else {
         s = s.concat( ":" + this.rate.toString() );
      }
      return s;
   }

}