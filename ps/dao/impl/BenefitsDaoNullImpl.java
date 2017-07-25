package ps.dao.impl;

import java.util.ArrayList;
import java.util.List;
import ps.benefits.BenEnrollment;

/* Benefits dao null interface; each call returns empty record set  */

public class BenefitsDaoNullImpl implements ps.dao.BenefitsDao {

   public BenefitsDaoNullImpl() {
      System.out.println( "BenefitsDaoNullImpl constructor" );
   }


   public List<BenEnrollment> getLifeADDEnrollments( String company ) {
      return new ArrayList<BenEnrollment>();
   }
}
