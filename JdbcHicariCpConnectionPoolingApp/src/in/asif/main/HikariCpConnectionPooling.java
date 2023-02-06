package in.asif.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


import com.zaxxer.hikari.*;

public class HikariCpConnectionPooling {

	public static void main(String[] args) throws SQLException {
		
		// Create an object of a class which implements javax.sql.DataSource
		
		String ConfigFile="E:\\javap\\JdbcHicariCpConnectionPoolingApp\\db.properties";
		HikariConfig Config = new HikariConfig(ConfigFile);
		
		
		
		try(HikariDataSource dataSource = new HikariDataSource(Config)){
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select eid,ename,eage,eaddress from employee");
		if (resultSet != null) {
			System.out.println("ID\tNAME\tAGE\tADDRESS");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3) + "\t"
						+ resultSet.getString(4));
			
		}
		}	
			// sending the connection object back to connection pool
			connection.close();
		
	}
	}

}
