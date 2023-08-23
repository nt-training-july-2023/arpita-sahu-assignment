package oops;

class Nested_Static_Class {
	int instanceVariable = 5;
	static int staticVariable = 10;

	void displayInstanceVariable() {
		System.out.println("OuterClass instance variable value: " + instanceVariable);
	}

	static class StaticNestedClass {
		void displayOuterClassMembers() {
			System.out.println("Accessing outer class static variable: " + Nested_Static_Class.staticVariable);

			// accessing the non-static instance variable of the outer class will give
			// compilation error
			// System.out.println("Accessing outer class instance variable: " +
			// instanceVariable);
			// accessing the non-static instance method of the outer class will give
			// compilation error
			// displayInstanceVariable();
		}
	}

	public static void main(String[] args) {
		System.out.println("OuterClass static variable: " + staticVariable);
		StaticNestedClass nestedObj = new StaticNestedClass();
		nestedObj.displayOuterClassMembers();
	}
}
