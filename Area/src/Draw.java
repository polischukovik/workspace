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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Draw extends JLayeredPane {
	private int dimScreenSizeX;
	private int dimScreenSizeY;	
	private NavigableSet<GeometryFactory.Point> grid = new TreeSet<>(GeometryFactory.Point.pointComparator);
	private final LinkedList<GeometryFactory.Line> lines = new LinkedList<>();
	private final LinkedList<GeometryFactory.Rectangle> rec = new LinkedList<>();
	private final LinkedList<GeometryFactory.Cros> cross = new LinkedList<>();
	private GeometryFactory.Rectangle focus = GeometryFactory.getRectangle(0, 0, 0, 0, this.getBackground());
	final static Color GRID_COLOR = Color.GRAY;

	public Draw(int dimscreensizex, int dimscreensizey) {
		dimScreenSizeX=dimscreensizex;
		dimScreenSizeY=dimscreensizey;
	}
	/**
	 * LINES
	 */
	public void addLine(int x1, int x2, int x3, int x4, Color color) {
		lines.add(GeometryFactory.getLine(x1, x2, x3, x4, color));
		repaint();
	}

	public void clearLines(LinkedList<GeometryFactory.Line> list) {
		list.clear();
		repaint();
	}
	/**
	 * RECTANGLES
	 */
	public void addRectangle(int x1, int x2, int width, int heigth, Color color) {
		rec.add(GeometryFactory.getRectangle(x1-1, x2-1, width, heigth, color));
		repaint();
	}

	public void clearRectangle(LinkedList<GeometryFactory.Rectangle> list) {
		list.clear();
		repaint();
	}	
	/**
	 * Cross
	 */
	public void addCros(GeometryFactory.Point p ,int size, Color color) {
		cross.add(GeometryFactory.getCros(p, size, color));
		repaint();
	}

	public void clearCros(LinkedList<GeometryFactory.Cros> list) {
		list.clear();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (GeometryFactory.Line line : lines) {
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
		}
		
		for (GeometryFactory.Rectangle r : rec) {
			g.setColor(r.color);
			g.drawRect(r.x, r.y, r.width, r.heigth);
		}
		
		for (GeometryFactory.Cros c : cross) {
			g.setColor(c.color);
			int halfSize = (int) Math.round(c.size / 2.0);
			g.drawLine(c.p.x - halfSize, c.p.y, c.p.x + halfSize, c.p.y);
			g.drawLine(c.p.x, c.p.y - halfSize, c.p.x, c.p.y + halfSize);
		}
	}

	public void createGrid(int pixelratio) {
		for(int i = 0; i < dimScreenSizeX; i+=pixelratio){			   
	        for(int j = 0; j < dimScreenSizeY; j+=pixelratio){       
		        addCros(new GeometryFactory.Point(i, j), 2, Color.gray);	        	
		        grid.add(new GeometryFactory.Point(i, j));		        
			}
		}
		System.out.println(grid.size());
	}
	
	public void changeFocus(int x1, int x2) {
		rec.remove(focus);
		focus = GeometryFactory.getRectangle(x1-1, x2-1, 3, 3, Color.GRAY);
		rec.add(focus);
		repaint();		
	}

	public GeometryFactory.Point mouseGridCoords(MouseEvent e) {
		if(!this.contains(e.getPoint())){
			return GeometryFactory.getPoint(0,0);
		}		
		//System.out.println();
		return grid.floor(GeometryFactory.getPoint(e.getX(),e.getY()));
	}

}