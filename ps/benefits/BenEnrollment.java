package ps.benefits;

/* Java representation of benefit enrollment  */

public class BenEnrollment {

   private String emplid;
   private String planType;
   private String benefitPlan;


   public BenEnrollment( String emplid, String planType, String benefitPlan ) {
      this.emplid = emplid;
      this.planType = planType;
      this.benefitPlan = benefitPlan;
   }

   public String getEmplid() {
      return this.emplid;
   }

   public String getPlanType() {
      return this.planType;
   }

   public String getBenefitPlan() {
      return this.benefitPlan;
   }

   public String toString() {
      return "ee:" + this.getEmplid() +
             ":planType:" + this.getPlanType() +
             ":plan:" + this.getBenefitPlan();
   }
}