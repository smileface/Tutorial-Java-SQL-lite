package students;

import static java.lang.System.out;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		int studentsNum = 1;       
		
		DB.setConnection();
		
		Scanner in = new Scanner(System.in);
		
		out.println("Enter the number of students:");
		studentsNum = in.nextInt();
		//nextInt() only reads the integer value, skip '\n' symbol
		in.nextLine(); 
		
		for (int i = 0; i < studentsNum; i++) {
			Student student = new Student();
			
			out.println("Student first name:");
			student.setFirstName(in.nextLine());
			
			out.println("Student last name:");
			student.setLastName(in.nextLine());
			
			out.println("Student middle name:");
			student.setMiddleName(in.nextLine());
			
			out.println("Student mark:");
			student.setMark(in.nextInt());
			in.nextLine();
			
			DB.insert(student);		
		}
		
		DB.getAll();
		
		in.close();
		
		DB.endConnection();
		
	}
}
