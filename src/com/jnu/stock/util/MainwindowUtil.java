package com.jnu.stock.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.jnu.stock.ShowStock;

public class MainwindowUtil 
{
	public static void CreateToolbar(final JFrame jframe,final String username,final JDesktopPane desktop) throws IOException
	{
		JMenuBar jmb = new JMenuBar();
		JMenu toolMenu = new JMenu("����");
		JMenu helpMenu = new JMenu("����");
		
		JMenuItem viewaccount,createaccount, deleteaccount, contactItem, aboutItem;
		
		jmb.add(toolMenu);
		jmb.add(helpMenu);
		viewaccount = new JMenuItem("�鿴�ʻ�");
		createaccount = new JMenuItem("�½��ʻ�");
		deleteaccount = new JMenuItem("ɾ���˻�");
		
		contactItem = new JMenuItem("��ϵ����");
		aboutItem = new JMenuItem("����");

		toolMenu.add(viewaccount);
		toolMenu.add(createaccount);
		toolMenu.add(deleteaccount);
		
		helpMenu.add(contactItem);
		helpMenu.add(aboutItem);
		
		
		createaccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String subaccountname =
				JOptionPane.showInputDialog(null,"��д�ʻ���", "�����ʻ�",JOptionPane.INFORMATION_MESSAGE);
				if(subaccountname != null)
				{
					JInternalFrame subinternalframe = new JInternalFrame(subaccountname,false,true,false,true);
					subinternalframe.setSize(700, 500);
					//frame.setLocation(null);
					
					desktop.add(subinternalframe);
					
					JApplet applet1 = new JApplet();
					//UsernameLabelUtil.replaceUsername(applet1, subaccountname);
					//PanelUtil.initTheSecondePanel(applet1, subaccountname, 0);//=====================6.7
					new TableUtil(applet1,subaccountname).createTable(applet1, subaccountname, 0,subinternalframe);
					subinternalframe.add(applet1);
					subinternalframe.setVisible(true);
				}
				
				try {
					AccountUtil.saveTotxt("subaccount.txt", subaccountname, username);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"�汾 1.0\n��Ȩ���� �Ŷ���Ŀ1�鱣������Ȩ��", "���ڡ�Ǭ�����С�",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		contactItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"����������·222��\n���䣺miaomiao@cat.com", "��ϵ����",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		viewaccount.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent event)
			{
				int subaccountnum=0;
				try {
					subaccountnum = AccountUtil.findAccountnum("subaccount.txt", username);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] subaccountname;
				try {
					subaccountname = AccountUtil.findAllsubaccount("subaccount.txt", username);
					Object[] subaccountnameobject = new Object[subaccountnum];
					for(int i=0;i<subaccountnum;i++)
						subaccountnameobject[i]=subaccountname[i];
					String selectaccount = (String)JOptionPane.showInputDialog(null,"ѡ���ʻ�",
							"�鿴�ʻ�",JOptionPane.PLAIN_MESSAGE,null,subaccountnameobject,subaccountnameobject[0]);
					if(selectaccount != null)
					{
						JInternalFrame subinternalframe = new JInternalFrame(selectaccount,false,true,false,true);
						subinternalframe.setSize(700, 500);
						//frame.setLocation(null);
						
						desktop.add(subinternalframe);
						
						JApplet applet1 = new JApplet();
						//UsernameLabelUtil.replaceUsername(applet1, subaccountname);
						//PanelUtil.initTheSecondePanel(applet1, subaccountname, 0);//=====================6.7
						new TableUtil(applet1,selectaccount).createTable(applet1, selectaccount, 0,subinternalframe);
						subinternalframe.add(applet1);
						subinternalframe.setVisible(true);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		deleteaccount.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent event)
			{
				int subaccountnum=0;
				try {
					subaccountnum = AccountUtil.findAccountnum("subaccount.txt", username);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] subaccountname;
				try {
					subaccountname = AccountUtil.findAllsubaccount("subaccount.txt", username);
					Object[] subaccountnameobject = new Object[subaccountnum];
					for(int i=0;i<subaccountnum;i++)
						subaccountnameobject[i]=subaccountname[i];
					
					String selectaccount = (String)JOptionPane.showInputDialog(null,"ѡ���ʻ�",
							"ɾ���ʻ�",JOptionPane.PLAIN_MESSAGE,null,subaccountnameobject,subaccountnameobject[0]);
					//String deleteaccount = (String)selectaccount;
					if(selectaccount != null)
					{
						AccountUtil.saveTotxt("deleteaccount.txt",selectaccount,username);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});		
		jframe.setJMenuBar(jmb);
		jframe.setLayout(null);
		jframe.setVisible(false);
		jframe.setVisible(true);
	}
}
