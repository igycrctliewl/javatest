package ps.dao.impl;

import java.util.ArrayList;
import java.util.List;
import ps.dao.JobDao;
import ps.hcm.PsJob;

/* JOB dao null data implementation
   All dao calls are resolved and return an empty record set  */

public class JobDaoNullImpl implements JobDao {

   public JobDaoNullImpl() {
      System.out.println( "JobDaoNullImpl constructor" );
   }


   public List<PsJob> getEmployeesOfCompany( String company ) {
      return new ArrayList<PsJob>();
   }
}