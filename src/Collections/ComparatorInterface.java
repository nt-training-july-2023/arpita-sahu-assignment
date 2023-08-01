package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Stud implements Comparable<Stud> {
    private String name;
    private int age;

    public Stud(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public int compareTo(Stud  otherStudent) {
        return Integer.compare(this.age, otherStudent.age);
    }

    @Override
    public String toString() {
        return name + " (age: " + age + ")";
    }
}

public class ComparatorInterface {

	public static void main(String[] args) {
	        ArrayList<Stud> students = new ArrayList<>();
	        students.add(new Stud("Arpita", 25));
	        students.add(new Stud("Mansi", 26));
	        students.add(new Stud("Omi", 24));
	        students.add(new Stud("Akansha", 26));
	        System.out.println("Students : " + students);
	        
	        Collections.sort(students, Comparator.comparing(Stud ::getName));
	        System.out.println("\nStudents (Sorted by Name) : " + students);
	}

}
