public class Sing {
   
   private static int nextInstance = 1001;
   private static final Sing singleton = new Sing();
   private static int getNextInstanceId() {
      return nextInstance++;
   }

   public static Sing getInstance() {
      return singleton;
   }


   private int instanceID;

   private Sing() {
      System.out.println( "new Sing created");
      this.instanceID = getNextInstanceId();
      System.out.println( "next instanceID now " + nextInstance );
   }

   public int getInstanceId() {
      return this.instanceID;
   }   
}