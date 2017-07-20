package ps.dao;

import java.util.List;
import ps.dao.JobDao;
import ps.dao.impl.BenefitsDaoHCImpl;
import ps.dao.impl.BenefitsDaoNullImpl;
import ps.dao.impl.JobDaoHCImpl;
import ps.dao.impl.JobDaoNullImpl;

/* Dao object factory  */

public class DaoFactory {

   private static final JobDao jobDao = new JobDaoHCImpl();
   //private static final JobDao jobDao = new JobDaoNullImpl();

   public static JobDao getJobDao() {
      System.out.println( ">>>> DaoFactory.getJobDao" );
      return DaoFactory.jobDao;
   }


   private static final BenefitsDao benefitsDao = new BenefitsDaoHCImpl();
   //private static final BenefitsDao benefitsDao = new BenefitsDaoNullImpl();

   public static BenefitsDao getBenefitsDao() {
      System.out.println( ">>>> DaoFactory.getBenefitsDao" );
      return DaoFactory.benefitsDao;
   }

}