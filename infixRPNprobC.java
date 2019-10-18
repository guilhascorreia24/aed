

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.RegularExpressions;

public class infixRPNprobC
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
			List<String> output = infixFullParenToRPN(aos);
			StdOut.print(output.get(0));
			for(int i=1;i<output.size();i++)
				StdOut.print(" "+output.get(i));
			StdOut.println();
		}

	}

	public static List<String> infixFullParenToRPN(String[] ss)
	{
		Stack<String> operadores = new Stack<String>();
		List<String> output = new ArrayList<String>();
		for(int i=0;i<ss.length;i++)
		{
			String s = ss[i];
			if(isOperator(s))
				operadores.push(s);
			else
				switch(s){ 
				case "(":
					break;
				case ")":
					output.add(operadores.pop());
					break;
				default:
					output.add(s);
					break;
				}
		}
		return output;

	}

	private static boolean isOperator(String s)
	{
		return (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*") || s.equals("sqrt"));
	}
	
}
