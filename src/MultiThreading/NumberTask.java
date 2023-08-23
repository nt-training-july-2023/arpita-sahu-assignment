package MultiThreading;

import java.util.Scanner;

public class NumberTask {
	public static int num = 0;
	
	static class ReverseList extends Thread {
		@Override
		public void run() {
			System.out.println("The reverse List is :");
			for (int i = num; i > 0; i--) {
				System.out.print(i + " ");
			}
		}
	}
	
	static class Fibonacci extends Thread {
		@Override
		public void run() {
			System.out.print("Fibonacci Sequence: ");
			int prev = 0, curr = 1;
			for (int i = 0; i < num; i++) {
				System.out.print(prev + " ");
				int next = prev + curr;
				prev = curr;
				curr = next;
			}
			System.out.println();
		}
	}

	static class Sum extends Thread {
		@Override
		public void run() {
			int sum = 0;
			for (int i = 1; i <= num; i++) {
				sum = sum + i;
			}
			System.out.println("Sum Of " + num + " : " + sum);
		}
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number : ");
		num = sc.nextInt();
		
		ReverseList reverse = new ReverseList();
		Fibonacci fibonacci = new Fibonacci();
		Sum sum = new Sum();
		sc.close();
		reverse.start();
		fibonacci.start();
		sum.start();
	}
}
