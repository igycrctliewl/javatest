
import java.math.BigDecimal;

public class MirrorPlanData {

	private String planType;
	private String stdPlan;
	private String mirrPlan;
	private String covrgCd;
	private BigDecimal fplCost;
	private BigDecimal planCost;
	private BigDecimal providerRate;

	public MirrorPlanData( String planType, String stdPlan, String mirrPlan
							, String covrgCd, BigDecimal fplCost, BigDecimal planCost, BigDecimal providerRate ) {

      this.planType = planType;
      this.stdPlan = stdPlan;
      this.mirrPlan = mirrPlan;
      this.covrgCd = covrgCd;
      this.fplCost = fplCost;
      this.planCost = planCost;
      this.providerRate = providerRate;
		
	}

	public String getMapKey() {
		return this.planType + this.stdPlan;
	}

	public String getPlanType() {
		return this.planType;
	}

	public String getMirrorPlan() {
		return this.mirrPlan;
	}

	public BigDecimal getEmployeeCost() {
		return this.fplCost;
	}

	public BigDecimal getCompanyCost() {
		return this.planCost.subtract( this.fplCost );
	}

	public BigDecimal getProviderRate() {
		return this.providerRate;
	}
}
