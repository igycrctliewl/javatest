package ps.benefits;

public abstract class Benefit {
   protected String planType;
   protected String benefitPlan;

   public String toString() {
      return "planType:" + this.planType + ":plan:" + this.benefitPlan;
   }
}