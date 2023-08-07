package ExceptionHandling;

import java.util.Scanner;

public class IntegerArrayException {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = {10,20,30,40,50};
		//arr=null;
		try {
		System.out.print("Enter index to get element ");
		int index = sc.nextInt();
		System.out.println("Element at index " + index + " is " + arr[index]);
		}catch (NullPointerException e) {
			System.out.println("Error : Array is Null");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error : Invalid Index");
		}
		sc.close();
		
	}

}
