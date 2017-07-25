package ps.dao;

import java.util.List;
import ps.hcm.PsJob;

/* JOB dao interface  */

public interface JobDao {

   public List<PsJob> getEmployeesOfCompany( String company );

}