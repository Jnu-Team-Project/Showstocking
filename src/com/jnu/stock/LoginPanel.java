package com.jnu.stock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoginPanel extends JPanel // ��¼���
{
	static Logger logger = Logger.getLogger(LoginPanel.class.getName()); // ================================
	private static final long serialVersionUID = 1L;
	JLabel user, password, register1, nullcaution, usercaution;
	JTextField user1;
	JPasswordField password1;
	JButton login, register;
	int width = 0, hight = 0;
	String imgpath = "";

	static class BarThread extends Thread {
		// PropertyConfigurator.configure("src//log4j.properties");
		private static int DELAY = 100;
		JProgressBar progressBar;

		public BarThread(JProgressBar bar) {
			progressBar = bar;
		}

		public void run() {
			int minimum = progressBar.getMinimum();
			int maximum = progressBar.getMaximum();
			Runnable runner = new Runnable() {
				public void run() {
					int value = progressBar.getValue();
					progressBar.setValue(value + 5);
				}
			};
			for (int i = minimum; i < maximum; i++) {
				try {
					SwingUtilities.invokeAndWait(runner);
					// Our task for each step is to just sleep
					Thread.sleep(DELAY);
				} catch (InterruptedException ignoredException) {
				} catch (InvocationTargetException ignoredException) {
				}
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public LoginPanel(int width, int hight, String file) {
		PropertyConfigurator.configure("src//log4j.properties");// ================================
		logger.info("��¼��幹��ɹ�"); // ================================
		this.width = width;
		this.hight = hight;
		imgpath = file;
		this.setLayout(null);
		// this.add(new JLabel("�û���"));
		// this.add(new
		// JTextField("Time to be displayed here"),BorderLayout.SOUTH);
		// jp.setLayout(new GridBagLayout());
		user = new JLabel("�û���");
		user.setFont(new Font("", Font.PLAIN, 20));
		password = new JLabel("����");
		password.setFont(new Font("", Font.PLAIN, 20));
		login = new JButton("��  ¼");
		register = new JButton("�����û�");
		register1 = new JLabel("�����û�");

		nullcaution = new JLabel("�û��������벻��Ϊ��");
		usercaution = new JLabel("�û������������");

		user1 = new JTextField(30);
		password1 = new JPasswordField(30);

		// //////////////////////////////////////////// 4.12����
		RegisterPanel rp = new RegisterPanel(750, 500);

		this.add(user);
		this.add(user1);
		this.add(password);
		this.add(password1);
		this.add(login);
		this.add(register1);
		this.add(nullcaution);
		this.add(usercaution);
		nullcaution.setVisible(false);
		usercaution.setVisible(false);

		nullcaution.setForeground(Color.red);
		usercaution.setForeground(Color.red);

		user.setForeground(Color.DARK_GRAY);
		password.setForeground(Color.DARK_GRAY);
		register1.setForeground(Color.BLUE);
		user.setBounds(240, 340, 80, 20);
		user1.setBounds(320, 340, 140, 25);
		password.setBounds(240, 375, 80, 20);
		password1.setBounds(320, 370, 140, 25);
		login.setBounds(310, 430, 100, 25);
		register1.setBounds(480, 340, 120, 25);

		nullcaution.setBounds(300, 400, 120, 20);
		usercaution.setBounds(300, 400, 120, 20);

		user1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				nullcaution.setVisible(false);
				usercaution.setVisible(false);
			}

			public void insertUpdate(DocumentEvent e) {
				nullcaution.setVisible(false);
				usercaution.setVisible(false);
			}

			public void removeUpdate(DocumentEvent e) {
				nullcaution.setVisible(false);
				usercaution.setVisible(false);
			}

		});
		password1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				nullcaution.setVisible(false);
				usercaution.setVisible(false);
			}

			public void insertUpdate(DocumentEvent e) {
				nullcaution.setVisible(false);
				usercaution.setVisible(false);
			}

			public void removeUpdate(DocumentEvent e) {
				nullcaution.setVisible(false);
				usercaution.setVisible(false);
			}

		});

		// ////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * final JProgressBar aJProgressBar = new JProgressBar(0, 100); //final
		 * JButton aJButton = new JButton("Start");
		 * 
		 * aJProgressBar.setStringPainted(true); // ��ʾ�ٷֱ��ַ�
		 * aJProgressBar.setIndeterminate(false); // ��ȷ���Ľ�����
		 */

		/*
		 * ActionListener actionListener = new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { login.setEnabled(false);
		 * user1.setEnabled(false); password1.setEnabled(false); Thread stepper
		 * = new BarThread(aJProgressBar); stepper.start(); } };
		 */
		// JPanel panel1=new JPanel();
		// this.setOpaque(false);
		// this.add(aJProgressBar);

		// login.addActionListener(actionListener);
		// this.add(panel1);
		// aJProgressBar.setBounds(260,200,120,20);
		// ////////////////////////////////////////////////////////////////////////////////////////////
		// user.setFont(new Font("",1,30));//�����С

		// this.add(user1);
		// user1.setLocation(350,250);
		// container.add(aField);
		// this.add(password);

	}

	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon(imgpath);
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, width, hight, this);
	}
}