package StoredProcedure;

import java.io.*;
import java.sql.*;


import java.util.*;
import in.asif.util.*;


public class CsStoredOperationInMYSQLApp {

	private static final String sql="{CALL P_GET_PRODUCT_BY_NAME(?,?)}";
	public static void main(String[] args) {
		
		Connection connection=null;
		CallableStatement cstmt=null;
		Scanner scanner=null;
		String name1=null;
		String name2=null;
		ResultSet resultSet =null;
		boolean flag=false;
		
			try {
			 connection = JdbcUtil.getJdbcConnection();
			 
			 if (connection!=null) {
	    
            
			 cstmt = connection.prepareCall(sql);
			 }
		       if (cstmt!=null) {
		    	   
		    	scanner=new Scanner(System.in);
		       }
		    	if (scanner!=null) 
		    	{
		    		System.out.println("Enter the product name1::");
			    	name1 = scanner.next();
			    	System.out.println("Enter the product name2::");
			    	name2 = scanner.next();
					
				}
		    	if (cstmt!=null) {
		    		cstmt.setString(1,name1);
		    		cstmt.setString(2,name2);
		    	
				}
		    	//run the stored value
		    	if (cstmt != null) {
					cstmt.execute();
				}
		    	//
		    	if (cstmt != null) {
					 resultSet = cstmt.getResultSet();
				}
		    	//retrieve the result
		    	if (resultSet != null) {
		    		System.out.println("pid\tproductName\tproductRate\tproductQuantity");
		    		while(resultSet.next())
		    			
		    		{  
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)
					+"\t\t"+resultSet.getInt(3) +"\t\t"+resultSet.getInt(4));
					flag=true;
		    		}
				}
		    	if (flag) {
		    		System.out.println("Record found and printed!");
		    		
					
				} else {
                    System.out.println("Record not found!");
				}
		    	
		    	
		    	
			 
		} 
		catch (SQLException | IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, cstmt, null);
				System.out.println("Closing The Resources");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		

	}

}
