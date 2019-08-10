
public class ClassTest {

	public static String getMessage() {
		return "message from ClassTest";
	}

	public static void main(String[] args) {
		ClassTest ct = new ClassTest();
		System.out.println( ct );
		System.out.println( ClassTest.getMessage() );
	}
}

