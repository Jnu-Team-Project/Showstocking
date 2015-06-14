package com.jnu.stock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Oldestdate 
{
	public static boolean isOldestdate(String newdate,String olddate)
	{
		boolean  bl = false;
		 try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yy-M-d");
	            Date date1 = sdf.parse(newdate);
	            Date date2 = sdf.parse(olddate);
	            
	            bl = ((date1.getTime()-date2.getTime())<=0); 
	            System.out.print(date1.getTime()-date2.getTime());
	            System.out.print(date1.getDay()+"\n");
	            System.out.print(date2.toString()+"\n");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		 return bl;
	}

}
