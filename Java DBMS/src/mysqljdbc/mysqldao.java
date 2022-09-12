package mysqljdbc;
import java.sql.*;
import java.util.*;

public class mysqldao {
	
	static EmployeeJava DAO = new EmployeeJava();
	
	public static void getEmployee(int emp_id) {	
		
		DAO.connect();
		
		Employee e1 = DAO.getEmployee(emp_id);
		
		System.out.println("ID: "+e1.getEmp_id()+
				"\nName: "+e1.getEmp_name()+
				"\nEmail: "+e1.getEmail()+
				"\nDepartment: "+e1.getDepartment()+
				"\nSalary: "+e1.getSalary());
	}
	
	public static void addEmployee() {
		
		Scanner sc = new Scanner(System.in);
		
		String emp_name, email, department;
		int salary;
		System.out.print("Employee Name: ");
		emp_name = sc.next();
		System.out.print("\nEmail: ");
		email = sc.next();
		System.out.print("\nDepartment: ");
		department = sc.next();
		System.out.print("\nSalary: ");
		salary = sc.nextInt();
		
		Employee e1 = new Employee();
		e1.setEmp_name(emp_name);
		e1.setEmail(email);
		e1.setDepartment(department);
		e1.setSalary(salary);
		
		DAO.connect();
		
		DAO.addEmployee(e1);
	}
	
	
	public static void main(String[] args) throws Exception{
		
		int emp_id = 1;
		
//		getEmployee(emp_id);
		
		addEmployee();
	}
}


class EmployeeJava{
	
	Connection con = null;
	private String url = "jdbc:mysql://localhost:3306/dbcompany";
	private String user = "root";
	private String pass = "pass";
	private String employeeJavaTable = "employeejavatable";
	public void connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  // Can be ignored since it is always initialized in the latest versions.
			con = DriverManager.getConnection(url, user, pass);
			String ctable = "CREATE TABLE IF NOT EXISTS " +this.employeeJavaTable
					+ " (emp_id int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,"
					+ " emp_name varchar(50),"
					+ " email varchar(100),"
					+ " department varchar(20),"
					+ " salary int(10));";
			
			Statement stmt = con.createStatement();
			try {
				stmt.execute(ctable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Employee getEmployee(int emp_id){
		
		try {
			
			Employee e1 = new Employee();
			
			String query = "Select * from "+this.employeeJavaTable+" where emp_id="+emp_id+";";
//			String query = "Select * from "+this.employeeJavaTable+" where emp_id = "+emp_id+";";
//			
//			String query = "Select * from employee_info where id = 1";
			PreparedStatement stmt = con.prepareStatement(query);
			
//			Statement stmt = con.createStatement();
			
			
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			String emp_name = rs.getString(2);
			String email = rs.getString(3);
			String department = rs.getString(4);
			int salary = rs.getInt(5);
				
			e1.setEmp_id(emp_id);
			e1.setEmp_name(emp_name);
			e1.setEmail(email);
			e1.setDepartment(department);
			e1.setSalary(salary);
				
			return e1;
//				System.out.println(emp_name);
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
			
	}
	
	public void addEmployee(Employee e1) {
		
		try {
			String query = "Insert into "+this.employeeJavaTable+"(emp_name, email, department, salary)"
					+ " values (?,?,?,?);";
		
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1, e1.getEmp_name());
			stmt.setString(2, e1.getEmail());
			stmt.setString(3, e1.getDepartment());
			stmt.setInt(4, e1.getSalary());
			
			try {
				stmt.executeUpdate();
				System.out.println("Data Inserted");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}


class Employee{
	
	private int emp_id;
	private String emp_name;
	private String email;
	private String department; 
	private int salary;
	
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
}