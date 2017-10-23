
import java.util.HashSet;
import java.util.Set;

public class TestMirror {

   public static void main(String[] args) {
      Company company = new Company( "S3Z", "9M7G", "Q1" );
      System.out.println( company );

		BenefitGroup group = new BenefitGroup( "001E4R" );
		System.out.println( group );

		String effdt = "01-JAN-2018";

		ACAMirrorPlan mirr = new ACAMirrorPlan( company, group, effdt );
		System.out.println( mirr.toString() );

		System.out.println( "Try plan type 10, plan 0021GP" );
		MirrorPlanData sample = mirr.get( "10", "0021GP" );
		if( sample != null ) {
			System.out.println( "Plan type:" + sample.getPlanType() );
			System.out.println( "Mirror plan:" + sample.getMirrorPlan() );
			System.out.println( "Employee cost:" + sample.getEmployeeCost() );
			System.out.println( "Company cost:" + sample.getCompanyCost() );
			System.out.println( "Provider rate:" + sample.getProviderRate() );
		} else {
			System.out.println( "No mirror plan" );
		}

		System.out.println( "Try plan type 10, plan 001EKS" );
		sample = mirr.get( "10", "001EKS" );
		if( sample != null ) {
			System.out.println( "Plan type:" + sample.getPlanType() );
			System.out.println( "Mirror plan:" + sample.getMirrorPlan() );
			System.out.println( "Employee cost:" + sample.getEmployeeCost() );
			System.out.println( "Company cost:" + sample.getCompanyCost() );
			System.out.println( "Provider rate:" + sample.getProviderRate() );
		} else {
			System.out.println( "No mirror plan" );
		}

		
		Set<String> planSet = new HashSet<>();
		//BenefitPlan bp = new BenefitPlan( "10", "001EKS" );
		planSet.add( "001EKS" );
		planSet.add( "0021DT" );
		planSet.add( "0021DV" );
		planSet.add( "0021DY" );
		planSet.add( "0021DZ" );
		planSet.add( "0021GO" );
		planSet.add( "0021GP" );
		planSet.add( "0021GQ" );
		System.out.println( "planSet contains " + planSet.size() + " entries." );

		// append mirror plans to the plan set list
		mirr.appendMedicalMirrorPlans( planSet );
		System.out.println( "after append, planSet contains " + planSet.size() + " entries." );
		for( String s : planSet ) {
			System.out.println( "plan: " + s );
		}



   }
   
}