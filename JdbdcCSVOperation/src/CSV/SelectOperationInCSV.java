package CSV;

import java.sql.*;

public class SelectOperationInCSV {

	private static final String EXCEL_CSV = "SELECT * FROM data";

	public static void main(String[] args) {
		String url="jdbc:CSV:///E:\\file";
		try(Connection connection=DriverManager.getConnection(url)){
			try(PreparedStatement preparedStatement=connection.prepareStatement(EXCEL_CSV)){
				try(ResultSet resultSet=preparedStatement.executeQuery()){
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	}