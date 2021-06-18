package com.anjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import org.json.JSONObject;




public class MainLogin extends JFrame implements ActionListener, KeyListener{
	JPanel logInPanel, signUpBtnPanel, imagePanel, signUpMainPanel, cRoomPanel;
	JPanel seatsPanel = new SeatsPanel();
	JLabel mainTitle, subTitle, idLabel, pwdLabel, welcome, reLabel, colLabel, rowLabel, blankLabel, roomNumLabel;
	AntialiasedLabel mainLogLabel, signUpPanelLabel;
	JTextField ID, col, row, roomNum, blank;
	JPasswordField PASSWORD;;
	JButton logInBtn, signUpBtn, backBtn2, signUpBtn2, exitButton, backBtn, mainBtn, logOutBtn, cRoom, dRoom, resetDate, makeRoomBtn;
	LoggedInPanel loggedInPanel;
	FakeDB fake = new FakeDB();
	Font Title = new Font(null);
	ImageIcon icon;
	HttpCaller hc = new HttpCaller();
	JPanel logInLabelPanel = new JPanel();
	SignUpPanel signUpPanel = new SignUpPanel();
	JLabel[] logInLabels = new JLabel[signUpPanel.categories.length];


	public MainLogin(){
		
		//Panel
		 //LogInPanel
		icon = new ImageIcon();
		logInPanel = new JPanel();
		imagePanel = new JPanel();
		
		//----------------------------------------------------------------------------------------------
		
		
		//Label
		
		 //Main Title Label
		mainTitle = new JLabel("���� 2WDJ �¼� ����");
		mainTitle.setBounds(155,-160,500,500);
		mainTitle.setFont(new Font("���� �߳�ü",Font.CENTER_BASELINE,45));
		
		 //Sub Title Label
		subTitle = new JLabel("Anjava");
		subTitle.setBounds(365,-110,500,500);
		subTitle.setFont(new Font("���� �߳�ü",Font.BOLD,20));
		
		 //ID Label
		idLabel = new JLabel("ID");
		idLabel.setBounds(47,20,135,20);
		
		 //Password Label
		pwdLabel = new JLabel("PW");
		pwdLabel.setBounds(38,45,135,20);
		
		 //welcome Label
		welcome = new JLabel();
		welcome.setBounds(5,-123,300,300);
		welcome.setFont(new Font(null,Font.CENTER_BASELINE,30));
		welcome.setVisible(false);
		
		 // ����Ÿ��Ʋ
		mainLogLabel = new AntialiasedLabel("");
		mainLogLabel.setIcon(new ImageIcon(MainLogin.class.getResource("/image/mainlogin.jpg")));
		mainLogLabel.setBounds(0, 0, 800, 500);
//		BufferedImage image = new BufferedImage();
		
		 // ȸ������

		
		
		//----------------------------------------------------------------------------------------------
		
		
		//TextField
		
		 //ID
		ID = new JTextField(15);
		ID.setBounds(64,20,135,20);
		ID.addKeyListener(this);
		ID.setText("test6");

		 //Password
		PASSWORD = new JPasswordField(15);
		PASSWORD.setBounds(64,45,135,20);
		PASSWORD.addKeyListener(this);
		PASSWORD.setText("12341234");

		
		//----------------------------------------------------------------------------------------------
		
		
		//Buttons
		 //LogIn Button
		logInBtn = new JButton("�α���");
		logInBtn.setBounds(85, 75, 80, 25);
		logInBtn.setBackground(Color.LIGHT_GRAY);
		logInBtn.addActionListener(this);
		
		//main Button
		mainBtn = new JButton("�ڷΰ���");
		mainBtn.setBounds(598, 464, 84, 25);
		
		//create Room Button
		cRoom = new JButton("�游���");
		
		//delete Room Button
		dRoom = new JButton("�������");
		
		//reset Date Button
		resetDate = new JButton("�ʱ�ȭ��¥����");
		
		 //SignUp Button
		signUpBtn = new JButton("ȸ������");
		signUpBtn.setBounds(80, 110, 90, 25);
		signUpBtn.setBackground(Color.PINK);
		signUpBtn.addActionListener(this);
		
		 //Exit Button
		exitButton = new JButton("");
		exitButton.setBounds(770, 10, 20, 20);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		 //LogOutButton
		logOutBtn = new JButton("�α׾ƿ�");
		logOutBtn.setVisible(true);
		logOutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addMainLogIn();
				deleteLoggedInPanel();
				hc.clearData();
				deleteSeats();
				deleteAdminBtn();
				welcome.setVisible(false);
				if(cRoomPanel != null)
				cRoomPanel.setVisible(false);
				}
			
		});
		logOutBtn.setBounds(699, 464, 84, 25);
		
		
		//ȸ������ â �Է��׸� ����
		for(int i = 0; i < signUpPanel.categories.length; i++) {
			logInLabels[i] = new JLabel(signUpPanel.categories[i]);
			logInLabels[i].setHorizontalAlignment(JLabel.RIGHT);
			logInLabelPanel.add(logInLabels[i]);
		}
		
		//----------------------------------------------------------------------------------------------
		
		
		
		//Panel Setting

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
		this.add(logInPanel);
		this.add(imagePanel);
		this.add(exitButton);
		this.add(mainLogLabel);
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.setResizable(true);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		imagePanel.setVisible(true);
		
		//�α��� ��ư ������ ��
		if(e.getSource()==logInBtn) {
			hc.postLogIn(ID.getText(), PASSWORD.getText());
			add(mainBtn);
			addAdminBtn();
			
			//�α��� �� â���� �ڷΰ��� ������ ��
			mainBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addLoggedInPanel();
					deleteSeats();
					addAdminBtn();
					mainBtn.setVisible(false);
				}
			});
			mainBtn.setVisible(false);
			//�α��� ������ ��ġ�� ��
			if(hc.isLoggedIn()) {		
				loggedInPanel = new LoggedInPanel(new JSONObject(hc.getAllRoom()).getJSONObject("data").getJSONArray("roomsData"));
				addLoggedInPanel();
				deleteMainLogIn();
				setLogInTextEmpty();
				//�α��� ���� �� ����� Buttons
				
				for(int i = 0; i < loggedInPanel.boxCount; i++) {
					loggedInPanel.reserveBtn[i].addActionListener(new ActionListener() {
						
											//�����ư ������ ��
						
											public void actionPerformed(ActionEvent e) {
												loggedInPanel.setVisible(false);
												addSeats();
												deleteAdminBtn();
												mainBtn.setVisible(true);
												}
											});
				}
				
				//�α��� ������ ����ġ�� ��
			}else {
				JOptionPane.showInternalMessageDialog(null, "ȸ�������� ��ġ���� �ʽ��ϴ�.", "���� ����ġ",0 );
			}
		}

		
		//���� ȭ�鿡�� ȸ������ ��ư ������ ��
		if(e.getSource()==signUpBtn) {
			
			
			signUpBtnPanel = new JPanel();
			signUpPanelLabel = new AntialiasedLabel("");
			signUpPanelLabel.setIcon(new ImageIcon(MainLogin.class.getResource("/image/signup.jpg")));
			signUpPanelLabel.setBounds(0, 0, 800, 500);

			logInLabelPanel.setLayout(new GridLayout(6,0,10,10));
			logInLabelPanel.setBounds(200,130,130,200);
			logInLabelPanel.setBackground(Color.white);
			
			
			//ȸ������â�� ��ư
			signUpBtn2 = new JButton("ȸ������");
			backBtn = new JButton("�ڷΰ���");
			signUpBtnPanel.add(backBtn);
			signUpBtn2.setBackground(new Color(255,128,0));
			signUpBtnPanel.add(signUpBtn2);
			signUpBtnPanel.setBounds(315,350,180,35);
			signUpBtnPanel.setBackground(Color.white);
			
			signUpMainPanel = new JPanel();
			signUpMainPanel.setSize(400,300);

			signUpPanel.add(signUpMainPanel);
			signUpPanel.setBackground(new Color(255, 0, 0, 0));
			signUpBtnPanel.add(signUpMainPanel);
			signUpMainPanel.setBounds(350,550,300,200);
			
			signUpBtn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//ȸ������ â���� ȸ������ ��ư ������ ��
					int yjuNum;
		               if (signUpPanel.fields[4].getText().equals("")) {
		                  yjuNum = 0;
		               } else {
		                  yjuNum = Integer.valueOf(signUpPanel.fields[4].getText());
		               }
		               
		               String res = hc.postSign(signUpPanel.fields[1].getText(), 
		                        signUpPanel.pwdFields[0].getText(), 
		                        signUpPanel.fields[0].getText(), 
		                        yjuNum, 
		                        signUpPanel.fields[5].getText());
					System.out.println(res);
					JSONObject jo = new JSONObject(res);
					if (!signUpPanel.pwdFields[0].getText().equals(signUpPanel.pwdFields[1].getText())) {
						JOptionPane.showInternalMessageDialog(null, "��й�ȣ�� �������� �ʽ��ϴ�.", "Error",1);
					}
					else if (jo.isNull("status")) {
						JOptionPane.showInternalMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.\n �ٽ� �α��� ���ֽʽÿ�.","ȸ������ �Ϸ�",1);
						for(int i = 0; i < signUpPanel.categories.length; i++) {
							logInLabels[i].setVisible(false);
						}
						deleteSignUpPanel();
						deleteInfo();
						addMainLogIn();
					} else {
						JOptionPane.showInternalMessageDialog(null, jo.getString("message"), "Error",1);
					}
				}				
			});
			
			//�ڷΰ��� ��ư
			backBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deleteSignUpPanel();
					deleteInfo();
					addMainLogIn();
					setLogInTextEmpty();
				}
			});
			deleteMainLogIn();
			addSignUpPanel();
		}	
	}
	
	
	//changes display
	
	public void setLogInTextEmpty() {
		ID.setText("");
		PASSWORD.setText("");
	}
	
	public void addSignUpPanel() {
		logInLabelPanel.setVisible(true);
		signUpPanel.setVisible(true);
		add(logInLabelPanel);
		add(signUpBtnPanel);
		add(signUpPanel);
		add(signUpPanelLabel);
	}
	
	public void deleteSignUpPanel() {
		logInLabelPanel.setVisible(false);
		signUpPanel.setVisible(false);
		signUpPanelLabel.setVisible(false);
		remove(signUpBtnPanel);
	}
	
	public void addLoggedInPanel() {
		add(loggedInPanel);
		if(hc.isAdmin()) {
//			add(loggedInPanel.reserve);
			loggedInPanel.btnPanel.setPreferredSize(new Dimension(600, (50 / 4 + 1) * 100));
			loggedInPanel.scroll.setPreferredSize(new Dimension(600, 405));
			loggedInPanel.setBounds(6, 49, 600, 405);
			add(cRoom);
			cRoom.setBounds(622,58,160,100);
			cRoom.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					loggedInPanel.setVisible(false);
//					cRoomPanel = new JPanel();
//					
//					roomNum = new JTextField();
//					roomNumLabel = new JLabel("���ǽ� ȣ��");
//					roomNumLabel.setHorizontalAlignment(JLabel.RIGHT);
//					cRoomPanel.add(roomNumLabel);
//					cRoomPanel.add(roomNum);
//					
//					col = new JTextField();
//					colLabel = new JLabel("������");
//					colLabel.setHorizontalAlignment(JLabel.RIGHT);
//					cRoomPanel.add(colLabel);
//					cRoomPanel.add(col);
//					
////					col.setBounds(150,150,80,40);
//					row = new JTextField();
//					rowLabel = new JLabel("������");
//					rowLabel.setHorizontalAlignment(JLabel.RIGHT);
//					cRoomPanel.add(rowLabel);
//					cRoomPanel.add(row);
//					
//					blank = new JTextField();
//					blankLabel = new JLabel("����");
//					blankLabel.setHorizontalAlignment(JLabel.RIGHT);
//					cRoomPanel.add(blankLabel);
//					cRoomPanel.add(blank);
//					
//					cRoomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
//					cRoomPanel.setLayout(new GridLayout(4,2,15,15));
//					cRoomPanel.setBounds(63,78,500,350);
//					add(cRoomPanel);
//					
//					backBtn2 = new JButton("�ڷΰ���");
//					backBtn2.setBounds(598, 464, 84, 25);
//					add(backBtn2);
//					
//					//�游��� â���� �ڷΰ��� ��ư ������ ��
//					if(e.getSource()==backBtn2) {
//////						cRoomPanel.setVisible(false);
//////						remove(cRoomPanel);
////						addLoggedInPanel();
//					}
					new createRoom();
				}			
			});
//			createRoom();
			add(dRoom);
			dRoom.setBounds(622,204,160,100);
			add(resetDate);
			resetDate.setBounds(622,350,160,100);
		}
		add(welcome);
		welcome.setVisible(true);
		loggedInPanel.setVisible(true);
		welcome.setText("�ȳ��ϼ���. " + hc.getName() + "��");
		add(logOutBtn);
//		addReserveBtn();
	}
	
	class createRoom extends JFrame implements ActionListener{
		public createRoom() {
		
		setSize(370,300);
		setLayout(null);
		roomNum = new JTextField();
		roomNum.setBounds(125,35,150,25);
		roomNum.setToolTipText("���ڷθ� �Է��ϼ���.");
		roomNumLabel = new JLabel("���ǽ� ȣ��");
		roomNumLabel.setBounds(48,35,70,25);
		roomNumLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(roomNumLabel);
		add(roomNum);
		
		col = new JTextField();
		col.setBounds(125,82,150,25);
		col.setToolTipText("���ڷθ� �Է��ϼ���.");
		colLabel = new JLabel("������");
		colLabel.setBounds(65,82,50,25);
		colLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(colLabel);
		add(col);
		

		row = new JTextField();
		row.setBounds(125,129,150,25);
		row.setToolTipText("���ڷθ� �Է��ϼ���.");
		rowLabel = new JLabel("������");
		rowLabel.setBounds(65,129,50,25);
		rowLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(rowLabel);
		add(row);
		
		
		blank = new JTextField();
		blank.setBounds(125,176,150,25);
		blankLabel = new JLabel("����");
		blankLabel.setBounds(65,176,50,25);
		blankLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(blankLabel);
		add(blank);
		
		makeRoomBtn = new JButton("�游���");
		makeRoomBtn.addActionListener(this);
		makeRoomBtn.setBounds(190,218,85,25);
		add(makeRoomBtn);
//			
//			cRoomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
//			cRoomPanel.setLayout(new GridLayout(4,2,15,15));
//			cRoomPanel.setBounds(63,78,500,350);
//			add(cRoomPanel);
		setLocationRelativeTo(null);
		setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			hc.postCreateRoom(Integer.valueOf(roomNum.getText()), Integer.valueOf(col.getText()), Integer.valueOf(row.getText()), null, null);
			dispose();
			loggedInPanel.revalidate();
			loggedInPanel.repaint();
			repaint();
		}
		
	}
	
	public void deleteLoggedInPanel() {
		remove(loggedInPanel);
	}
	
	public void addMainLogIn() {
		logInPanel.setVisible(true);
		mainTitle.setVisible(true);
		subTitle.setVisible(true);
		mainLogLabel.setVisible(true);
		remove(logOutBtn);
	}
	
	public void deleteMainLogIn() {
		logInPanel.setVisible(false);
		mainTitle.setVisible(false);
		subTitle.setVisible(false);
		mainLogLabel.setVisible(false);
	}
	
	public void deleteInfo() {
		for(int i = 0; i < signUpPanel.categories.length; i++) {
			if(i == 2 || i ==3) {
				signUpPanel.pwdFields[i-2].setText("");
			}else {
				signUpPanel.fields[i].setText("");
			}
		}
	}
	
	public void addSeats() {
		
		seatsPanel.setVisible(true);
		seatsPanel.setBounds(20, -46, 800, 500);
		add(seatsPanel);	
		seatsPanel.setVisible(true);
	}
	
	public void deleteSeats() {

		mainBtn.setVisible(false);
		seatsPanel.setVisible(false);
	}
	
	public void addReserveBtn() {
		for(int i=0; i <loggedInPanel.boxCount; i++) {
			loggedInPanel.reserveBtn[i].setVisible(false);
		}
	}
	
	public void deleteReserveBtn() {
		for(int i=0; i <loggedInPanel.boxCount; i++) {
			loggedInPanel.reserveBtn[i].setVisible(true);
		}
	}
	
	public void deleteAdminBtn() {
		cRoom.setVisible(false);
		dRoom.setVisible(false);
		resetDate.setVisible(false);
	}
	
	public void addAdminBtn() {
		if(hc.isAdmin()) {
		cRoom.setVisible(true);
		dRoom.setVisible(true);
		resetDate.setVisible(true);
		}
	}
	
	public void createRoom() {
		if(hc.isAdmin()) {
		cRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cRoomPanel = new JPanel();
				
				roomNum = new JTextField();
				cRoomPanel.add(roomNum);
				
				col = new JTextField();
				cRoomPanel.add(col);
				
				row = new JTextField();
				cRoomPanel.add(row);
				
				blank = new JTextField();
				cRoomPanel.add(blank);
				cRoomPanel.setBounds(350,130,200,200);
				add(cRoomPanel);
			}
		});
		}
	}
	//key events
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			logInBtn.doClick();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	public static void main(String[] args) {
		new MainLogin();
	}
}