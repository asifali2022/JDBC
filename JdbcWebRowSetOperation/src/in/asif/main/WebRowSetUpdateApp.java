package in.asif.main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowSetUpdateApp {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rsf = RowSetProvider.newFactory();
		WebRowSet wrs = rsf.createWebRowSet();
		//same as ResultSET(connected) BUT scrollable and updatable
		
		//not serializable
		
		//Setting Username ,Url,Password
		wrs.setUrl("jdbc:mysql:///javafsj");
		wrs.setUsername("root");
		wrs.setPassword("nopassword");
		System.out.println("Retrieving The ResultSet");
        System.out.println("ID\tNAME\tAGE\tADDRESS");
        wrs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
        wrs.execute();
        while (wrs.next()) {
        	int salary = wrs.getInt(5);
        	if (salary<10000) {
        		int newSalary=salary+1000;
        		wrs.updateInt(5, newSalary);
        		wrs.updateRow();
				
			}	
		}
        System.out.println("Records updated succesfully....");
        wrs.moveToCurrentRow();
        wrs.acceptChanges();
		wrs.close();
	}

}
