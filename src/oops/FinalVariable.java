package oops;

public class FinalVariable {
	public static void main(String[] args) {
		final int AGE_LIMIT = 18;

		int age = 20;
		if (age >= AGE_LIMIT) {
			System.out.println("eligible " + age);
		} else {
			System.out.println("Not eligible " + age);
		}
	}
}
