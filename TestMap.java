import java.util.HashMap;
import java.util.Map;

public class TestMap {
   public static void main(String[] args) {

		Map<Object,Object> tnetMap = new HashMap<Object,Object>();

		tnetMap.put("0000123", "DT");
		tnetMap.put("0000142", "MB");

      System.out.println( "map returned " + tnetMap.get( "0000142" ) + "<");

   }
}



