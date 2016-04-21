import java.awt.Color;
import java.util.Comparator;

public class GeometryFactory {
	private GeometryFactory(){
		
	}
	
	public static Line getLine(int x1, int y1, int x2, int y2, Color color){
		return new Line(x1, y1, x2, y2, color);
	}
	
	public static Rectangle getRectangle(int x, int y, int width, int heigth, Color color){
		return new Rectangle( x,  y, width, heigth, color);
	}
	
	public static Cros getCros(Point p, int size, Color color){
		return new Cros(p, size, color);
	}
	
	public static Point getPoint(double i, double j){
		return new Point(i, j);
	}
	
	public static Point getPoint(int i, int j){
		return new Point(i, j);
	}
	
	public static class Line {		
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
	
	public static class Cros {		
		final Point p;
		final int size;
		final Color color;
		public Cros(Point p, int size, Color color) {
			this.p = p;
			this.size = size;
			this.color = color;		
		}
	}
	
    public static class Rectangle {		
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
    
    public static class Point extends java.awt.Point {
    	
    	public final static Comparator<Point> pointComparator = new Comparator<Point>() {
    		
    		@Override
    		public int compare(Point o1, Point o2) {

    			//System.out.println((int)o1.getX() +";"+ (int)o1.getY()+"  " + (int)o2.getX() + ";" + (int)o2.getY() + "  =  "+ a + ";" + b);
    			if((o1.getX() - o2.getX())<0 || (o1.getY() - o2.getY())<0){
    				return -1;
    			}
    			return 1;
    		}
    	};
    	
    	public Point(double i, double j) {
    		this.x = (int) i; this.y = (int) j;
    	}	
    	public Point(int i, int j) {
    		this.x = i; this.y = j;
    	}
    }
}
