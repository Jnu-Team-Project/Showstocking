package com.jnu.stock;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;

import com.jnu.stock.util.MainwindowUtil;
import com.jnu.stock.util.PanelUtil;
import com.jnu.stock.util.TableUtil;
import com.jnu.stock.util.UsernameLabelUtil;

public class ShowStock extends JApplet {
	static Logger logger = Logger.getLogger(ShowStock.class.getName());

	static String username;
	static String jframename;
	static int news;
	
	/**
	 * ShowStock���캯��
	 */
	public ShowStock() {
		PropertyConfigurator.configure("src//log4j.properties");
	}

	/**
	 * ������main
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		PropertyConfigurator.configure("src//log4j.properties"); 
		
		//��������
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			SubstanceLookAndFeel
					.setCurrentTheme(new SubstanceTerracottaTheme());
			logger.info("���������ɹ�");
		} catch (Exception e) {
			logger.error("Info ..."); 
			System.err.println("Something went wrong!");
		}
		
		final JFrame jf = new JFrame("��ӭ");
		jf.setSize(1200, 750);  
        jf.setLocation(100, 80);  
        jf.setLayout(null); 
        jf.setVisible(true);
		final JDesktopPane desktop = new JDesktopPane();
		desktop.setLayout(null);  
        desktop.setBounds(0, 0, 1200, 700);
         
		//ʵ����һ��JFrame����ʼ��
        jf.add(desktop);
        //
        
		final JInternalFrame frame = new JInternalFrame("Ǭ������",false,true,false,true);
		//frame.setDefaultCloseOperation(0);
		frame.setSize(700, 500);
		//frame.setLocation(null);
		frame.setVisible(true);//===============================6.7
		//frame.setResizable(false);
		/*frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int selected = JOptionPane.showConfirmDialog(frame, "�Ƿ��˳�", "",
						JOptionPane.YES_NO_OPTION);
				if (JOptionPane.OK_OPTION == selected) {
					System.exit(0);
				}
			}
		});*/
		desktop.add(frame);
		//ʵ����һ��ShowStock����
		final ShowStock applet = new ShowStock();
		//applet.add(desktop);
		final JPanel jp = new JPanel();
		final CardLayout cardLayout = new CardLayout();
		jp.setLayout(cardLayout);

		frame.add(jp);

		final RegisterPanel registerPanel = new RegisterPanel(750, 500);
		final LoginPanel loginPanel = new LoginPanel(750, 500, "background1.jpg");

		//ע���û�����¼����ҳ��
		final DataImportPanel dataImportPanel = new DataImportPanel(750, 500);
		jp.add(loginPanel, "1");
		jp.add(registerPanel, "2");
		jp.add(dataImportPanel, "3");
		
		cardLayout.show(jp, "1");
		
		//Ϊ��¼��ť�����Ӧ
		loginPanel.login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String loginPassword = String.valueOf(loginPanel.password1.getPassword());
				String loginUserName = loginPanel.user1.getText();
				String txtUserName = new String();
				String txtPassword = new String();
				if (loginPassword.equals("") || loginUserName.equals("")) {
					loginPanel.nullcaution.setVisible(true);
				}
				else {
					BufferedReader input = null;
					BufferedReader input1 = null;
					try {
						input = new BufferedReader(new FileReader("user.txt")); // ��ȡ��
						input1 = new BufferedReader(new FileReader("password.txt")); // ��ȡ��
						
						while ((txtUserName = input.readLine()) != null && (txtPassword = input1.readLine()) != null) { // �ж��Ƿ���������һ��
							if (txtUserName.equals(loginUserName) && txtPassword.equals(loginPassword)) {
								
								//UsernameLabelUtil.replaceUsername(applet, loginUserName);
								//applet.username = loginUserName;
								//applet.init();
								//applet.Createtable();
								//PanelUtil.initTheSecondePanel(applet, loginUserName, news);
								jp.setVisible(false);
								new TableUtil(applet,loginUserName).createTable(applet, loginUserName, news,frame);
								frame.add(applet);
								//applet.show();
								frame.setTitle(loginUserName);
								jf.setTitle(loginUserName);
								
								MainwindowUtil.CreateToolbar(jf, loginUserName,desktop);//========================
								jframename = loginUserName;
								
								frame.setVisible(false);
								
								break;
							}
						}
						loginPanel.usercaution.setVisible(true);

					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(input != null){
							try {
								input.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(input1 != null){
							try {
								input1.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}

			}
		});
		
		registerPanel.confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String password = String.valueOf(registerPanel.password1.getPassword());
				String user = registerPanel.user1.getText();
				
				//�������������ַ��������ļ���д��ע���û�����Ϣ
				BufferedWriter userWriter =null;
				BufferedWriter passwordWriter = null;
				try {
					userWriter = new BufferedWriter(new FileWriter(new File("user.txt"), true));
					passwordWriter = new BufferedWriter(new FileWriter(new File("password.txt"), true));
					userWriter.newLine();
					passwordWriter.newLine();
					userWriter.write(user);
					passwordWriter.write(password);
					
					//applet.username = user;
					//UsernameLabelUtil.replaceUsername(applet, user);
					//applet.replaceUsername(user);
					
					jp.setVisible(false);
					//new TableUtil(applet,loginUserName).createTable(applet, loginUserName, news,frame);
					//frame.add(applet);
					//applet.show();
					frame.setTitle(user);
					jf.setTitle(user);
					
					MainwindowUtil.CreateToolbar(jf, user,desktop);//========================
					jframename = user;
					
					frame.setVisible(false);
					
					applet.news = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(userWriter != null){
						try {
							userWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(passwordWriter != null){
						try {
							passwordWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				cardLayout.show(jp, "3");
			}
		});
		
		dataImportPanel.next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				jp.setVisible(false);
				//applet.init();
//				applet.Createtable();
				//PanelUtil.initTheSecondePanel(applet, username, news);
				new TableUtil(applet,username).createTable(applet, username, news,frame);
								
				//TableUtil.createTable(applet, loginUserName, news);
				frame.add(applet);
				frame.setTitle(username);
				applet.show();
			}
		});
		
		dataImportPanel.daoru.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				// ��ʼ���ļ�ѡ���
				JFileChooser fDialog = new JFileChooser();
				// �����ļ�ѡ���ı���
				fDialog.setDialogTitle("��ѡ�����ļ�");
				// ����ѡ���
				int returnVal = fDialog.showOpenDialog(null);
				// �����ѡ�����ļ�
				if (JFileChooser.APPROVE_OPTION == returnVal) {

					JOptionPane.showMessageDialog(null, "����ɹ�", "",
							JOptionPane.INFORMATION_MESSAGE);

					// ��ӡ���ļ���·����������޸�λ ��·��ֵ д�� textField��
					// System.out.println(fDialog.getSelectedFile());
					String name = fDialog.getSelectedFile().toString();
					Copy copy = new Copy();
					copy.copyFile(name, username + ".xls");
					applet.news = 0;
					//applet.Createtable();
					new TableUtil(applet,username).createTable(applet, username, news,frame);
					jp.setVisible(false);

					frame.add(applet);
					//applet.show();
				}
			}

		});
		
		//Ϊ�������û�����ǩ��ӵ���¼�
		loginPanel.register1.addMouseListener(new MouseAdp(cardLayout, jp));
	}
}
