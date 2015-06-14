package com.jnu.stock.util;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jnu.stock.Catchgra;
import com.jnu.stock.Chigugouchen;
import com.jnu.stock.Copy;
import com.jnu.stock.Dealdialog;
import com.jnu.stock.ModifyTable;
import com.jnu.stock.Oldestdate;
import com.jnu.stock.Shouyilv;
import com.jnu.stock.ShowStock;
import com.jnu.stock.StockInfoLayout;

/**
 * createTable和updateTable
 * 
 * @author Administrator
 *
 */
public class TableUtil 
{	
	static Logger logger = Logger.getLogger(TableUtil.class.getName());
	
	JTabbedPane jtpFigures = new JTabbedPane();
	StockInfoLayout stockInfoLayout = new StockInfoLayout();
	
	public TableUtil(final JApplet showStcok,final String username)
	{
		JMenuBar jmb = new JMenuBar();
		JMenu toolMenu = new JMenu("工具");
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem importDataItem, exportDateItem, contactItem, aboutItem, refreshItem;
		jmb.add(toolMenu);
		jmb.add(helpMenu);
		importDataItem = new JMenuItem("导入数据");
		exportDateItem = new JMenuItem("导出数据");
		refreshItem = new JMenuItem("刷新");
		contactItem = new JMenuItem("联系我们");
		aboutItem = new JMenuItem("关于");

		toolMenu.add(importDataItem);
		toolMenu.add(exportDateItem);
		toolMenu.add(refreshItem);
		
		helpMenu.add(contactItem);
		helpMenu.add(aboutItem);
		showStcok.setJMenuBar(jmb);
		showStcok.setLayout(null);

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"版本 1.0\n版权所有 团队项目1组保留所有权利", "关于“乾道量行”",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//添加事件
		refreshItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//showStock.Updatetable();
				System.out.print(username+"####+\n");
				updatetable(showStcok, username, 0);
			}
		});

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// 初始化文件选择框
				JFileChooser fDialog = new JFileChooser();
				// 设置文件选择框的标题
				fDialog.setDialogTitle("请选择导入文件");
				// 弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if (JFileChooser.APPROVE_OPTION == returnVal) {
					JOptionPane.showMessageDialog(null, "导入成功", "",
							JOptionPane.INFORMATION_MESSAGE);

					String name = fDialog.getSelectedFile().toString();
					Copy copy = new Copy();
					copy.copyFile(name, username + ".xls");
					//showStock.Createtable();
					updatetable(showStcok, username, 0);
				}
			}
		});

		exportDateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// 初始化文件选择框
				JFileChooser fDialog = new JFileChooser();
				// 设置文件选择框的标题
				fDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fDialog.setDialogTitle("请选择导出位置");
				// 弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if (JFileChooser.APPROVE_OPTION == returnVal) {

					JOptionPane.showMessageDialog(null, "导出成功", "",
							JOptionPane.INFORMATION_MESSAGE);
					// 打印出文件的路径，你可以修改位 把路径值 写到 textField中
					Copy copy = new Copy();
					copy.copyFile(username + ".xls", fDialog.getSelectedFile().toString() + "//" + username + ".xls");
					System.out.println(username);
					System.out.println(fDialog.getSelectedFile());
				}
			}
		});
	}
	public void updatetable(final JApplet showStcok, final String username,final int news){
		//news = 1;

		
		//JTabbedPane jtpFigures = new JTabbedPane();
		//StockInfoLayout stockInfoLayout = new StockInfoLayout();
		
		Shouyilv j3 = new Shouyilv(690, 360, username);
		Chigugouchen j4 = new Chigugouchen(690, 360, username);
		jtpFigures.removeAll();
		stockInfoLayout.removeAll();
		
		//showStcok.remove(stockInfoLayout);
		jtpFigures.setBounds(0, 45, 690, 400);
		stockInfoLayout.setBounds(190, 6, 510, 42);
	
		final ModifyTable applet2 = new ModifyTable(username, 0);
		
		showStcok.add(stockInfoLayout);
		showStcok.add(jtpFigures);
		//new TableUtil().createTable(applet1, subaccountname, 0,subinternalframe);
		
		DecimalFormat df=new DecimalFormat(".##");
		//String st=df.format(d);
		//System.out.println(st);
		
		/*stockInfoLayout.setRyke1(new JLabel(String.valueOf(applet2.getDinglanriyingkui())));
		stockInfoLayout.setYk1(new JLabel(String.valueOf(applet2.getDinglanyingkui())));
		stockInfoLayout.setSz1(new JLabel(String.valueOf(applet2.getDinglanshizhi())));
		stockInfoLayout.setXj1(new JLabel(String.valueOf(applet2.getDinglanxianjin())));*/
		
		stockInfoLayout.setRyke1(new JLabel(df.format(applet2.getDinglanriyingkui())));
		stockInfoLayout.setYk1(new JLabel(df.format(applet2.getDinglanyingkui())));
		stockInfoLayout.setSz1(new JLabel(df.format(applet2.getDinglanshizhi())));
		stockInfoLayout.setXj1(new JLabel(df.format(applet2.getDinglanxianjin())));
		
		stockInfoLayout.createStockInfoLayout();
		
		System.out.print("进入到更新哈哈哈");
		showStcok.add(stockInfoLayout);
		jtpFigures.add(applet2, "持仓盈亏");
		jtpFigures.add(j3, "收益率");
		jtpFigures.add(j4, "持股构成");
		PropertyConfigurator.configure("src//log4j.properties");
		applet2.getjTable1().addMouseListener(new MouseAdapter() 
				{
					public void mouseClicked(MouseEvent e) {
						final int r = applet2.getjTable1().getSelectedRow();
						int c = applet2.getjTable1().getSelectedColumn();

						final JFrame framejioayi = new JFrame("增加交易");
						
						if (c == 6) {
							final Dealdialog dlog = new Dealdialog(applet2,r,null);
							framejioayi.add(dlog);
							dlog.setDealdialogname(String.valueOf(applet2.getjTable1().getValueAt(r, 0)));
							framejioayi.setSize(680, 110);
							framejioayi.setLocationRelativeTo(null);
							framejioayi.setVisible(true);
							framejioayi.setResizable(false);
							dlog.setDealdialogusername(applet2.getUsername());
							//final double chiyouliang  = Double.parseDouble((String)applet2.getjTable1().getValueAt(3, 0));
							dlog.getOkButton().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									String datestr = dlog.getDateTextField().getText();
									String styletr = dlog.getStyleTextField().getText();
									String pricestr = dlog.getPriceTextField().getText();
									String amountstr = dlog.getAmountTextField().getText();
									String ratestr = dlog.getRateTextField().getText();
									String yongjinstr = dlog.getYongjinTextField().getText();
									double pricestr1 = Double.parseDouble(pricestr);
									double amountstr1 = Double.parseDouble(amountstr);
									double cut=0.0;
									//double cut = pricestr1*amountstr1;
									double add = pricestr1*amountstr1;
									double nowxianjin=0.0;
									String[] datearray1 = datestr.split("-");
									if(styletr.equals("买入")||styletr.equals("补仓"))
									{
										cut = pricestr1*amountstr1;
										nowxianjin  = applet2.getDinglanxianjin() - cut;
										
									}
									else if(styletr.equals("卖出")||styletr.equals("卖空"))
										nowxianjin  = applet2.getDinglanxianjin() + add;
									
									//boolean full = (!datestr.equals(""))&&(!pricestr.equals(""))&&(!amountstr.equals(""));
									if(cut < applet2.getDinglanxianjin())
									{
										applet2.setDinglanxianjin(nowxianjin);
										Workbook wb;
										try {
											wb = Workbook.getWorkbook(new File(
													dlog.getDealdialogusername() + ".xls"));
											WritableWorkbook book;
											try {
												book = Workbook
														.createWorkbook(
																new File(
																		dlog.getDealdialogusername() + ".xls"),
																wb);
												WritableSheet sheet2 = book
														.getSheet(dlog.getDealdialogname());
												WritableSheet sheet3 = book
														.getSheet(dlog.getDealdialogname());
	
												int m = Integer.parseInt(sheet2
														.getCell(11, 0)
														.getContents()) + 1;
												
												String mindate = sheet2.getCell(13, 0).getContents();
												System.out.print(mindate+"\n");
												//String[] datearray2 = mindate.split("-");
												/*boolean ismindate = Integer.parseInt(datearray1[0])>Integer.parseInt(datearray2[0])||
														(Integer.parseInt(datearray1[0])==Integer.parseInt(datearray2[0])&&Integer.parseInt(datearray1[1])>Integer.parseInt(datearray2[1]))
												||(Integer.parseInt(datearray1[0])==Integer.parseInt(datearray2[0])&&Integer.parseInt(datearray1[1])==Integer.parseInt(datearray2[1])&&
														Integer.parseInt(datearray1[2])>Integer.parseInt(datearray2[2]));*/
												Oldestdate cp = new Oldestdate();
												//if((cp.isOldestdate(datestr, mindate)&&styletr.equals("卖出")))
												if(HoldingAmount.holdingAmountCalculation(datestr,dlog.getDealdialogname(),wb)<amountstr1&&styletr.equals("卖出"))
												{
													int selected = JOptionPane.showConfirmDialog(framejioayi, "持有股数不足,是否确认添加此交易(这将导致您的持有股数为负)", "",
															JOptionPane.YES_NO_OPTION);
													if (JOptionPane.NO_OPTION == selected) 
													{
														//System.exit(0);
														book.write();
														book.close();
													}
													
													/*JOptionPane.showMessageDialog(null,
															"持有股数不足", "停止操作",
															JOptionPane.INFORMATION_MESSAGE);
													book.write();
													book.close();*/
													else
													{

														Label l;
														l = new Label(2, m, datestr);
														sheet2.addCell(l);
			
														l = new Label(3, m, styletr);
														sheet2.addCell(l);
			
														l = new Label(4, m, pricestr);
														sheet2.addCell(l);
			
														l = new Label(5, m, amountstr);
														sheet2.addCell(l);
			
														l = new Label(6, m, ratestr);
														sheet2.addCell(l);
			
														l = new Label(7, m, yongjinstr);
														sheet2.addCell(l);
			
														l = new Label(11, 0, String
																.valueOf(m));
														sheet2.addCell(l);
														
														l = new Label(12, 0, String
																.valueOf(nowxianjin));
														sheet3.addCell(l);
			
														book.write();
														book.close();
														framejioayi.dispose();
														updatetable(showStcok, username, 0);
													}
												}
												//if(ismindate)
													//System.out.print("?????????????????????????????");
												else
												{
													
													/*if(cp.isOldestdate(datestr, mindate)&&styletr.equals("买入"))
													{
														Label lb;
														lb = new Label(13, 0, datestr);
														sheet2.addCell(lb);
													}*/
												
												Label l;
												l = new Label(2, m, datestr);
												sheet2.addCell(l);
	
												l = new Label(3, m, styletr);
												sheet2.addCell(l);
	
												l = new Label(4, m, pricestr);
												sheet2.addCell(l);
	
												l = new Label(5, m, amountstr);
												sheet2.addCell(l);
	
												l = new Label(6, m, ratestr);
												sheet2.addCell(l);
	
												l = new Label(7, m, yongjinstr);
												sheet2.addCell(l);
	
												l = new Label(11, 0, String
														.valueOf(m));
												sheet2.addCell(l);
												
												l = new Label(12, 0, String
														.valueOf(nowxianjin));
												sheet3.addCell(l);
	
												book.write();
												book.close();
												framejioayi.dispose();
												updatetable(showStcok, username, 0);
												}
											} catch (IOException e1) {
												e1.printStackTrace();
											} catch (RowsExceededException e1) {
												e1.printStackTrace();
											} catch (WriteException e1) {
												e1.printStackTrace();
											}
											
											wb.close();
											
										}
										catch (BiffException e1) {
											e1.printStackTrace();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
											
									}
									else
									{
										JOptionPane.showMessageDialog(null,
												"现金不足", "停止操作",
												JOptionPane.INFORMATION_MESSAGE);
										dlog.setAmountTextFieldstr("");
										
									}
								}
							});							
						}
						String number = "";
						if (e.getClickCount() == 2 && c != 6) {
							File file = new File(applet2.getUsername() + ".xls");
							String[] columnNames1 = { "日期", "类型", "价格", "数量",
									"税率", "佣金","操作" };
							Object[][] rowData1 = {};
							final DefaultTableModel tableModel1 = new DefaultTableModel(rowData1, columnNames1) {
							
								@Override
								public boolean isCellEditable(int row,
										int column) {
									return true;
								}
								/* public Class getColumnClass(int column) {  
								        Class returnValue;  
								        if ((column >= 0) && (column < getColumnCount())) {  
								            returnValue = getValueAt(0, column).getClass();  
								        } else {  
								            returnValue = Object.class;  
								        }  
								        return returnValue;  
								    }  */
							};
							RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel1);
							//tableModel1.setRowSorter(sorter); 
							final String search = String.valueOf(applet2.getjTable1().getValueAt(r, 0));
							StringBuffer sb = new StringBuffer();
							//DecimalFormat df=new DecimalFormat(".##");
							try {
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);

								number = sheet.getCell(1, 1).getContents();

								try {
									int m = Integer.parseInt(sheet.getCell(11,
											0).getContents());
									for (int i = 0; i < m; i++) {
										String ctime="";
										try {
								            SimpleDateFormat sdf = new SimpleDateFormat("yy-M-d");
								            //sheet.getCell(2, i + 1).getContents()
								            Date date = sdf.parse(sheet.getCell(2, i + 1).getContents());
								            SimpleDateFormat formatter; 
								            formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
								            ctime = formatter.format(date); 
								        } catch (Exception ex) {
								            ex.printStackTrace();
								        }
										String[] row = {ctime
												,
												sheet.getCell(3, i + 1)
														.getContents(),
												sheet.getCell(4, i + 1)
														.getContents(),
												sheet.getCell(5, i + 1)
														.getContents(),
												sheet.getCell(6, i + 1)
														.getContents(),
												sheet.getCell(7, i + 1)
														.getContents(), 
														"保存"};
										tableModel1.addRow(row);
										System.out.print("进入到更新。。。\n");
									}
								} finally {
									if (book != null) {
										book.close();
									}
								}
							} catch (BiffException er) {
								logger.error(er);
								System.err.println("");
							} catch (IOException er) {
								logger.error(er); 
								System.err.println("文件读取错误");
							}
							final JTable jTable1 = new JTable(tableModel1);
							jTable1.addMouseListener(new MouseAdapter() 
							{
								public void mouseClicked(MouseEvent e) {
									final int r = jTable1.getSelectedRow();
									int c = jTable1.getSelectedColumn();
									if(c==6)
									{
										
								
											Workbook wb;
											try {
												wb = Workbook.getWorkbook(new File(
														applet2.getUsername() + ".xls"));
												WritableWorkbook book;
												try {
													book = Workbook
															.createWorkbook(
																	new File(
																			applet2.getUsername() + ".xls"),
																	wb);
													WritableSheet sheet2 = book
															.getSheet(search);
													WritableSheet sheet3 = book
															.getSheet(search);
													
													String old1 = sheet2.getCell(2, r + 1).getContents();
													String old2 = sheet2.getCell(2, r + 1).getContents();
													String old3 = sheet2.getCell(2, r + 1).getContents();
													String old4 = sheet2.getCell(2, r + 1).getContents();
													String old5 = sheet2.getCell(2, r + 1).getContents();
													String old6 = sheet2.getCell(2, r + 1).getContents();
													
													Label l;
													l = new Label(2, r+1, (String)jTable1.getValueAt(r, 0));
													sheet2.addCell(l);
		
													l = new Label(3, r+1, (String)jTable1.getValueAt(r, 1));
													sheet2.addCell(l);
		
													l = new Label(4, r+1, (String)jTable1.getValueAt(r, 2));
													sheet2.addCell(l);
		
													l = new Label(5, r+1, (String)jTable1.getValueAt(r, 3));
													sheet2.addCell(l);
		
													l = new Label(6, r+1, (String)jTable1.getValueAt(r, 4));
													sheet2.addCell(l);
		
													l = new Label(7, r+1, (String)jTable1.getValueAt(r, 5));
													sheet2.addCell(l);
											
													book.write();
													book.close();
													//framejioayi.dispose();
													//TableUtil.updatetable(showStcok, username, news);
													JOptionPane.showMessageDialog(null,
															"修改成功", "提示",
															JOptionPane.INFORMATION_MESSAGE);
													updatetable(showStcok, username, 0);
													
												} catch (IOException e1) {
													e1.printStackTrace();
												} catch (RowsExceededException e1) {
													e1.printStackTrace();
												} catch (WriteException e1) {
													e1.printStackTrace();
												}
												
												wb.close();
												
											}
											catch (BiffException e1) {
												e1.printStackTrace();
											} catch (IOException e1) {
												e1.printStackTrace();
											}									
									}									
								}
							});
							//jTable1.setRowSorter(sorter);
							final JFrame frame = new JFrame(String.valueOf(applet2.getjTable1().getValueAt(r, 0)));
							JLabel title = new JLabel();
							JPanel jpl = new JPanel();
							JPanel jpr = new JPanel();
							JPanel jps = new JPanel();
							frame.add(jpl);
							jpl.setBounds(5, 50, 170, 220);
							jpl.setLayout(new GridLayout(9, 2));

							try {
								System.out.print(number+"@@@@\n");
								URL gis = new URL("http://hq.sinajs.cn/list=" + number);// 你要报错的网页
								BufferedReader in = new BufferedReader(
										new InputStreamReader(gis.openStream()));
							
								String line = "";
								String[] data;
								
								while ((line = in.readLine()) != null) {
									System.out.println(line);
									data = line.split(",");
									JLabel jl1 = new JLabel("今日开盘价 ");
									JLabel jl11 = new JLabel(data[1]);

									JLabel jl2 = new JLabel("昨日收盘价 ");
									JLabel jl3 = new JLabel("当前价格 ");
									JLabel jl4 = new JLabel("今日最高价 ");
									JLabel jl5 = new JLabel("今日最低价 ");
									JLabel jl6 = new JLabel("竞买价 ");
									JLabel jl7 = new JLabel("竞卖价 ");
									JLabel jl8 = new JLabel("成交的股票数 ");
									JLabel jl9 = new JLabel("成交金额 ");

									JLabel jl22 = new JLabel(data[2]);
									JLabel jl33 = new JLabel(data[3]);
									JLabel jl44 = new JLabel(data[4]);
									JLabel jl55 = new JLabel(data[5]);
									JLabel jl66 = new JLabel(data[6]);
									JLabel jl77 = new JLabel(data[7]);
									JLabel jl88 = new JLabel(data[8]);
									JLabel jl99 = new JLabel(data[9]);
									jpl.add(jl1);
									jpl.add(jl11);
									jpl.add(jl2);
									jpl.add(jl22);
									jpl.add(jl3);
									jpl.add(jl33);
									jpl.add(jl4);
									jpl.add(jl44);
									jpl.add(jl5);
									jpl.add(jl55);
									jpl.add(jl6);
									jpl.add(jl66);
									jpl.add(jl7);
									jpl.add(jl77);
									jpl.add(jl8);
									jpl.add(jl88);
									jpl.add(jl9);
									jpl.add(jl99);
								}
								in.close();
							} catch (Exception er) {
								System.out.println(er);
							}
							final JTabbedPane jtpFigures = new JTabbedPane();
							Catchgra fenshi = new Catchgra();
							final Catchgra rik = new Catchgra();
							Catchgra zhouk = new Catchgra();
							Catchgra yuekp = new Catchgra();
							System.out.print(number+"@@@@\n");
							try {
								fenshi.Setimg("http://image.sinajs.cn/newchart/min/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								rik.Setimg("http://image.sinajs.cn/newchart/daily/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}

							try {
								zhouk.Setimg("http://image.sinajs.cn/newchart/weekly/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								yuekp.Setimg("http://image.sinajs.cn/newchart/monthly/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							jtpFigures.add(fenshi, "分时");
							jtpFigures.add(rik, "日K");
							jtpFigures.add(zhouk, "周K");
							jtpFigures.add(yuekp, "月K");

							jtpFigures.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent e) {
									JTabbedPane tabbedPane = (JTabbedPane) e
											.getSource();
									int selectedIndex = tabbedPane
											.getSelectedIndex();
									switch (selectedIndex) {
									case 0:
										break;

									case 1:
										JFrame frame2 = new JFrame("");
										frame2.setVisible(true);
										frame2.setResizable(false);
										frame2.setSize(1, 1);
										frame2.dispose();
										frame2 = null;
										break;
									case 2:
										frame.setSize(700, 581);
										JFrame frame3 = new JFrame("");
										frame3.setVisible(true);
										frame3.setResizable(false);
										frame3.setSize(1, 1);
										frame3.dispose();
										frame3 = null;
										break;
									case 3:
										JFrame frame4 = new JFrame("");
										frame4.setVisible(true);
										frame4.setResizable(false);
										frame4.setSize(1, 1);
										frame4.dispose();
										frame4 = null;
										break;
									}
								}
							});
							frame.add(jtpFigures);
							jtpFigures.setBounds(190, 10, 500, 330);
							jps.setLayout(new BorderLayout());
							jps.add(new JScrollPane(jTable1));
							frame.add(jps);
							jps.setBounds(40, 350, 620, 140);
							frame.setLayout(null);
							frame.setSize(700, 560);
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							frame.setResizable(false);
						}
					}
				});
	}
	
	public void createTable(final JApplet showStcok, final String username,final int news,final JInternalFrame frame) {
		showStcok.add(jtpFigures);
		jtpFigures.setBounds(0, 45, 690, 400);
		stockInfoLayout.setBounds(190, 6, 510, 42);
		Shouyilv j3 = new Shouyilv(690, 360, username);
		Chigugouchen j4 = new Chigugouchen(690, 360, username);
		final ModifyTable applet2 = new ModifyTable(username, news);
		//stockInfoLayout.setRyke1(new JLabel(String.valueOf(applet2.getDinglanriyingkui())));
		//stockInfoLayout.setYk1(new JLabel(String.valueOf(applet2.getDinglanyingkui())));
		
		
		stockInfoLayout.setRyke1(new JLabel(String.valueOf(applet2.getDinglanriyingkui())));
		stockInfoLayout.setYk1(new JLabel(String.valueOf(applet2.getDinglanyingkui())));
		stockInfoLayout.setSz1(new JLabel(String.valueOf(applet2.getDinglanshizhi())));
		stockInfoLayout.setXj1(new JLabel(String.valueOf(applet2.getDinglanxianjin())));
		stockInfoLayout.createStockInfoLayout();
		
		
		showStcok.add(stockInfoLayout);
		jtpFigures.add(applet2, "持仓盈亏");
		jtpFigures.add(j3, "收益率");
		jtpFigures.add(j4, "持股构成");
		PropertyConfigurator.configure("src//log4j.properties");
		applet2.getjTable1().addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				final int r = applet2.getjTable1().getSelectedRow();
				int c = applet2.getjTable1().getSelectedColumn();

				//final JFrame framejioayi = new JFrame("增加交易");
				//final JDialog framejioayi = new JDialog(jf,"增加交易",true);
				final JFrame framejioayi = new JFrame("增加交易");
				if (c == 6) {
					final Dealdialog dlog = new Dealdialog(applet2,r,null);
					//final Dealdialog dlog = new Dealdialog(applet2,r,null);
					framejioayi.add(dlog);
					dlog.setDealdialogname(String.valueOf(applet2.getjTable1().getValueAt(r, 0)));
					
					framejioayi.setSize(680, 110);
					framejioayi.setLocationRelativeTo(null);
					framejioayi.setVisible(true);
					framejioayi.setResizable(false);
					
					dlog.setDealdialogusername(applet2.getUsername());
					
					dlog.getOkButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							String datestr = dlog.getDateTextField().getText();
							String styletr = dlog.getStyleTextField().getText();
							String pricestr = dlog.getPriceTextField().getText();
							String amountstr = dlog.getAmountTextField().getText();
							String ratestr = dlog.getRateTextField().getText();
							String yongjinstr = dlog.getYongjinTextField().getText();
							double pricestr1 = Double.parseDouble(pricestr);
							double amountstr1 = Double.parseDouble(amountstr);
							double cut=0.0;
							//double cut = pricestr1*amountstr1;
							double add = pricestr1*amountstr1;
							double nowxianjin=0.0;
							if(styletr.equals("买入")||styletr.equals("补仓"))
							{
								cut = pricestr1*amountstr1;
								nowxianjin  = applet2.getDinglanxianjin() - cut;
								
							}
							else if(styletr.equals("卖出")||styletr.equals("卖空"))
								nowxianjin  = applet2.getDinglanxianjin() + add;

							if(cut < applet2.getDinglanxianjin())
							{
								
								Workbook wb;
								try {
									wb = Workbook.getWorkbook(new File(
											dlog.getDealdialogusername() + ".xls"));
									WritableWorkbook book;
									try {
										book = Workbook
												.createWorkbook(
														new File(
																dlog.getDealdialogusername() + ".xls"),
														wb);
										WritableSheet sheet2 = book
												.getSheet(dlog.getDealdialogname());
										
										WritableSheet sheet3 = book
												.getSheet(0);
										int m = Integer.parseInt(sheet2
												.getCell(11, 0)
												.getContents()) + 1;

										Label l;
										l = new Label(2, m, datestr);
										sheet2.addCell(l);

										l = new Label(3, m, styletr);
										sheet2.addCell(l);

										l = new Label(4, m, pricestr);
										sheet2.addCell(l);

										l = new Label(5, m, amountstr);
										sheet2.addCell(l);

										l = new Label(6, m, ratestr);
										sheet2.addCell(l);

										l = new Label(7, m, ratestr);
										sheet2.addCell(l);

										l = new Label(11, 0, String
												.valueOf(m));
										sheet2.addCell(l);
										
										l = new Label(12, 0, String
												.valueOf(nowxianjin));
										sheet3.addCell(l);

										book.write();
										book.close();
										framejioayi.dispose();
										updatetable(showStcok, username, news);
									} catch (IOException e1) {
										e1.printStackTrace();
									} catch (RowsExceededException e1) {
										e1.printStackTrace();
									} catch (WriteException e1) {
										e1.printStackTrace();
									}
									wb.close();
								}
								catch (BiffException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null,
										"现金不足", "停止操作",
										JOptionPane.INFORMATION_MESSAGE);
								dlog.setAmountTextFieldstr("");
								//framejioayi.dispose();
								
							}
						}
					});
							
						}
						System.out.print(c);
						String number = "";
						if (e.getClickCount() == 2 && c != 6) {
							File file = new File(applet2.getUsername() + ".xls");
							String[] columnNames1 = { "日期", "类型", "价格", "数量",
									"税率", "佣金" };
							Object[][] rowData1 = {};
							final DefaultTableModel tableModel1 = new DefaultTableModel(rowData1, columnNames1) {
								@Override
								public boolean isCellEditable(int row,
										int column) {
									return false;
								}
							};
							String search = String.valueOf(applet2.getjTable1().getValueAt(r, 0));
							
							StringBuffer sb = new StringBuffer();
							try {
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);

								number = sheet.getCell(1, 1).getContents();

								try {
									int m = Integer.parseInt(sheet.getCell(11,
											0).getContents());
									for (int i = 0; i < m; i++) {
										String[] row = {
												sheet.getCell(2, i + 1)
														.getContents(),
												sheet.getCell(3, i + 1)
														.getContents(),
												sheet.getCell(4, i + 1)
														.getContents(),
												sheet.getCell(5, i + 1)
														.getContents(),
												sheet.getCell(6, i + 1)
														.getContents(),
												sheet.getCell(7, i + 1)
														.getContents(), };
										tableModel1.addRow(row);
									}
								} finally {
									if (book != null) {
										book.close();
									}
								}
							} catch (BiffException er) {
								logger.error(er);
								System.err.println("");
							} catch (IOException er) {
								logger.error(er);
								System.err.println("文件读取错误");
							}
							
							final JTable jTable1 = new JTable(tableModel1);

							final JFrame frame = new JFrame(String
									.valueOf(applet2.getjTable1().getValueAt(r, 0)));
							JLabel title = new JLabel();
							JPanel jpl = new JPanel();
							JPanel jpr = new JPanel();
							JPanel jps = new JPanel();
							frame.add(jpl);
							jpl.setBounds(5, 50, 170, 220);
							jpl.setLayout(new GridLayout(9, 2));

							try {
								URL gis = new URL("http://hq.sinajs.cn/list="
										+ number);// 你要报错的网页
								BufferedReader in = new BufferedReader(
										new InputStreamReader(gis.openStream()));
								String line = "";
								String[] data;
								// System.out.print(number);
								while ((line = in.readLine()) != null) {
									System.out.println(line);
									data = line.split(",");
									JLabel jl1 = new JLabel("今日开盘价 ");
									JLabel jl11 = new JLabel(data[1]);

									JLabel jl2 = new JLabel("昨日收盘价 ");
									JLabel jl3 = new JLabel("当前价格 ");
									JLabel jl4 = new JLabel("今日最高价 ");
									JLabel jl5 = new JLabel("今日最低价 ");
									JLabel jl6 = new JLabel("竞买价 ");
									JLabel jl7 = new JLabel("竞卖价 ");
									JLabel jl8 = new JLabel("成交的股票数 ");
									JLabel jl9 = new JLabel("成交金额 ");

									JLabel jl22 = new JLabel(data[2]);
									JLabel jl33 = new JLabel(data[3]);
									JLabel jl44 = new JLabel(data[4]);
									JLabel jl55 = new JLabel(data[5]);
									JLabel jl66 = new JLabel(data[6]);
									JLabel jl77 = new JLabel(data[7]);
									JLabel jl88 = new JLabel(data[8]);
									JLabel jl99 = new JLabel(data[9]);
									jpl.add(jl1);
									jpl.add(jl11);
									jpl.add(jl2);
									jpl.add(jl22);
									jpl.add(jl3);
									jpl.add(jl33);
									jpl.add(jl4);
									jpl.add(jl44);
									jpl.add(jl5);
									jpl.add(jl55);
									jpl.add(jl6);
									jpl.add(jl66);
									jpl.add(jl7);
									jpl.add(jl77);
									jpl.add(jl8);
									jpl.add(jl88);
									jpl.add(jl9);
									jpl.add(jl99);
								}
								in.close();
							} catch (Exception er) {
								System.out.println(er);
							}
							final JTabbedPane jtpFigures = new JTabbedPane();
							Catchgra fenshi = new Catchgra();
							final Catchgra rik = new Catchgra();
							Catchgra zhouk = new Catchgra();
							Catchgra yuekp = new Catchgra();
							try {
								fenshi.Setimg("http://image.sinajs.cn/newchart/min/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								rik.Setimg("http://image.sinajs.cn/newchart/daily/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}

							try {
								zhouk.Setimg("http://image.sinajs.cn/newchart/weekly/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								yuekp.Setimg("http://image.sinajs.cn/newchart/monthly/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							jtpFigures.add(fenshi, "分时");
							jtpFigures.add(rik, "日K");
							jtpFigures.add(zhouk, "周K");
							jtpFigures.add(yuekp, "月K");
							jtpFigures.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent e) {
									JTabbedPane tabbedPane = (JTabbedPane) e
											.getSource();
									int selectedIndex = tabbedPane
											.getSelectedIndex();
									switch (selectedIndex) {
									case 0:
										break;

									case 1:
										JFrame frame2 = new JFrame("");
										frame2.setVisible(true);
										frame2.setResizable(false);
										frame2.setSize(1, 1);
										frame2.dispose();
										frame2 = null;
										break;
									case 2:
										frame.setSize(700, 581);
										JFrame frame3 = new JFrame("");
										frame3.setVisible(true);
										frame3.setResizable(false);
										frame3.setSize(1, 1);
										frame3.dispose();
										frame3 = null;
										break;
									case 3:
										JFrame frame4 = new JFrame("");
										frame4.setVisible(true);
										frame4.setResizable(false);
										frame4.setSize(1, 1);
										frame4.dispose();
										frame4 = null;
										break;
									}
								}
							});
							frame.add(jtpFigures);
							jtpFigures.setBounds(190, 10, 500, 330);
							jps.setLayout(new BorderLayout());
							jps.add(new JScrollPane(jTable1));
							frame.add(jps);
							jps.setBounds(40, 350, 620, 140);
							frame.setLayout(null);
							frame.setSize(700, 560);
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							frame.setResizable(false);

						}
					}
				});
	}
}
