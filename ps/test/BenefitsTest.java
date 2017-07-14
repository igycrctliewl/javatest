package ps.test;

import java.util.ArrayList;
import java.util.List;
import ps.benefits.BenEnrollment;
import ps.dao.BenefitsDao;
import ps.dao.impl.BenefitsDaoHCImpl;

/* unit test PsJob class  */

public class BenefitsTest {

   public static void main(String[] args) {
      BenefitsDao benDao = new BenefitsDaoHCImpl();
      List<BenEnrollment> enrls = new ArrayList<BenEnrollment>();
      enrls = benDao.getLifeADDEnrollments("FXS");

      for( BenEnrollment b : enrls ) {
         System.out.println( b.toString() );
      }

   }

}
