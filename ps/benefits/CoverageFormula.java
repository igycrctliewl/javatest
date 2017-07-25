package ps.benefits;

public class CoverageFormula {
   
   private String formulaId;

   public CoverageFormula( String formulaId ) {
      this.formulaId = formulaId;
   }
   
   public String toString() {
      return "coverage formula " + this.formulaId;
   }
}