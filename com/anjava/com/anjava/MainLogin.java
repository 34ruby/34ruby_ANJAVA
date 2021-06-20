package com.anjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;

import org.json.JSONArray;
import org.json.JSONObject;




public class MainLogin extends JFrame implements ActionListener, KeyListener{
	JPanel logInPanel, signUpBtnPanel, imagePanel, signUpMainPanel, cRoomPanel;
	JLabel mainTitle, subTitle, idLabel, pwdLabel, welcome, reLabel, colLabel, rowLabel, colBlankLabel, rowBlankLabel, roomNumLabel, logTypingLabel, roomPanelLabel;
	AntialiasedLabel mainLogLabel, signUpPanelLabel;
	JTextField ID, col, row, roomNum, colBlank, rowBlank;
	JPasswordField PASSWORD;;
	JButton logInBtn, signUpBtn, backBtn2, signUpBtn2, exitButton, backBtn, mainBtn, logOutBtn, cRoom, dRoom, resetDate, makeRoomBtn;
	LoggedInPanel loggedInPanel;
	FakeDB fake = new FakeDB();
	Font Title, LoginInfo, WhiteButtonText, ColorButtonText = new Font(null);
	ImageIcon icon, icon1;
	HttpCaller hc = new HttpCaller();
	JPanel logInLabelPanel = new JPanel();
	SignUpPanel signUpPanel = new SignUpPanel();
	JLabel[] logInLabels = new JLabel[signUpPanel.categories.length];
	JPanel seatsPanel;
	JSONArray roomsData;
	int  xDrag, yDrag, xPress, yPress;
	
	private BufferedImage img = null;


	public MainLogin(){
		
		//Panel
		 //LogInPanel
//		icon = new ImageIcon();
		logInPanel = new JPanel();
		imagePanel = new JPanel();
//		icon1 = new ImageIcon("/image/mainlogin2.jpg");
//		JLabel background = new JLabel() {
//			public void paintComponent(Graphics g) {
//				g.drawImage(icon1.getImage(),0,0,null);
//				super.paintComponent(g);
//			}
//		};
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
		idLabel = new JLabel("");
		idLabel.setBounds(47,20,135,20);
		
		 //Password Label
		pwdLabel = new JLabel("");
		pwdLabel.setBounds(38,45,135,20);
		
		 //welcome Label
		welcome = new JLabel();
		welcome.setBounds(15,-128,300,300);
		welcome.setFont(new Font("HY�߰���", Font.PLAIN, 20));
		welcome.setForeground(Color.white);
		welcome.setVisible(false);
		
		 // ����Ÿ��Ʋ
		mainLogLabel = new AntialiasedLabel("");
		mainLogLabel.setIcon(new ImageIcon(MainLogin.class.getResource("/image/mainlogin2.jpg")));
//		mainLogLabel.setIcon()
		mainLogLabel.setBounds(0, 0, 800, 500);
//		BufferedImage image = new BufferedImage();
		
		 // ȸ������

		
		
		//----------------------------------------------------------------------------------------------
		
		
		//TextField
		//setBorder �޼ҵ� �������̵����� JTextField�� �׵θ� ����
		//ID
		ID = new JTextField(15);
		ID.setBorder(null);
		
		ID.setBounds(70,40,250,40);
		ID.addKeyListener(this);
		ID.setText("test6");
//		ID.setBackground(Color.gray);
		ID.setFont(new Font("SAN SERIF", Font.PLAIN, 25));
		ID.setForeground(new Color( 125, 124, 130));
		

		 //Password
		PASSWORD = new JPasswordField(15){
			@Override 
			public void setBorder(Border border) {		
			}
		};
		PASSWORD.setBounds(70,108,250,40);
		PASSWORD.addKeyListener(this);
		PASSWORD.setText("12341234");
		PASSWORD.setFont(new Font("SAN SERIF", Font.PLAIN, 25));
		PASSWORD.setForeground(new Color( 125, 124, 130));
		
		// �α��� â
		logTypingLabel = new AntialiasedLabel("");
		logTypingLabel.setIcon(new ImageIcon(MainLogin.class.getResource("/image/logTypingLabel.jpg")));
		logTypingLabel.setBounds(0, 0, 300, 300);
		
		
		//----------------------------------------------------------------------------------------------
		
		
		//Buttons
		 //LogIn Button
		logInBtn = new JButton();
		logInBtn.setText("�α���");
		logInBtn.setBounds(160, 185, 100, 35);
		logInBtn.setBackground(new Color(135,77,162));
		logInBtn.setFont(new Font("HY�߰���", Font.PLAIN, 14));
		logInBtn.setForeground(Color.white);
		logInBtn.addActionListener(this);
		logInBtn.setBorderPainted(false);
		
		//main Button
		mainBtn = new JButton("�ڷΰ���");
		mainBtn.setBounds(598, 464, 84, 25);
		
		//create Room Button
		cRoom = new JButton("�游���"){
			@Override 
			public void setBorder(Border border) {		
			}
		};
		cRoom.setFont(new Font("HY�߰���", Font.PLAIN, 25));
		cRoom.setForeground(Color.white);
		cRoom.setBackground(new Color(135,77,162));
		cRoom.setBounds(622,58,160,100);
		cRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new createRoom();
			}			
		});
		
		//delete Room Button
		dRoom = new JButton("�������"){
			@Override 
			public void setBorder(Border border) {		
			}
		};
		dRoom.setBounds(622,204,160,100);
		dRoom.setFont(new Font("HY�߰���", Font.PLAIN, 25));
		dRoom.setForeground(Color.white);
		dRoom.setBackground(new Color(135,77,162));
		dRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // TODO Auto-generated method stub
               String input = JOptionPane.showInputDialog("���� ���� ȣ���� �Է��ϼ���.");
               hc.deleteRoom(Integer.valueOf(input));
            }
         });
		
		//reset Date Button
		resetDate = new JButton("�ʱ�ȭ����"){
			@Override 
			public void setBorder(Border border) {		
			}
		};
		
		resetDate.setBounds(622,350,160,100);
		resetDate.setFont(new Font("HY�߰���", Font.PLAIN, 25));
		resetDate.setForeground(Color.white);
		resetDate.setBackground(new Color(135,77,162));

		
		 //SignUp Button
		signUpBtn = new JButton("ȸ������");
		signUpBtn.setBounds(50, 185, 100, 35);
		signUpBtn.setBackground(Color.LIGHT_GRAY);
		signUpBtn.setFont(new Font("HY�߰���", Font.PLAIN, 14));
		signUpBtn.setBorderPainted(false); // ��ư �׵θ� ���ֱ�
		signUpBtn.addActionListener(this);
		
		 //Exit Button
		exitButton = new JButton();

		exitButton.setBounds(770, 10, 20, 20);
		exitButton.setBorderPainted(false);
		exitButton.setBackground(Color.red);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		 //LogOutButton
		logOutBtn = new JButton("�α׾ƿ�");
		
		logOutBtn.setBackground(new Color(135,77,162));
		logOutBtn.setFont(new Font("HY�߰���", Font.PLAIN, 10));
		logOutBtn.setForeground(Color.white);
		logOutBtn.setBorderPainted(false);
		logOutBtn.setVisible(true);
		logOutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				deleteLoggedInPanel();
				hc.clearData();
				if (seatsPanel != null) deleteSeats();
				deleteAdminBtn();
				welcome.setVisible(false);
				if(cRoomPanel != null)
				cRoomPanel.setVisible(false);
				addMainLogIn();
				remove(roomPanelLabel);
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

		logInPanel.setBounds(450, 160, 350, 250);
		logInPanel.setBackground(new Color(255,255,255));
		logInPanel.add(ID);
		logInPanel.add(PASSWORD);
		logInPanel.add(idLabel);
		logInPanel.add(pwdLabel);
		logInPanel.add(logInBtn);
		logInPanel.add(signUpBtn);
		logInPanel.add(logTypingLabel);

		
		
		//----------------------------------------------------------------------------------------------
		
		
		//Frame Setting
		this.setLayout(null);

		this.add(logInPanel);

		this.add(exitButton);
		this.add(imagePanel);
		this.add(mainLogLabel);
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.setResizable(true);
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
			    xDrag = e.getX();
			    yDrag = e.getY();
			    JFrame sFrame = (JFrame) e.getSource();
			    sFrame.setLocation(sFrame.getLocation().x+xDrag-xPress, 
			    sFrame.getLocation().y+yDrag-yPress);
			 }
			 @Override
			 public void mouseMoved(MouseEvent e) {
			     xPress = e.getX();
			     yPress = e.getY();
			  }
			
		});
		this.setUndecorated(true);
		this.setVisible(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		imagePanel.setVisible(true);
		
		//�α��� ��ư ������ ��
		if(e.getSource()==logInBtn) {
			hc.postLogIn(ID.getText(), PASSWORD.getPassword());
			add(mainBtn);
			addAdminBtn();
			
			//�α��� �� â���� �ڷΰ��� ������ ��
			mainBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					addAdminBtn();	
					deleteSeats();
					mainBtn.setVisible(false);
					roomPanelLabel.setVisible(false);
					addLoggedInPanel();
					
				}
				
			});
			mainBtn.setVisible(false);
			//�α��� ������ ��ġ�� ��
			if(hc.isLoggedIn()) {		
				roomsData = new JSONObject(hc.getAllRoom()).getJSONObject("data").getJSONArray("roomsData");
			    roomsData = sortJsonArray(roomsData, "roomNum");
				loggedInPanel = new LoggedInPanel(roomsData);
				addLoggedInPanel();
				deleteMainLogIn();
				setLogInTextEmpty();
				//�α��� ���� �� ����� Buttons
				
				for(int i = 0; i < loggedInPanel.boxCount; i++) {
					int roomNum = roomsData.getJSONObject(i).getInt("roomNum");
					loggedInPanel.reserveBtn[i].addActionListener(new ActionListener() {
//											//�����ư ������ ��
//											@Override
											public void actionPerformed(ActionEvent e) {
//												remove(loggedInPanel);
//												deleteLoggedInPanel();
												loggedInPanel.setVisible(false);
												addSeats(roomNum);
												deleteAdminBtn();
												
//												deleteReserveBtn();
												loggedInPanel.reserve.setVisible(false);
												mainBtn.setVisible(true);
												}
											});
////					loggedInPanel.scroll.add(loggedInPanel.reserveBtn[i]);
//					loggedInPanel.add(loggedInPanel.reserveBtn[i]);
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
			
//			logInLabelPanel.setLayout(new GridLayout(6,0,10,10));
//			logInLabelPanel.setBounds(400,130,130,200);
//			logInLabelPanel.setBackground(Color.white);
			
//			logInBtn.setBounds(160, 185, 100, 35);
//			logInBtn.setBackground(new Color(135,77,162));
//			logInBtn.setFont(new Font("HY�߰���", Font.PLAIN, 14));
			//ȸ������â�� ��ư
			signUpBtn2 = new JButton("ȸ������");
			backBtn = new JButton("�ڷΰ���");
			backBtn.setBackground(Color.LIGHT_GRAY);
			backBtn.setFont(new Font("HY�߰���", Font.PLAIN, 12));
			signUpBtn2.setFont(new Font("HY�߰���", Font.PLAIN, 12));
			signUpBtn2.setBackground(new Color(135,77,162));
			signUpBtn2.setForeground(Color.white);
			signUpBtn2.setBorderPainted(false);
			backBtn.setBorderPainted(false);
			
			
			signUpBtnPanel.setBounds(515,420,180,35);
			signUpBtnPanel.setBackground(Color.white);
			
			
			signUpMainPanel = new JPanel();
			signUpMainPanel.setSize(400,300);

			
			signUpPanel.setBackground(new Color(135,77,162,0));
			signUpMainPanel.setBounds(350,550,300,200);

			
			signUpPanel.add(signUpMainPanel);
			signUpBtnPanel.add(backBtn);
			signUpBtnPanel.add(signUpBtn2);
			signUpBtnPanel.add(signUpMainPanel);
			
			
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
		                        signUpPanel.pwdFields[0].getPassword(), 
		                        signUpPanel.fields[0].getText(), 
		                        yjuNum, 
		                        signUpPanel.fields[5].getText());
					System.out.println(res);
					JSONObject jo = new JSONObject(res);
					if (!signUpPanel.pwdFields[0].getPassword().equals(signUpPanel.pwdFields[1].getPassword())) {
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

//					setLogInTextEmpty();
					addMainLogIn();
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
		add(signUpPanel);
		add(logInLabelPanel);
		add(signUpBtnPanel);
		add(signUpPanelLabel);
		logInLabelPanel.setVisible(true);
		signUpPanel.setVisible(true);


	}
	
	public void deleteSignUpPanel() {
		logInLabelPanel.setVisible(false);
		signUpPanel.setVisible(false);
		signUpPanelLabel.setVisible(false);
		remove(signUpBtnPanel);
	}
	
	public void addLoggedInPanel() {
		add(welcome);
		welcome.setVisible(true);
		loggedInPanel.setVisible(true);
		welcome.setText(hc.getName() + "�� �ݰ����ϴ�.");
		add(logOutBtn);
		add(loggedInPanel);
		if(hc.isAdmin()) {
//			add(loggedInPanel.reserve);
			loggedInPanel.btnPanel.setPreferredSize(new Dimension(600, (50 / 4 + 1) * 100));
			loggedInPanel.scroll.setPreferredSize(new Dimension(600, 405));
			loggedInPanel.setBounds(6, 49, 600, 415);	
			add(cRoom);
			add(dRoom);
			add(resetDate);
		}
		roomPanelLabel = new AntialiasedLabel("");
		roomPanelLabel.setIcon(new ImageIcon(MainLogin.class.getResource("/image/room.jpg")));
		roomPanelLabel.setBounds(0, 0, 800, 500);
		add(roomPanelLabel);
	}
	
	class createRoom extends JFrame implements ActionListener{
		public createRoom() {
		
		setSize(370,347);
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
		colLabel = new JLabel("�� ��");
		colLabel.setBounds(65,82,50,25);
		colLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(colLabel);
		add(col);
		

		row = new JTextField();
		row.setBounds(125,129,150,25);
		row.setToolTipText("���ڷθ� �Է��ϼ���.");
		rowLabel = new JLabel("�� ��");
		rowLabel.setBounds(65,129,50,25);
		rowLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(rowLabel);
		add(row);
		
		
		colBlank = new JTextField();
		colBlank.setBounds(125,176,150,25);
		colBlankLabel = new JLabel("�� ����");
		colBlankLabel.setBounds(35,176,80,25);
		colBlankLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(colBlankLabel);
		add(colBlank);
		
		rowBlank = new JTextField();
		rowBlank.setBounds(125,223,150,25);
		rowBlankLabel = new JLabel("�� ����");
		rowBlankLabel.setBounds(35,223,80,25);
		rowBlankLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(rowBlankLabel);
		add(rowBlank);
		
		makeRoomBtn = new JButton("�游���");
		makeRoomBtn.addActionListener(this);
		makeRoomBtn.setBounds(190,265,85,25);
		
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
			StringTokenizer colst = new StringTokenizer(colBlank.getText(), ", ");
			StringTokenizer rowst = new StringTokenizer(rowBlank.getText(), ", ");
			int[] colBlankArray = new int[colst.countTokens()];
			int[] rowBlankArray = new int[rowst.countTokens()];
			
			System.out.println("coltokencount: " + colst.countTokens());
			System.out.println("rowtokencount: " + rowst.countTokens());
			
			for (int i=0; i<colBlankArray.length; i++) {
				colBlankArray[i] = Integer.valueOf(colst.nextToken());
			}
			for (int i=0; i<rowBlankArray.length; i++) {
				rowBlankArray[i] = Integer.valueOf(rowst.nextToken());
			}
			
			for (int a : colBlankArray) {
				System.out.println("colblank: " + a);
			}
			for (int a : rowBlankArray) {
				System.out.println("rowblank: " + a);
			}
			System.out.println("colblanklength: " + colBlankArray.length);
			System.out.println("rowblanklength: " + rowBlankArray.length);
			
			hc.postCreateRoom(Integer.valueOf(roomNum.getText()), 
							  Integer.valueOf(col.getText()), 
							  Integer.valueOf(row.getText()), 
							  colBlankArray.length == 0 ? null : colBlankArray,
							  rowBlankArray.length == 0 ? null : rowBlankArray);
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
	
	public void addSeats(int roomNum) {
		JSONObject obj = new JSONObject(hc.getOneRoom(roomNum)).getJSONObject("data").getJSONObject("roomData");
		int col = obj.getInt("column");
		int row = obj.getInt("row");
		JSONArray jcolbl = obj.getJSONArray("columnBlankLine");
		JSONArray jrowbl = obj.getJSONArray("rowBlankLine");
		JSONObject rsvd = obj.getJSONObject("reservedData");
		int[] colbl = new int[jcolbl.length()];
		int[] rowbl = new int[jrowbl.length()];
		int[] reservedData = new int[rsvd.length()];
		for (int i = 0; i<colbl.length; i++) {
			colbl[i] = jcolbl.getInt(i);
		}
		for (int i = 0; i<rowbl.length; i++) {
			rowbl[i] = jrowbl.getInt(i);
		}
		Iterator<String> iter = rsvd.keys();
		for (int i=0; i<reservedData.length; i++) {
			reservedData[i] = Integer.valueOf((String) iter.next());
		}
		int[] reservedSeats;
		seatsPanel = new SeatsPanel(col, row, colbl, rowbl, roomNum, reservedData, hc);
		
		seatsPanel.setBounds(10, 50, 800, 400);
		seatsPanel.setBackground(Color.white);
	
		remove(roomPanelLabel);
		add(seatsPanel);
//		remove(loggedInPanel);
		add(roomPanelLabel);
		seatsPanel.setVisible(true);
//		seatsPanel.setBackground(Color.gray.brighter());
		
//		seatsPanel.setVisible(true);
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
	
	private <T extends Comparable<T>> JSONArray sortJsonArray(JSONArray jsonArr, String KEY_NAME) {
		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		JSONArray sortedJsonArray = new JSONArray();
	    for (int i = 0; i < jsonArr.length(); i++) {
	        jsonValues.add(jsonArr.getJSONObject(i));
	    }
	    Collections.sort( jsonValues, new Comparator<JSONObject>() {
	        @SuppressWarnings("unchecked")
			@Override
	        public int compare(JSONObject a, JSONObject b) {
	        	
	        	T valA = (T) a.get(KEY_NAME);
	            T valB = (T) b.get(KEY_NAME);

	            return valA.compareTo(valB);
	        }
	    });
	    
	    for (int i = 0; i < jsonArr.length(); i++) {
	        sortedJsonArray.put(jsonValues.get(i));
	    }
	    
	    return sortedJsonArray;
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