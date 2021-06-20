package com.anjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.json.JSONArray;
import org.json.JSONObject;

class SeatsPanel extends JPanel {
	public JPanel buttonPanel = new JPanel();
		
	   class  reserves{  
	      
	      private int sitNum;
	      private String userId;
	      
	      public reserves(int sitNum, String userId) {
	         this.sitNum=sitNum;
	         this.userId=userId;
	      
	         
	      }
	      

	      
	      public int getSitNum() {
	         return sitNum;
	      }

	      public void setSitNum(int sitNum) {
	         this.sitNum = sitNum;
	      }

	      public String getUserId() {
	         return userId;
	      }

	      public void setUserId(String userId) {
	         this.userId = userId;
	      }

	   }

	   HttpCaller hc;
	   boolean isAdmin =true;
	   static int q;
	   static String qq;
	    
	//   static JPanel mainPanel;
	   
//	   

	   int column;
	   int row;
	   int maxseat;
	   int roomNum;
	   
	   static int myReservedSeat = 0;
	   int[] otherReservedSeats;
	   
	   
	   ArrayList<reserves> reservedData =new ArrayList<>();
	   
	   MyButton[] btn;
	   
	   int[] colblock;
	   boolean colbool =false;
	   int[] rowblock;
	   boolean rowbool=false;

	   public SeatsPanel(int col, int row, int[] colbl, int[] rowbl, int roomNum, int[] otherReservedSeats, HttpCaller hc) {
//	      this.setLayout(null);
	      this.hc = hc;
		  this.roomNum = roomNum;
	      this.setLayout(null);
	      this.column = col + colbl.length;
	      this.row = row + rowbl.length;
	      this.colblock = colbl;
	      this.rowblock = rowbl;
	      this.maxseat = this.column * this.row;
	      this.isAdmin = hc.isAdmin();
	      this.otherReservedSeats = otherReservedSeats;
	      JSONArray reservedRooms = new JSONObject(hc.getUserDetail()).getJSONObject("data").getJSONArray("reservedRooms");
	      for (int i=0; i<reservedRooms.length(); i++) {
	    	  JSONObject obj;
	    	  if ((obj = (JSONObject) reservedRooms.get(i)).getInt("roomNum") == roomNum) {
	    		  SeatsPanel.myReservedSeat = obj.getInt("sitNum");
	    	  }
	      }
	      btn = new MyButton[maxseat];

//	         topLabel=new JLabel("�ȳ��ϼ���. user.name��");
	         buttonPanel=new JPanel();
//	         topLabel.setFont(new Font("202ȣ�� ����", Font.BOLD, 30));
	         
	         
	         buttonPanel.setLayout(new GridLayout(column,row,10,20));
	         buttonPanel.setBackground(Color.white);
	         
	      

	         //���� �����ִ� �迭�� ���� int k�� �־ j==k�϶� �η�(����)
	         
	         int index=1;
	         for(int i=0; i<this.column; i++) {
	            for(int j=0; j<this.row; j++) {
	            	final int jj = j;
	            	final int ii = i;
	            	final int iidx = index;
	            	btn[i*this.row+j]=new MyButton(){
	        			@Override 
	        			public void setBorder(Border border) {		
	        			}
	        		};
		            btn[i*this.row+j].setPreferredSize(new Dimension(180,180));
	            	if (IntStream.of(rowblock).anyMatch(x -> x == jj) || IntStream.of(colblock).anyMatch(x -> x == ii)) {
	            		// ������ ��
	            		btn[i*this.row+j].setEnabled(false);
		                btn[i*this.row+j].setBackground(Color.white);
		                btn[i*this.row+j].setBorder(null);
	            	} else {
	            		if (index == myReservedSeat) {
	            			// ���� ������ �ڸ� �� ��
	            			btn[i*this.row+j].setEnabled(true);
			                btn[i*this.row+j].setBackground(Color.orange);
		            		btn[i*this.row+j].setText(String.valueOf(index++));
		            		btn[i*this.row+j].setStatus(1);
	            		} else if (IntStream.of(otherReservedSeats).anyMatch(x -> x == iidx)) {
	            			// �ٸ������ ������ �ڸ��� ��
	            			btn[i*this.row+j].setEnabled(true);
			                btn[i*this.row+j].setBackground(Color.gray);
		            		btn[i*this.row+j].setText(String.valueOf(index++));
		            		btn[i*this.row+j].setStatus(2);
	            		} else {
	            			// �� �ڸ��� ��
	            			btn[i*this.row+j].setEnabled(true);
		            		btn[i*this.row+j].setText(String.valueOf(index++));
			                btn[i*this.row+j].setBackground(Color.LIGHT_GRAY);
			                btn[i*this.row+j].setStatus(0);
	            		}
	            	}
	            	btn[i*this.row+j].addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                       for(int i=0; i<maxseat; i++) {
	                          if(e.getSource()==btn[i]) {
	                             new yeyakok(i, Integer.valueOf(btn[i].getText()), roomNum);
	                          }
	                       }
	                    }
	                });
	            	buttonPanel.add(btn[i*this.row+j]);
	             }
	         }
	         add(buttonPanel);
	         
	         buttonPanel.setBounds(10,000,760,400);
	         repaint();     
	   }
	   
	   
	   
	   //-----------------------------------------------------------------------
	   //�����Ͻðڽ��ϱ�?
	   class yeyakok extends JFrame implements ActionListener {
//		  JPanel popPanel;
	      JLabel topLabel;
	      JButton yesbtn;
	      JButton noBtn;
	      int index;
	      int roomNum;
	      int number;
	      public yeyakok(int index, int number, int roomNum) {
	    	  this.index = index;
	    	  this.roomNum = roomNum;
	    	  this.number = number;
	    	  this.setLayout(null);
	    	  
	    	  setSize(300,120);
	          setLocationRelativeTo(null);
	          topLabel = new JLabel();
	          
	          if (btn[index].getStatus() == 0) {
	        	  
	        	  if (myReservedSeat > 0) {
	        		  topLabel.setText("�̹� �� �濡 ������ �ڸ��� �ֽ��ϴ�.");
	        		  topLabel.setFont(new Font("HY�߰��", Font.PLAIN, 12));
	        		  topLabel.setBounds(45,20,210,40);
			          noBtn = new JButton("���ư���");
			          noBtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
			          noBtn.setForeground(Color.BLACK);
			          noBtn.setBackground(Color.LIGHT_GRAY);
			          noBtn.addActionListener(this);
			          noBtn.setBorderPainted(false);
			          noBtn.setBounds(100,70,100,20);
			          this.add(topLabel);
			          this.add(noBtn);
			          this.setUndecorated(true);
	        	  } else {
	        		  topLabel.setText(number+" �� �¼��� �����Ͻðڽ��ϱ�?");
	        		  topLabel.setFont(new Font("HY�߰��", Font.PLAIN, 12));
		        	  
			          yesbtn = new JButton("��");
			          yesbtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
			          yesbtn.setForeground(Color.white);
			          yesbtn.setBackground(new Color(135,77,162));
			          yesbtn.addActionListener(this);
			          yesbtn.setBorderPainted(false);
			          
			          noBtn = new JButton("�ƴϿ�");
			          noBtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
			          noBtn.setForeground(Color.BLACK);
			          noBtn.setBackground(Color.LIGHT_GRAY);
			          noBtn.addActionListener(this);
			          noBtn.setBorderPainted(false);
			          
			          topLabel.setBounds(60,30,200,20);
			          yesbtn.setBounds(20,70,80,20);
			          noBtn.setBounds(200,70,80,20);
			          this.add(topLabel);
			          this.add(yesbtn);
			          this.add(noBtn);
			          this.setUndecorated(true);
	        	  }
	          } else if (btn[index].getStatus() == 1) {
	        	  topLabel.setText(number+" �� �¼��� ���� ����Ͻðڽ��ϱ�?");
	        	  topLabel.setFont(new Font("HY�߰��", Font.PLAIN, 12));
	        	  
		          yesbtn = new JButton("��");
		          yesbtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
		          yesbtn.setForeground(Color.white);
		          yesbtn.setBackground(new Color(135,77,162));
		          yesbtn.addActionListener(this);
		          yesbtn.setBorderPainted(false);
		          
		          noBtn = new JButton("�ƴϿ�");
		          noBtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
		          noBtn.setForeground(Color.BLACK);
		          noBtn.setBackground(Color.LIGHT_GRAY);
		          noBtn.addActionListener(this);
		          noBtn.setBorderPainted(false);
		          
		          topLabel.setBounds(50,30,200,20);
		          yesbtn.setBounds(20,70,80,20);
		          noBtn.setBounds(200,70,80,20);
		          this.add(topLabel);
		          this.add(yesbtn);
		          this.add(noBtn);
		          this.setUndecorated(true);
	          } else {
	        	  if (isAdmin) {
	        		  topLabel.setText(number+" �� �¼��� ���� ����Ͻðڽ��ϱ�?");
		        	  topLabel.setFont(new Font("HY�߰��", Font.PLAIN, 10));
		        	  
			          yesbtn = new JButton("��");
			          yesbtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
			          yesbtn.setForeground(Color.white);
			          yesbtn.setBackground(new Color(135,77,162));
			          yesbtn.addActionListener(this);
			          yesbtn.setBorderPainted(false);
			          
			          noBtn = new JButton("�ƴϿ�");
			          noBtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
			          noBtn.setForeground(Color.BLACK);
			          noBtn.setBackground(Color.LIGHT_GRAY);
			          noBtn.addActionListener(this);
			          noBtn.setBorderPainted(false);
			          
			          topLabel.setBounds(50,30,200,20);
			          yesbtn.setBounds(20,70,80,20);
			          noBtn.setBounds(200,70,80,20);
			          this.add(topLabel);
			          this.add(yesbtn);
			          this.add(noBtn);
			          this.setUndecorated(true);
//			          yesbtn = new JButton("��");
//			          yesbtn.addActionListener(this);
//			          noBtn = new JButton("�ƴϿ�");
//			          noBtn.addActionListener(this);
//			          add(topLabel,BorderLayout.NORTH);
//			          add(yesbtn,BorderLayout.WEST);
//			          add(noBtn,BorderLayout.EAST);
	        	  } else {
	        		  topLabel.setText("�̹� �� �濡 ������ �ڸ��� �ֽ��ϴ�.");
	        		  topLabel.setFont(new Font("HY�߰��", Font.PLAIN, 10));
	        		  topLabel.setBounds(45,20,210,40);
			          noBtn = new JButton("���ư���");
			          noBtn.setFont(new Font("HY�߰��", Font.PLAIN, 12));
			          noBtn.setForeground(Color.BLACK);
			          noBtn.setBackground(Color.LIGHT_GRAY);
			          noBtn.addActionListener(this);
			          noBtn.setBorderPainted(false);
			          noBtn.setBounds(100,70,100,20);
			          this.add(topLabel);
			          this.add(noBtn);
			          this.setUndecorated(true);
//	        		  topLabel.setText("�̹� ����� �ڸ��Դϴ�.");
//			          add(topLabel,BorderLayout.CENTER);
//	        		  setSize(250, 100);
	        	  }
	          }
	          this.setBackground(Color.white);
	          setVisible(true);
	         
	          
	      }

	      @Override
	      public void actionPerformed(ActionEvent e) {
	    	  if(e.getSource()==yesbtn) {
	    		  if (btn[index].getStatus() == 0) {
//	    			  btn[index].addMouseListener(this);
			          btn[index].setBackground(Color.orange);
			          hc.postReserveRoom(this.roomNum, this.number);
			          btn[index].setStatus(1);
			          myReservedSeat = this.number;
			          new ok("������ �Ϸ�Ǿ����ϴ�.");
	    		  } else if (btn[index].getStatus() == 1) {
//	    			  btn[index].addMouseListener(this);
			          btn[index].setBackground(Color.blue);
			          hc.deleteReserveRoom(this.roomNum, this.number);
			          btn[index].setStatus(0);
			          myReservedSeat = 0;
			          new ok("������ ��ҵǾ����ϴ�.");
	    		  } else {
	    			  if (isAdmin) {
				          btn[index].setBackground(Color.blue);
				          hc.deleteReserveRoom(this.roomNum, this.number);
				          btn[index].setStatus(0);
				          new ok("������ ��ҵǾ����ϴ�.");
				          
	    			  }
	    		  }
	    		  
	            
	            dispose();
	         } 
	         if(e.getSource()==noBtn) {
	            dispose();
	         }
	      }
	   }
	      
	      //----------------------------------------------------

	      class ok extends JFrame{
	         
	         JLabel lb;
	         JButton okButton;
	         public ok(String message) {
	          setSize(300,120);
//	          this.setBounds(100,100,30,30);
//	          this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	          this.setLocationRelativeTo(null);
	          
	          setLayout(null);
	          lb=new JLabel(message);
	          lb.setFont(new Font("HY�߰��", Font.PLAIN, 12));
	          lb.setBounds(85,20,130,20);
//	          lb.setHorizontalAlignment(JLabel.CENTER);
	          okButton=new JButton("Ȯ��");
	          okButton.setFont(new Font("HY�߰��", Font.PLAIN, 12));
	          okButton.setForeground(Color.BLACK);
	          okButton.setBackground(Color.LIGHT_GRAY);
	          okButton.setBorderPainted(false);
	          okButton.setBounds(100,85,100,20);
	          okButton.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               
	               dispose();
	            }
	         });
	          
	          add(okButton);
	          add(lb);
	          setUndecorated(true);
	          
	          setVisible(true);
	         }
	      }


	//=---------------------------------------------------------
	
	class MyButton extends JButton {
		int status;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}
		
	}
	      //--------------------------------------------------------


	}