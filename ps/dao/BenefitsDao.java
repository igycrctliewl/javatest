package ps.dao;

import java.util.List;
import ps.benefits.BenEnrollment;

/* Benefits dao interface  */

public interface BenefitsDao {

   public List<BenEnrollment> getLifeADDEnrollments( String company );

}