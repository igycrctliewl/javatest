
public class TestMirror {

   public static void main(String[] args) {
      Company c = new Company( "S3Z", "9M7G", "Q1" );
      System.out.println( c );

		BenefitGroup g = new BenefitGroup( "001E4R" );
		System.out.println( g );

		String effdt = "01-JAN-2018";

		ACAMirrorPlan mirr = new ACAMirrorPlan( c, g, effdt );
		System.out.println( mirr.toString() );

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

   }
   
}