
import java.util.Comparator;

import pt.ualg.fct.aed.Quicksort;
import pt.ualg.fct.aed.Sort;

public class Pancake<T> extends Sort<T>{
	private int y=0;
	public Pancake(Comparator<? super T> cmp) {
		super(cmp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sort(T[] a, int size) {
		for(int j=0;j<size;j++) {
		T h=null;
		int k=0;
		y=size-j;
		for(int i=0;i<y;i++) {
			if(greater(h,a[i])) {
				h=a[i];
				k=i;
			}
		}
		reverse(a,0,k);
		}
		
	}
	
	public void reverse(T[] a,int b,int c) {
		T o=a[b];
		a[b]=a[c];
		a[c]=o;
		int size=(int) Math.floor(y/2);
		for(int i=0;i<size;i++) {
			o=a[i];
			a[i]=a[size-i];
			a[size-i]=o;
		}
	}

	@Override
	public <U> Sort<U> algorithm(Comparator<? super U> cmp) {
		return new Pancake<U>(cmp);
	} 
	  public static void main(String[] args)
	  {
	    char choice = 'A';
	    if (args.length >= 1)
	      choice = args[0].charAt(0);
	    if (choice == 'A')
	      testIntegers(new Quicksort<Integer>((x, y) -> x - y));
	  }

}
