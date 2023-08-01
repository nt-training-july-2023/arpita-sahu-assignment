package oops;

public class InnerClass {
	private String name = "Arpita";
    class Inner{
		void msg() {
			System.out.println("Hi I'm " + name);
		}
	}
	public static void main(String[] args) {
	InnerClass obj = new InnerClass();
	InnerClass.Inner in = obj.new Inner();
	in.msg();
	
	}

}
