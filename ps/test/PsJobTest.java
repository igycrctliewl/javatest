package ps.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ps.benefits.LifeInsuranceBenefit;
import ps.hcm.PsJob;

/* unit test PsJob class  */

public class PsJobTest {
   
   public static void main(String[] args) {
      List<PsJob> jobRecords = new ArrayList<PsJob>();

      jobRecords.add( new PsJob( new BigDecimal( "320000" ), new BigDecimal( "305000" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "136000" ), new BigDecimal( "131000" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "52800" ) ));
      jobRecords.add( new PsJob( new BigDecimal( "157500" ), new BigDecimal( "152000" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "82600" ), new BigDecimal( "75500" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "60000" ), BigDecimal.ZERO ) );

      for( PsJob j : jobRecords ) {      
         System.out.println( j.toString() );
         System.out.println( "     " + j.getAnnualRate() );
         System.out.println( "     " + j.getAbbr() );
      }
      
      LifeInsuranceBenefit life = new LifeInsuranceBenefit( "23", "000TMB" );
      System.out.println( "life: " + life.toString() );
   }
  
}
