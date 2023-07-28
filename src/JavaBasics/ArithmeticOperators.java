package JavaBasics;

import java.util.Scanner;

public class ArithmeticOperators {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a = ");
		int a = sc.nextInt();
		System.out.println("Enter b = ");
		int b = sc.nextInt();
		int add = a + b;
		System.out.println("Addition of two number " + add);
		int sub = a - b;
		System.out.println("Subtraction of two number " + sub);
		int mul = a * b;
		System.out.println("Multiplication of two number " + mul);
		int div = a / b;
		System.out.println("Division of two number " + div);
		int mod = a % b;
		System.out.println("Modulo of two number " + mod);

		sc.close();

	}

}
