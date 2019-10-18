package pratice;
import java.util.Enumeration;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.HashTable;

public class triplesumht implements Iterable<Integer>{
	private static HashTable<Integer,Integer> ht;
	public triplesumht() {
		ht=new HashTable<>();
	}
	@Override
	public Iterator<Integer> iterator() {
		return ht.iterator();
	}
	public static int get(int key) {
		return ht.get(key);
	}
	public static int count(int[] a,int x) {			
		int t=0,u=0;
		for(int i=0;i<a.length;i++) {
			for(int j=i+1;j<a.length;j++) {
				if(!ht.has(a[i]) && ht.)
				ht.put(a[i],a[j]);
			}
		}
		while(ht.iterator().hasNext()) {
			StdOut.println(ht.keys().next());
		}
		return t;
	}
	public static void taskA(int x) {
			ht=new HashTable<Integer,Integer>();
			int[] a=StdIn.readAllInts();
			int n=count(a,x);
			StdOut.println(n);
	}

	public static void main(String [] args) {
		char x='B';
		if(args.length>0) {
			x=args[0].charAt(0);
		}
		if(x=='B') {
			//int x2=StdIn.readInt();
			taskA(20);
		}
	}
	
}

