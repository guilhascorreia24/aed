

import java.util.Iterator;

public class BagRandom<T> implements Iterable<T>{
		  private T[] items;
		  private int size;
	private class BagIterator implements Iterator<T>{
		private int i=0;
		@Override
		public T next() {
			assert hasNext();
			return items[(int)(Math.random()%size)];
		}
		@Override
		public boolean hasNext() {
			return i<size;
		}
		
	}
	@Override
	public Iterator<T> iterator() {
		return new BagIterator();
	}
	
}
