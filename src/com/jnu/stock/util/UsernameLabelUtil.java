package com.jnu.stock.util;

import java.awt.Font;

import javax.swing.JApplet;
import javax.swing.JLabel;

import com.jnu.stock.ShowStock;

public class UsernameLabelUtil {
	/**
	 * ��¼������������Ͻǵı�ǩΪ�û���
	 * @param userName �û���
	 */
	public static void replaceUsername(JApplet showStock, String userName) {
		JLabel usernameLabel = new JLabel(userName);
		usernameLabel.setFont(new Font("", 1, 30));
		showStock.add(usernameLabel);
		usernameLabel.setBounds(10, 10, 120, 30);
	}
}
