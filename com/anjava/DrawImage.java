package com.anjava;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

class DrawImage extends JLabel {
	
	public static void main(String[] args) throws IOException {
		File file = new File("image/mainLogin2.jpg");
		BufferedImage img = ImageIO.read(file);
		
	}

}
