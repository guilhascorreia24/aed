

import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.Quicksort;
import pt.ualg.fct.aed.Sort;

public class carzysort<T> extends Sort<T> {
	public carzysort(Comparator<? super T> cmp) {
		super(cmp);
	}
	@Override
	public void sort(T[] a, int size) {
		for(int i=0;i<size;i++) {
			if(greater(a[i+1],a[i])) {	}
			else {
				int h=(int)Math.random()%size;
				for(int j=0;j<size;j++) {
					a[(int)h%size++]=a[i];
				}
				i=0;
			}
		}
	}

	@Override
	public <U> Sort<U> algorithm(Comparator<? super U> cmp) {
		return new carzysort<U>(cmp);
	}
	  public static void main(String[] args)
	  {
		  long startTime = System.nanoTime();
		    char choice = 'A';
		    if (args.length >= 1)
		      choice = args[0].charAt(0);
		    if (choice == 'A')
		      testIntegers(new Quicksort<Integer>((x, y) -> x - y));
		  long endTime = System.nanoTime();

		  long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		  StdOut.print(duration/1000000);
	  }
}
