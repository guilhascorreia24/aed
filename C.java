

import java.util.ArrayList;

import java.util.List;
import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.RegularExpressions;

public class C {
	private static String[] binaryOperators = {"+", "-", "*", "/","min","max"};
	private static String[] unaryOperators = {"sqrt","!","sq","inv","~","^"};
	public static void main(String[] args) {
		testInfixFullParenToRPN();
	}
	public static void testInfixFullParenToRPN() {
		while(!StdIn.isEmpty()){
			String line=StdIn.readLine();
			String[] z=infixfullParentorpn(RegularExpressions.tokens(line));
			for(int i=0;i<z.length-1;i++) {
				StdOut.print(z[i]+" ");
			}
			StdOut.println(z[z.length-1]);
		}
	}
	
	private static boolean isNumeric(String s){
		return Character.isDigit(s.charAt(0));
	}
	
	private static String[] infixfullParentorpn(String[] b) {
		List<String> g=new ArrayList<String>();
		Stack<String> operators=new Stack<String>();
		Stack<String> values=new Stack<String>();
		for(int i=0;i<b.length;i++) {
			if(isNumeric(b[i])) {
				values.push(b[i]);
			}
			else if(isOperator(b[i])) {
				operators.push(b[i]);
				if(!values.isEmpty())g.add(values.pop());
			}
			else if("(".equals(b[i])) {}
			else if(")".equals(b[i])) {	
				g.addAll(new_string(operators,values));
			}
		}
		while(!values.isEmpty()) {
			g.add(values.pop());
		}
		String[] k=g.toArray(new String[g.size()]);
		return k;
	}
	
	private static List<String> new_string(Stack<String> operators,Stack<String> values) {
		List<String> g=new ArrayList<String>(); 
		String op=operators.pop();
		if(isOperator(op)) {
			if(!values.isEmpty())g.add(values.pop());
			if(!op.isEmpty())g.add(op);
		}
		return g;
	}
	private static boolean has(String[] a, String x){
		for (int i = 0; i < a.length; i++)
			if (a[i].equals(x))
				return true;
		return false;
	}
	private static boolean isOperator(String s)
	{
	return isBinaryOperator(s) || isUnaryOperator(s);
	}
	private static boolean isBinaryOperator(String s){
		return has(binaryOperators, s);
	}
	private static boolean isUnaryOperator(String s){
		return has(unaryOperators, s);
	}

}