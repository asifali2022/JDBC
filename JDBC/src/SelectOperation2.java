

import java.io.*;
import java.sql.*;
import in.asif.util.JdbcUtil;

/**
 * @author asif
 * @Company iNeuron
 * @see www.ineuron.ai
 *
 */
public class SelectOperation2 {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				statement = connection.createStatement();

			if (statement != null)
				resultSet = statement.executeQuery("select id,name,course from batchinfo");

			if (resultSet != null) {
				System.out.println("ID\tNAME\tCOURSE");
				while (resultSet.next()) {
					Integer ID=resultSet.getInt(1);
		        	String NAME=resultSet.getString(2);
		        	String COURSE=resultSet.getString(3);
		        	System.out.println(ID+"\t"+NAME+"\t"+COURSE);
				}
			}
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally 
		{
			try {
				JdbcUtil.cleanUp(connection, statement, resultSet);
				System.out.println("closing the resources...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
