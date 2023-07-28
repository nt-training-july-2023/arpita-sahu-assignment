package JavaBasics;

import java.util.Scanner;

public class RotateArray {

	public static void rotateByTwoPositions(int[] arr, int n) {

		int rotations = 2;
		for (int i = 0; i < rotations; i++) {
			int lastElement = arr[n - 1];
			// Shift elements to the right by one position
			for (int j = n - 1; j > 0; j--) {
				arr[j] = arr[j - 1];
			}
			// Place the last element at the beginning
			arr[0] = lastElement;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter length of Array = ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter Elements of the Array");
		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();
		rotateByTwoPositions(arr, n);
		for (int num : arr) {
			System.out.print(num + " ");
		}
		sc.close();

	}

}
