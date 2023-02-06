package in.asif.main;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.rowset.*;

public class WebRowSetSelectApp 
{

	public static void main(String[] args) throws SQLException, IOException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		WebRowSet wrs = rsf.createWebRowSet();
		//same as ResultSET(connected) BUT scrollable and updatable
		
		//not serializable
		
		//Setting Username ,Url,Password
		wrs.setUrl("jdbc:mysql:///javafsj");
		wrs.setUsername("root");
		wrs.setPassword("nopassword");
		
		wrs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
        wrs.execute();
        FileWriter fw = new FileWriter("EmplyoeeData.xml");
        wrs.writeXml(fw);
        System.out.println("Data Imported to Xml File......");
        System.out.println("Retrieving The ResultSet");
        wrs.beforeFirst();
        System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
        while (wrs.next()) {
        	System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
		}
        System.out.println("Retrieving The ResultSet in previous Direction");
        System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
        while (wrs.previous()) {
        	System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
		}
        System.out.println("------------------------------------------------------------");
        System.out.println("checking the scrollability ");
        wrs.beforeFirst();
        wrs.first();
        System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
        wrs.absolute(3);
        System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
        wrs.relative(2);
        System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
        wrs.last();
        wrs.relative(-6);
        System.out.println(wrs.getInt(1)+"\t"+wrs.getString(2)+"\t"+wrs.getInt(3)+"\t"+wrs.getString(4)+"\t"+wrs.getInt(5));
        System.out.println("------------------------------------------------------------");
        
        
            
            wrs.close();
	}
}