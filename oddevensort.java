

import java.util.Comparator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.Arrays;
import pt.ualg.fct.aed.Sort;

public class oddevensort<T> extends Sort<T> {

	public oddevensort(Comparator<? super T> cmp) {
		super(cmp);
	}

	@Override
	public void sort(T[] a, int size) {
		for(int i=0;i<size;i++) {
			/*for(int j=i%2;j<size-1;j+=2) {
			if(less(a[j+1],a[j])) {
				T o=a[j+1];
				a[j+1]=a[j];
				a[j]=o;
			}
			}*/
		if(less(a[i+1],a[i])) {
			T o=a[i+1];
			a[i+1]=a[i];
			a[i]=o;
		}
			}
	}

	@Override
	public <U> Sort<U> algorithm(Comparator<? super U> cmp) {
		return new oddevensort<U>(cmp);
	}
	public static void test(oddevensort<Integer> s) {
		Integer[] a=Arrays.integersFromInts(StdIn.readAllInts());
		s.sort(a);
		StdOut.print(Arrays.mkString(a," "));
	}
	public static void main(String[] args) {
		test(new oddevensort<Integer>((x,y)->x-y));
	}
}
