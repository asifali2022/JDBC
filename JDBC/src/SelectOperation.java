import java.sql.*;
public class SelectOperation {

	public static void main(String[] args) 
	{
	   
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
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
			
			String SelectQuery="SELECT id,name,course FROM batchinfo";
			resultSet=statement.executeQuery(SelectQuery);
			
			System.out.println("RESULTSET object created...");
			System.out.println("ID\tNAME\tCOURSE");
			while(resultSet.next())
			{
				Integer ID=resultSet.getInt(1);
	        	String NAME=resultSet.getString(2);
	        	String COURSE=resultSet.getString(3);
	        	System.out.println(ID+"\t"+NAME+"\t"+COURSE);
			}
			// Step6. Close the resources
			resultSet.close();
			statement.close();
			connection.close();
			System.out.println("Closing the resources...");	
			
		} 
		catch (ClassNotFoundException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
		

	}

}
