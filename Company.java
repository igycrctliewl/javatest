
public class Company {

	private String companyCode;
	private String pfClient;
	private String oeQuarter;

	public Company( String companyCode, String pfClient, String oeQuarter ) {
		this.companyCode = companyCode;
	   this.pfClient = pfClient;
		this.oeQuarter = oeQuarter;
	}

	@Override
	public String toString() {
		return this.getClass() + ":[" + this.companyCode + "][" + this.pfClient + "][" + this.oeQuarter + "]";
	}


	public String getCode() {
		return this.companyCode;
	}

	public String getPfClient() {
		return this.pfClient;
	}

	public String getQuater() {
		return this.oeQuarter;
	}
}