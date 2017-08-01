package ps.test;

import java.util.List;
import ps.dao.JobDao;
import ps.dao.DaoFactory;
import ps.hcm.PsJob;

/* unit test PsJob class  */

public class PsJobTest {

   public static void main(String[] args) {
      mainJob( args );
   }
   

   public static void mainJob(String[] args) {
      JobDao jobDao = DaoFactory.getJobDao();

      List<PsJob> jobRecords = jobDao.getEmployeesOfCompany( "FXS" );

      for( PsJob j : jobRecords ) {
         System.out.println( j.toString() );
         System.out.println( "     " + j.getAnnualRate() );
         System.out.println( "     " + j.getAbbr() );
      }
   }

}
