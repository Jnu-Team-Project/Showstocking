package com.jnu.stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class Getstockinfnow 
{
	
	public String[] getinf(String url)
	{
		String[] data={};
	
		try
		{
			URL gis = new URL("http://hq.sinajs.cn/list="+url);//��Ҫ�������ҳ
			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
			//PrintWriter pw=new PrintWriter( new FileOutputStream("api.htm"));//�����·��
			String line="";
			
			while( (line = in.readLine()) != null )
			{
				data = line.split(",");
			}
			in.close();
			//pw.close();
		}
		catch(Exception er){
				System.out.println(er); 
			}
		return data;
	}

}
