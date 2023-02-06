package in.asif.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetInsertApp {

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
        jrs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress` FROM employee");
        jrs.execute();
        while (jrs.next()) {
        	System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		}
        
        jrs.last();
        jrs.moveToInsertRow();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
        	
        	
        	System.out.println("Enter The Name::");
        	String name = sc.next();
        	System.out.println("Enter The Age::");
        	Integer age = sc.nextInt();
        	System.out.println("Enter The Address::");
        	String address = sc.next();
        	
        	
        	
            jrs.updateString(2,name);
            jrs.updateInt(3,age);
            jrs.updateString(4,address);
            
            
            jrs.insertRow();
            System.out.println("Record Inserted Sucessfully");
            System.out.println("Do you Want To Enter More Record[YES/NO]");
            String option = sc.next();
            if (option.equalsIgnoreCase("no")) {
            	break;
				
			}
        }
        sc.close();
        jrs.beforeFirst();
        System.out.println("Updated resultset");
        System.out.println("ID\tNAME\tAGE\tADDRESS");
        while (jrs.next()) {
        	System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		}

}
}