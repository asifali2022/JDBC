package savepoint;


import java.io.IOException;
import java.sql.*;
import java.util.*;
import in.asif.util.*;


public class SavePointOperation {

	public static void main(String[] args) {
		Connection connection =null;
		Statement statement=null;
	
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection!=null) 
				 statement = connection.createStatement();
				 
			System.out.println("Transaction Begin");
			connection.setAutoCommit(false);
			statement.executeUpdate("insert into polititian(`name`,`party`) values('MODI','BJP')");
			statement.executeUpdate("insert into polititian(`name`,`party`) values('KCR','TRS')");
			Savepoint sp = connection.setSavepoint();
			statement.executeUpdate("insert into polititian(`name`,`party`) values('siddu','BJP')");
			System.out.println("last provided data incorrect");
			connection.rollback(sp);
			System.out.println("Operations are rolled back to savepoint....");
			connection.commit();
			
			System.out.println("Transaction done....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.cleanUp(connection, statement, null);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
	

