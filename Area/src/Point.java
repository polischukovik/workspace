import java.util.Comparator;

public class Point extends java.awt.Point {
	public static int yRegisters;// = String.valueOf(dimScreenSizeY).length();
	public static Comparator<Point> pointComparator = new Comparator<Point>() {
		
		@Override
		public int compare(Point o1, Point o2) {
			
			int a = (int) (o1.getX() - o2.getX());
			int b = (int) (o1.getY() - o2.getY());
			
			//System.out.println((int)o1.getX() +";"+ (int)o1.getX()+"  " + (int)o2.getX() + ";" + (int)o2.getY() + "  =  "+ a + ";" + b);
			if(a<0 && b<0){
				return -1;
			}
			return 1;
		}
	};
	
	public Point(int yRegisters) {
		super();
		Point.yRegisters = yRegisters;
	}
	public Point(int i, int j) {
		this.x = i; this.y = j;
	}	
	
	
}
