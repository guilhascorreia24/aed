import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Lukasiewicz
{
	public static void main(String[] args)
	{
		testLukasiewicz();
	}

	public static void testLukasiewicz()
	{
		while(!StdIn.isEmpty())
		{
			String input = StdIn.readString();
			StdOut.println(polish(input));
		}
	}

	public static boolean polish(String s)
	{
		Stack<Boolean> result=new Stack<Boolean>();
		for(int i=s.length()-1;i>=0;i--)
		{
			boolean x,y;
			char c=s.charAt(i);
			switch(c)
			{
			case 'A':
				x=result.pop();
				y=result.pop();
				result.push(or(x,y));
				break;
			case 'K':
				x=result.pop();
				y=result.pop();
				result.push(and(x,y));
				break;
			case 'I':
				x=result.pop();
				y=result.pop();
				result.push(implica(x,y));
				break;
			case 'E':
				x=result.pop();
				y=result.pop();
				result.push(equivalente(x,y));
				break;
			case 'D':
				x=result.pop();
				y=result.pop();
				result.push(nand(x,y));
				break;
			case 'N':
				x=result.pop();
				result.push(not(x));
				break;
			case 'O':
				result.push(false);
				break;
			default: break;
			}
		}
		return result.pop();
	}

	public static boolean or(Boolean x, Boolean y)
	{
		return (x || y);
	}
	
	public static boolean and(Boolean x, Boolean y)
	{
		return (x && y);
	}
	
	
	public static boolean implica(Boolean x, Boolean y)
	{
		return (!x || y);
	}

	
	public static boolean equivalente(Boolean x, Boolean y)
	{
		return ( (x && y) || (!x && !y) );
	}
	
	
	public static boolean nand(Boolean x, Boolean y)
	{
		return (!(!x && !y));
	}
	
	
	public static boolean not(Boolean x)
	{
		return !x;
	}
}
