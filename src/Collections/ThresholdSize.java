package Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ThresholdSize {
	HashMap<Integer, String> map = new HashMap<>();

	public void add(int id, String name, int size) {
		map.put(id, name);
		if (map.size() >= size)
			printAndClearMap();
	}

	void printAndClearMap() {
		for (Map.Entry<Integer, String> mapElement : map.entrySet()) {
			int key = mapElement.getKey();
			String value = mapElement.getValue();
			System.out.println(key + " : " + value);
		}
		map.clear();
	}

	public static void main(String[] args) {
		ThresholdSize thresMap = new ThresholdSize();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Threshold Size: ");
		int size = sc.nextInt();
		System.out.print("Enter number of employees: ");
		int n = sc.nextInt();
		sc.nextLine(); // Consume the leftover newline character
		String name = " ";
		int id = 0;
		while (n-- > 0) {
			System.out.print("Enter Id: ");
			id = sc.nextInt();
			sc.nextLine(); // consume the newline character after reading the ID
			System.out.print("Enter name ");
			name = sc.nextLine();
			thresMap.add(id, name, size);
		}
		sc.close();
	}
}
