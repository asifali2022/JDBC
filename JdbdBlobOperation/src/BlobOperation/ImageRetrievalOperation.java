package BlobOperation;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.IOUtils;

import in.asif.util.*;


public class ImageRetrievalOperation {

	public static void main(String[] args) {
		
		Connection connection=null;
		PreparedStatement prepareStatement=null;
		Scanner scanner=null;
		ResultSet resultSet=null;
		

		try {
			 connection = JdbcUtil.getJdbcConnection();
			 System.out.println("Connection Established....");
			 
			 if (connection!=null) {
	    
            String SqlInsertionQuery="SELECT id,name,profilepic FROM picinfo WHERE id=?";
		       prepareStatement = connection.prepareStatement(SqlInsertionQuery);
				
		       if (prepareStatement!=null) {
		    	   
		    	scanner=new Scanner(System.in);
		    	System.out.println("Enter the ID::");
		    	int id = scanner.nextInt();
		    
		    	// Set the input values to Query
		    	prepareStatement.setInt(1, id);
		    	
		    	
		    	//executing the query
                resultSet = prepareStatement.executeQuery();
		       }
                if (resultSet!=null) 
                {
                	System.out.println("ID\tNAME\tPROFILEPIC");
                	if (resultSet.next()) 
                	{
                		int ID=resultSet.getInt(1);
                		String name = resultSet.getString(2);
                	    InputStream is = resultSet.getBinaryStream(3);
                	    File file = new File("image.png");
                	    FileOutputStream fos = new FileOutputStream(file);
                	    
//                	    /// way --->01
//                	    int i=is.read();
//                	    while(i!=-1)
//                	    {
//                	    	fos.write(i);
//                	    	i=is.read();
//                	    	
//                	    }
//                	    /// way --->02
//                	    byte [] b=new byte[1024];
//                	    while(is.read(b)>0)
//                	    {
//                	    	fos.write(b);
//                	    }
                	    
                	    //way ---->03(preffered use Apache Util API)
                	    IOUtils.copy(is, fos);
                	    
                	    
                	    fos.close();
						
						System.out.println(ID+"\t"+name+"\t"+file.getAbsolutePath());
					} 
                	else 
                	{
                  System.out.println("Data Not Found!");
					}
					
				}	    	   
				
			}
			
			 
		} 
		catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, prepareStatement, null);
				System.out.println("Closing The Resources");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

}
