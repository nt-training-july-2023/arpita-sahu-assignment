package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
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
    public int compareTo(Student otherStudent) {
        return Integer.compare(this.age, otherStudent.age);
    }

    @Override
    public String toString() {
        return name + " (age: " + age + ")";
    }
}

public class ComparableInterface {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Arpita", 25));
        students.add(new Student("Mansi", 26));
        students.add(new Student("Omi", 24));
        Collections.sort(students);
        System.out.println("Sorted by age : " + students);
    }
}
