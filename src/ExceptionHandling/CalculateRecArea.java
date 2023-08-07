package ExceptionHandling;

import java.util.Scanner;

public class CalculateRecArea {
	public static int calculateRectangleArea(int length, int width) throws InvalidDimensionException {
		if (length <= 0 || width <= 0) {
			throw new InvalidDimensionException("Invalid dimensions: Length and width must be positive integers.");
		}
		return length * width;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Enter the length of the rectangle: ");
			int length = sc.nextInt();
			System.out.print("Enter the width of the rectangle: ");
			int width = sc.nextInt();
			System.out.println("Area of the rectangle: " + calculateRectangleArea(length, width));
		} catch (InvalidDimensionException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
