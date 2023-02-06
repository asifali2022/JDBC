package in.asif.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.*;

public class WebRowSetInsertApp {

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
        System.out.println("ID\\tNAME\\tAGE\\tADDRESS\\tSALARY");
        wrs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
        wrs.execute();
        while (wrs.next()) {
        	System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
		}
        
        wrs.last();
        wrs.moveToInsertRow();
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
        	
        	
        	wrs.updateInt(1,id);
            wrs.updateString(2,name);
            wrs.updateInt(3,age);
            wrs.updateString(4,address);
            wrs.updateInt(5,salary);
            
            
            wrs.insertRow();
            System.out.println("Record Inserted Sucessfully");
            System.out.println("Do you Want To Enter More Record[YES/NO]");
            String option = sc.next();
            if (option.equalsIgnoreCase("no")) {
            	break;
			}
            wrs.moveToCurrentRow();
            wrs.acceptChanges();
        }
        sc.close();
        wrs.beforeFirst();
        System.out.println("Updated resultset");
        System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
        while (wrs.next()) {
        	System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
		}

}
}