package ps.test;

import java.util.List;
import ps.benefits.BenefitRate;
import ps.benefits.CoverageFormula;
import ps.benefits.LifeInsuranceBenefit;
import ps.dao.JobDao;
import ps.dao.DaoFactory;
import ps.hcm.PsJob;

/* unit test PsJob class  */

public class PsJobTest {

   public static void main(String[] args) {
      mainBenefits( args );
   }
   
   public static void mainBenefits(String[] args) {
      BenefitRate rt = new BenefitRate( "0ORPLF" );
      CoverageFormula cvg = new CoverageFormula( "AMB0000123" );
      LifeInsuranceBenefit life = new LifeInsuranceBenefit( "23", "000TMB" );
      life.setRate( "0ORPLF" );
      System.out.println( "rate: " + rt.toString() );
      System.out.println( "cvrg: " + cvg.toString() );
      System.out.println( "life: " + life.toString() );
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
