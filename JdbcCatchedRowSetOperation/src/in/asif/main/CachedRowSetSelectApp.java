package in.asif.main;

import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.rowset.*;

public class CachedRowSetSelectApp 
{

	public static void main(String[] args) throws SQLException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();
		//same as ResultSET(connected) BUT scrollable and updatable
		
		//not serializable
		
		//Setting Username ,Url,Password
		crs.setUrl("jdbc:mysql:///javafsj");
		crs.setUsername("root");
		crs.setPassword("nopassword");
		
		crs.setCommand("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
        crs.execute();
        
        System.out.println("Retrieving The ResultSet");
        System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
        while (crs.next()) {
        	System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
        System.out.println("Retrieving The ResultSet in previous Direction");
        System.out.println("ID\\tNAME\\tAGE\\tADDRESS\\tSALARY");
        while (crs.previous()) {
        	System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
        System.out.println("------------------------------------------------------------");
        System.out.println("checking the scrollability ");
        crs.beforeFirst();
        crs.first();
        System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
        crs.absolute(3);
        System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
        crs.relative(2);
        System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
        crs.last();
        crs.relative(-6);
        System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
        System.out.println("------------------------------------------------------------");
        
        
            
            crs.close();
	}
}