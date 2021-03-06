package ps.benefits;

public class LifeInsuranceBenefit extends Benefit {

   private BenefitRate rate;

   public LifeInsuranceBenefit( String planType, String benefitPlan ) {
      super( planType, benefitPlan );
      System.out.println( "LifeInsuranceBenefit constructor" );
   }

   public BenefitRate getRate() {
      return this.rate;
   }

   public void setRate( String newRateTblId ) {
      this.rate = new BenefitRate( newRateTblId );
   }

   public void setRate( BenefitRate newRate ) {
      this.rate = newRate;
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