package oops;

import java.util.Scanner;

abstract class Calculation {
	abstract void sum(int a, int b);
	abstract void sub(int a, int b);
}

class Test extends Calculation{

	@Override
	void sum(int a, int b) {
		System.out.println("Sum : " + (a+b));
		
	}

	@Override
	void sub(int a, int b) {
		System.out.println("Sub : " + (a-b));
		
	}
	
}
public class Abstraction{
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a: ");
		int a = sc.nextInt();
		System.out.print("Enter b: ");
		int b = sc.nextInt();
		Test t = new Test();
		t.sum(a,b);
		t.sub(a, b);
		sc.close();
	}
}
