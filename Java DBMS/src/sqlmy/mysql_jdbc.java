package sqlmy;
import java.sql.*;

public class mysql_jdbc {

	public static void main(String[] args) throws Exception {
		
		
		String url = "jdbc:mysql://localhost:3306/dbcompany";
		String user = "root";
		String pass = "pass";
		String query = "select * from employee";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url,user,pass);
		
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		
		System.out.println(rs.getMetaData());		
	}

}
