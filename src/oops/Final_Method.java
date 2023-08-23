package oops;

class Parent {

	void nonFinalMethod() {
		System.out.println("This method can be overridden by subclasses.");
	}

	final void finalMethod() {
		System.out.println("This method can't be overridden by subclasses.");
	}
}

final class FinalClass {
	void finalClassMethod() {
		System.out.println("This is final class method");
	}
}

class Child extends Parent {

	@Override
	void nonFinalMethod() {
		System.out.println("This is the overridden method in the subclass.");
	}

	// can't override a final method.
	// @Override
	// void finalMethod() {}
}

public class Final_Method {
	public static void main(String[] args) {
		Parent parent = new Parent();
		Child child = new Child();

		parent.nonFinalMethod();
		parent.finalMethod();

		child.nonFinalMethod();
		child.finalMethod();

		FinalClass finalClass = new FinalClass();
		finalClass.finalClassMethod();
	}
}
