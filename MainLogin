import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;




public class MainLogin extends JFrame implements ActionListener{
	JPanel logInPanel, signUpBtnPanel, logInLabelPanel, imagePanel, signUpMainPanel;
	JLabel[] logInLabels;
	JLabel mainTitle, subTitle, idLabel, pwdLabel, welcome, reLabel;
	MainLogLabel mainLogLabel;
	SignUpPanelLabel signUpPanelLabel;
	JTextField ID;
	JPasswordField PASSWORD;;
	JButton logInBtn, signUpBtn, signUpBtn2, exitButton;
	LoggedInPanel loggedInPanel = new LoggedInPanel();
	LoggedInPanel2 loggedInPanel2 = new LoggedInPanel2();
	FakeDB fake = new FakeDB();
	Font Title = new Font(null);
	ImageIcon icon;
	
//	public void paint()
	

	
	
	public MainLogin(){
		
		//Panel
		 //LogInPanel

		
		icon = new ImageIcon();
		logInPanel = new JPanel();
		imagePanel = new JPanel();
		
		//----------------------------------------------------------------------------------------------
		
		
		//Label
		
		 //Main Title Label
		mainTitle = new JLabel("영진 2WDJ 좌석 예약");
		mainTitle.setBounds(155,-160,500,500);
		mainTitle.setFont(new Font("여기어때 잘난체",Font.CENTER_BASELINE,45));
		
		 //Sub Title Label
		subTitle = new JLabel("Anjava");
		subTitle.setBounds(365,-110,500,500);
		subTitle.setFont(new Font("여기어때 잘난체",Font.BOLD,20));
		
		 //ID Label
		idLabel = new JLabel("ID");
		idLabel.setBounds(47,20,135,20);
		
		 //Password Label
		pwdLabel = new JLabel("PW");
		pwdLabel.setBounds(38,45,135,20);
		
		 //welcome Label
		welcome = new JLabel("안녕하세요. " + fake.name + "님");
		welcome.setBounds(5,-123,300,300);
		welcome.setFont(new Font(null,Font.CENTER_BASELINE,30));
		welcome.setVisible(false);
		
		 // 메인타이틀
		mainLogLabel = new MainLogLabel("");
		mainLogLabel.setIcon(new ImageIcon(""));
		mainLogLabel.setBounds(0, 0, 800, 500);
//		BufferedImage image = new BufferedImage();
		
		 // 회원가입

		
		
		//----------------------------------------------------------------------------------------------
		
		
		//TextField
		
		 //ID
		ID = new JTextField(15);
		ID.setBounds(64,20,135,20);
	
		 //Password
		PASSWORD = new JPasswordField(15);
		PASSWORD.setBounds(64,45,135,20);
		
		
		//----------------------------------------------------------------------------------------------
		
		
		//Button
		 //LogIn Button
		logInBtn = new JButton("로그인");
		logInBtn.setBounds(85, 75, 80, 25);
		logInBtn.setBackground(Color.LIGHT_GRAY);
		logInBtn.addActionListener(this);
		
		 //SignUp Button
		signUpBtn = new JButton("회원가입");
		signUpBtn.setBounds(80, 110, 90, 25);
		signUpBtn.setBackground(Color.PINK);
		signUpBtn.addActionListener(this);
		
		// 종료버튼
		exitButton = new JButton("");
		exitButton.setBounds(770, 10, 20, 20);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		//----------------------------------------------------------------------------------------------
		
		
		
		//Panel Setting
//		logInPanel.setBorder(new LineBorder(Color.red));
		logInPanel.setLayout(null);
		logInPanel.setBounds(275, 220, 250, 150);
		logInPanel.setBackground(new Color(255,255,255));
		logInPanel.add(ID);
		logInPanel.add(PASSWORD);
		logInPanel.add(idLabel);
		logInPanel.add(pwdLabel);
		logInPanel.add(logInBtn);
		logInPanel.add(signUpBtn);
		
		
		
		//----------------------------------------------------------------------------------------------
		
		
		//Frame Setting
		this.setLayout(null);
//		this.add(mainTitle);
//		this.add(subTitle);
		this.add(logInPanel);
		this.add(imagePanel);
		this.add(exitButton);
		this.setTitle("Anjava(앉아봐)");
		this.add(welcome);
		this.add(mainLogLabel);
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setVisible(true);
		
			
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		MainLogin logInfo = new MainLogin();
		logInfo.imagePanel.setVisible(true);
		
		//로그인 버튼 눌렀을 때
		if(e.getSource()==logInBtn) {			
			if(ID.getText().equals(fake.userId)&&PASSWORD.getText().equals(fake.password)) {
				logInfo.logInPanel.setVisible(false);
				logInfo.mainTitle.setVisible(false);
				logInfo.subTitle.setVisible(false);
				logInfo.mainLogLabel.setVisible(false);
				logInfo.add(welcome);
				logInfo.add(loggedInPanel);
				welcome.setVisible(true);
				logInfo.add(loggedInPanel2.reserve);
				
				//Buttons
				for(int i = 0; i < 12; i++) {
					loggedInPanel.reserveBtn[i] = new JButton("<HTML>" + fake.relistedRoom[0] + "<br>예약 현황20/40<br>좌석초기화:2021/03/09<br>앞으로 27일 5시간 39분<HTML>");
					loggedInPanel.reserveBtn[i].setBackground(Color.gray.brighter());
					loggedInPanel.reserveBtn[i].setBorder(null);
					loggedInPanel.reserveBtn[i].addActionListener((ActionListener) new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												//예약버튼설정
													for(int i=0; i <12; i++) {
														loggedInPanel.reserveBtn[i].setVisible(false);
													}
													loggedInPanel2.reserve.setVisible(false);
												}
											});
					loggedInPanel.add(loggedInPanel.reserveBtn[i]);
				}
				
				
			}else {
				JOptionPane.showInternalMessageDialog(null, "회원정보가 일치하지 않습니다.", "정보 불일치",0 );
			}
		}

		
		//메인 화면에서 회원가입 버튼 눌렀을 때
		if(e.getSource()==signUpBtn) {
			
//			JPanel signUpPagePanel = new JPanel();
			
			SignUpPanel signUpPanel = new SignUpPanel();
			signUpBtnPanel = new JPanel();
			
			logInLabelPanel = new JPanel();
			
			signUpPanelLabel = new SignUpPanelLabel("");
			signUpPanelLabel.setIcon(new ImageIcon(""));
			signUpPanelLabel.setBounds(0, 0, 800, 500);
//			reLabel = new JLabel(" ");
//			reLabel.setIcon(new ImageIcon(""));
//			reLabel.setBounds(0, 0, 800, 500);
			
			logInLabels = new JLabel[signUpPanel.categories.length];
			for(int i = 0; i < signUpPanel.categories.length; i++) {
				logInLabels[i] = new JLabel(signUpPanel.categories[i]);
				logInLabels[i].setHorizontalAlignment(JLabel.RIGHT);
				logInLabelPanel.add(logInLabels[i]);
			}
			
			
			logInLabelPanel.setLayout(new GridLayout(6,0,10,10));
			logInLabelPanel.setBounds(200,130,130,200);
			logInLabelPanel.setBackground(Color.white);
			
			
			
			signUpBtn2 = new JButton("회원가입");
			signUpBtn2.setBackground(new Color(255,128,0));
			signUpBtnPanel.add(signUpBtn2);
			signUpBtnPanel.setBounds(350,350,100,40);
			signUpBtnPanel.setBackground(Color.white);
			
			signUpMainPanel = new JPanel();
			signUpMainPanel.setSize(400,300);
//			signUpMainPanel.setBackground(Color.white);
			signUpPanel.add(signUpMainPanel);
			signUpBtnPanel.add(signUpMainPanel);
//			logInLabelPanel.add(signUpMainPanel);
			signUpMainPanel.setBounds(350,550,300,200);
			
//			signUpPagePanel.add(logInLabelPanel);
			
			signUpBtn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//회원가입 창에서 회원가입 버튼 눌렀을 때
					
					logInfo.remove(signUpPanel);
					
					logInfo.logInPanel.setVisible(true);
					logInfo.mainTitle.setVisible(true);
					logInfo.subTitle.setVisible(true);
					for(int i = 0; i < signUpPanel.categories.length; i++) {
						logInLabels[i].setVisible(false);
					}
					signUpBtn2.setVisible(false);
					signUpPanelLabel.setVisible(false);
					logInfo.mainLogLabel.setVisible(true);
					
					JOptionPane.showInternalMessageDialog(null, "회원가입이 완료되었습니다.\n 다시 로그인 해주십시오.","회원가입 완료",1);
				}				
			});
			
			
			
			
			logInfo.mainLogLabel.setVisible(false);
			logInfo.logInPanel.setVisible(false);
			logInfo.mainTitle.setVisible(false);
			logInfo.subTitle.setVisible(false);
			welcome.setVisible(false);
//			System.out.println("나는 최강이다");
			logInfo.add(signUpPanelLabel);
			logInfo.add(logInLabelPanel);
			logInfo.add(signUpBtnPanel);
			logInfo.add(signUpPanel);
			logInfo.add(signUpPanelLabel);
		}
		
	}
	public static void main(String[] args) {
		new MainLogin();
	}
