

import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.Arrays;
import pt.ualg.fct.aed.Bag;
import pt.ualg.fct.aed.Colors;
import pt.ualg.fct.aed.Queue;
import pt.ualg.fct.aed.RegularExpressions;

abstract class AVLTree<T extends Comparable<T>>
{
  public abstract int size();
public abstract boolean isEmpty();
  public abstract boolean has(T x);
  
  public abstract T value(); // requires !isEmpty();
  public abstract AVLTree<T> left(); // requires !isEmpty();
  public abstract AVLTree<T> right(); // requires !isEmpty();
  
  public abstract AVLTree<T> put(T x);
  public abstract AVLTree<T> delete(T x);
  public abstract AVLTree<T> leftRotate();
  public abstract AVLTree<T> RightRotate();
  

  // Utilities

  public static <U extends Comparable<U>> AVLTree<U> fromArray(U[] a)
  {
    AVLTree<U> result = new AVLEmpty<>();
    for (U x : a)
      result = result.put(x);
    return result;
  }

  public static AVLTree<Integer> fromInts(int[] a)
  {
    AVLTree<Integer> result = new AVLEmpty<>();
    for (int x : a)
      result = result.put(x);
    return result;
  }

  protected static String newLine = System.getProperty("line.separator"); // used in derived classes.

  public final boolean SHOW_FRAMES = false;
  public final int COLOR_FRAMES = Colors.GREEN;
  public final int COLOR_VALUES = Colors.BLACK;
  public final int COLOR_TEXT = Colors.YELLOW;
  public final int COLOR_LINES = Colors.DARKRED;

public T value;

  // Exercises

  public abstract T fold(BinaryOperator<T> f, T zero);
  public abstract boolean isLeaf();
  public abstract int countLeaves();
  public abstract AVLTree<T> trimLeaves();
  public abstract AVLTree<T> cut(int n);

  // --------------------
  // Functions to be programmed by you in derived classes

  public abstract boolean isomorphs(AVLTree<T> u);
  public abstract int height();
  public abstract int unbalance();
  public abstract AVLTree<T> populate(T x);
  public abstract boolean isHeap();
  public abstract AVLTree<T> setLeaves(T x);
  public abstract boolean isDegenerate();
  public abstract boolean isZig();
  public abstract boolean isZag();
  public abstract AVLTree<T> degenarate(AVLTree<T> u);
  public abstract int countAtLevel(int x);
  public abstract boolean issubtree(AVLTree<T> a) ;
  public boolean isZigzag()
  {
    return isZig() || isZag();
  }

  // -------------------

  // Functions to create zigzag trees

  public static <U extends Comparable<U>> AVLTree<U> zigzagFrom(U[] a)
  {
    return zigFrom(a, 0);
  }

  public static <U extends Comparable<U>> AVLTree<U> zagzigFrom(U[] a)
  {
    return zagFrom(a, 0);
  }

  public static <U extends Comparable<U>> AVLTree<U> zigFrom(U[] a, int d)
  {
    return d == a.length ? new AVLEmpty<>() : new AVLCons<>(a[d], zagFrom(a, d + 1), new AVLEmpty<>());
  }

  public static <U extends Comparable<U>> AVLTree<U> zagFrom(U[] a, int d)
  {
    return d == a.length ? new AVLEmpty<>() : new AVLCons<>(a[d], new AVLEmpty<>(), zigFrom(a, d + 1));
  }
}
class AVLEmpty<T extends Comparable<T>> extends AVLTree<T>
{
  @Override
  public T value()
  {
    throw new NoSuchElementException();
  }

  @Override
  public int size()
  {
    return 0;
  }

  @Override
  public boolean isEmpty()
  {
    return true;
  }

  @Override
  public boolean has(T x)
  {
    return false;
  }

  @Override
  public String toString()
  {
    return "()";
  }

  @Override
  public AVLTree<T> left()
  {
	    throw new NoSuchElementException();
  }

  @Override
  public AVLTree<T> right()
  {
    throw new NoSuchElementException();
  }

  @Override
  public AVLTree<T> put(T x)
  {
    return new AVLCons<>(x, this, this);
  }



  @Override
  public T fold(BinaryOperator<T> f, T zero)
  {
    return zero;
  }

  @Override
  public boolean isLeaf()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public int countLeaves()
  {
    return 0;
  }

  @Override
  public AVLTree<T> trimLeaves()
  {
    return this;
  }

  @Override
  public AVLTree<T> cut(int n)
  {
    return this;
  }

  @Override
  public boolean isomorphs(AVLTree<T> u)
  {
	  return this.isEmpty() && u.isEmpty();
  }

  @Override
  public int height()
  {
	  return 0;
  }

  @Override
  public int unbalance()
  {
	  return 0;
  }

  @Override
  public AVLTree<T> populate(T x)
  {
	  return this;
  }

  @Override
  public boolean isHeap()
  {
	  return true;
  }

  @Override
  public AVLTree<T> setLeaves(T x)
  {
	  return this;
  }

  @Override
  public boolean isDegenerate()
  {
	  return true;
  }

  @Override
  public boolean isZig()
  {
	  return true;
  }

  @Override
  public boolean isZag()
  {
	  return true;
  }

@Override
public AVLTree<T> degenarate(AVLTree<T> u) {
	return this;
}

@Override
public int countAtLevel(int x) {
	return 0;
}


@Override
public boolean issubtree(AVLTree<T> a) {
	return isEmpty() && a.isEmpty();
}

@Override
public AVLTree<T> delete(T x) {
	return this;
}

@Override
public AVLTree<T> leftRotate() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public AVLTree<T> RightRotate() {
	// TODO Auto-generated method stub
	return null;
}  
}
class AVLCons<T extends Comparable<T>> extends AVLTree<T>
{
  private final T value;
  private  AVLTree<T> left;
  private AVLTree<T> right;
  private final int size;
  private final int height;

  public AVLCons(T value, AVLTree<T> left, AVLTree<T> right)
  {
    this.value = value;
    this.left = left;
    this.right = right;
    this.size = 1 + left.size() + right.size();
    height=this.height();
  }

  @Override
  public T value()
  {
    return value;
  }

  @Override
  public int size()
  {
    return size;
  }

  @Override
  public AVLTree<T> left()
  {
    return left;
  }

  @Override
  public AVLTree<T> right()
  {
    return right;
  }

  @Override
  public boolean isEmpty()
  {
    return false;
  }

  @Override
  public boolean has(T x)
  {
    boolean result = true;
    int cmp = x.compareTo(value);
    if (cmp < 0)
      result = left.has(x);
    else if (cmp > 0)
      result = right.has(x);
    return result;
  }
  

  @Override
  public AVLTree<T> put(T x)
  {
	 if(this.value.compareTo(x)>0) {
		 left=left.put(x);
	 }
	 else if(this.value.compareTo(x)<0) {
		 right=right.put(x);
	 }
	 else {
		 return this;
	 }
	 int balance=this.unbalance();
	 
	 if( balance>1 && x.compareTo(value)<0) {
		 return this.RightRotate();
	 }
	 if( balance<-1 && x.compareTo(value)>0) {
		 return this.leftRotate();
	 }
	 if( balance>1 && x.compareTo(value)>0) {
		 this.left=this.left.leftRotate();
		 return this.RightRotate();
	 }
	 if( balance<-1 && x.compareTo(value)<0) {
		 this.right=this.right.RightRotate();
		 return this.leftRotate();
	 }
	 return this;
  }
  @Override
  public AVLTree<T> leftRotate(){
	  AVLCons<T> y=(AVLCons<T>) this.right;
	  AVLCons<T> t=(AVLCons<T>) y.left;
	  y.right=this;
	  this.left=t;
	  return y; 
  }
  @Override
  public AVLTree<T> RightRotate(){
	  AVLCons<T> y=(AVLCons<T>) this.left;
	  AVLCons<T> t=(AVLCons<T>) y.right;
	  y.left=this;
	 this.right=t;
	  return y; 
  }
  @Override
  public String toString()
  {
    return "(" + value + left.toString() + right.toString() + ")";
  }



  @Override
  public T fold(BinaryOperator<T> f, T zero)
  {
    return f.apply(f.apply(value, left.fold(f, zero)), right.fold(f, zero));
  }

  @Override
  public boolean isLeaf()
  {
    return left.isEmpty() && right.isEmpty();
  }

  @Override
  public int countLeaves()
  {
    return isLeaf() ? 1 : left.countLeaves() + right.countLeaves();
  }

  @Override
  public AVLTree<T> trimLeaves()
  {
    return isLeaf() ? new AVLEmpty<>() : new AVLCons<T>(value, left.trimLeaves(), right.trimLeaves());
  }

  @Override
  public AVLTree<T> cut(int n)
  {
    assert n >= 0;
    return n == 0 ? new AVLEmpty<>() : new AVLCons<T>(value, left.cut(n - 1), right.cut(n - 1));
  }

  @Override
  public boolean isomorphs(AVLTree<T> u)
  {
	  return u.isEmpty() ? false : this.left().isomorphs(u.left()) && this.right().isomorphs(u.right()); 
  }

  @Override
  public int height()
  {
	  return Math.max(right.height()+1,left.height()+1);
  }

  @Override
  public int unbalance()
  {
	  return Math.max(height()-Math.min(right.height()+1,left.height()+1),Math.max(right.unbalance(),left.unbalance()));
  }

  @Override
  public AVLTree<T> populate(T x)
  {
	  return new AVLCons<T>(x,left.populate(x),right.populate(x));
  }

  @Override
  public boolean isHeap()
  {
		  return false;
  }
  @Override
  public AVLTree<T> setLeaves(T x)
  {
	  return isLeaf() ? new AVLCons<T>(x,left,right) : new AVLCons<T>(value,left.setLeaves(x),right.setLeaves(x));
  }

  @Override
  public boolean isDegenerate()
  {
	  return !left.isEmpty() && !right.isEmpty() ? false : left.isDegenerate() && right.isDegenerate(); 
  }

  @Override
  public boolean isZig()
  {
	  return left.isEmpty() && !right.isEmpty() ? false : left.isZag();
  }

  @Override
  public boolean isZag()
  {
	  return right.isEmpty() && !left.isEmpty() ? false :right.isZig();
  }

@Override
public AVLTree<T> degenarate(AVLTree<T> u) {
	return !u.isEmpty() ?  new AVLCons<T>(value,left.degenarate(left),new AVLEmpty<T>()) : new AVLCons<T>(value,new AVLEmpty<T>(),right.degenarate(left)) ;
	}

@Override
public int countAtLevel(int x) {
	return cut(x).countLeaves();
}

@Override
public boolean issubtree(AVLTree<T> a) {
	return (!isEmpty() && a.isEmpty()) ? true : (this.right.issubtree(a.right()) && this.left.issubtree(a.left()));
}

@Override
public AVLTree<T> delete(T x) {
	return null;
}
}
public class AVL{
  public static final String REGEX_TOKEN = "\\-?\\d+|\\(|\\)";

  protected static String newLine = System.getProperty("line.separator");
  public final boolean SHOW_FRAMES = false;
  public final int COLOR_FRAMES = Colors.GREEN;
  public final int COLOR_VALUES = Colors.BLACK;
  public final int COLOR_TEXT = Colors.YELLOW;
  public final int COLOR_LINES = Colors.DARKRED;

  public static Tree<Integer> fromString(String s)
  {
    String[] tokens = RegularExpressions.groups(s, REGEX_TOKEN);
    Queue<String> queue = new Queue<>();
    Arrays.visit(tokens, x -> queue.enqueue(x));
//    for (String x : tokens)
//      queue.enqueue(x);
    return fromQueue(queue);
  }

  public static Tree<Integer> fromQueue(Queue<String> q)
  {
    Tree<Integer> result = new Empty<Integer>();
    assert "(".equals(q.front());
    q.dequeue();
    if (!")".equals(q.front()))
    {
      int x = Integer.parseInt(q.dequeue());
      Tree<Integer> left = fromQueue(q);
      Tree<Integer> right = fromQueue(q);
      result = new Cons<Integer>(x, left, right);
    }
    assert ")".equals(q.front());
    q.dequeue();
    return result;
  }

  public static void testIsomorphs()
  {
    String[] lines = StdIn.readAllLines();
    Bag<Tree<Integer>> t = new Bag<Tree<Integer>>(Arrays.map(lines, gdfsgdsfgdsh::fromString));
    int n = t.size();
    StdOut.println(n);
    for (int i = 0; i < n; i++)
      for (int j = i + 1; j < n; j++)
        if (t.at(i).isomorphs(t.at(j)))
          StdOut.println(i + " " + j + t.at(i) + t.at(j));
  }

  public static void testHeight()
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      int z = t.height();
      StdOut.println(z);
    }
  }

  public static void testUnbalance()
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      int z = t.unbalance();
      StdOut.println(z);
    }
  }

  public static void testPopulate(int x)
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      Tree<Integer> z = t.degenarate(t.left());
      StdOut.println(z);
    }
  }

  public static void testIsHeap()
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      boolean z = t.isHeap();
      StdOut.println(z);
    }
  }

  public static void testSetLeaves(int x)
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      Tree<Integer> z = t.setLeaves(x);
      StdOut.println(z);
    }
  }

  public static void testIsDegenerate()
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      boolean z = t.isDegenerate();
      StdOut.println(z);
    }
  }
  
  public static void testIsZigzag()
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      boolean z = t.isZigzag();
      StdOut.println(z);
    }
  }
  public static void testcountAtLevel()
  {
      Tree<Integer> t = fromString(StdIn.readLine());
      int y=StdIn.readInt();
      int z = t.countAtLevel(y+1);
      StdOut.println(z);
  }
  public static void testsub()
  {
    while (StdIn.hasNextLine())
    {
      Tree<Integer> t = fromString(StdIn.readLine());
      Tree<Integer> a = fromString(StdIn.readLine());
      boolean z = t.issubtree(a);
      StdOut.println(z);
    }
  }
	public static void testAVL()
	{
		AVLTree<Integer> t = new AVLEmpty<Integer>();
		while (!StdIn.isEmpty())
		{
			int x = StdIn.readInt();
			t = t.put(x);
				StdOut.println(t);
		}
	}

  public static void main(String[] args)
  {
    char choice = 'u';
    int x = 0;
    if (args.length > 0)
      choice = args[0].charAt(0);
    if (args.length > 1)
    	x = Integer.parseInt(args[1]);
    if (choice == 'A')
      testIsomorphs();
    else if (choice == 'B')
      testHeight();
    else if (choice == 'C')
      testUnbalance();
    else if (choice == 'D')
      testPopulate(x);
    else if (choice == 'E')
      testIsHeap();
    else if (choice == 'F')
      testSetLeaves(x);
    else if (choice == 'G')
      testIsDegenerate();
    else if (choice == 'H')
      testIsZigzag();
    else if (choice == 'p')
        testsub();
    else if(choice=='u')
    	testAVL();
    else
      System.out.printf("Illegal choice: %s\n", args[0]);
  }

}


