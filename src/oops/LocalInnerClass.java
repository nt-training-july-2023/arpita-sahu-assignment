package oops;

//A class i.e., created inside a method, is called local inner class
public class LocalInnerClass {
	private int data = 30;

	void display() {
		class Local {
			void msg() {
				System.out.println(data);
			}
		}
		Local loc = new Local();
		loc.msg();
	}

	public static void main(String args[]) {
		LocalInnerClass obj = new LocalInnerClass();
		obj.display();
	}
}