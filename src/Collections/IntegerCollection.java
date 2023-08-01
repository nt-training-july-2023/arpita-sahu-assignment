package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IntegerCollection {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Element to add");
		for (int i = 0; i < 10; i++)
			list.add(sc.nextInt());
		Collections.reverse(list);
		ArrayList<Integer> updateList = new ArrayList<>();
		list.forEach((element) -> {
			if (element >= 50) {
				updateList.add(5);
			} else {
				updateList.add(element);
				System.out.print(element + " ");
			}
		});
		sc.close();
	}
}
