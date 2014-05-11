package students;

import static java.lang.System.out;

import java.sql.*;

public final class DB {
	private static Connection conn = null;
	private static Statement stat = null;
	
	public static void setConnection()
	{
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:Students.sqlite");
			stat = conn.createStatement();
			stat.executeUpdate("DROP TABLE IF EXISTS students;");
			stat.executeUpdate(
				"CREATE TABLE 'main'.'students'"
				+ " ('id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE"
				+ ", 'firstName' VARCHAR"
				+ ", 'lastName' VARCHAR"
				+ ", 'middleName' VARCHAR"
				+ ", 'mark' VARCHAR)"
			);
		} catch(Exception e) {
			e.printStackTrace();
		}
        
	}
	
	public static void insert(Student student)
	{
		try {
			PreparedStatement prep = conn.prepareStatement(
			        "INSERT INTO students(firstName, lastName, middleName, mark)"
			        + " VALUES (?, ?, ?, ?);");
				
				prep.setString(1, student.getFirstName());
			    prep.setString(2, student.getLastName());
			    prep.setString(3, student.getMiddleName());
			    prep.setInt(4, student.getMark());
			    prep.addBatch();
			    
			    conn.setAutoCommit(true);
			    prep.executeBatch();
			    
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public static Student[] getAll()
	{
		Student[] students = null;
		try {
			
			ResultSet rs = stat.executeQuery("SELECT * FROM students;");
		    while (rs.next()) {
		        out.println("Student first name = " + rs.getString("firstName"));
		        out.println("Student last name = " + rs.getString("lastName"));
		        out.println("Student middle name = " + rs.getString("middleName"));
		        out.println("Student mark = " + rs.getString("mark"));
		    }
		    rs.close();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return students;
	}
	
	public static void endConnection()
	{
		try {
			
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
