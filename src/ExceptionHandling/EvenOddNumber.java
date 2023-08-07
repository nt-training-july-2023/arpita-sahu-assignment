package ExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EvenOddNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Enter Number: ");
			int num = sc.nextInt();
			sc.close();
			if (num % 2 != 0)
				throw new NotEvenNumberException("Error : Not an Even Number.");
			System.out.println("Even Number");
		} catch (InputMismatchException e) {
			System.out.println("Error: Enter an integer number.");
		} catch (NotEvenNumberException e) {
			System.out.println(e.getMessage());
		}
	}

}
