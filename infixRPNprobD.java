

import java.util.ArrayList;

import java.util.List;
import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.RegularExpressions;

public class infixRPNprobD
{
	public static void main(String[] args)
	{
		testinfixRPN();
	}

	public static void testinfixRPN()
	{
		while (StdIn.hasNextLine())
		{
			String line = StdIn.readLine();
			String[] tokens = RegularExpressions.tokens(line);
			String [] aos = new String[tokens.length];
			for(int i=0;i<tokens.length;i++)
			{
				aos[i]=tokens[i];
			}
			List<String> output = infixToRPN(tokens);
			StdOut.print(output.get(0));
			for(int i=1;i<output.size();i++)
				StdOut.print(" "+output.get(i));
			StdOut.println();
		}

	}
	
	public static List<String> infixToRPN(String[] ss)
	{
		Stack<String> operadores = new Stack<String>();
		List<String> output = new ArrayList<String>();
		for(int i=0;i<ss.length;i++)
		{
			String s = ss[i];
			if(isOperator(s))
			{				
				while(!operadores.isEmpty() && prioridade(operadores.lastElement(),s))
					output.add(operadores.pop());
				operadores.push(s);
			}
			else
				switch(s){ 
				case "(":
					operadores.push(s);
					break;
				case ")":
					while(!operadores.isEmpty())
						if(operadores.lastElement().compareTo("(")!=0)
							output.add(operadores.pop());
						else
						{
							operadores.pop();
							break;
						}
					break;
				default:
					output.add(s);
					break;
				}
		}

		while(!operadores.isEmpty())
			output.add(operadores.pop());
		return output;

	}
	private static boolean prioridade(String top, String s)
	{
		return value(top)>=value(s);
	}

	private static int value(String top)
	{
		switch(top){
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
			return 2;
		case "sqrt":
			return 3;
		default:
			return -1;
		}
	}

	private static boolean isOperator(String s)
	{
		return (s.compareTo("+")==0 || s.compareTo("-")==0 || s.compareTo("/")==0 || s.compareTo("*")==0 || s.compareTo("sqrt")==0);
	}
}
