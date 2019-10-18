package pratice;

import java.util.Iterator;

import pt.ualg.fct.aed.Table;

public class HashTableAED<K,V> implements Table<K,V>  {
	private class List<K,V> implements Table<K,V>{
		private List<K,V> ht;
		public List() {
			ht=new List<K,V>();
		}
		@Override
		public Iterator<K> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V get(K key) {
			return ht.get(key);
		}

		public void put(K key, V value,int x) {
			ht.put(key, value);
		}

		@Override
		public void delete(K key) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean has(K key) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void put(K key, V value) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean has(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
