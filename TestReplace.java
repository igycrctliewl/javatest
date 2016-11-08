
public class TestReplace {

    private static final String NBSP = "\u00a0";
    
    public static void main(String args[]) {
 
        String planName = "NY UHC Premium PPO";
        System.out.print("plan name:" + planName.replaceAll(" ",NBSP) + ":");
        
    }
}

