package oops;

interface Moveable {
	void run();
}

interface Speakable {
	void speak();
}

interface Ability extends Moveable, Speakable {
	void show();
}

class Person implements Ability {
	public void run() {
		System.out.println("I can run !!");
	}
	public void speak() {
		System.out.println("I can speak !!");
	}

	public void show() {
		System.out.println("I am a person, I can speak and run !!");
	}
}

public class MultipleInheritance {
	public static void main(String[] args) {
		Person obj = new Person();
		obj.run();
		obj.speak();
		obj.show();
	}
}