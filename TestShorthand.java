
public class TestShorthand {
   public static void main(String[] args) {
      StubClass sc = new StubClass();
      //String flag = ( sc.getAcaFplOpted() == 1 ) ? "Y" : "N";
      System.out.println( "new ===>  " + (( sc.getAcaFplOpted() == 1 ) ? "Y" : "N" ) );
   }

   static class StubClass {
      public int getAcaFplOpted() {
         return 1;
      }
   }
}