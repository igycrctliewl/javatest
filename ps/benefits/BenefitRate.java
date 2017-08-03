package ps.benefits;

public class BenefitRate {
   private String rateTblId;

   public BenefitRate( String rateTblId ) {
      this.rateTblId = rateTblId;
   }
   
   public String toString() {
      return "rateTblId:" + this.rateTblId;
   }
}