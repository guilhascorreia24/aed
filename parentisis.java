
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.Stack;

public class parentisis {
	public static boolean para(Stack<String> d) {
		Stack<String> h=new Stack<String>();
		for(String g:d) {
			if(g=="(" || g=="{" || g=="[") {h.push(g);}
			if(g==")" && h.top()=="(") {
				h.pop();
				}
			else if(g=="}" && h.top()=="{"){
				h.pop();
				}
			else if(g=="]" && h.top()=="]"){
				h.pop();
				}
			else {
				return false;
			}
			
		}
		return true;
	}
	public static void test() {
		while(StdIn.hasNextLine()) {
			Stack<String> d=new Stack<String>();
			String h=StdIn.readLine();
			String[] a=h.split("\\d+|\\D+");
			for(String t:a) {
				if(t=="+" || t=="-") {
					d.push(t);
				}
			}
			boolean y=para(d);
			StdOut.print(y);
		}
	}
	public static void main(String[] args) {
		test();
	}
}
