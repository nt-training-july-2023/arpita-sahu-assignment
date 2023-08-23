package oops;

class StaticBlock {
	static int staticVar;

	static {
		System.out.println("Static Block 1: Initializing staticVariable to 10");
		staticVar = 10;
	}

	static {
		System.out.println("Static Block 2: Modifying staticVariable to 20");
		staticVar = 20;
	}

	static void display() {
		System.out.println("Value of staticVariable: " + staticVar);
	}

	public static void main(String[] args) {
		display();
	}
}
