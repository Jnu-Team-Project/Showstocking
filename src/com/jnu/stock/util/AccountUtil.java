package com.jnu.stock.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//deleteaccount.txt
//subaccount.txt


public class AccountUtil 
{
	
	
	
	static public void saveTotxt(String txtname,String accountname,String username) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(txtname),true));
		
		try{
					writer.newLine();
					writer.write(username + " "+accountname);

			}catch(Exception e){
						     }
			
			
		writer.close();	
	}
	
	static public boolean isDeleteaccount(String txtname,String accountname) throws IOException
	{
		boolean is = false;
		BufferedReader input = new BufferedReader(new FileReader(txtname));
		String s="";
		while((s = input.readLine())!=null)
		{ //判断是否读到了最后一行
				//System.out.println(s);
			if(s.equals(accountname))
	  		{
	  			is = true;
	  			break;
	  		}
		} 
		input.close();
		return is;
	}
	
	static int findAccountnum(String txtname,String username) throws IOException
	{
		int total =0;
		BufferedReader input = new BufferedReader(new FileReader(txtname));
		String s="";
		while((s = input.readLine())!=null)
		{ //判断是否读到了最后一行
				//System.out.println(s);
			if(s.indexOf(username)!=-1)
	  		{
				if(!isDeleteaccount("deleteaccount.txt",s))
				{
					total++;
				}
	  		}
		} 	
		input.close();
		return total;
	}
	
	static String[] findAllsubaccount(String txtname,String username) throws IOException
	{
		String[] subaccounts = new String[20];
		int i = 0;
		BufferedReader input = new BufferedReader(new FileReader(txtname));
		String s="";
		while((s = input.readLine())!=null)
		{ //判断是否读到了最后一行
				//System.out.println(s);
			if(s.indexOf(username)!=-1)
	  		{
				if(!isDeleteaccount("deleteaccount.txt",s))
				{
					subaccounts[i]=(s.split(" "))[1];
					i++;
				}
	  		}
		} 	
		input.close();
		return subaccounts;
	}
	
	
	
}
	

