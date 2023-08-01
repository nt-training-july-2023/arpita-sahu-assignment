package oops;

abstract class Persons {
	abstract void eat();
}

public class AnonymousInnerClass {
	public static void main(String[] args) {
		Persons p = new Persons() {
			void eat() {
				System.out.println("nice fruits");
			}
		};
		p.eat();
	}
}
