import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteOperation {

public static void main(String[] args) {
		
		
		Connection connection=null;
		Statement statement=null;
		
		try {
			//Step:01 > loading the driver class
			// and this part can b e skipped from java 4.0
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			//step:02> establishing connection with database
			String url="jdbc:mysql://localhost:3306/javafsj";
//		    
//			String url="jdbc:mysql:///javafsj";
//			localhost:3306 --> If java pgm and database engine is 
//			running in the same program with the 
//			default port no for database then
//		    url can be of the following type
			
			String userName="root";
			String passWord="nopassword";
			
			connection=DriverManager.getConnection(url,userName,passWord);
			System.out.println("Connection established successfully");
			
			//Step3. Create statement Object and send the query
			
			statement=connection.createStatement();
			System.out.println("STATEMENT object created...");
			
			

			
			
			String UpdateQuery=" DELETE FROM batchinfo WHERE ID=5";

			
			int rowAffected = statement.executeUpdate(UpdateQuery);
			System.out.println("No of rows affected is :: " + rowAffected);

			// Step6. Close the resources
			statement.close();
			connection.close();
			
			System.out.println("closing the resources...");
			
		} 
		catch (ClassNotFoundException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
		//

	}

}
