package ps.dao;

import java.util.List;
import ps.dao.JobDao;
import ps.dao.impl.JobDaoHCImpl;

/* Dao object factory  */

public class DaoFactory {

   private static final JobDao jobDao = new JobDaoHCImpl();

   public static JobDao getJobDao() {
      System.out.println( ">>>> getJobDao" );
      return DaoFactory.jobDao;
   }

}