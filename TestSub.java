import java.math.BigDecimal;

public class TestSub extends TestAbsSuper {

   public static void main( String[] args ) {
      TestSub a = new TestSub();
      a.doSomeWork();
   }

   protected void doSomeWork() {
      System.out.println(  "000TM9 : " + TestAbsSuper.SALARY_000TM9  );
      System.out.println(  "000SRO : " + TestAbsSuper.SALARY_000SRO  );
      System.out.println(  "000TMA : " + TestAbsSuper.SALARY_000TMA  );
      System.out.println(  "000TMB : " + TestAbsSuper.SALARY_000TMB  );

      System.out.println(  "000SRS : " + TestAbsSuper.RATE_000SRS  );
      System.out.println(  "000TMC : " + TestAbsSuper.RATE_000TMC  );
      System.out.println(  "000TMD : " + TestAbsSuper.RATE_000TMD  );
      System.out.println(  "000TME : " + TestAbsSuper.RATE_000TME  );
      System.out.println(  "000WA9 : " + TestAbsSuper.RATE_000WA9  );
      System.out.println(  "000WAB : " + TestAbsSuper.RATE_000WAB  );
      System.out.println(  "000WAA : " + TestAbsSuper.RATE_000WAA  );

      System.out.println(  "000SRT : " + TestAbsSuper.SALARY_000SRT );

      System.out.println(  "000W46 : " + TestAbsSuper.RATE_000W46  );
   }
}

