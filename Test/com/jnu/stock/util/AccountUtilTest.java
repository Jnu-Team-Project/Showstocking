package com.jnu.stock.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException 
	{
		if(AccountUtil.isDeleteaccount("deleteaccount.txt", "meijia aaa"))
			System.out.print("*************\n");
		int total = AccountUtil.findAccountnum("subaccount.txt", "meijia");
		System.out.print(total+"\n");
		String[] allname = AccountUtil.findAllsubaccount("subaccount.txt", "meijia");
		for(int i=0;i<total;i++)
		{
			System.out.print(allname[i]+"\n");
		}
	}

}
