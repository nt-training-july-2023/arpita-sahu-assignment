package JavaBasics;

public class StringEqual {

	public static void main(String[] args) {
		// equals method is used to compare the content of two string
		String str1 = "Hello";
		String str2 = "Hello";
		String str3 = "hello";
		String str4 = new String("Hello");
		System.out.println(str1.equals(str2));
		System.out.println(str2.equals(str3));
		System.out.println(str1.equals(str4));
		// == operator is used for reference comparison
		System.out.println(str1 == str2);
		System.out.println(str1 == str4);
	}

}
