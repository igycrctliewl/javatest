
public class BenefitGroup {

	private String benefitProgram;

	public BenefitGroup( String benefitProgram ) {
		this.benefitProgram = benefitProgram;
	}

	@Override
	public String toString() {
		return this.getClass() + ":[" + this.benefitProgram + "]";
	}


	public String getBenefitProgram() {
		return this.benefitProgram;
	}

}