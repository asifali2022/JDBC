package BatchUpdate;

import java.io.*;
import java.sql.*;


import java.util.*;
import in.asif.util.*;


public class JDBDBatchUpdate {

	private static final String sql="{CALL P_GET_PRODUCTDETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner scanner=null;
		Integer id=null;
		
			try {
			 connection = JdbcUtil.getJdbcConnection();
			 
			 if (connection!=null) {
	    
            
			 String sqlBatchQuery="INSERT INTO `employee`(`ename`,`eage`,`eaddress`) VALUES(?,?,?)";
			 pstmt = connection.prepareStatement(sqlBatchQuery);
			 }
		       if (pstmt!=null) {
		    	   
		       scanner=new Scanner(System.in);
		       while(true) {
		       System.out.println("Enter Employee Name::");
		        String name = scanner.next();
		       
		       System.out.println("Enter Employee Age::");
		       int age = scanner.nextInt();
		       
		       System.out.println("Enter Employee Address::");
		       String address = scanner.next();
		       
		       pstmt.setString(1,name);
		       pstmt.setInt(2,age);
		       pstmt.setString(3, address);
		       
		       pstmt.addBatch();
		       
		       System.out.println("Do You Want To Add More Data[Yes/No]");
		       String option = scanner.next();
		       if (option.equalsIgnoreCase("yes")) 
		       {
				continue;
			} else {
              break;
			}
		      
		       }
		       pstmt.executeBatch();
		       System.out.println("Data Entered Successfully !");
		       
		       }
		       
		       
		} 
		catch (SQLException | IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
				System.out.println("Closing The Resources");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		

	}

}
