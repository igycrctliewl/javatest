public class TestScope {
   public static void main( String[] args ) {

		{
			int a = 1;
			System.out.println( "in first block and a is " + a );
		}

		{
			int a = 5;
			System.out.println( "in second block and a is " + a );
		}

		{
			int a = 9;
			System.out.println( "in third block and a is " + a );
		}
	}

}