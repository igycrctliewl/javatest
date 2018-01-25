import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestPlanTypeSet {

	private Map<String, String> planTypeToDpType;
	private Set<String> planTypes;

	public TestPlanTypeSet() {
		planTypes = new HashSet<>();
		planTypeToDpType = new HashMap<>();
		planTypeToDpType.put( "10", "15" );
		planTypeToDpType.put( "11", "16" );
		planTypeToDpType.put( "14", "17" );
		planTypeToDpType.put( "1D", "1E" );
		planTypeToDpType.put( "1V", "1U" );
		planTypeToDpType.put( "1G", "1H" );
	}

	public void addToSet( String planType ) {
		planTypes.add( planType );
		String dpPlanType = planTypeToDpType.get( planType );
		if( dpPlanType != null ) {
			planTypes.add( dpPlanType );
		}
	}

	public Set getPlanTypeSet() {
		return this.planTypes;
	}


   public static void main( String[] args ) {

		TestPlanTypeSet obj = new TestPlanTypeSet();

		obj.addToSet( "A3" );
		obj.addToSet( "1V" );
		obj.addToSet( "11" );
		obj.addToSet( "31" );
		obj.addToSet( "10" );
		obj.addToSet( "1D" );
		obj.addToSet( "14" );
		obj.addToSet( "23" );
		obj.addToSet( "30" );

      System.out.println( "print set as a string: " + obj.getPlanTypeSet().toString() );

   }
}
