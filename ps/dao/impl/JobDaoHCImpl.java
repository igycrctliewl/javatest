package ps.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ps.dao.JobDao;
import ps.hcm.PsJob;

/* JOB dao HC (hardcoded) data implementation  */

public class JobDaoHCImpl implements JobDao {

   public List<PsJob> getEmployeesOfCompany( String company ) {

      return getSampleData();
   }



   private List<PsJob> getSampleData() {
      List<PsJob> jobRecords = new ArrayList<PsJob>();

      jobRecords.add( new PsJob( new BigDecimal( "320000" ), new BigDecimal( "321321" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "136000" ), new BigDecimal( "131000" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "52800" ) ));
      jobRecords.add( new PsJob( new BigDecimal( "157500" ), new BigDecimal( "152000" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "82600" ), new BigDecimal( "75500" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "60000" ), BigDecimal.ZERO ) );

      //simulate some I/O delay
      try {
         Thread.sleep( 3000 );
      } catch ( Exception e ) {
         e.printStackTrace();
      }

      return jobRecords;
   }


}