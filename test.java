package tp2;


import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;

import edu.princeton.cs.algs4.StdOut;

public class test {
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
	public static double  evaluateRPN(String expression){
		String tokens[] = expression.split(" ");
		double result = evalExpression(tokens);
		return result;
		}
	public static void testEvaluateRPN()
	{
	while (StdIn.hasNextLine()){
	String line = StdIn.readLine();
	double z =  evaluateRPN(line);
	StdOut.println(z);
	}
	}
	public static void main(String[] args) {
		testEvaluateRPN();
	}
}
