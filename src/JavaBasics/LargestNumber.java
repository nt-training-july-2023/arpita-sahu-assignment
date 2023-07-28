package JavaBasics;

import java.util.Scanner;

public class LargestNumber {

	public static int findLargestNumber(int[] arr) {
		int largest = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > largest) {
				largest = arr[i];
			}
		}
		return largest;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter length of Array = ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter Elements of the Array");
		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();
		int ans = findLargestNumber(arr);
		System.out.println("The largest number is: " + ans);
		sc.close();
	}
}
