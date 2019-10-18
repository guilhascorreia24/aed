package pratice;

import java.util.Iterator;

import pt.ualg.fct.aed.HashTable;
import pt.ualg.fct.aed.Table;

public class HashTableDK<K,V> implements Table<K,V>, Iterable<V> {
	@Override
	public void put(K key, V value) {
		this.put(has(key), value);
	}
	public HashTableDK(int d)
	{
		HashTable a=new HashTable(d);
	}
	public V get(K key)
	{
		return this.get(key);
	}
}
