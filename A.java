
import java.text.DecimalFormat;

import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
	class Point{
		private int x,y,z;
		public Point(int x, int y, int z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}	
		@Override
		public int hashCode() {
			return  (x+1000*y)+1000000*z;
		}
		@Override
		public boolean equals(Object b){
			Point a=(Point) b;
			if(hashCode()==a.hashCode()) 
				return true;
			return false;
		}
		@Override
		public String toString() {
			return "["+x+" "+y+" "+z+"]";
		}
	}
	public class A{
		public static void test() {
			Scanner s=new Scanner(System.in);
			int lines=Integer.parseInt(s.nextLine());
			Hashtable<Point,Integer> a=new Hashtable<Point,Integer>();
			int i=0,j=0;
			while(i<lines) {
				String h=s.nextLine();
				String[] p1=h.split(" ");
				Point b=new Point(Integer.parseInt(p1[0]),Integer.parseInt(p1[1]),Integer.parseInt(p1[2]));
				Point c=new Point(Integer.parseInt(p1[3]),Integer.parseInt(p1[4]),Integer.parseInt(p1[5]));
				if(!a.containsKey(b)) {
					a.put(b,j++);
				}
				else {
					a.put(b,(int)a.get(b));
				}
				if(!a.containsKey(c)) {
					a.put(c,j++);
				}
				else {
					a.put(c,(int)a.get(c));
				}
				i++;
			}
			s.close();
			Enumeration<Point> p2= a.keys();
			Point y=p2.nextElement();
			System.out.println(j+"\n"+y.toString()+"\n"+a.get(y));
		}
		public static void main(String[] args) {
			long start = System.currentTimeMillis();
			test();
			long end = System.currentTimeMillis();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		}
	}
