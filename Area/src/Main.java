import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayoutInfo;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	
	final static int dimScreenSizeX = 800;
	final static int dimScreenSizeY = 600;
	final static int pixelRatio = 8;

	public static void main(String[] args) {
	    MainForm form = new MainForm(dimScreenSizeX, dimScreenSizeY, pixelRatio);
	}

}
