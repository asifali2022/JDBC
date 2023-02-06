package in.asif.main;

import java.sql.SQLException;

import javax.sql.rowset.*;

public class CachedRowSetDeleteApp {

	public static void main(String[] args) throws SQLException {
      
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();
		// setting url,username,password and getting connection object..
		crs.setUrl("jdbc:mysql:///javafsj");
		crs.setUsername("root");
		crs.setPassword("nopassword");

		// setting a command for execution
		crs.setCommand("select eid,ename,eage,eaddress,esalary from employee");
		crs.execute();

		while (crs.next()) {
			int actualSalary = crs.getInt(5);
			if (actualSalary <5000) {
				
				crs.deleteRow();
			}
		}
		System.out.println("Records Deleted succesfully....");
        crs.moveToCurrentRow();
        crs.acceptChanges();
		crs.close();
		

	}

}
