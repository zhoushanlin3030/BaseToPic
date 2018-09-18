package com.kye;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Loading {
	
	static JFrame jFrame = null;

	public static void main(String[] args) {
		
        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				jFrame = new JFrame();
				jFrame.setTitle("Test Frame");  
				jFrame.setSize(800,600);  
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				final JPanel panel = new JPanel();  
                final JLabel label = new JLabel(); 
                
                JScrollPane sp = new JScrollPane(panel);  
                sp.setSize(new Dimension(700,500));  
                jFrame.add(sp,BorderLayout.CENTER);  
                  
                JPanel stp = new JPanel();  
                final JProgressBar jpb = new JProgressBar();  
                jpb.setMinimum(1);  
                jpb.setMaximum(100);  
                stp.add(jpb);  
                stp.add(label);  
                jFrame.add(stp,BorderLayout.CENTER);  
				
			}
		});
	}
	
}
