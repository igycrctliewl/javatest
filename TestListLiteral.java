import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestListLiteral {

	private static final String[] ALL_STATE_VALUES = new String[] 
				{"AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL"
				,"GA","HI","IA","ID","IL","IN","KS","KY","LA","MA"
				,"MD","ME","MI","MN","MO","MS","MT","NC","ND","NE"
				,"NH","NJ","NM","NV","NY","OH","OK","OR","PA","PR"
				,"RI","SC","SD","TN","TX","UT","VA","VT","WA","WI"
				,"WV","WY"};

	public static void main(String[] args) {
		Set<String> myStates = new HashSet<String>(Arrays.asList(ALL_STATE_VALUES));

		myStates.remove( "HI" );
		myStates.remove( "MA" );

		int i = 0;
		for( String state : myStates ) {
			System.out.println( "" + ++i + " > " + state );
		}

	}
}