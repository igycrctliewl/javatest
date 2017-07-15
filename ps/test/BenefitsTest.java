package ps.test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import ps.benefits.BenEnrollment;
import ps.dao.BenefitsDao;
import ps.dao.impl.BenefitsDaoHCImpl;

/* unit test PsJob class  */

public class BenefitsTest {

   public static void main(String[] args) {
      BenefitsDao benDao = new BenefitsDaoHCImpl();
      List<BenEnrollment> enrls = new ArrayList<BenEnrollment>();
      enrls = benDao.getLifeADDEnrollments("FXS");

      Set<String> benPlans = new HashSet<String>();

      for( BenEnrollment b : enrls ) {
         benPlans.add( b.getBenefitPlan() );
         System.out.println( b.toString() );
      }

      for( String p : benPlans ) {
         System.out.println( p );
      }

   }

}
