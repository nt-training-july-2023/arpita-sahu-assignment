package oops;

	abstract class Shape{
	    abstract int calculateArea();
	}
	class Rectangle extends Shape{
	    int calculateArea(int length, int width){
	        return length*width;
	    }
	    int calculateArea(int side){
	        return side*side;
	    }
	    @Override
	    int calculateArea() {
	        return 0;
	    }
	}
	class Circle extends Shape{
	    double calculateArea(double radius){
	    return Math.PI*radius*radius;
	    }
	    @Override
	    int calculateArea() {
	        return 0;
	    }
	}
	class Triangle extends Shape{
	    public double calculateArea(double base, double height) {
	        return 0.5 * base * height;
	    }
	    @Override
	    int calculateArea() {
	        return 0;
	    }

	}
	public class Polymorphism{
	    public static void main(String[] args) {
	        Rectangle rectangle = new Rectangle();
	        Circle circle = new Circle();
	        Triangle triangle = new Triangle();
	        System.out.println("Area of rectangle is : "+ rectangle.calculateArea(10,20));      
	        System.out.println("Area of Square is :" + rectangle.calculateArea(5));
	        System.out.println("Area of Circle is :" + circle.calculateArea(5));
	        System.out.println("Area of Triangle is :" + triangle.calculateArea(5, 4));
	    }
	}

