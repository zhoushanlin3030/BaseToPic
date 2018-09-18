package com.kye;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PickFile extends JFrame implements ActionListener{
	
	JFrame jFrame = null;
	JButton open=null; 
	JButton start = null;
	String filePatch;
	Base64ToPicture btp;
	
	public static void main(String[] args) {
		new PickFile();
	}
	
	public PickFile() {
		jFrame = new JFrame("�Ĵ���־ͼƬץȡ");
		jFrame.setBounds(400,200,400,100);
		jFrame.setSize(500, 150);
		jFrame.setResizable(false);
		setSize(805, 410);
		setLocationRelativeTo(getOwner()); 
		open=new JButton("ѡ����־");  
		start = new JButton("��ȡͼƬ");

		jFrame.add(open, BorderLayout.WEST);
		jFrame.add(start, BorderLayout.EAST);
		
		jFrame.setVisible(true);  
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        open.addActionListener(this);  
        start.addActionListener(this);
       
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == open) {
			JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "ѡ��");  
	        File file=jfc.getSelectedFile();  
	        if(file.isDirectory()){  
	            System.out.println("�ļ���:"+file.getAbsolutePath()); 
	            filePatch = file.getAbsolutePath();
	        }else if(file.isFile()){  
	            System.out.println("�ļ�:"+file.getAbsolutePath());  
	            filePatch = file.getAbsolutePath();
	        }  
	        System.out.println(jfc.getSelectedFile().getName());  
		}else if(e.getSource() == start) {
			if(btp == null) {
				btp = new Base64ToPicture();
			}
			btp.readTxtFile(filePatch);
		}
		
	}
	
	

}
