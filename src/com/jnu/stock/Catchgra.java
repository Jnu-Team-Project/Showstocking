package com.jnu.stock;
import java.awt.AWTEvent;  
import java.awt.Frame;  
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.event.WindowEvent;  
import java.awt.image.ImageProducer;  
import java.io.IOException;  
import java.net.URL;  

import javax.swing.JPanel;
  
public class Catchgra extends JPanel
{  
    private Image img;
    
    public void Setimg(String path)throws IOException
    {
    	URL url = new URL(path);  
    	this.img  = this.createImage((ImageProducer)url.getContent()); 
    	this.setSize(425,300);
    	System.out.print("这里是通过的，setimg+\n");
    }
    public void paint(Graphics g)
    {  
      g.drawImage(img, 0, 0,500,290, this);//显示图像  
    }  
}  
