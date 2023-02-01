package in.asif.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class ConnectionPoolingOperation {

	public static void main(String[] args) throws SQLException {
		
		// Create an object of a class which implements javax.sql.DataSource
		
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setURL("jdbc:mysql:///javafsj");
		dataSource.setUser("root");
		dataSource.setPassword("nopassword");
		
		// Getting the connection object from connection pool
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select eid,ename,eage,eaddress from employee");
		if (resultSet != null) {
			System.out.println("ID\tNAME\tAGE\tADDRESS");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3) + "\t"
						+ resultSet.getString(4));
			
		}
			// sending the connection object back to connection pool
			connection.close();
		
	}
	}

}
