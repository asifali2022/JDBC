import in.asif.util.*;
import java.io.*;
import java.sql.*;
import java.util.*;


public class PreparedStatementInsertOperation {

	public static void main(String[] args) {
	
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner scanner=null;
		ResultSet resultSet=null;
		
		try {
			connection=JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
			{
				String InsertQuery="INSERT INTO batchinfo(`name`,`course`) VALUES(?,?)";
				pstmt=connection.prepareStatement(InsertQuery);
			}
			if(pstmt!=null)
			{
//				Scanner sc=new Scanner(System.in);
				
//				System.out.println("Enter The Student Name::");
//				String name=sc.next();
//				
//				System.out.println("Enter The Student's Course_Name::");
//				String course=sc.next();
				
				pstmt.setString(1,"xie");
				pstmt.setString(2, "css");
				
				int rowCount=pstmt.executeUpdate();
				
				System.out.println("No of row updated ::"+rowCount);
				
				
			}
			
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
				System.out.println("Closing The Resources.....");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}
