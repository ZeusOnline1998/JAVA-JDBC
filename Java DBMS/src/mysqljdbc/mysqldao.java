package mysqljdbc;
import java.sql.*;


public class mysqldao {
	public static void main(String[] args) throws Exception{
		
		EmployeeJava DAO = new EmployeeJava();
		
		DAO.connect();
		
		DAO.getEmployee(1);
	}
}


class EmployeeJava{
	
	Connection con = null;
	private String url = "jdbc:mysql://localhost:3306/dbcompany";
	private String user = "root";
	private String pass = "pass";
	private String employeeJavaTable = "employee_info";
	public void connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			String ctable = "CREATE TABLE IF NOT EXISTS " +this.employeeJavaTable
					+ " (emp_id int(10),"
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
			String query = "Select * from "+this.employeeJavaTable+" where id="+emp_id+";";
//			String query = "Select * from "+this.employeeJavaTable+" where emp_id = "+emp_id+";";
//			
//			String query = "Select * from employee_info where id = 1";
			PreparedStatement stmt = con.prepareStatement(query);
			
//			Statement stmt = con.createStatement();
			
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String emp_name = rs.getString(2);
				String email = rs.getString(3);
				String department = rs.getString(4);
				int salary = rs.getInt(5);
				
				System.out.println("Employee: "+emp_name+"\nEmail: "+email
						+"\nDeparment: "+department+"\nSalary: "+salary);
				
//				System.out.println(emp_name);
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
			
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