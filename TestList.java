import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestList {

	public TestList() {
	}

	public void traverseList( List<String> lst ) {
		for( String s : lst ) {
			System.out.println( s );
		}
		System.out.println( "done" );
	}


   public static void main(String[] args) {
      List<String> list = new ArrayList<String>();

      list.add( "23" );
      list.add( "30" );
      list.add( "31" );
      list.add( "A3" );

		TestList tl = new TestList();
		tl.traverseList( list );
		list = null;
		tl.traverseList( list );
   }


	public static void main4( String[] args ) {
		main3( args );
	}


   public static void main2(String[] args) {

      System.out.println("started email list test");
      
      List<String> emailList = new ArrayList<String>();
      
      emailList.add( "mike.bro@att.net" );
      emailList.add( "yomikebro@gmail.com" );
      emailList.add( "mike.bro@att.net" );  
      emailList.add( "michael.brothers@trinet.com" );

      
      for( String s : emailList ) {
         System.out.println( "::" + s + "::" );
         // createEmailRequest
      }
   
      System.out.println( "toString():" + emailList.toString() + ":" );
      System.out.println( "List contains " + emailList.size() + " elements." );
   }


   public static void main3(String[] args) {
		// test when parameter passed to addAll method is null
      System.out.println("started null parm test test");
      
      List<String> emailList = new ArrayList<String>();
		Collection<String> someColl = null;
      
      emailList.add( "mike.bro@att.net" );
      emailList.add( "yomikebro@gmail.com" );
      emailList.add( "mike.bro@att.net" );  
      emailList.add( "michael.brothers@trinet.com" );

      emailList.addAll( someColl );
/*  caused:
Exception in thread "main" java.lang.NullPointerException
        at java.util.ArrayList.addAll(ArrayList.java:581)
        at TestList.main3(TestList.java:60)
        at TestList.main(TestList.java:7)
*/   

      System.out.println( "toString():" + emailList.toString() + ":" );
      System.out.println( "List contains " + emailList.size() + " elements." );
   }


}