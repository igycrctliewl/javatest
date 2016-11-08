import java.util.ArrayList;

public class ListTest {

   public static void main(String[] args) {

      System.out.println("started main method");
      
      ArrayList<String> emailList = new ArrayList<String>();
      
      emailList.add( "mike.bro@att.net" );
      emailList.add( "yomikebro@gmail.com" );
      emailList.add( "mike.bro@att.net" );  
      emailList.add( "michael.brothers@trinet.com" );
      
      
      for( String s : emailList ) {
         System.out.println( "::" + s + "::" );
         // createEmailRequest
      }
   
      System.out.println( "toString():" + emailList.toString() + ":" );
   }

/*   
   public static void main(String[] args) {

      System.out.println("started main method");
      
      class EmailList<String> extends ArrayList<String> {
         public void testAndAdd( String addy ) {
            if( !super.contains( addy ) ) {
               super.add( addy );
            }
         }
      }
      
      EmailList<String> emailList = new EmailList<String>();
      
      emailList.testAndAdd( "mike.bro@att.net" );
      emailList.testAndAdd( "yomikebro@gmail.com" );
      emailList.testAndAdd( "mike.bro@att.net" );  //duplicate is ignored
      emailList.testAndAdd( "michael.brothers@trinet.com" );
      
      
      for( String s : emailList ) {
         System.out.println( "::" + s + "::" );
         // createEmailRequest
      }
   
      System.out.println( "toString():" + emailList.toString() + ":" );
   }
*/   
}   

