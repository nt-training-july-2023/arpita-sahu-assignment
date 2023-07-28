package JavaBasics;

import java.util.Scanner;

public class StringOperations {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter String = ");
		String str = sc.nextLine();
		// length of the String
		System.out.println("Length of the String = " + str.length());
		// String Concatenation
		System.out.println("Enter String to concatenate");
		System.out.print("Enter First String = ");
		String str1 = sc.next();
		System.out.print("Enter Second string = ");
		String str2 = sc.next();
		System.out.println("Concatenation of the string " + str1.concat(str2));
		// getting character at a given position from String
		System.out.print("Enter position to search from the string ");
		int pos = sc.nextInt();
		System.out.println("string character at " + pos + " is " + str.charAt(pos));
		// String starts with a given character or String
		System.out.print("Enter a character ");
		String givenChar = sc.next();
		System.out.println(str.startsWith(givenChar));
		// index of a given character
		String str3 = "Hi I am Arpita";
		System.out.println(str3.indexOf("am"));
		// Replace a character from a string
		String replace = str.replace('p', 's');
		System.out.println(replace);
		// compareTo() - Comparing two String
		System.out.print("Enter First String ");
		String str4 = sc.next();
		System.out.print("Enter Second String ");
		String str5 = sc.next();
		int result = str4.compareTo(str5);
		if (result == 0)
			System.out.println("Both Strings are same");
		else if (result < 0)
			System.out.println("string is lexicographically less than the other string");
		else
			System.out.println("string is lexicographically greater than the other string");
		// contains - check if string contains the sequence of characters
		String str6 = "Hello";
		System.out.println(str6.contains("Hel"));
		// toCharArray - converts the string into new character array
		char[] ch = str6.toCharArray();
		for (char c : ch)
			System.out.print(c + " ");
		System.out.println();
		// toLowerCase() - converts string into Lower Case
		// toUpperCase() - converts string into upper case
		System.out.println("Lowercase " + str6.toLowerCase());
		System.out.println("UpperCase " + str6.toUpperCase());
		sc.close();

	}

}
