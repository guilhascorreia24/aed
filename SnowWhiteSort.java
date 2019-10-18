

import java.util.Comparator;

import pt.ualg.fct.aed.Sort;

public class SnowWhiteSort<T> extends Sort<T> {

	public SnowWhiteSort(Comparator<? super T> cmp) {
		super(cmp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void sort(T[] a, int size) {
		T o=null;
		int t=0;
		for(int i=1;i<size-1;i++) {
			if(less(a[i+1], a[i])) {
				o=a[i];
				a[i]=a[i+1];
				a[i+1]=o;
				t++;
			}
			if(less(a[i],a[i-1])) {
				o=a[i-1];
				a[i-1]=a[i];
				a[i]=o;
				t++;
			}
		}
		if(t!=0) {
			sort(a,size);
		}
		
	}

	@Override
	public <U> Sort<U> algorithm(Comparator<? super U> cmp) {
		return new SnowWhiteSort<U>(cmp);
	}
	
	public static void main(String[] args) {
		
	}

}
