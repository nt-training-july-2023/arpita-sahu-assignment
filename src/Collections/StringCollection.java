package Collections;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class StringCollection {
	public static void main(String[] args) {
		LinkedHashSet<String> linkedSet = new LinkedHashSet<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Elements");
		for (int i = 0; i < 5; i++)
			linkedSet.add(sc.nextLine());
		System.out.println("LinkedSet size: " + linkedSet.size());
		System.out.print("Enter String to remove ");
		String remove = sc.nextLine();
		System.out.println("Remove Element: " + linkedSet.remove(remove));
		System.out.print("Enter Element to check if it exist or not ");
		String check = sc.nextLine();
		if (linkedSet.contains(check))
			System.out.println(check + " is exist");
		else
			System.out.println(check + " is not exist");
		System.out.print(linkedSet);
		sc.close();
	}

}
