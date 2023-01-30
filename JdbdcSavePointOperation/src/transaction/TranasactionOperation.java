package transaction;


import java.io.IOException;
import java.sql.*;
import java.util.*;
import in.asif.util.*;


public class TranasactionOperation {

	public static void main(String[] args) {
		Connection connection =null;
		Statement statement=null;
		ResultSet resultSet=null;
		ResultSet resultSet2=null;
		Scanner scanner =null;
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection!=null) 
				 statement = connection.createStatement();
				 if (statement != null) {
					String query="SELECT `name`,`balance` FROM account";
					 resultSet = statement.executeQuery(query);
				 }
				if (resultSet != null) {
					System.out.println("NAME\tBALANCE");
					while(resultSet.next()) {
						System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2));
					}
					
				}
				// Transaction begins
				System.out.println("\n Transaction begins.....");
				connection.setAutoCommit(false);
				//Prepare the operations as a single unit
				statement.executeUpdate("update account set balance = balance-20000 where name = 'ali'");
				statement.executeUpdate("update account set balance = balance+20000 where name = 'asif'");
				System.out.print("Can u please confirm the transaction  of 20000INR...[YES/NO]");
				scanner = new Scanner(System.in);
				String option = scanner.next();
				if (option.equalsIgnoreCase("YES")) {
					connection.commit();
				}
				else
				{
					connection.rollback();
				}
				System.out.println("\nData after transaction....");
			   resultSet2 = statement.executeQuery("SELECT `name`,`balance` FROM account");
			   if (resultSet2 != null) {
					System.out.println("NAME\tBALANCE");
					while(resultSet2.next()) {
						System.out.println(resultSet2.getString(1)+"\t"+resultSet2.getInt(2));
					}
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
				JdbcUtil.cleanUp(null, null, resultSet2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
	

