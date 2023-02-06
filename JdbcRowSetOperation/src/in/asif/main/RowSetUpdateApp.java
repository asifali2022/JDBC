package in.asif.main;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetUpdateApp {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();
		//same as ResultSET(connected) BUT scrollable and updatable
		
		//not serializable
		
		//Setting Username ,Url,Password
		jrs.setUrl("jdbc:mysql:///javafsj");
		jrs.setUsername("root");
		jrs.setPassword("nopassword");
		System.out.println("Retrieving The ResultSet");
        System.out.println("ID\tNAME\tAGE\tADDRESS");
        jrs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
        jrs.execute();
        while (jrs.next()) {
        	int salary = jrs.getInt(5);
        	if (salary<9000) {
        		int newSalary=salary+1000;
        		jrs.updateInt(5, newSalary);
        		jrs.updateRow();
				
			}	
		}
        System.out.println("Records updated succesfully....");
		jrs.close();
	}

}
