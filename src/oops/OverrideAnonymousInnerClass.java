package oops;

interface MyInterface {
	void myMethod();
}

public class OverrideAnonymousInnerClass {

	public static void main(String[] args) {

		MyInterface instance1 = new MyInterface() {
			@Override
			public void myMethod() {
				System.out.println("Instance 1: Overriding myMethod");
			}
		};

		MyInterface instance2 = new MyInterface() {
			@Override
			public void myMethod() {
				System.out.println("Instance 2: Overriding myMethod");
			}
		};

		instance1.myMethod();
		instance2.myMethod();
	}
}
