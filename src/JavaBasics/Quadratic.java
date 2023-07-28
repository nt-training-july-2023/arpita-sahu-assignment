package JavaBasics;

import java.util.Scanner;

public class Quadratic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter a= ");
		Double a = sc.nextDouble();
		System.out.print("Enter b= ");
		Double b = sc.nextDouble();
		System.out.print("Enter c= ");
		Double c = sc.nextDouble();
		Double root1 = (-b+Math.sqrt(b*b-4*a*c))/2*a;
		Double root2 = (-b-Math.sqrt(b*b-4*a*c))/2*a;
		System.out.println(root1);
		System.out.println(root2);
		sc.close();
	// 1, -7, 12	
	}
}
