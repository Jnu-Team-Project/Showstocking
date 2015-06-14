package com.jnu.stock.util;

import com.jnu.stock.Oldestdate;

import jxl.Sheet;
import jxl.Workbook;

public class HoldingAmount {
	public static int holdingAmountCalculation(String date, String stockName, Workbook book){
		
		
		Sheet stockSheet = book.getSheet(stockName);
		int recordCount = Integer.parseInt(stockSheet.getCell(11, 0).getContents());
		int buy = 0;  //����
		int sell = 0; //����
		if (recordCount > 0) {
			System.out.println(stockSheet.getCell(2, 1).getContents()); 
			for (int j = 0; j < recordCount; j++) 
			{
				if(Oldestdate.isOldestdate(stockSheet.getCell(2, j + 1).getContents(), date))
				{
					System.out.print("#########"+stockSheet.getCell(2, j + 1).getContents()+"####\n");
					if ("����".equals(stockSheet.getCell(3, j + 1).getContents())||
							"����".equals(stockSheet.getCell(3, j + 1).getContents())) {
						buy += Integer.parseInt(stockSheet.getCell(5, j + 1).getContents());
					}
					
					if (stockSheet.getCell(3, j + 1).getContents().equals("����") || stockSheet.getCell(3, j + 1)
									.getContents()
									.equals("����")) {
						sell += Integer.parseInt(stockSheet.getCell(5, j + 1).getContents());
					}
				}
			}
		}
		return buy - sell;
	}
}
