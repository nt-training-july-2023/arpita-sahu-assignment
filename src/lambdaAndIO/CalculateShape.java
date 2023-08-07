package lambdaAndIO;

interface Shape {
	 double area();
	}

	class CalculateShape {

	 static class Rectangle implements Shape {
	     private double length;
	     private double width;

	     public Rectangle(double length, double width) {
	         this.length = length;
	         this.width = width;
	     }

	     @Override
	     public double area() {
	         return length * width;
	     }
	 }


	 static class Square implements Shape {
	     private double side;

	     public Square(double side) {
	         this.side = side;
	     }

	     @Override
	     public double area() {
	         return side * side;
	     }
	 }


	 static class Circle implements Shape {
	     private double radius;

	     public Circle(double radius) {
	         this.radius = radius;
	     }

	     @Override
	     public double area() {
	         return Math.PI * radius * radius;
	     }
	 }

	 // Cube implementation
	 static class Cube implements Shape {
	     private double side;

	     public Cube(double side) {
	         this.side = side;
	     }

	     @Override
	     public double area() {
	         return 6 * side * side;
	     }
	 }


	 static class Sphere implements Shape {
	     private double radius;

	     public Sphere(double radius) {
	         this.radius = radius;
	     }

	     @Override
	     public double area() {
	         return 4 * Math.PI * radius * radius;
	     }
	 }


	 static class Cylinder implements Shape {
	     private double radius;
	     private double height;

	     public Cylinder(double radius, double height) {
	         this.radius = radius;
	         this.height = height;
	     }

	     @Override
	     public double area() {
	         return 2 * Math.PI * radius * (radius + height);
	     }
	 }

	 public static void main(String[] args) {

	     Shape rectangle = new Rectangle(7, 13);
	     System.out.println("Area of Rectangle: " + rectangle.area());

	     Shape square = new Square(4);
	     System.out.println("Area of Square: " + square.area());

	     Shape circle = new Circle(9);
	     System.out.println("Area of Circle: " + circle.area());

	     Shape cube = new Cube(6);
	     System.out.println("Surface Area of Cube: " + cube.area());

	     Shape sphere = new Sphere(5);
	     System.out.println("Surface Area of Sphere: " + sphere.area());

	     Shape cylinder = new Cylinder(2, 8);
	     System.out.println("Surface Area of Cylinder: " + cylinder.area());
	 }
	}
