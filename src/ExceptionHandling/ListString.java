package ExceptionHandling;

import java.util.ArrayList;
import java.util.Scanner;

public class ListString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		//list=null;
		list.add("Arpita");
		list.add("Omi");
		list.add("Yamini");
		list.add("Mansi");
		try {
			System.out.print("Enter index to display element ");
			int index = sc.nextInt();
			System.out.println("Element at index " + index + " is " + list.get(index));
		} catch (NullPointerException e) {
			System.out.println("Error : List is Null");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error : Invalid Index");
		}
		sc.close();
	}

}
