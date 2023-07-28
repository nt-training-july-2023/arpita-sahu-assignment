package JavaBasics;

import java.util.Scanner;

public class AverageNumberInArray {

	public static double findAverage(int[] arr) {
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		return (double) sum / arr.length;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter length of Array = ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter Elements of the Array");
		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();
		double average = findAverage(arr);
		System.out.println("Average is: " + average);
		sc.close();
	}

}
