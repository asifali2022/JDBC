package transaction;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import in.asif.util.*;

public class ScrollableSensitiveOperation {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Scanner scanner = null;

		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (statement != null) {
				String Selectquery = "select eid,ename,eage,eaddress from employee";
				resultSet = statement.executeQuery(Selectquery);
			}
			if (resultSet != null) {

				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t" + resultSet.getString(4));
				}
				System.out.println();
				System.out.println("Application in pausing state! kindly update the database");
				System.in.read();
				System.out.println("printing the updated resultset");
				System.out.println();

				resultSet.beforeFirst();

				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (resultSet.next()) {
					resultSet.refreshRow();
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t" + resultSet.getString(4));
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
