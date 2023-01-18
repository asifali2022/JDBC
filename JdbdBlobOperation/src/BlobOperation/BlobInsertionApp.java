package BlobOperation;

import java.io.*;
import java.sql.*;


import java.util.*;
import in.asif.util.*;


public class BlobInsertionApp {

	public static void main(String[] args) {
		
		Connection connection=null;
		PreparedStatement prepareStatement=null;
		Scanner scanner=null;
		
		
		
		int rowCount =0;
		
		try {
			 connection = JdbcUtil.getJdbcConnection();
			 System.out.println("Connection Established....");
			 
			 if (connection!=null) {
	    
            String SqlInsertionQuery="INSERT INTO picinfo(name,profilepic) VALUES(?,?)";
		       prepareStatement = connection.prepareStatement(SqlInsertionQuery);
				
		       if (prepareStatement!=null) {
		    	   
		    	scanner=new Scanner(System.in);
		    	System.out.println("Enter the Name::");
		    	String name = scanner.next();
		    	
		    	System.out.println("Enter Picture Location::");
		    	String location = scanner.next();
		    	
		    	
		    	// Set the input values to Query
		    	prepareStatement.setString(1, name);
		    	prepareStatement.setBlob(2, new FileInputStream(new File(location)));
		    	
		    	//executing the query
                rowCount = prepareStatement.executeUpdate();
                System.out.println("No of rows inserted inserted is :: " + rowCount);	    	   
				
			}
			}
			 
		} 
		catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, prepareStatement, null);
				System.out.println("Closing The Resources");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

}
