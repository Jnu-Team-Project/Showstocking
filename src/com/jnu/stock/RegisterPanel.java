package com.jnu.stock;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class RegisterPanel extends JPanel //ע�����
{
	static Logger logger = Logger.getLogger(RegisterPanel.class.getName());  //================================
	 private static final long serialVersionUID = 1L;
	 private JLabel user,password,passwordnd,register1,caution,caution1,usercaution,passwordcaution,passwordcaution1,passwordndcaution;
	 JTextField user1;
	 JPasswordField password1,passwordnd1;
	 private int userstate = 0;
	 int passwordstate = 0;
	 int passwordndstate = 0;
	 
	 JButton confirm;
	 int width = 0, hight = 0;
	 public RegisterPanel(int width, int hight)
	 {
		 PropertyConfigurator.configure ("src//log4j.properties");//================================
		 logger.info("���ע�ṹ��ɹ�"); //===================
		 
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  user=new JLabel("�û���");
		  caution=new JLabel("���û����Ѵ��� ������������");
		  caution1=new JLabel("�û�����ʽ������Ҫ��");
		  
		  usercaution=new JLabel("���û��������֡���ĸ���»�����ɣ�");
		  passwordcaution=new JLabel("��������6~16λ��ɣ�");
		  passwordcaution1=new JLabel("�����ʽ������Ҫ��");
		  
		  passwordndcaution=new JLabel("ǰ�����벻һ��");
		  user.setFont(new Font("", Font.PLAIN, 30));
		  password=new JLabel("����");
		  passwordnd=new JLabel("ȷ������");
		  password.setFont(new Font("", Font.PLAIN, 30));
		  confirm = new JButton("��һ��");
		  passwordnd.setFont(new Font("", Font.PLAIN, 30));
		  user1=new JTextField(40);
		  user1.setFont(new Font("", Font.PLAIN, 20));
		  //zhuce.setFont(new Font("����",Font.BOLD,20));
		  password1=new JPasswordField(40);
		  password1.setFont(new Font("", Font.PLAIN, 20));
		  passwordnd1=new JPasswordField(40);
		  passwordnd1.setFont(new Font("", Font.PLAIN, 20));
		  this.add(user);
		  this.add(user1);
		  this.add(caution);
		  this.add(caution1);
		  this.add(password);
		  this.add(password1);
		  this.add(passwordnd);
		  this.add(passwordnd1);
		  this.add(passwordcaution);
		  this.add(passwordcaution1);
		  this.add(usercaution);
		  this.add(passwordndcaution);
		  
		  //this.add(login);
		  this.add(confirm);	
		  confirm.setEnabled(false);
		  //user.setFont(new Font("",1,30));//�����С
		  user.setForeground(Color.DARK_GRAY); 
		  
		  caution.setForeground(Color.red);
		  caution1.setForeground(Color.red);
		  password.setForeground(Color.DARK_GRAY); 
		  passwordnd.setForeground(Color.DARK_GRAY); 
		  user.setBounds(180,160,100,30);
		  usercaution.setBounds(180,200,210,20);
		  usercaution.setForeground(Color.DARK_GRAY);
		  passwordcaution1.setForeground(Color.red);
		  
		  caution.setVisible(false);
		  caution1.setVisible(false);
		  passwordndcaution.setVisible(false);
		  passwordcaution1.setVisible(false);
		  
		  user1.setBounds(320,160,200,35);
		  
		  caution.setBounds(525,165,190,20);
		  caution1.setBounds(525,165,190,20);
		  
		  password.setBounds(180,240,100,30);
		  passwordcaution.setBounds(180,280,200,20);
		  passwordcaution.setForeground(Color.DARK_GRAY);
		  
		  password1.setBounds(320,240,200,30);
		  passwordcaution1.setBounds(525,245,190,20);
		  
		  passwordnd.setBounds(180,320,130,30);
		  passwordnd1.setBounds(320,320,200,30);
		  passwordndcaution.setBounds(525,325,190,20);
		  passwordndcaution.setForeground(Color.red);
		  
		  
		  confirm.setBounds(280,400,100,25);
		  user1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					//public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		//int in;	
				  		String na = user1.getText();
				  		String s = new String();
				  		
				  		if(!na.matches("[A-Za-z0-9_]+"))
				  		{
				  			caution1.setVisible(true);
				  			userstate=0;
				  		}
				  		else
				  		{
		  					caution1.setVisible(false);
		  					try { 
					  			BufferedReader input = new BufferedReader(new FileReader("user.txt")); //��ȡ��
					  			BufferedReader input1 = new BufferedReader(new FileReader("user.txt")); //��ȡ��
					  			if(input1.readLine()==null)
					  			{
					  				userstate = 1;
					  			}
					  			else
					  			{
						  			while((s = input.readLine())!=null)
						  			{ //�ж��Ƿ���������һ��
						  				//System.out.println(s);
						  				if(s.equals(na))
								  		{
								  			caution.setVisible(true);
								  			userstate=0;
								  			break;
								  		}
						  				else
						  				{
						  					caution.setVisible(false);
						  					userstate = 1;
						  				}
						  			} 
					  			}
					  			input.close(); 
					  			input1.close();

				  			}
				  			catch (Exception et) { 
				  				logger.error(et); //===================
				  				
				  			} 
				  		}
				  		if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		
				  		String na = user1.getText();
				  		String s = new String();
				  		
				  		if(!na.matches("[A-Za-z0-9_]+"))
				  		{
				  			caution1.setVisible(true);
				  			userstate=0;
				  		}
				  		else
				  		{
		  					caution1.setVisible(false);
		  					try { 
					  			BufferedReader input = new BufferedReader(new FileReader("user.txt")); //��ȡ��
					  			BufferedReader input1 = new BufferedReader(new FileReader("user.txt")); //��ȡ��
					  			if(input1.readLine()==null)
					  			{
					  				userstate = 1;
					  			}
					  			else
					  			{
						  			while((s = input.readLine())!=null)
						  			{ //�ж��Ƿ���������һ��
						  				//System.out.println(s);
						  				if(s.equals(na))
								  		{
								  			caution.setVisible(true);
								  			userstate=0;
								  			break;
								  		}
						  				else
						  				{
						  					caution.setVisible(false);
						  					userstate = 1;
						  				}
						  			} 
					  			}
					  			input.close(); 
					  			input1.close();

				  			}
				  			catch (Exception et) { 
				  			} 
				  		}
				  		if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = user1.getText();
				  		String s = new String();
				  		
				  		if(!na.matches("[A-Za-z0-9_]+"))
				  		{
				  			caution1.setVisible(true);
				  			userstate=0;
				  		}
				  		else
				  		{
		  					caution1.setVisible(false);
		  					try { 
					  			BufferedReader input = new BufferedReader(new FileReader("user.txt")); //��ȡ��
					  			BufferedReader input1 = new BufferedReader(new FileReader("user.txt")); //��ȡ��
					  			if(input1.readLine()==null)
					  			{
					  				userstate = 1;
					  			}
					  			else
					  			{
						  			while((s = input.readLine())!=null)
						  			{ //�ж��Ƿ���������һ��
						  				//System.out.println(s);
						  				if(s.equals(na))
								  		{
								  			caution.setVisible(true);
								  			userstate=0;
								  			break;
								  		}
						  				else
						  				{
						  					caution.setVisible(false);
						  					userstate = 1;
						  				}
						  			} 
					  			}
					  			input.close(); 
					  			input1.close();

				  			}
				  			catch (Exception et) { 
				  			} 
				  		}
				  		if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	}
				  	
				  } 
		  ); 
		  
		  password1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					//public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
						{
							passwordcaution1.setVisible(true);
							passwordstate = 0;
						}
						else
						{
							passwordcaution1.setVisible(false);
							passwordstate = 1;
						}
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!nu.equals(""))
						{
							if(!(na.equals(nu)))
							{
								passwordndcaution.setVisible(true);
								passwordndstate = 0;
								confirm.setEnabled(false);
							}
							else
							{
								passwordndcaution.setVisible(false);
								passwordndstate = 1;
								if(userstate==1&&passwordstate==1&&passwordndstate==1)
								{
									confirm.setEnabled(true);
								}
								else
								{
									confirm.setEnabled(false);
								}
							}
						}
						if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
						 			
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
						{
							passwordcaution1.setVisible(true);
							passwordstate = 0;
						}
						else
						{
							passwordcaution1.setVisible(false);
							passwordstate = 1;
						}
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!nu.equals(""))
						{
							if(!(na.equals(nu)))
							{
								passwordndcaution.setVisible(true);
								passwordndstate = 0;
								confirm.setEnabled(false);
							}
							else
							{
								passwordndcaution.setVisible(false);
								passwordndstate = 1;
								if(userstate==1&&passwordstate==1&&passwordndstate==1)
								{
									confirm.setEnabled(true);
								}
								else
								{
									confirm.setEnabled(false);
								}
							}
						}
						if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
						{
							passwordcaution1.setVisible(true);
							passwordstate = 0;
						}
						else
						{
							passwordcaution1.setVisible(false);
							passwordstate = 1;
						}
						
						//String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!nu.equals(""))
						{
							if(!(na.equals(nu)))
							{
								passwordndcaution.setVisible(true);
								passwordndstate = 0;
								confirm.setEnabled(false);
							}
							else
							{
								passwordndcaution.setVisible(false);
								passwordndstate = 1;
								if(userstate==1&&passwordstate==1&&passwordndstate==1)
								{
									confirm.setEnabled(true);
								}
								else
								{
									confirm.setEnabled(false);
								}
							}
						}
						if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	}
				  } 
		  ); 

		  passwordnd1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					//public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!(na.equals(nu)&&!nu.equals("")))
						{
							passwordndcaution.setVisible(true);
							passwordndstate = 0;
							confirm.setEnabled(false);
						}
						else
						{
							passwordndcaution.setVisible(false);
							passwordndstate = 1;
							if(userstate==1&&passwordstate==1&&passwordndstate==1)
							{
								confirm.setEnabled(true);
							}
							else
							{
								confirm.setEnabled(false);
							}
						}
				  	}
				  	public void insertUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!(na.equals(nu)&&!nu.equals("")))
						{
							passwordndcaution.setVisible(true);
							passwordndstate = 0;
							confirm.setEnabled(false);
						}
						else
						{
							passwordndcaution.setVisible(false);
							passwordndstate = 1;
							if(userstate==1&&passwordstate==1&&passwordndstate==1)
							{
								confirm.setEnabled(true);
							}
							else
							{
								confirm.setEnabled(false);
							}
						}
				  	}
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!(na.equals(nu)&&!nu.equals("")))
						{
							passwordndcaution.setVisible(true);
							passwordndstate = 0;
							confirm.setEnabled(false);
						}
						else
						{
							passwordndcaution.setVisible(false);
							passwordndstate = 1;
							if(userstate==1&&passwordstate==1&&passwordndstate==1)
							{
								confirm.setEnabled(true);
							}
							else
							{
								confirm.setEnabled(false);
							}
						}
				  	}
				  } 
		  ); 
		  
	 /*confirm.addActionListener(new ActionListener()	                    
	 {
			public void actionPerformed(ActionEvent event)
			{
				//int in;
				String na = String.valueOf(password1.getPassword());
				//String nu = String.valueOf(passwordnd1.getPassword());
				String u = user1.getText();
				if(userstate==1&&passwordstate==1&&passwordndstate==1)
				{	
					//File file = new File("J:\\user.txt");
					try{
					     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("J:\\user.txt"),true));
					     writer.newLine();
					     writer.write(u);
					     writer.newLine();
					     writer.write(na);
					     writer.close();
					    // System.out.print(na);

					}catch(Exception e){

					     }
				}
			}
	}
	);*/
	 /*protected void paintComponent(Graphics g) 
	 {
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, this);
	 }*/
}
}