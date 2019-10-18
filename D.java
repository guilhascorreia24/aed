
import java.util.ArrayList;


import java.util.List;
import java.util.Stack;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.RegularExpressions;

public class D {
	private static String[] binaryOperators = {"+", "-", "*", "/","min","max"};
	private static String[] unaryOperators = {"sqrt","!","sq","inv","~","^"};
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
	private static int prioridades(String t) {
		int x = 0;
		if(t.equals("-") || t.equals("+"))
			x=1;
		else if(t.equals("/") || t.equals("*")) {
			x=2;
		}
		else if(t.equals("sqrt") || t.equals("sq") ||  t.equals("inv") || t.equals("!") || t.equals("~")) {
			x=6;
		}
		else if( t.equals("max") || t.equals("min") ) {
			x=4;
		}
		else if(t.equals("^")) {
			x=3;
		}
		return x;
	}
	private static String[] infixToRPN(String[] split) {
		List<String> a=new ArrayList<String>();
		Stack<String> fact=new Stack<String>();
		Stack<String> operators=new Stack<String>();
		for(int i=0;i<split.length;i++) {
			if(split[i].equals("!") || split[i].equals("~")) {
				fact.push(split[i]);
			}
			else if(isOperator(split[i])) {
				while(!operators.isEmpty() && prioridades(operators.lastElement())>=prioridades(split[i])) {	
					a.add(operators.pop());
				}
				while(!fact.isEmpty()) 
					a.add(fact.pop());	
				operators.push(split[i]);
			}
			else if(split[i].equals("(")) {
				operators.push(split[i]);
			}
			else if(split[i].equals(")")) {
				while(!operators.isEmpty()) {
					if(!operators.lastElement().equals("("))
						a.add(operators.pop());
					else { 
						operators.pop();
					break;
				}
				}
			}
			else {
				a.add(split[i]);
				while(!fact.isEmpty()) {
					a.add(fact.pop());
				}
			}
		}
		while(!fact.isEmpty()) {
			a.add(fact.pop());
		}
		while(!operators.isEmpty())
			a.add(operators.pop());
		String[] s = a.toArray(new String[a.size()]);
		return s;
	}

	private static void testInfixToRPN() {
		while(StdIn.hasNextLine()) {
			String a=StdIn.readLine();
			String[] b= infixToRPN(RegularExpressions.tokens(a));
			for(int i=0;i<b.length-1;i++) {
				StdOut.print(b[i]+" ");
			}
			StdOut.println(b[b.length-1]);
		}
	}
	public static void main(String[] args) {
		testInfixToRPN();
	}
}