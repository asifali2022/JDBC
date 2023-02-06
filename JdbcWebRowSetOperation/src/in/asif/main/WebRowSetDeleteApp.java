package in.asif.main;

import java.sql.SQLException;

import javax.sql.rowset.*;

public class WebRowSetDeleteApp {

	public static void main(String[] args) throws SQLException {
      
		RowSetFactory rsf = RowSetProvider.newFactory();
		WebRowSet wrs = rsf.createWebRowSet();
		// setting url,username,password and getting connection object..
		wrs.setUrl("jdbc:mysql:///javafsj");
		wrs.setUsername("root");
		wrs.setPassword("nopassword");

		// setting a command for execution
		wrs.setCommand("select eid,ename,eage,eaddress,esalary from employee");
		wrs.execute();
        
		while (wrs.next()) {
			int actualSalary = wrs.getInt(5);
			if (actualSalary <7000) {
				
				wrs.deleteRow();
			}
		}
		System.out.println("Records Deleted succesfully....");
        wrs.moveToCurrentRow();
        wrs.acceptChanges();
		wrs.close();
		

	}

}
