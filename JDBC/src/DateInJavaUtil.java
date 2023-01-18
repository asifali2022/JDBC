
import java.text.SimpleDateFormat;
import java.util.*;

public class DateInJavaUtil {

	public static void main(String[] args) throws Exception {
		
		Date udate=new Date();
		System.out.println("date and time is::"+udate);
		
		long time = udate.getTime();
		java.sql.Date date = new java.sql.Date(time);
		System.out.println("sql date is ::"+date);
		
		System.out.println("*******************************");
		
		//STEP:01>>Read Input From The User
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Date in dd-MM-yyyy");
		//MM always in uppercase
		System.out.println();
		String d = sc.next();
		
		//STEP:02>>convert date from string format to java.Util.Date
		
		// user entered string format must be same as SimpleDateFormat(AS SPECIFIED)
		//or else there will be error in value displayed
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date udate1=sdf.parse(d);
		
		//STEP:03>>convert java.util.date to java.sql.date
		
		long time2 = udate1.getTime();
		java.sql.Date sqlDate = new java.sql.Date(time2);
		
		System.out.println("Sting Format Date ::"+d);
		System.out.println("Java Util Date ::"+udate1);
		System.out.println("Java Sql Date ::"+sqlDate);
		
		System.out.println("*********************");
		
		// if the input is directly in dd-MM-yyyy
		//then we can skip step 1-3
		
		System.out.println("Enter Date in yyyy-MM-dd");
		Scanner scan = new Scanner(System.in);
		String standardInput = scan.next();
		
		java.sql.Date SqlstandardInput = java.sql.Date.valueOf(standardInput);
		System.out.println("StandardInput Date::"+standardInput);
		System.out.println("SqlstandardInput Date::"+SqlstandardInput);
	
		sc.close();
		

	}

}
