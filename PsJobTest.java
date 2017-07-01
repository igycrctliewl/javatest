
import java.math.BigDecimal;

/* unit test PsJob class  */

public class PsJobTest {
   
   public static void main(String[] args) {
      PsJob job1 = new PsJob();
      PsJob job2 = new PsJob();
      PsJob job3 = new PsJob( new BigDecimal( "125000" ) );
      PsJob job4 = new PsJob( new BigDecimal( "125000" ) );
      PsJob job5 = new PsJob( new BigDecimal( "125000" ) );
      
      System.out.println( "job1:" + job1.toString() );
      System.out.println( "job2:" + job2.toString() );
      System.out.println( "job3:" + job3.toString() );
   }
   
}