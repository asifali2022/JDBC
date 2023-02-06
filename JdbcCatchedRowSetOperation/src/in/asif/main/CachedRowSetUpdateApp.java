package in.asif.main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetUpdateApp {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rsf = RowSetProvider.newFactory();
		 CachedRowSet crs = rsf.createCachedRowSet();
		//same as ResultSET(connected) BUT scrollable and updatable
		
		//not serializable
		
		//Setting Username ,Url,Password
		crs.setUrl("jdbc:mysql:///javafsj");
		crs.setUsername("root");
		crs.setPassword("nopassword");
		System.out.println("Retrieving The ResultSet");
        System.out.println("ID\tNAME\tAGE\tADDRESS");
        crs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
        crs.execute();
        while (crs.next()) {
        	int salary = crs.getInt(5);
        	if (salary<9000) {
        		int newSalary=salary+1000;
        		crs.updateInt(5, newSalary);
        		crs.updateRow();
				
			}	
		}
        System.out.println("Records updated succesfully....");
		crs.close();
	}

}
