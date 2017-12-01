
public class ClassTest {

   public static String getMessage() {
		return "message from ClassTest";
	}

   public static void main(String[] args) {

      ClassTest ct = new ClassTest();
      System.out.println( ct );
     
      ClassTest ct2 = new ClassTest();
      System.out.println( ct2 );
     
   }
}

