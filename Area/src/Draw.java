import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw extends JComponent {
	private int dimScreenSizeX;
	private int dimScreenSizeY;
	
	private HashSet<Point> grid = new HashSet<Point>();
	private NavigableSet<Double> gridX = new TreeSet<>(Point.coordinateComparator);

	private static class Line {
		
		final int x1;
		final int y1;
		final int x2;
		final int y2;
		final Color color;

		public Line(int x1, int y1, int x2, int y2, Color color) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.color = color;
			
		}
	}

	private final LinkedList<Line> lines = new LinkedList<Line>();

	public Draw(int dimscreensizex, int dimscreensizey) {
		dimScreenSizeX=dimscreensizex;
		dimScreenSizeY=dimscreensizey;
	}

	public void addLine(int x1, int x2, int x3, int x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}

	public void addLine(int x1, int x2, int x3, int x4, Color color) {
		lines.add(new Line(x1, x2, x3, x4, color));
		repaint();
	}

	public void clearLines() {
		lines.clear();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Line line : lines) {
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
		}
	}

	public void createGrid(int pixelratio) {
		int cnt = 0;
		Point.xRegisters = (int) Math.pow(10, String.valueOf(dimScreenSizeX).length());
		for(int i = 0; i < dimScreenSizeX; i+=pixelratio){			   
	        for(int j = 0; j < dimScreenSizeY; j+=pixelratio){       
		        this.addLine(i, j, i, j, Color.GRAY);
		        grid.add(new Point(i, j));		        
			}		        
		}		
//		for(Point p : grid){
//			System.out.println("creating point "+p.getX()+";"+p.getY());
//			
//		}
	}

	public String mouseGridCoords(MouseEvent e) {
		if(!this.contains(e.getPoint())){
			return "x: 0 y:0";
		}
		Point mousePosition = new Point(e.getX(),e.getY());
		Point p = grid.floor(mousePosition);		
		System.out.println();
		return "x: " + p.getX() + "y: " + p.getY() + "  "+ grid.size() ;
	}

}