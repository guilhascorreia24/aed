

import java.util.Comparator;

import pt.ualg.fct.aed.Quicksort;
import pt.ualg.fct.aed.Sort;

public class gardensort<T> extends Sort<T> {

	public gardensort(Comparator<? super T> cmp) {
		super(cmp);
	}

	@Override
	public void sort(T[] a, int size) {
		if(greater(a[0],a[1])) {
			reverse(a,0,1);
		}
		for(int i=1;i<size-1;) {
			if(greater(a[i],a[i+1]) && i>0) {
				reverse(a,i,i+1);
				i--;
			}
			else {
				if(greater(a[i],a[i+1])) {
					reverse(a,i,i+1);
				}
				i++;
			}
		}
	}

	private void reverse(T[] a,int x,int y) {
		T o=a[x];
		a[x]=a[y];
		a[y]=o;
	}
	@Override
	public <U> Sort<U> algorithm(Comparator<? super U> cmp) {
		return new gardensort<U>(cmp);
	}
	  public static void main(String[] args)
	  {
	    char choice = 'A';
	    if (args.length >= 1)
	      choice = args[0].charAt(0);
	    if (choice == 'A')
	      testIntegers(new gardensort<Integer>((x, y) -> x - y));
	  }

}
/*import java.util.ArrayList;

import java.util.List;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.Sort;


class gardensort<T extends Comparable<T>> extends Sort<T>{


    public gardensort(Comparator<? super T> cmp) {
		super(cmp);
	}
    @Override
	public void sort(T[] a, int size){
        if(a.length == 0)
            return;
        for(int i = 1; i<a.length;){
            if(a[i].compareTo(a[i-1])<0){
                T b = a[i];
                a[i] = a[i-1];
                a[i-1] = b;
                i--;
            }else{i++;}
        }
    }

	@Override
	public <U> Sort<U> algorithm(Comparator<? super U> cmp) {
		return new gardensort<U>(cmp);
	}
	  public static void main(String[] args)
	  {
	    char choice = 'A';
	    if (args.length >= 1)
	      choice = args[0].charAt(0);
	    if (choice == 'A')
	      testIntegers(new gardensort<Integer>((x, y) -> x - y));
	  }
	
} */
