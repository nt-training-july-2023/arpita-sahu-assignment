package oops;

import java.util.Scanner;
public class Encapsulation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Student[] student = new Student[1];
		for(int i=0; i<student.length; i++) {
		System.out.print("Enter Student name: ");
		String name = sc.nextLine();
		System.out.print("Enter Student age: ");
		int age = sc.nextInt();
		System.out.print("Enter student grade: ");
		String grade = sc.next();
		student[i] = new Student(name,age,grade);
		}
		for(int i=0; i<student.length; i++)
			System.out.println(student[i]);
		sc.close();
		
	}

}
