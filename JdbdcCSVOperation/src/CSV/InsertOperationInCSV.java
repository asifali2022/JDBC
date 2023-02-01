package CSV;

import java.sql.*;
import java.util.*;

public class InsertOperationInCSV {

	private static final String EXCEL_CSV = "INSERT INTO data VALUES(?,?,?)";

	public static void main(String[] args) {
		String name=null;
		String address =null;
		Integer id =null;
		Scanner scanner=null;
		
		
		try {
			 scanner=new Scanner(System.in);
			 if (scanner != null) {
				 
				 System.out.println("Enter the name::");
				 name = scanner.next();
				 System.out.println("Enter the address::");
				 address = scanner.next();
				 System.out.println("Enter the id::");
				 id = scanner.nextInt();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (scanner!=null) {
				scanner.close();
				
			}
		}
		
		// jdbc:Excel:///location where the file is present
		String url="jdbc:CSV:///E:\\file";
		try(Connection connection=DriverManager.getConnection(url)){
			try(PreparedStatement preparedStatement=connection.prepareStatement(EXCEL_CSV)){
				preparedStatement.setString(1,name);
				preparedStatement.setString(2, address);
				preparedStatement.setInt(3, id);
				
				int row = preparedStatement.executeUpdate();
				if (row==0) {
					System.out.println("Data Not Updated Successfully !");
					
				} else {
                     System.out.println("Data Entered Successfully !");
				}
				}
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	}