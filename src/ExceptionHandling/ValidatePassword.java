package ExceptionHandling;

import java.util.Scanner;

public class ValidatePassword {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Enter Password ");
			String pass = sc.nextLine();
			sc.close();
			if (pass.length() < 8 || !pass.matches(".*[a-zA-Z].*") || !pass.matches(".*\\d.*")) {
				throw new InvalidPasswordException(
						"Invalid password: The password must have at least eight characters and contain both letters and numbers.");
			}
			System.out.println("Password is valid");
		} catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
		}
	}
}
