import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
	class Sombra{
		private int x,y,z;
		public Sombra(int x, int y,int z) {
			this.x=x;
			this.y=y;
			this.z=z;

		}
		@Override
		public int hashCode() {
			return  (x+1000*y)+1000000*0;
		}
		@Override
		public boolean equals(Object b){
			Sombra a=(Sombra) b;
			if(hashCode()==a.hashCode()) 
				return true;
			return false;
		}
		@Override
		public String toString() {
			return "["+x+" "+y+" "+z+"]";
		}
		
	}
	public class Floor{
		public static void test() {
			Scanner s=new Scanner(System.in);
			int lines=Integer.parseInt(s.nextLine());
			Hashtable<Sombra, Integer> a=new Hashtable<Sombra,Integer>();
			int i=0,j=0;
			while(i<lines) {
				String h=s.nextLine();
				String[] p1=h.split(" ");
				Sombra b=new Sombra(Integer.parseInt(p1[0]),Integer.parseInt(p1[1]),Integer.parseInt(p1[2]));
				Sombra c=new Sombra(Integer.parseInt(p1[3]),Integer.parseInt(p1[4]),Integer.parseInt(p1[5]));
				if(!a.containsKey(b)) {a.put(b,j++);}
				if(!a.containsKey(c)) {a.put(c,j++);}
				i++;
			}
			s.close();
			Enumeration<Sombra> p= a.keys();
			Sombra y=p.nextElement();
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
