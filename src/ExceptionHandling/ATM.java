package ExceptionHandling;

import java.util.Scanner;

public class ATM {

	public static double withdraw(double accBal, double withdrawAmnt) throws InvalidInputException {
		if (accBal < 0 || withdrawAmnt < 0) {
			throw new InvalidInputException("Account balance and withdrawal amount can't be negative.");
		}
		if (withdrawAmnt > accBal) {
			throw new InvalidInputException("Withdrawal Amount can't be greater than account balance");
		}
		return accBal - withdrawAmnt;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double accBal = 0;
		double withdrawAmnt = 0;

		try {
			System.out.print("Enter your account balance: ");
			accBal = sc.nextDouble();
			System.out.print("Enter the amount you want to withdraw: ");
			withdrawAmnt = sc.nextDouble();
			double remainBal = withdraw(accBal, withdrawAmnt);
			System.out.println("Withdrawal successful. Remaining balance: " + remainBal);
		} catch (InvalidInputException e) {
			System.out.println("Error: " + e.getMessage());
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
