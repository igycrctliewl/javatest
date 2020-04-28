
public class TestHSA {

	public static void main( String[] args ) {
		BenDefnOptnHSA optn = new BenDefnOptnHSA();
		optn.benefitPlan = "004XYZ";
		optn.pfClient = "9EON";

		System.out.println( optn.benefitPlan );
		System.out.println( optn.pfClient );
	}
}