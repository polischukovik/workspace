import java.util.Comparator;

public class Point extends java.awt.Point {
	public static int xRegisters;// = String.valueOf(dimScreenSizeY).length();
	public static Comparator<Point> pointComparator = new Comparator<Point>() {
		
		@Override
		public int compare(Point o1, Point o2) {
			int resA =(int) (o1.getX() * xRegisters + o1.getY());
			int resB =(int) (o2.getX() * xRegisters + o2.getY());
			System.out.println((int)o1.getX()+";"+(int)o1.getY()+" - "+(int)o2.getX()+";"+(int)o2.getY()+" : "+resA+" - "+resB+" = "+(resA-resB));
			return resA-resB;
			
		}
	};
	
	public static Comparator<java.lang.Double> coordinateComparator = new Comparator<java.lang.Double>() {		
		
		@Override
		public int compare(java.lang.Double d1, java.lang.Double d2) {
			// TODO Auto-generated method stub			
			return (int) (d1-d2);
		}
	};
	
	public Point(int yRegisters) {
		super();
		Point.xRegisters = yRegisters;
	}
	public Point(int i, int j) {
		this.x = i; this.y = j;
	}	
	
	
}
