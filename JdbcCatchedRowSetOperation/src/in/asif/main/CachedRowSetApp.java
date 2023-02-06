package in.asif.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetApp {

	public static void main(String[] args) throws SQLException {
		
		
		Connection connection = DriverManager.getConnection("jdbc:mysql:///javafsj", "root", "nopassword");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT `eid`,`ename`,`eage`,`eaddress`,`esalary` FROM employee");
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs =  rsf.createCachedRowSet();
		crs.populate(resultSet);
		
		connection.close();//peration not allowed after ResultSet closed
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while (crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
			
		}
//		while (resultSet.next()) {
//			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getInt(5));
//			
//		}
		
		
        
        }
	}
	