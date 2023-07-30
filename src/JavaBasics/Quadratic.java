package JavaBasics;

import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the coefficients of the quadratic equation :");
        System.out.print("a: ");
        double a = sc.nextDouble();
        System.out.print("b: ");
        double b = sc.nextDouble();
        System.out.print("c: ");
        double c = sc.nextDouble();
        // (b^2 - 4ac)
        double disc = b*b-4*a*c;
        if (disc > 0) {
            // Two distinct real roots
            double root1 = (-b+Math.sqrt(disc))/(2*a);
            double root2 = (-b-Math.sqrt(disc))/(2*a);
            System.out.println("Root 1: " + root1);
            System.out.println("Root 2: " + root2);
        } else if (disc == 0) {
            // One real root (repeated)
            double root = -b/(2*a);
            System.out.println("Root: " + root);
        } else {
            // Complex roots (disc < 0)
            double real = -b/(2*a);
            double imaginary = Math.sqrt(Math.abs(disc))/(2*a);
            System.out.println("Root 1: " + real + " + " + imaginary + "i");
            System.out.println("Root 2: " + real + " - " + imaginary + "i");
        }
        sc.close();    
        }
}
