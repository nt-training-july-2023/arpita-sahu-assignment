package JavaBasics;

import java.util.Scanner;

public class PalindromeNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int num = sc.nextInt();
		int originalNum = num;
		int reverseNum = 0;
		while (num != 0) {
			int digit = num % 10;
			reverseNum = reverseNum * 10 + digit;
			num /= 10;
		}
		if (reverseNum == originalNum)
			System.out.println(originalNum + " is a palindrome number.");
		else
			System.out.println(originalNum + " is not a palindrome number.");

		sc.close();
	}
}
