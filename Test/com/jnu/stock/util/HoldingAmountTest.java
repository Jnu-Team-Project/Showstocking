package com.jnu.stock.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HoldingAmountTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws BiffException, IOException {
		Workbook book = Workbook.getWorkbook(new File("meijia.xls"));
		int i =HoldingAmount.holdingAmountCalculation("15-3-10","北京银行",book);
		System.out.print(i+"\n");
		book.close();
	}

}
