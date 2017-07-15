package ps.benefits;

public class LifeInsuranceBenefit extends Benefit {

   private String rateTblId;

   public LifeInsuranceBenefit( String planType, String benefitPlan ) {
      super.planType = planType;
      super.benefitPlan = benefitPlan;
   }

   public String getRateTblId() {
      return this.rateTblId;
   }

   public void setRateTblId( String newRateTblId ) {
      this.rateTblId = newRateTblId;
   }

   public String toString() {
      return super.toString() + ":rateTblId:" + this.getRateTblId();
   }

}