package ps.test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import ps.benefits.BenEnrollment;
import ps.benefits.LifeInsuranceBenefit;
import ps.dao.BenefitsDao;
import ps.dao.DaoFactory;

/* unit test PsJob class  */

public class BenefitsTest {

   public static void main(String[] args) {
      BenefitsDao benDao = DaoFactory.getBenefitsDao();
      List<BenEnrollment> enrls = new ArrayList<BenEnrollment>();
      enrls = benDao.getLifeADDEnrollments("FXS");

      Map<String, LifeInsuranceBenefit> benPlanMap = new HashMap<String, LifeInsuranceBenefit>();

      for( BenEnrollment b : enrls ) {
         LifeInsuranceBenefit pl = new LifeInsuranceBenefit( b.getPlanType(), b.getBenefitPlan() );
         pl.setRateTblId( "0dummy" );

         benPlanMap.put( b.getBenefitPlan(), pl  );
         System.out.println( b.toString() );
      }

      for( LifeInsuranceBenefit p : benPlanMap.values() ) {
         System.out.println( p.toString() );
      }

   }

}
