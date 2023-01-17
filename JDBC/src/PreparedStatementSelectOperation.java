
import in.asif.util.JdbcUtil;
import java.io.*;
import java.sql.*;
import java.util.Scanner;



/**
 * @author asif
 * @Company iNeuron
 * @see www.ineuron.ai
 *
 */
public class PreparedStatementSelectOperation {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt=null;
		Scanner scanner=null;
		ResultSet resultSet = null;
		int id=0;

		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {
				String SelectQuery="select id,name,course from batchinfo WHERE id=?";
				pstmt=connection.prepareStatement(SelectQuery);
			}
			if (pstmt != null)
				scanner = new Scanner(System.in);

			System.out.print("Enter the id of the student :: ");
			id = scanner.nextInt();

			// use precompiled query to set the values
			pstmt.setInt(1,id);
			
			resultSet=pstmt.executeQuery();

			if (resultSet != null) {
				
				if (resultSet.next()) {
					System.out.println("ID\tNAME\tCOURSE");
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(1) + "\t" 
							+ resultSet.getString(2) );
				} else 
				{
					System.out.println("Record not available for the give id :: " + id);
				}
			}
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally 
		{
			try {
				JdbcUtil.cleanUp(connection, pstmt, resultSet);
				System.out.println("closing the resources...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
