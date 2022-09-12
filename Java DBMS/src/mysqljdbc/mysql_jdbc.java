package mysqljdbc;
import java.sql.*;

public class mysql_jdbc {

	public static void main(String[] args) throws Exception {
		
		
		String url = "jdbc:mysql://localhost:3306/dbcompany";
		String user = "root";
		String pass = "pass";
		String query = "select * from employee";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url,user,pass);
		
//		Statement st = con.createStatement();
		
		PreparedStatement st = con.prepareStatement(query);
		
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			System.out.println(rs.getString("emp_name"));
		}
		System.out.println(rs.getMetaData());		
	}

}
