package Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeMap {
	HashMap<Integer, String> map = new HashMap<>();

	public void add(int id, String name) {
		map.put(id, name);
	}

	public void printMapElements() {
		for (Map.Entry<Integer, String> mapElement : map.entrySet()) {
			int key = mapElement.getKey();
			String value = mapElement.getValue();
			System.out.println(key + " : " + value);
		}
	}

	public static void main(String[] args) {
		EmployeeMap empMap = new EmployeeMap();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of employees: ");
		int n = sc.nextInt();
		sc.nextLine(); //Consume the leftover newline character
		String name = " ";
		int id = 0;
		while (n-- > 0) {
			System.out.print("Enter Id: ");
			id = sc.nextInt();
			sc.nextLine(); // consume the newline character after reading the ID
			System.out.print("Enter name ");
			name = sc.nextLine();
			empMap.add(id, name);
		}
		sc.close();
		empMap.printMapElements();
	}

}
