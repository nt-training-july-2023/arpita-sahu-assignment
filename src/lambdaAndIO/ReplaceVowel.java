package lambdaAndIO;

import java.util.Scanner;
import java.util.function.Function;

public class ReplaceVowel {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter String ");
		String str = sc.nextLine();
		String ans = replaceVowels(str);
		System.out.println("Original String: " + str);
		System.out.println("Modified String: " + ans);
		sc.close();
	}

	public static String replaceVowels(String str) {
		Function<String, String> replaceVowels = (string) -> str.replaceAll("[aeiouAEIOU]", "#");
		String ans = replaceVowels.apply(str);
		return ans;
	}
}
