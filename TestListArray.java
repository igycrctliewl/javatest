import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TestListArray {

	public static void main(String[] args) {
		List<String[]> result = new ArrayList<String[]>();

		String[] oneResult = new String[2];
		oneResult[0] = "UVA";
		oneResult[1] = "1G9P";
		result.add( oneResult );

		oneResult = new String[2];
		oneResult[0] = "UVB";
		oneResult[1] = "2G9P";
		result.add( oneResult );

		for( String[] r : result ) {
			System.out.printf( "BenProg: %s, BenClass: %s\n", r[0], r[1] );
		}

	}
}