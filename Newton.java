


import edu.princeton.cs.algs4.*;
public class Newton {
	
	private static double r;
	public static double nextguess(double xo,double z) {
		
		double f=Math.pow(xo, 2)-z;
		double fl=2*xo;
		if(xo-(f/fl)!=r) {
			return nextguess(xo-(f/fl),z);
		}
		return xo-(f/fl);
	}
	public static double mysqrt(double z) {
		double x=nextguess(z,z);
		if(z<1)
			x=nextguess(1/z,z);
		return x;
	}
	
	public static void newton() {
		while(!StdIn.isEmpty()) {
			double z=StdIn.readDouble();
			r=Math.sqrt(z);
			StdOut.printf("%.8f %.8f\n",z,mysqrt(z));
		}
	}
	
	public static void main(String[] args) {
		newton();
	}

}
