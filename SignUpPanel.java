import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class SignUpPanel extends JPanel{
	
	String[] categories = {"Name", "ID", "Password", "Password Check", "Student ID", "Email"};
	JTextField[] fields = new JTextField[categories.length];
	JPasswordField[] pwdFields = new JPasswordField[2];
	JLabel reLabel;
	
	public SignUpPanel(){
		


		for(int i = 0; i < categories.length; i++) {			
			//패스워드필드 설정
			if(i == 2 || i ==3) {
				pwdFields[i-2] = new JPasswordField(i);
				this.add(pwdFields[i-2]);
			}else {
			fields[i] = new JTextField(15);
			fields[i].setToolTipText(categories[i]);
			this.add(fields[i]);
			}
		}

		this.setBounds(350,130,200,200);
		this.setLayout(new GridLayout(6,0,10,10));
		this.setBackground(Color.white);
		
	}

}
