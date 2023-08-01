package Collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RemoveKeyValuePair {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put("D", 20); 
        int removeValue = 20; 
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() == removeValue) {
                iterator.remove(); 
            }
        }
        System.out.println(map);
    }
}
