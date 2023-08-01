package oops;

class Employee{
	void salary() {
		System.out.println("20000");
	}
}
class Developer extends Employee{
	void bonus(){
		System.out.println("6000");
	}
}
public class SingleInheritance {
	public static void main(String[] args) {
		Developer developer = new Developer();
		developer.salary();
		developer.bonus();
	}

}
