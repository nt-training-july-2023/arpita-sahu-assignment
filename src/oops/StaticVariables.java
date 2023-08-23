package oops;

class StaticVariables {
	static int count = 0;
	int value;

	StaticVariables(int value) {
		this.value = value;
		count++;
	}

	static void displayInstanceCount() {
		System.out.println("Number of instances created: " + count);
	}

	public static void main(String[] args) {
		StaticVariables obj1 = new StaticVariables(10);
		StaticVariables obj2 = new StaticVariables(20);
		StaticVariables obj3 = new StaticVariables(30);

		System.out.println("Value of obj1: " + obj1.value);
		System.out.println("Value of obj2: " + obj2.value);
		System.out.println("Value of obj3: " + obj3.value);

		StaticVariables.displayInstanceCount();
	}
}
