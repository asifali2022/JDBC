package StoredProcedure;

import java.io.*;
import java.sql.*;


import java.util.*;
import in.asif.util.*;


public class CsStoredOperationInMYSQL {

	private static final String sql="{CALL P_GET_PRODUCTDETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		
		Connection connection=null;
		CallableStatement cstmt=null;
		Scanner scanner=null;
		Integer id=null;
		
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
		    		System.out.println("Enter the product id::");
			    	id = scanner.nextInt();
					
				}
		    	if (cstmt!=null) {
		    		cstmt.setInt(1,id);
		    	
				}
		    	//setting he datatype of input values
		    	if (cstmt != null) {
					cstmt.registerOutParameter(2,Types.VARCHAR);
					cstmt.registerOutParameter(3,Types.INTEGER);
					cstmt.registerOutParameter(4,Types.INTEGER);
				}
		    	//run the stored value
		    	if (cstmt != null) {
					cstmt.execute();
				}
		    	//retrieve the result
		    	if (cstmt != null) {
					System.out.println("Product Name is :: " +cstmt.getString(2));
					System.out.println("Product Cost is :: " +cstmt.getInt(3));
					System.out.println("Product QTY  is :: " +cstmt.getInt(4));
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
