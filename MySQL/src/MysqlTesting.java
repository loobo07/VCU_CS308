//import com.mysql.jdbc.Connection;
//import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MysqlTesting {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("java");
		dataSource.setPassword("cmsc308");
		dataSource.setServerName("localhost");
		
		
		
		Connection conn = (Connection) dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT 1");
		while(rs.next()) {
			System.out.println(rs.getString("1"));
		}
		rs.close();
		stmt.close();
		conn.close();
	}

}
