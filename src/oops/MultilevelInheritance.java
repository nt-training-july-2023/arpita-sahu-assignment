package oops;

class A {
	void fun1() {
		System.out.println("fun1() is implemented");
	}
}

class B extends A {
	void fun2() {
		System.out.println("fun2() is implemented");
	}
}

class C extends B {
		void fun3() {
			System.out.println("fun3() is implemented");
		}
	}

	public class MultilevelInheritance {
		public static void main(String[] args) {
			C obj = new C();
			obj.fun1();
			obj.fun2();
			obj.fun3();

		}
	}

