import java.util.function.BinaryOperator;

import pt.ualg.fct.aed.Colors;

public abstract class Tree<T extends Comparable<T>>
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