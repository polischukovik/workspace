import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	
	private NavigableSet<Point> grid = new TreeSet<>(Point.pointComparator);
	
	private Rectangle focus = null;

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
	
    private static class Rectangle {
		
		final int x;
		final int y;
		final int width;
		final int heigth;
		final Color color;

		public Rectangle(int x, int y, int width, int heigth, Color color) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.heigth = heigth;
			this.color = color;			
		}
		
	}

	private final LinkedList<Line> lines = new LinkedList<Line>();
	private final LinkedList<Rectangle> rec = new LinkedList<>();
	
	public void changeFocus(int x1, int x2) {
		if(focus != null){
			Rectangle fil = new Rectangle(focus.x-1, focus.y-1, 3, 3, getBackground());
			rec.remove(focus);
			rec.add(fil);
		}
		
		rec.removeIf( n -> n.color == getBackground() );
		
		focus = new Rectangle(x1-1, x2-1, 3, 3, Color.GRAY);
		rec.add(focus);
		repaint();
	}
	

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
		
		for (Rectangle r : rec) {
			g.setColor(r.color);
			g.drawRect(r.x, r.y, r.width, r.heigth);
		}
	}

	public void createGrid(int pixelratio) {
		Point.yRegisters = (int) Math.pow(10, String.valueOf(dimScreenSizeY).length());
		for(int i = 0; i < dimScreenSizeX; i+=pixelratio){			   
	        for(int j = 0; j < dimScreenSizeY; j+=pixelratio){       
		        this.addLine(i, j, i, j, Color.GRAY);
		        grid.add(new Point(i, j));		        
			}
		}
		System.out.println(grid.size());
	}

	public Point mouseGridCoords(MouseEvent e) {
		if(!this.contains(e.getPoint())){
			return new Point(0,0);
		}
		Point mousePosition = new Point(e.getX(),e.getY());
		Point p = grid.floor(mousePosition);		
		System.out.println();
		return new Point((int)p.getX(),(int)p.getY());
	}

}