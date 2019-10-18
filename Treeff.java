

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.Arrays;
import pt.ualg.fct.aed.Colors;
import pt.ualg.fct.aed.Queue;
import pt.ualg.fct.aed.RegularExpressions;

 abstract class Tree11 {
	public abstract int value();
	public abstract Tree11 left();
	public abstract Tree11 right();
	public abstract int size();
	public abstract boolean isEmpty();
	public abstract boolean issubtree(Tree11 a);
	public abstract int evenMinusOdd();
}
  class Empty1 extends Tree11{

	@Override
	public int value() {
		throw new NoSuchElementException();
	}

	@Override
	public Tree11 left() {
		throw new NoSuchElementException();
	}

	@Override
	public Tree11 right() {
		throw new NoSuchElementException();
	}

	@Override
	public boolean issubtree(Tree11 a) {
		return isEmpty() && a.isEmpty();
	}

	@Override
	public int evenMinusOdd() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	@Override
	public String toString() {
		return "()";
	}
	
}
  class Cons1 extends Tree11{
	private final int value;
	private final Tree11 left;
	private final Tree11 right;
	private final int size;
	public Cons1(int value,Tree11 left,Tree11 right) {
		this.value=value;
		this.left=left;
		this.right=right;
		this.size=1+left.size()+right.size();
	}
	@Override
	public int value() {
		return value;
	}
	@Override
	public Tree11 left() {
		return left;
	}
	@Override
	public Tree11 right() {
		return right;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return true;
	}
	@Override
	public String toString() {
		return "("+value+left.toString()+right.toString()+")";
	}
	@Override
	public int evenMinusOdd() {
		if (value%2==0)
			return right.evenMinusOdd()+left.evenMinusOdd()+value;
		else
			return right.evenMinusOdd()+left.evenMinusOdd()-value; 
	}
	@Override
	public boolean issubtree(Tree11 a) {
		return (!isEmpty() && a.isEmpty()) ? true : (this.right.issubtree(a.right()) && this.left.issubtree(a.left()));
	}
}
public class Treeff
{
  public static final String REGEX_TOKEN = "\\-?\\d+|\\(|\\)";

  protected static String newLine = System.getProperty("line.separator");
  public final boolean SHOW_FRAMES = false;
  public final int COLOR_FRAMES = Colors.GREEN;
  public final int COLOR_VALUES = Colors.BLACK;
  public final int COLOR_TEXT = Colors.YELLOW;
  public final int COLOR_LINES = Colors.DARKRED;

  public static Tree11 fromString(String s)
  {
    String[] tokens = RegularExpressions.groups(s, REGEX_TOKEN);
    Queue<String> queue = new Queue<>();
    Arrays.visit(tokens, x -> queue.enqueue(x));
//    for (String x : tokens)
//      queue.enqueue(x);
    return fromQueue(queue);
  }

  public static Tree11 fromQueue(Queue<String> q)
  {
    Tree11 result = new Empty1();
    assert "(".equals(q.front());
    q.dequeue();
    if (!")".equals(q.front()))
    {
      int x = Integer.parseInt(q.dequeue());
      Tree11 left = fromQueue(q);
      Tree11 right = fromQueue(q);
      result = new Cons1(x, left, right);
    }
    assert ")".equals(q.front());
    q.dequeue();
    return result;
  }
  public static void testHeight()
  {
    while (StdIn.hasNextLine())
    {
      Tree11 t = fromString(StdIn.readLine());
      int z = t.evenMinusOdd();
      StdOut.println(z);
    }
  }
  public static void testsub()
  {
    while (StdIn.hasNextLine())
    {
      Tree11 t = fromString(StdIn.readLine());
      Tree11 a = fromString(StdIn.readLine());
      boolean z = a.issubtree(t);
      StdOut.println(z);
    }
  }
  public static void main(String[] args) {
	    char choice = 'B';
	    int x = 0;
	    if (args.length > 0)
	      choice = args[0].charAt(0);
	    if (choice == 'A')
	    	testHeight();
	    else if (choice == 'B')
	    	testsub();
	    	
  }
}