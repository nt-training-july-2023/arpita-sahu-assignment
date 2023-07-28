package JavaBasics;
import java.util.Scanner;

public class ReverseNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int number = sc.nextInt();
		int reverseNum = 0;
		while (number != 0) {
			int digit = number % 10;
			reverseNum = reverseNum * 10 + digit;
			number /= 10;
		}
		System.out.println("Reversed number: " + reverseNum);
		sc.close();
	}
}
