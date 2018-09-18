package com.kye;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProgressBar implements ActionListener, ChangeListener{

	JFrame frame = null;

    JProgressBar progressbar;

    JLabel label;

    Timer timer;

    JButton b;
    
    JButton pick;
 
    String filePatch;

    Base64ToPicture base2Pic;
    
    boolean succ;
    
    public ProgressBar() {

       frame = new JFrame("�Ĵ���־ͼƬץȡ");

       frame.setBounds(750, 400, 400, 130);

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       frame.setResizable(false);

       Container contentPanel = frame.getContentPane();

       label = new JLabel("��ѡ���ļ��ٵ����ȡͼƬ", JLabel.CENTER);
       
       
       
       progressbar = new JProgressBar();

       progressbar.setOrientation(JProgressBar.HORIZONTAL);

       progressbar.setMinimum(0);

       progressbar.setMaximum(100);

       progressbar.setValue(0);

       progressbar.setStringPainted(true);

       progressbar.addChangeListener(this);

       progressbar.setPreferredSize(new Dimension(300, 20));

       progressbar.setBorderPainted(true);

       progressbar.setBackground(Color.pink);

 

       JPanel panel = new JPanel();
       
       pick = new JButton("ѡ���ļ�");
       
       pick.setForeground(Color.blue);

       pick.addActionListener(this);

       b = new JButton("��ȡͼƬ");

       b.setForeground(Color.blue);

       b.addActionListener(this);
       
       panel.add(pick);

       panel.add(b);
      

       timer = new Timer(100, this);

       contentPanel.add(panel, BorderLayout.NORTH);

       contentPanel.add(label, BorderLayout.CENTER);

       contentPanel.add(progressbar, BorderLayout.SOUTH);

       frame.setVisible(true);
       
       
       label.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			String text = label.getText();
			if(text.equals("ͼƬ��ȡ��ɣ�����鿴")) {
				try {
					String picFile2 = filePatch.substring(0, filePatch.lastIndexOf("\\")+1);
					File dirPic = new File(picFile2,"��־ͼƬ");
					if(dirPic.exists()) {
						java.awt.Desktop.getDesktop().open(dirPic);
					}else {
						JOptionPane.showMessageDialog(frame, "�ļ�������!!!", "��ʾ",JOptionPane.WARNING_MESSAGE); 
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
			}
		}
	});

    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
       if(e.getSource() == pick) {
    	   JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "ѡ��");  
	        File file=jfc.getSelectedFile();  
	        if(file.isDirectory()){   
	        	JOptionPane.showMessageDialog(frame, "��ѡ��txt�ļ�!!!", "��ʾ",JOptionPane.WARNING_MESSAGE);  
	        }else if(file.isFile()){  
	            filePatch = file.getAbsolutePath();
	            label.setText(filePatch);
	        }   
       }
    	
       if (e.getSource() == b) {

    	   String filePatch = label.getText();
    	   File tempFile = new File(filePatch);
    	   if(!tempFile.exists()) {
    		   JOptionPane.showMessageDialog(frame, "����ѡ���ļ�!!!", "��ʾ",JOptionPane.WARNING_MESSAGE);  
    		   return;
    	   }
    	   
           timer.start();
           
           //��ʼ����ͼƬ 
           if(base2Pic == null) {
        	   base2Pic = new Base64ToPicture();
           }
           succ = base2Pic.readTxtFile(filePatch);
       }

       if (e.getSource() == timer) {

           int value = progressbar.getValue();

           if (value < 100 || !succ) {
        	   progressbar.setValue(++value);
           }else {

              timer.stop();

              label.setText("ͼƬ��ȡ��ɣ�����鿴");

           }
       }

    }

    @Override
    public void stateChanged(ChangeEvent e1) {

       int value = progressbar.getValue();

       if (e1.getSource() == progressbar) {

           label.setText("Ŀǰ����ɽ��ȣ�" + Integer.toString(value) + "%");

           label.setForeground(Color.blue);

       }

    }


    public static void main(String[] args) {

       try {
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
       } catch (Exception e) {

           Logger.getLogger(ProgressBar.class.getName()).log(Level.FINE,

                  e.getMessage());

           e.printStackTrace();
       }
       new ProgressBar();
    }
	
}
