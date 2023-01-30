package transaction;


import java.io.IOException;
import java.sql.*;
import java.util.*;
import in.asif.util.*;


public class ScrollableOperation {

	public static void main(String[] args) {
		Connection connection =null;
		Statement statement=null;
		ResultSet resultSet=null;
		
		Scanner scanner =null;
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection!=null) 
				 statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 if (statement != null) {
					String Selectquery="select eid,ename,eage,eaddress from employee";
					 resultSet = statement.executeQuery(Selectquery);
				 }
				if (resultSet != null) {
					System.out.println("MOVING IN FORWARD DIRECTION...");
					System.out.println("ID\tNAME\tAGE\tADDRESS");
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
								+ "\t" + resultSet.getString(4));
					}
					System.out.println();
					System.out.println("MOVING IN BACKWARD DIRECTION...");
					System.out.println("ID\tNAME\tAGE\tADDRESS");
					
					while (resultSet.previous()) {
						
						System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
						+ "\t" + resultSet.getString(4));
						
					}
					System.out.println();
					
					resultSet.first();
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					
					System.out.println();
					
					resultSet.last();
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					System.out.println();
					
					resultSet.absolute(2);
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					System.out.println();
					
					resultSet.relative(2);
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					System.out.println();
					
					resultSet.absolute(-3);
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					System.out.println();
					
					resultSet.absolute(-2);
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					System.out.println();
					
					resultSet.last();
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					System.out.println();
					
					resultSet.relative(-1);
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
					System.out.println();
					
				}
				
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.cleanUp(connection, statement, resultSet);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
	

