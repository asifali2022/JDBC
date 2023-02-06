package in.asif.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.*;

public class CachedRowSetInsertApp {

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
        System.out.println("ID\\tNAME\\tAGE\\tADDRESS\\tSALARY");
        crs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
        crs.execute();
        while (crs.next()) {
        	System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
        
        crs.last();
        crs.moveToInsertRow();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
        	
        	System.out.println("Enter The Id::");//cached rowset doesnot support autoincrement
        	Integer id = sc.nextInt();
        	System.out.println("Enter The Name::");
        	String name = sc.next();
        	System.out.println("Enter The Age::");
        	Integer age = sc.nextInt();
        	System.out.println("Enter The Address::");
        	String address = sc.next();
        	System.out.println("Enter The Salary::");
        	Integer salary = sc.nextInt();
        	
        	
        	crs.updateInt(1,id);
            crs.updateString(2,name);
            crs.updateInt(3,age);
            crs.updateString(4,address);
            crs.updateInt(5,salary);
            
            
            crs.insertRow();
            System.out.println("Record Inserted Sucessfully");
            System.out.println("Do you Want To Enter More Record[YES/NO]");
            String option = sc.next();
            if (option.equalsIgnoreCase("no")) {
            	break;
			}
            crs.moveToCurrentRow();
            crs.acceptChanges();
        }
        sc.close();
        crs.beforeFirst();
        System.out.println("Updated resultset");
        System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
        while (crs.next()) {
        	System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}

}
}