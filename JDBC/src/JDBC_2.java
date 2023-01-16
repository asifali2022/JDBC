import java.sql.*;

class JDBC_2 {
	public static void main(String[] args) {
		
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;	
		try {
				//Step:1 Load and register the Driver
				
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				System.out.println("Driver loaded succesfully....");
			
				//Step2. Establish the Connection with database
				
				String url="jdbc:mysql:///javafsj";
				
				//user id and password
				String userName="root";
				String passWord="nopassword";
				
          connection=DriverManager.getConnection(url, userName, passWord);			
          
          System.out.println("connection established succesfully...");
          
          System.out.println("The implement class name is "+
          connection.getClass().getName());
          
        //Step3. Create statement Object and send the query
          
        String sqlselectquery="Select id,name,course FROM batchinfo";
         
        statement=connection.createStatement();
        
        System.out.println("The implement class name is "+
                statement.getClass().getName());
          // collecting result
        resultSet=statement.executeQuery(sqlselectquery);
        
        System.out.println("The implement class name is "+
                resultSet.getClass().getName());
       
        System.out.println();
        
        System.out.println("ID\tNAME\tCOURSE");
        
      //Step4. Process the resultSet
        while(resultSet.next())
        {
        	Integer ID=resultSet.getInt("id");
        	String NAME=resultSet.getString("name");
        	String COURSE=resultSet.getString("course");
        	System.out.println(ID+"\t"+NAME+"\t"+COURSE);
        }
             
			} 
//			catch (ClassNotFoundException ce) {
//				// TODO Auto-generated catch block
//				ce.printStackTrace();
//			} 
			catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if(connection!=null)
				{
					try
					{
						connection.close();
						System.out.println("Connection closed...");
					}
					catch (SQLException se){
						se.printStackTrace();
					}
				}
			}
			
			
			
		
}
}
