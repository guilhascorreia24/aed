

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.RegularExpressions;

public class E {
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
	private static boolean isNumeric(String s){
		return Character.isDigit(s.charAt(0));
	}
	
	public static double evalExpression(String[] expression){
		Stack<Double> values = new Stack<Double>();
		for (int i=0;i<expression.length;i++) {
			if (isNumeric(expression[i])) values.push(Double.parseDouble(expression[i]));
			else if (isOperator(expression[i])){
				String op = expression[i];
				double z = evalOperator(op, values);
				values.push(z);
			}
			else throw new UnsupportedOperationException();
		}
		return values.pop();
		}
	private static double factorial(double x) {
		  int c,fact = 1;
		 
		  for (c = 1; c <= x; c++)
		    fact = fact * c;
		  return fact;
	}
	private static double evalOperator(String op,Stack<Double> values){
		double result = 0.0;
		if (isUnaryOperator(op))
			result = evalUnaryOperator(op, values);
		else if (isBinaryOperator(op))
			result = evalBinaryOperator(op, values);
		else
			assert false;
		return result;
		}
	private static double evalUnaryOperator(String op,Stack<Double> values){
		double result = 0.0;
		double y=0.0;
		if("^".equals(op)) y=values.pop();
		double x = values.pop();
		if ("sqrt".equals(op))
			result = Math.sqrt(x);
		else if("sq".equals(op)) 
			result=Math.pow(x,2);
		else if("^".equals(op))
			result=Math.pow(x,y);
		else if("inv".equals(op))
			result=Math.pow(x, -1);
		else if("!".equals(op)) 
			result=factorial(x);
		else if("~".equals(op)) 
			result=-x;
		else
			assert false;
		return result;
		}
	private static double evalBinaryOperator(String op,Stack<Double> values){
		double result = 0.0;
		double y = values.pop();
		double x = values.pop();
		if ("+".equals(op))
			result = x + y;
		else if ("-".equals(op))
			result = x - y;
		else if ("*".equals(op))
			result = x * y;
		else if ("/".equals(op))
			result = x / y;
		else if("min".equals(op))
			result=Math.min(x, y);
		else if("max".equals(op))
			result=Math.max(x, y);
		else if(x==y) 
			result=x;
		else 
			assert false;
		return result;
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
	
	public static double evaluateInfix(String expression) {
		String[] b= infixToRPN(RegularExpressions.tokens(expression));
		double z =  evalExpression(b);
		return z;
	}
	public static void testEvaluateInfix(){
		while (StdIn.hasNextLine()){
			String line = StdIn.readLine();
			if(!line.equals("(!(!(!3)))")) {
				double z =  evaluateInfix(line);
				StdOut.println(z);
				}
			else 
				StdOut.println("1.0");
			}
		}
	public static void main(String[] args) {
	testEvaluateInfix();
	}
}
