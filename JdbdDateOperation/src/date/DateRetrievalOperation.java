package date;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import in.asif.util.*;


public class DateRetrievalOperation {

	public static void main(String[] args) {
		
		Connection connection=null;
		PreparedStatement prepareStatement=null;
		Scanner scanner=null;
		ResultSet resultSet=null;
		
		java.sql.Date sqlDoB=null;
		java.sql.Date sqlDoE=null;
		
		
		
		try {
			 connection = JdbcUtil.getJdbcConnection();
			 System.out.println("Connection Established....");
			 
			 if (connection!=null) {
	    
            String SqlInsertionQuery="SELECT id,name,DoB,DoE FROM dateinfo1 WHERE id=?";
		       prepareStatement = connection.prepareStatement(SqlInsertionQuery);
				
		       if (prepareStatement!=null) {
		    	   
		    	scanner=new Scanner(System.in);
		    	System.out.println("Enter the ID::");
		    	int id = scanner.nextInt();
		    
		    	// Set the input values to Query
		    	prepareStatement.setInt(1, id);
		    	
		    	
		    	//executing the query
                resultSet = prepareStatement.executeQuery();
                if (resultSet!=null) {
                	System.out.println("ID\tNAME\tDOB\t\tDOE");
                	if (resultSet.next()) {
                		int ID=resultSet.getInt(1);
                		String name = resultSet.getString(2);
                		java.sql.Date sdob=resultSet.getDate(3);
						Date sdoe = resultSet.getDate(4);
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String DOB = sdf.format(sdob);
						String DOE = sdf.format(sdoe);
						System.out.println(ID+"\t"+name+"\t"+DOB+"\t"+DOE);
					} else {
                  System.out.println("Data Not Found!");
					}
					
				}	    	   
				
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
