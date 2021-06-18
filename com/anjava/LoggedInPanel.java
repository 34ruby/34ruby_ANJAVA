package com.anjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import org.json.JSONArray;

public class LoggedInPanel extends JPanel{
	int boxCount;
	JButton[] reserveBtn;
	JPanel btnPanel = new JPanel();
	JTextArea reserve = new JTextArea();
	JScrollPane scroll = new JScrollPane(btnPanel,
										 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	
	public LoggedInPanel(JSONArray roomsData){
		this.boxCount = roomsData.length();
		this.reserveBtn = new JButton[boxCount];
	
		//PanelSetting
		btnPanel.setPreferredSize(new Dimension(750, (50 / 4 + 1) * 100));
		scroll.setPreferredSize(new Dimension(750, 405));
		this.setBounds(6, 49, 750, 405);
			
		for(int i = 0; i < roomsData.length(); i++) {
			int roomNum = roomsData.getJSONObject(i).getInt("roomNum");
			int maxSit = roomsData.getJSONObject(i).getInt("maxSit");
			int remainSit = roomsData.getJSONObject(i).getInt("remainSit");
			boolean isUserIncluded = roomsData.getJSONObject(i).getBoolean("isUserIncluded");
			String btnText = "<HTML>" + "방번호: " + roomNum + "<br>" + "남은 좌석: " + remainSit + "/" + maxSit;
			if (!roomsData.getJSONObject(i).isNull("resetDate")) {
//				btnText.concat("<br>" + "좌석 초기화: " + roomsData.getJSONObject(i).getString("resetDate") + "</HTML>");
				btnText += "<br>" + "좌석 초기화: " + roomsData.getJSONObject(i).getString("resetDate") + "</HTML>";
				System.out.println(btnText);

			} else {
				btnText.concat("</HTML>");
			}
			reserveBtn[i] = new JButton(btnText);
			if (isUserIncluded) {
				reserveBtn[i].setBackground(Color.orange);
			}else {				
				reserveBtn[i].setBackground(Color.gray.brighter());
			}
			reserveBtn[i].setBorder(null);
			reserveBtn[i].setPreferredSize(new Dimension(130,100));
			btnPanel.add(reserveBtn[i]);
			scroll.setBorder(null);
			add(scroll);
		
			
			reserve.setBounds(615,54,160,400);
			reserve.setBackground(Color.gray.brighter());
		}
	}
}