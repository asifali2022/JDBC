package in.asif.main;

import java.sql.SQLException;

import javax.sql.rowset.*;

public class RowSetDeleteApp {

	public static void main(String[] args) throws SQLException {
      
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();// same as resultSet(connected),but it is scrollable and updatable.

		// setting url,username,password and getting connection object..
		jrs.setUrl("jdbc:mysql:///javafsj");
		jrs.setUsername("root");
		jrs.setPassword("nopassword");

		// setting a command for execution
		jrs.setCommand("select eid,ename,eage,eaddress,esalary from employee");
		jrs.execute();

		while (jrs.next()) {
			int actualSalary = jrs.getInt(5);
			if (actualSalary < 4000) {
				
				jrs.deleteRow();
			}
		}
		System.out.println("Records Deleted succesfully....");
		jrs.close();

	}

}
