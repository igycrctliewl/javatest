
public class BenefitPlan {

	private String planType;
	private String benefitPlan;

	public BenefitPlan( String planType, String benefitPlan ) {
		this.planType = planType;
		this.benefitPlan = benefitPlan;
	}

	@Override
	public String toString() {
		return this.getClass() + ":[" + this.planType + "][" + this.benefitPlan + "]";
	}


	public String getPlanType() {
		return planType;
	}

	public String getId() {
		return this.benefitPlan;
	}

}