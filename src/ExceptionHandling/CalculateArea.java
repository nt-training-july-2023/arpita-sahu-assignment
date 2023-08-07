package ExceptionHandling;

import java.util.Scanner;

public class CalculateArea {
	 public static double rectangleArea(double length, double width) {
	        return length * width;
	    }
	 
	 public static double circleArea(double radius) {
	        return Math.PI * radius * radius;
	    }
	 
	 public static double triangleArea(double base, double height) {
	        return 0.5 * base * height;
	    }


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter length of Rectangle ");
		double length = sc.nextDouble();
		System.out.print("Enter width of Rectangle ");
		double width = sc.nextDouble();
		System.out.println("Area of Rectangle " + rectangleArea(length, width));
		System.out.print("Enter Radius of Circle ");
		double radius = sc.nextDouble();
		System.out.println("Area of Circle " + circleArea(radius));
		System.out.print("Enter Base of Triangle ");
		double base = sc.nextDouble();
		System.out.print("Enter height of Triangle");
		double height = sc.nextDouble();
		System.out.println("Area of Triangle " + triangleArea(base, height));
		sc.close();
	}

}
