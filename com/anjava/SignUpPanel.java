package com.anjava;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class SignUpPanel extends JPanel{
	
	String[] categories = {"Name", "ID", "Password", "Password Check", "Student ID", "Email"};
	JTextField[] fields = new JTextField[categories.length];
	JPasswordField[] pwdFields = new JPasswordField[2];
	
	public SignUpPanel(){

		for(int i = 0; i < categories.length; i++) {			
			//패스워드필드 설정
			if(i == 2 || i ==3) {
				pwdFields[i-2] = new JPasswordField(i){
					@Override 
					public void setBorder(Border border) {		
					}
				};
				pwdFields[i-2].setToolTipText(categories[i]);
				this.add(pwdFields[i-2]);
			}else {
			fields[i] = new JTextField(15){
				@Override 
				public void setBorder(Border border) {		
				}
			};
			fields[i].setToolTipText(categories[i]);
			this.add(fields[i]);
			}
		}
		
		this.setBounds(550,130,200,200);
		this.setLayout(new GridLayout(6,0,10,10));
	}

}