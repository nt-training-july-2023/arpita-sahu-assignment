package Collections;

import java.util.HashMap;
import java.util.Scanner;

public class HashMapCRUD {
	HashMap<Integer, String> map = new HashMap<>();

	void add(int id, String name) {
		map.put(id, name);
		System.out.println(id + " added successfully !");
	}

	void delete(int id) {
		map.remove(id);
		System.out.println(id + " deleted Successfully ");

	}

	void find(int id) {
		String value = map.get(id);
		if (value != null) {
			System.out.println("Value of " + id + ": " + value);
		} else {
			System.out.println("Name with ID " + id + " not found.");
		}

	}

	void update(int id, String value) {
		map.put(id, value);
		System.out.println(value + " updated Successfully");

	}

	public static void main(String[] args) {
		HashMapCRUD mapCrud = new HashMapCRUD();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. ADD Employees");
			System.out.println("2. DELETE Employees");
			System.out.println("3. GET Employees");
			System.out.println("4. Update Employees");
			System.out.println("5. Exit");
			System.out.print("Enter Choice ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Enter number of employees to add ");
				int n = sc.nextInt();
				sc.nextLine();
				while (n-- > 0) {
					System.out.print("Enter Id: ");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter Name: ");
					String name = sc.nextLine();
					mapCrud.add(id, name);
				}
				break;
			case 2:
				System.out.print("Enter Key to delete: ");
				int key = sc.nextInt();
				mapCrud.delete(key);
				break;
			case 3:
				System.out.print("Enter Key to find value: ");
				int key1 = sc.nextInt();
				sc.nextLine();
				mapCrud.find(key1);
				break;
			case 4:
				System.out.print("Enter key to update value");
				int key2 = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Value to update");
				String value = sc.nextLine();
				mapCrud.update(key2, value);
				break;
			case 5:
				sc.close();
				System.exit(0);
			default : System.out.println("Invalid Choice");
			}
		}
		
	}
	
}
