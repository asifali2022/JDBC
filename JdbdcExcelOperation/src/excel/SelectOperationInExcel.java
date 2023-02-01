package excel;

import java.sql.*;

public class SelectOperationInExcel {

	private static final String EXCEL_FILE = "SELECT * FROM data.student";

	public static void main(String[] args) {
		String url="jdbc:Excel:///E:\\file";
		try(Connection connection=DriverManager.getConnection(url)){
			try(PreparedStatement preparedStatement=connection.prepareStatement(EXCEL_FILE)){
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