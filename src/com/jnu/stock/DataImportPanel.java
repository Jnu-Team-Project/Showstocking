package com.jnu.stock;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


class DataImportPanel extends JPanel //ע�����
{
	 private static final long serialVersionUID = 1L;
	 private JLabel caution;
	 //private JTextField user1;
	 //private JPasswordField password1,passwordnd1;
	 JButton daoru,next;
	 int width = 0, hight = 0;
	 public DataImportPanel(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  caution=new JLabel("�Ƿ��оɽ��׼�¼��Ҫ����");
		  caution.setFont(new Font("", Font.PLAIN, 25));
		  daoru = new JButton("�ǣ���������");
		  next = new JButton("�ޣ�ֱ�ӽ�����ҳ");
		  this.add(daoru);
		  this.add(next);
		  this.add(caution);
		  caution.setForeground(Color.DARK_GRAY);
		  caution.setBounds(240,180,330,40);
		  daoru.setBounds(200,270,130,30);
		  next.setBounds(350,270,130,30);
	 }
}