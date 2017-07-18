package ps.benefits;

public abstract class Benefit {
   protected String planType;
   protected String benefitPlan;

   public Benefit( String planType, String benefitPlan ) {
      //System.out.println( "Benefit constructor" );
      this.planType = planType;
      this.benefitPlan = benefitPlan;
   }

   public String toString() {
      return "planType:" + this.planType + ":plan:" + this.benefitPlan;
   }
}