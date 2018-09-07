import java.util.HashSet;
import java.util.Set;

public class TestList {
	private Set<Integer> locations;

	public TestList() {
		locations = new HashSet<Integer>();
	}

	public int getSetSize() {
		return locations.size();
	}

	public void addRange( String from, String to ) {
		int intFrom = Integer.parseInt( from );
		int intTo;
		if( to.length() > 5 )
			intTo = Integer.parseInt( to.substring( 0, 5 ) );
		else
			intTo = Integer.parseInt( to );

		for( int k = intFrom; k <= intTo; k++ ) {
			System.out.println( "Adding " + k + " to set." );
			locations.add( k );
		}
	}

	public boolean isIncluded( Integer zip ) {
		return locations.contains( zip );
	}



	public static void main(String[] args) {

		TestList tl = new TestList();

		tl.addRange( "93673", "936739999" );
		tl.addRange( "93675", "936759999" );
		tl.addRange( "93675", "93675" );
		tl.addRange( "93701", "937129999" );
		tl.addRange( "93714", "937189999" );

		System.out.println( tl );
		System.out.println( tl.getSetSize() );

		System.out.println( tl.isIncluded( 94044 ) );
		System.out.println( tl.isIncluded( 93716 ) );

	}
}