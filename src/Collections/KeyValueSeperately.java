package Collections;

import java.util.HashMap;
import java.util.Map;
public class KeyValueSeperately {

	public static void main(String[] args) {
		HashMap <Integer, String>  map = new HashMap<>();
		map.put(1, "Arpita Sahu");
		map.put(2, "Laxmi");
		map.put(3, "Pratibha");
		map.put(4,"Mansi Panthari");
		for (Map.Entry<Integer, String> mapElement : map.entrySet()) {
			int key = mapElement.getKey();
			String value = mapElement.getValue();
			System.out.println(key + " : " + value);
		}
	}
}
