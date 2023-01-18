package date;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import in.asif.util.*;


public class DateinsertionOperation1 {

	public static void main(String[] args) {
		
		Connection connection=null;
		PreparedStatement prepareStatement=null;
		Scanner scanner=null;
		
		java.sql.Date sqlDoB=null;
		java.sql.Date sqlDoE=null;
		
		int rowCount =0;
		
		try {
			 connection = JdbcUtil.getJdbcConnection();
			 System.out.println("Connection Established....");
			 
			 if (connection!=null) {
	    
            String SqlInsertionQuery="INSERT INTO dateinfo1(name,DoB,DoE) VALUES(?,?,?)";
		       prepareStatement = connection.prepareStatement(SqlInsertionQuery);
				
		       if (prepareStatement!=null) {
		    	   
		    	scanner=new Scanner(System.in);
		    	System.out.println("Enter the Name::");
		    	String name = scanner.next();
		    	
		    	System.out.println("Enter the dob(MM-dd-yyyy)::");
		    	String DoB = scanner.next();
		    	
		    	System.out.println("Enter the dob(yyyy-MM-dd)::");
		    	String DoE = scanner.next();
		    	
		    	if(DoB!=null)
		    	{
		        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		        
		        	java.util.Date udate=sdf.parse(DoB);
		        	long value = udate.getTime();
		        	sqlDoB = new java.sql.Date(value);
		        	
				
		        
		    	}
		    	if (DoE!=null) {
		        	 sqlDoE= java.sql.Date.valueOf(DoE);
					
				}
		    	// Set the input values to Query
		    	prepareStatement.setString(1, name);
		    	prepareStatement.setDate(2, sqlDoB);
		    	prepareStatement.setDate(3, sqlDoE);
		    	
		    	//executing the query
                rowCount = prepareStatement.executeUpdate();
                System.out.println("No of rows inserted inserted is :: " + rowCount);	    	   
				
			}
			}
			 
		} 
		catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
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
