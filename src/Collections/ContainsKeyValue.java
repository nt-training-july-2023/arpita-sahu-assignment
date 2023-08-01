package Collections;

import java.util.HashMap;
import java.util.Scanner;

public class ContainsKeyValue {
	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "Arpita");
		map.put(2, "Mansi Panthari");
		map.put(3, "Omi");
		map.put(4, "Yamini");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter key to find ");
		int key = sc.nextInt();
		sc.nextLine();
		if (map.containsKey(key))
			System.out.println("Map Contains Key : " + key);
		else
			System.out.println("Map doesn't contains key : " + key);
		System.out.print("Enter value to find ");
		String value = sc.nextLine();
		if (map.containsValue(value))
			System.out.println("Map Contains value : " + value);
		else
			System.out.println("Map doesn't contains value : " + value);
		sc.close();
	}

}
