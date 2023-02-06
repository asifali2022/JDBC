package in.asif.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetApp {

	public static void main(String[] args) throws SQLException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();
		//same as ResultSET(connected) BUT scrollable and updatable
		
		//not serializable
		
		//Setting Username ,Url,Password
		jrs.setUrl("jdbc:mysql:///javafsj");
		jrs.setUsername("root");
		jrs.setPassword("nopassword");
		
		jrs.setCommand("SELECT `eid`,`ename`,`eaddress` FROM employee");
        jrs.execute();
        
        System.out.println("Retrieving The ResultSet");
        System.out.println("ID\tNAME\tADDRESS");
        while (jrs.next()) {
        	System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getString(3));
		}
        System.out.println("Retrieving The ResultSet in previous Direction");
        System.out.println("ID\tNAME\tADDRESS");
        while (jrs.previous()) {
        	System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getString(3));
		}
        System.out.println("------------------------------------------------------------");
        System.out.println("checking the scrollability ");
        jrs.beforeFirst();
        jrs.first();
        System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getString(3));
        jrs.absolute(3);
        System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getString(3));
        jrs.relative(2);
        System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getString(3));
        jrs.afterLast();
        jrs.relative(-3);
        System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getString(3));
        System.out.println("------------------------------------------------------------");
        System.out.println("checking the updatiblity");
        
            
            jrs.close();
            
        	
        }
	}
	


