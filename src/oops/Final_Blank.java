package oops;

public class Final_Blank {
	final int finalBlank;

	public Final_Blank(int value) {
		finalBlank = value;
	}

	public static void main(String[] args) {
		Final_Blank obj1 = new Final_Blank(10);
		Final_Blank obj2 = new Final_Blank(20);

		System.out.println("Value of finalBlankField in obj1: " + obj1.finalBlank);
		System.out.println("Value of finalBlankField in obj2: " + obj2.finalBlank);
	}
}
