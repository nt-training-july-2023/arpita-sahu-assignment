package JavaBasics;

public class BitwiseOperator {

	public static void main(String[] args) {
		int x=2, y=3;
//	    |   Bitwise OR
		System.out.println("Bitwise OR: " + (x | y));
//   	&	Bitwise AND
		System.out.println("Bitwise AND: " + (x & y));
//   	^	Bitwise XOR
		System.out.println("Bitwise XOR: " + (x ^ y));
//		~	Bitwise Complement
		System.out.println("Bitwise Complement: " + (~x));
//		<<	Left Shift
		x = 101 ; y=2;
		System.out.println("Bitwise Left Shift: " + (x << y));
//		>>	Signed Right Shift
		System.out.println("Bitwise Right Shift: " + (x >> y));
//		>>>	Unsigned Right Shift
		System.out.println("Bitwise Unsigned Right Shift: " + (x >>> y));

	}

}
