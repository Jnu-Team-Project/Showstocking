package com.jnu.stock;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ModifyTable extends JPanel 
{
	int[] total = new int[20];
  String username="";
  Double dinglanriyingkui = 0.0;
  Double dinglanyingkui = 0.0;
  Double dinglanshizhi = 0.0;
  Double dinglanxianjin = 0.0;
  
  public Double getDinglanshizhi() {
	return dinglanshizhi;
}
public void setDinglanshizhi(Double dinglanshizhi) {
	this.dinglanshizhi = dinglanshizhi;
}
public Double getDinglanxianjin() {
	return dinglanxianjin;
}
public void setDinglanxianjin(Double dinglanxianjin) {
	this.dinglanxianjin = dinglanxianjin;
}

  
  public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Double getDinglanriyingkui() {
	return dinglanriyingkui;
}
public void setDinglanriyingkui(Double dinglanriyingkui) {
	this.dinglanriyingkui = dinglanriyingkui;
}
public Double getDinglanyingkui() {
	return dinglanyingkui;
}
public void setDinglanyingkui(Double dinglanyingkui) {
	this.dinglanyingkui = dinglanyingkui;
}
private String[] columnNames={"��Ʊ", "��ǰ��", "�ǵ�","������","������ֵ","ӯ��","����"};
  Object[][] rowData=
	  {
	  };
  DefaultTableModel tableModel = new DefaultTableModel(rowData,columnNames){  
      @Override  
      public boolean isCellEditable(int row,int column){  
          return false;  
      }  
  }; 
  // Create a table
  JTable jTable1 = new JTable(tableModel);
  
  
  
  public JTable getjTable1() {
	return jTable1;
}
public void setjTable1(JTable jTable1) {
	this.jTable1 = jTable1;
}
private JTextField bianhao = new JTextField(8);
  private JLabel add = new JLabel("��Ʊ��ţ�");
  private JButton jbtAddRow = new JButton("���");
  public int[] SetTable()
  {
	  return total;
  }
  public  ModifyTable(String uname,int n) 
  {
	 
	 if(n!=1)
	 {
	  username=uname;
	  //System.out.print("ModifyTable�����ܹ���������������������������������������������");//------
  			try {			
  				//System.out.print(username+"haha");////////////////////////////////////////////////////////////////////////////////////////////		
				Workbook book = Workbook.getWorkbook(new File(username+".xls"));			
				// System.out.print("book�����ܹ���������������������������������������������");
				try
				{
					Sheet sheet1 = book.getSheet(0);
					String xianjin = sheet1.getCell(12, 0).getContents();
					dinglanxianjin = Double.parseDouble(xianjin);
					for(int i=0;i<book.getNumberOfSheets();i++)
					{
						String search = book.getSheet(i).getName();
						Sheet sheet = book.getSheet(search);
						
						
						String number = sheet.getCell(1, 1).getContents();
						int amountru=0;
						int amountchu=0;
						int amount=0;
						Double riyingkui = 0.0;
						Double chengben = 0.0;
						Double shouru = 0.0;
						//String[] row={book.getSheet(i).getName(),"","","","","","","","����"};///////////////////////netnetnetn
						try{
	            			URL gis = new URL("http://hq.sinajs.cn/list="+number);//��Ҫ�������ҳ
	            			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
	            			String line="";
	            			String[] data;
	            			DecimalFormat df=new DecimalFormat(".##");
	            			//System.out.print(number+"&&&&&&&\n");
	            			while( (line = in.readLine()) != null )
	            			{
	            				data = line.split(",");
	            				String nowprice = data[3];
	            				Double zhangdie1 = Double.parseDouble(nowprice)-Double.parseDouble(data[2]);
	            				String zhangdie = df.format(zhangdie1);
	            				int m = Integer.parseInt(sheet.getCell(11,0).getContents());
	            				
	            				if(m>0)
	            				{
									for(int j=0;j<m;j++)
									{
										if(sheet.getCell(3,j+1).getContents().equals("����")||sheet.getCell(3,j+1).getContents().equals("����"))
										{
											int num = Integer.parseInt(sheet.getCell(5,j+1).getContents());
											amountru = amountru + num;
											chengben = chengben+(num*Double.parseDouble(sheet.getCell(4,j+1).getContents()))*1.0013;
										}
										//System.out.print("########"+number);
										if(sheet.getCell(3,j+1).getContents().equals("����")||sheet.getCell(3,j+1).getContents().equals("����"))
										{
											//amountchu = amountchu + Integer.parseInt(sheet.getCell(5,j+1).getContents());
											int num = Integer.parseInt(sheet.getCell(5,j+1).getContents());
											amountchu = amountchu + num;
											shouru = shouru+(num*Double.parseDouble(sheet.getCell(4,j+1).getContents()))*(1-0.0013);
										}		
									}					
									amount = amountru-amountchu;
		            				total[i] = amount;
		            				
		            				
		            				
		            				Double chiyoushizhi = amount*Double.parseDouble(nowprice);
		            				
		            				riyingkui = amount*zhangdie1;//////////////////////////////////////////////////////////////////////////////
		            				dinglanriyingkui = dinglanriyingkui +riyingkui;
		            				String chiyouliang = String.valueOf(amount);
		            				String chiyoushizhistr = df.format(chiyoushizhi);/////���й�Ʊ��*��ǰ�۸�
		            				
		            				Double yingkui = chiyoushizhi+shouru-chengben;
		            				dinglanyingkui=dinglanyingkui+yingkui;
		            				dinglanshizhi += chiyoushizhi;
		            				String yingkuistr = df.format(yingkui);	
		            				
		            				System.out.print(chiyouliang+"\n");
		            				String sheetname = book.getSheet(i).getName();
		            				if(!sheetname.equals("Sheet1"))
		            				{
			            				String[] row={sheetname,nowprice,zhangdie,chiyouliang,chiyoushizhistr,yingkuistr,"����"};///////////////////////netnetnetn			                	
			            				tableModel.addRow(row);	
		            				}
	            				}
	            				else
	            				{
	            					String[] row={book.getSheet(i).getName(),nowprice,zhangdie,"","","","����"};
	            					tableModel.addRow(row);	
	            				}
	            			}
	            			in.close();
	            			//pw.close();
	            			}
	            			catch(Exception er)
							{
	            				System.out.println(er); 
	            			}
					}
				}finally
				{
					if(book != null)
					{
						book.close();
					}
				}
  	  		} catch (BiffException e) {
  				System.err.println(e+"");
			} catch (IOException e) {
				System.err.println(e+"�ļ���ȡ����");
			}

	  TableColumn OpColumn = jTable1.getColumn("����");  
	  DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer();
	  fontColor.setForeground(Color.blue); 
	  OpColumn.setCellRenderer(fontColor);
	this.setLayout(new BorderLayout());
    JPanel panel1 = new JPanel();
    panel1.add(add);
    panel1.add(bianhao);
    panel1.add(jbtAddRow);

    JPanel panel6 = new JPanel();
    panel6.setLayout(new BorderLayout());
    panel6.add(panel1, BorderLayout.SOUTH);

    add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
    add(panel1, BorderLayout.SOUTH);

    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    jbtAddRow.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    		Addnewstock addns = new Addnewstock();
    	 	String bianhaostr = bianhao.getText();
    	 	String stockname = addns.Searchstock(bianhaostr);
    	 	//addns.stockname=stockname;
    	 	System.out.print(stockname+"%%%%\n");
    	 	if(!stockname.equals(""))
    	 	{
    	 		String nowprice = addns.data[3];
    			Double zhangdie1 = Double.parseDouble(nowprice)-Double.parseDouble(addns.data[2]);
    			String zhangdie = String.valueOf(zhangdie1);
        	 	String[] row={stockname,nowprice,zhangdie,"","","","","","����"};///////////////////////netnetnetn
    	 		tableModel.addRow(row);	 
    	 		addns.Addsheet(username);
    	 		bianhao.setText("");
    	 	}
    	 	
    	 	else
    	 	{
    	 		JOptionPane.showMessageDialog(null,"�ù�Ʊ������","",JOptionPane.INFORMATION_MESSAGE);
    	 		bianhao.setText("");
    	 	}
    	 
    } 
  });

  }
  else
  {
	  System.out.print("����û�û�ü�¼");/////////////////////////////////////////////////////////////////////////////////////////////
	  username=uname;
	this.setLayout(new BorderLayout());
    JPanel panel1 = new JPanel();
    panel1.add(jbtAddRow);
    JPanel panel6 = new JPanel();
    panel6.setLayout(new BorderLayout());
    panel6.add(panel1, BorderLayout.SOUTH);
    add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
    add(panel1, BorderLayout.SOUTH);
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jbtAddRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0)
        tableModel.addRow(new java.util.Vector());
        else
          tableModel.addRow(new java.util.Vector());
      }
      
    });
  	}
	
  }
  private Vector getColumnNames() {
    Vector<String> columnNames = new Vector<String>();

    for (int i = 0; i < jTable1.getColumnCount(); i++)
      columnNames.add(jTable1.getColumnName(i));

    return columnNames;
  }
}
