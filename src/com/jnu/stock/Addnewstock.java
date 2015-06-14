package com.jnu.stock;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * �ṩ������Ʊ���޸Ĺ�Ʊ�Ĺ���
 * @author MJ
 *
 */

public class Addnewstock 
{
	String stockname="";
	String stocknum="";
	String[] data;
	public String Searchstock(String stock)
	{
		String name="";
		try{
			URL gis = new URL("http://hq.sinajs.cn/list="+stock);//��Ҫ�������ҳ
			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
			//PrintWriter pw=new PrintWriter( new FileOutputStream("api.htm"));//�����·��
			String line="";
			
			
			//System.out.print(number);
			while( (line = in.readLine()) != null )
			{
				
				data = line.split(",");
				if(data.length>1)
				{
	            	String[] data1 = data[0].split("=\"");
	            	name=data1[1];	
				}
			}
			in.close();
			//pw.close();
			}
		catch (MalformedURLException e) {  
			//System.out.print("dede");
            //e.printStackTrace();  
        } catch (IOException e) {  
        	//System.out.print("dede");
            //e.printStackTrace();  
        }  
		stockname = name;
		stocknum = stock;
		return name;
	}
	public String Addsheet(String usename)
	{
		System.out.print(usename+"#############?$$$\n");
		String newsheetname="";
		//File file;
		
		File file = new File(usename+".xls");
		System.out.print("#############?$$$333\n");
		if(!file.exists())
		{	
			
			try {
				WritableWorkbook book = Workbook.createWorkbook(new File(usename+".xls"));
				WritableSheet sheet = book.createSheet(stockname,0);  
				
				Label l;   	
				l=new Label(11,0,"0");
				sheet.addCell(l);
				l=new Label(1,1,stocknum);
				sheet.addCell(l);
				newsheetname = book.getSheet(0).getName();
				 book.write();  
		         book.close();  
			} catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (RowsExceededException e) {  
	            e.printStackTrace();  
	        } catch (WriteException e) {  
	            e.printStackTrace();  
	        }  
			  
			
		}
		//System.out.print("#############?$$$333\n");
		else
		{
		try
		{	
			//Workbook wb=Workbook.getWorkbook(new File(usename+".xls")); 
			//WritableWorkbook book1 = Workbook.createWorkbook(new File(usename+".xls"));  			
            //WritableSheet sheet1 = book1.createSheet(stockname,0); 
            
            System.out.print("�����ܴ�����\n");
			Workbook wb=Workbook.getWorkbook(new File(usename+".xls"));  
			
			 System.out.print("�����ܴ�����2\n");
			WritableWorkbook book=  Workbook.createWorkbook(file,wb);  
			System.out.print("#############?$$$222\n");
			int total = book.getNumberOfSheets();
			System.out.print(total+"#############?$$$223\n");
			System.out.print(stockname+"#############$$$\n");
			WritableSheet sheet=book.createSheet(stockname,total); 
			Label l;   	
			l=new Label(11,0,"0");
			sheet.addCell(l);
			
			l=new Label(12,0,"0");
			sheet.addCell(l);
			
			l=new Label(1,1,stocknum);
			sheet.addCell(l);
			newsheetname = book.getSheet(total).getName();
			
			book.write();  
			book.close();  
			wb.close();
		
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		}
		return newsheetname;
	}
	
	public void creatxls(String filename)
	{
		
		File file = new File(filename);
		if(!file.exists())
		{
			try {
			file.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}
