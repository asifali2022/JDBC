package transaction;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import in.asif.util.*;

public class InsertResultsetOperation {
	

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (statement != null) {
				String Selectquery = "select eid,ename,eage,eaddress,esalary from employee";
				resultSet = statement.executeQuery(Selectquery);
			}
			if (resultSet != null) {

				System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t" + resultSet.getString(4)+ "\t"+resultSet.getInt(5));
				}
				resultSet.beforeFirst();
				while (resultSet.next()) {
					int original = resultSet.getInt(5);
					if (original<10000) {
						int incrsal=original+1000;
						resultSet.updateInt(5,incrsal);
						resultSet.updateRow();
						
					}
					
				}
				
				
				
				System.out.println("printing the updated resultset");
				resultSet.beforeFirst();

				System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
				while (resultSet.next()) {
					resultSet.refreshRow();
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t" + resultSet.getString(4)+ "\t"+resultSet.getInt(5));
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, statement, resultSet);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
