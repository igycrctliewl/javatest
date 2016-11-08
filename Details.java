import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
public class Details {

   public static void main(String args[]) {

      HashMap<String, String> covrgCodes = new HashMap<String, String>();
      covrgCodes.put("employee", "Employee Only");
      covrgCodes.put("employeePlusOne", "Employee + 1");
      covrgCodes.put("family", "Family");

      
      
      /* Display content using Iterator*/
      Set set = covrgCodes.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
         System.out.println(mentry.getValue());
      }

      /* Get values based on key*/
      String var= covrgCodes.get("employee");
      System.out.println("Key was 'employee'  -- Value is:" + var + ":");

    //  /* Remove values based on key*/
    //  covrgCodes.remove(3);
    //  System.out.println("Map key and values after removal:");
    //  Set set2 = covrgCodes.entrySet();
    //  Iterator iterator2 = set2.iterator();
    //  while(iterator2.hasNext()) {
    //      Map.Entry mentry2 = (Map.Entry)iterator2.next();
    //      System.out.print("Key is: "+mentry2.getKey() + " & Value is: ");
    //      System.out.println(mentry2.getValue());
    //   }

   }
}