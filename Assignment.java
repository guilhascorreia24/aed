
// Simple trees, with comparable elements and BST like put and has.
// This is for practice only. Using the constructor, non-BST trees
// may be created. Hence, this is not a class for BST trees.

import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.Arrays;
import pt.ualg.fct.aed.Bag;
import pt.ualg.fct.aed.Colors;
import pt.ualg.fct.aed.Queue;
import pt.ualg.fct.aed.RegularExpressions;

 abstract class Tree<T extends Comparable<T>>
{
  public abstract int size();
  public abstract boolean isEmpty();
  public abstract boolean has(T x);
  
  public abstract T value(); // requires !isEmpty();
  public abstract Tree<T> left(); // requires !isEmpty();
  public abstract Tree<T> right(); // requires !isEmpty();
  
  public abstract Tree<T> put(T x);
   
  

  // Utilities

  public static <U extends Comparable<U>> Tree<U> fromArray(U[] a)
  {
    Tree<U> result = new Empty<>();
    for (U x : a)
      result = result.put(x);
    return result;
  }

  public static Tree<Integer> fromInts(int[] a)
  {
    Tree<Integer> result = new Empty<>();
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
  public abstract Tree<T> trimLeaves();
  public abstract Tree<T> cut(int n);

  // --------------------
  // Functions to be programmed by you in derived classes

  public abstract boolean isomorphs(Tree<T> u);
  public abstract int height();
  public abstract int unbalance();
  public abstract Tree<T> populate(T x);
  public abstract boolean isHeap();
  public abstract Tree<T> setLeaves(T x);
  public abstract boolean isDegenerate();
  public abstract boolean isZig();
  public abstract boolean isZag();
  
  public boolean isZigzag()
  {
    return isZig() || isZag();
  }

  // -------------------

  // Functions to create zigzag trees

  public static <U extends Comparable<U>> Tree<U> zigzagFrom(U[] a)
  {
    return zigFrom(a, 0);
  }

  public static <U extends Comparable<U>> Tree<U> zagzigFrom(U[] a)
  {
    return zagFrom(a, 0);
  }

  public static <U extends Comparable<U>> Tree<U> zigFrom(U[] a, int d)
  {
    return d == a.length ? new Empty<>() : new Cons<>(a[d], zagFrom(a, d + 1), new Empty<>());
  }

  public static <U extends Comparable<U>> Tree<U> zagFrom(U[] a, int d)
  {
    return d == a.length ? new Empty<>() : new Cons<>(a[d], new Empty<>(), zigFrom(a, d + 1));
  }
}

//---------------------

class Empty<T extends Comparable<T>> extends Tree<T>
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
  public Tree<T> left()
  {
    throw new NoSuchElementException();
  }

  @Override
  public Tree<T> right()
  {
    throw new NoSuchElementException();
  }

  @Override
  public Tree<T> put(T x)
  {
    return new Cons<>(x, this, this);
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
  public Tree<T> trimLeaves()
  {
    return this;
  }

  @Override
  public Tree<T> cut(int n)
  {
    return this;
  }

  @Override
  public boolean isomorphs(Tree<T> u)
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
  public Tree<T> populate(T x)
  {
	  return this;
  }

  @Override
  public boolean isHeap()
  {
	  return true;
  }

  @Override
  public Tree<T> setLeaves(T x)
  {
	  return this;
  }

  @Override
  public boolean isDegenerate()
  {
	  return isEmpty();
  }

  @Override
  public boolean isZig()
  {
    throw new UnsupportedOperationException("Not implemented yet"); 
    // TODO
  }

  @Override
  public boolean isZag()
  {
    throw new UnsupportedOperationException("Not implemented yet"); 
   // TODO
  }
}

// ------------------------

class Cons<T extends Comparable<T>> extends Tree<T>
{
  private final T value;
  private final Tree<T> left;
  private final Tree<T> right;
  private final int size;

  public Cons(T value, Tree<T> left, Tree<T> right)
  {
    this.value = value;
    this.left = left;
    this.right = right;
    this.size = 1 + left.size() + right.size();
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
  public Tree<T> left()
  {
    return left;
  }

  @Override
  public Tree<T> right()
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
  public Tree<T> put(T x)
  {
    Tree<T> result = this;
    int cmp = x.compareTo(value);
    if (cmp < 0)
      result = new Cons<T>(value, left.put(x), right);
    else if (cmp > 0)
      result = new Cons<T>(value, left, right.put(x));
    return result;
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
  public Tree<T> trimLeaves()
  {
    return isLeaf() ? new Empty<>() : new Cons<T>(value, left.trimLeaves(), right.trimLeaves());
  }

  @Override
  public Tree<T> cut(int n)
  {
    assert n >= 0;
    return n == 0 ? new Empty<>() : new Cons<T>(value, left.cut(n - 1), right.cut(n - 1));
  }

  @Override
  public boolean isomorphs(Tree<T> u)
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
  public Tree<T> populate(T x)
  {
	  return new Cons<T>(x,left.populate(x),right.populate(x));
  }

  @Override
  public boolean isHeap()
  {
	  if(isLeaf()) {
		  return false;
	  }
	return SHOW_FRAMES;
  }
  @Override
  public Tree<T> setLeaves(T x)
  {
	  return isLeaf() ? new Cons<T>(x,left,right) : new Cons<T>(value,left.setLeaves(x),right.setLeaves(x));
  }

  @Override
  public boolean isDegenerate()
  {
	  if(right.isDegenerate() && left.isDegenerate()) {
		  return true;
	  }
	  return false;
  }

  @Override
  public boolean isZig()
  {
	    throw new UnsupportedOperationException("Not implemented yet"); 
	    // TODO
  }

  @Override
  public boolean isZag()
  {
    throw new UnsupportedOperationException("Not implemented yet"); 
    // TODO
  }

}

public class Assignment
{
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
    Bag<Tree<Integer>> t = new Bag<Tree<Integer>>(Arrays.map(lines, Assignment::fromString));
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
      Tree<Integer> z = t.populate(x);
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



  public static void main(String[] args)
  {
    char choice = 'E';
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
    else
      System.out.printf("Illegal choice: %s\n", args[0]);
  }

}
