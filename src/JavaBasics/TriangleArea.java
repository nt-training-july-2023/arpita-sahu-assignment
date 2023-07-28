package JavaBasics;

import java.util.Scanner;
public class TriangleArea {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Base ");
		double base = sc.nextInt();
		System.out.print("Enter Height ");
		double height = sc.nextInt();
		double area = (base*height)/2;
		System.out.println(area);
		sc.close();
		
	}

}
