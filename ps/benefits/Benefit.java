package ps.benefits;

public abstract class Benefit {
   protected String planType;
   protected String benefitPlan;

   public String toString() {
      return "plan type " + this.planType + ", benefit plan " + this.benefitPlan;
   }
}