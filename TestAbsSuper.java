import java.math.BigDecimal;

public abstract class TestAbsSuper {

   /* for life insurance, we pass the salary estimate and the UI will
   /* calculate the monthly cost.  
   /*   This is an opportunity for future development:
   /*     perform all the cost calculations in the API
   /**/
   protected static BigDecimal SALARY_000TM9 = new BigDecimal("50000");
   protected static BigDecimal SALARY_000SRO = new BigDecimal("200000");
   protected static BigDecimal SALARY_000TMA = new BigDecimal("400000");
   protected static BigDecimal SALARY_000TMB = new BigDecimal("750000");

   /* for STD, the amount here is the monthly cost per employee
   /**/
   protected static BigDecimal RATE_000SRS = new BigDecimal("15");
   protected static BigDecimal RATE_000TMC = new BigDecimal("9");
   protected static BigDecimal RATE_000TMD = new BigDecimal("22.5");
   protected static BigDecimal RATE_000TME = new BigDecimal("5");
   protected static BigDecimal RATE_000WA9 = new BigDecimal("15");
   protected static BigDecimal RATE_000WAB = new BigDecimal("9");
   protected static BigDecimal RATE_000WAA = new BigDecimal("22.5");

   /* for LTD, we pass the salary estimate and the UI will
   /* calculate the monthly cost.  
   /*   This is an opportunity for future development:
   /*     perform all the cost calculations in the API
   /**/
   protected static BigDecimal SALARY_000SRT = new BigDecimal("250000");

   /* for commuter benefit, the amount here is the monthly cost per employee
   /**/
   protected static BigDecimal RATE_000W46 = new BigDecimal("5.85");

   protected abstract void doSomeWork();
}

