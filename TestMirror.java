
public class TestMirror {

   public static void main(String[] args) {
      Company c = new Company( "S3Z", "9M7G", "Q1" );
      System.out.println( c );

		BenefitGroup g = new BenefitGroup( "001E4R" );
		System.out.println( g );

		String effdt = "01-JAN-2018";

		ACAMirrorPlan mirr = new ACAMirrorPlan( c, g, effdt );
		System.out.println( mirr.toString() );
   }
   
}