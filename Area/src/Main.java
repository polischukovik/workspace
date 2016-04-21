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
	    JFrame testFrame = new JFrame();
	    testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    final Draw comp = new Draw(dimScreenSizeX, dimScreenSizeY);
	    comp.setPreferredSize(new Dimension(dimScreenSizeX, dimScreenSizeY));
	    testFrame.getContentPane().add(comp, BorderLayout.CENTER);
	    JPanel buttonsCoordsPanel = new JPanel(new GridLayout(1,2));
	    JPanel buttonsPanel = new JPanel();
	    JButton newLineButton = new JButton("New Line");
	    JButton clearButton = new JButton("Clear");	
	    JLabel coords = new JLabel("Coordinates: ");
	    buttonsPanel.add(newLineButton);
	    buttonsPanel.add(clearButton);
	    buttonsCoordsPanel.add(coords);
	    buttonsCoordsPanel.add(buttonsPanel);
	    testFrame.getContentPane().add(buttonsCoordsPanel, BorderLayout.SOUTH);
	    
	    comp.createGrid(pixelRatio);
	    comp.addMouseMotionListener(new MouseMotionListener () {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = comp.mouseGridCoords(e);
				coords.setText("Coordinates: " + p.getX() + ";" + p.getY());	
				comp.changeFocus(p.x, p.y);
			}

		});
	    newLineButton.addActionListener(new ActionListener() {	    	

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int x1 = (int) (Math.random()*dimScreenSizeX);
	            int x2 = (int) (Math.random()*dimScreenSizeX);
	            int y1 = (int) (Math.random()*dimScreenSizeY);
	            int y2 = (int) (Math.random()*dimScreenSizeY);
	            Color randomColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
	            comp.addLine(x1, y1, x2, y2, randomColor);
	        }
	    });
	    clearButton.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            comp.clearLines();
	        }
	    });
	    testFrame.pack();
	    testFrame.setVisible(true);
	}

}
