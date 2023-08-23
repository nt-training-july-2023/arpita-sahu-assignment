package oops;

final class Vehicle {
	private String brand;

	public Vehicle(String brand) {
		this.brand = brand;
	}

	public void start() {
		System.out.println(brand + " is starting...");
	}

	// This method can't be overridden by subclasses because Vehicle is a final
	public final void stop() {
		System.out.println(brand + " is stopping...");
	}
}
//cannot inherit Vehicle Class
// class Car extends Vehicle {
// }

public class Final_Class {
	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle("Toyota");
		vehicle.start();
		vehicle.stop();
	}
}
