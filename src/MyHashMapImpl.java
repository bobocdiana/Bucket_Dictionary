import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MyHashMapImpl<K,V> implements MyHashMap <K,V> {
	private ArrayList<MyBucket<K,V>> H;
	private int HMAX;
	
	/**Is the constructor of the class which receives as parameter 
	 * the maximum size of the list of buckets.
	 * It also allocates the list of buckets and each bucket from the list.
	 * 
	 * @param HMAX
	 */
	public MyHashMapImpl (int HMAX) {
		this.HMAX=HMAX;
		H=new ArrayList<MyBucket<K,V>> (HMAX);
		for (int i=1;i<=HMAX;i++) {
			H.add(new MyBucket<K,V> ());
		}
	}
	
	/**This method is used to translate the hash function in order for it to 
	 * return an acceptable index of the lost of buckets.
	 * 
	 * @param key is the key for which will pe calculated its index in the list of
	 * buckets
	 * @return the index where the key key will pe put in the list of buckets
	 */
	public int translate(K key) {
		String x=new String ();
		x=key.toString();
		return Math.abs(x.hashCode())%size();
	}
	
	/**This method returns the value for the key given as parameter if the
	 * key exists in the list of buckets or it returns null if the key 
	 * doesn't exist.
	 * 
	 */
	@Override
	public V get(K key) {
		int hkey=translate(key);
		MyBucket<K,V> bucket=H.get(hkey);
		LinkedList<MyEntry<K,V>> entries=(LinkedList<MyHashMapImpl<K, V>.MyEntry<K, V>>) bucket.getEntries();
		String md,ms;
		md=key.toString();
		for (MyEntry<K,V> i: entries) {
			ms=i.getKey().toString();
			if (ms.equals(md)) {
				return i.getValue();
			}
		}
		return null;
	}
	
	/**This metod puts a pair of key and its value in the list of buckets if the key 
	 * doesn't exist already.Otherwise, the previous value of the key will be replaced
	 * with the value given as parameter.
	 * The method will return the previous value of the key given as parameter or
	 * null if the key didn't exist in the list of buckets.
	 * 
	 */
	@Override
	public V put(K key, V value) {
		MyEntry<K,V> new_entry=new MyEntry<K,V> (key,value);
		int hkey=translate(key);
		MyBucket<K,V> bucket=H.get(hkey);
		LinkedList<MyEntry<K,V>> entries=(LinkedList<MyHashMapImpl<K, V>.MyEntry<K, V>>) bucket.getEntries();
		V last_value=null;
		String md,ms;
		md=key.toString();
			
		for (MyEntry<K,V> i: entries) {
			ms=i.getKey().toString();
			if (md.equals(ms)) {
				last_value=i.getValue();
				i.setValue(value);
				break;
			}
		}
		if (last_value!=null) return last_value;
		else {
			bucket.addEntry(new_entry);
			return null;
		}
	}
	
	
	/**This method removes the pair formed of the key given as parameter
	 * and its value if the key is found in the list of buckets.Thus, the
	 * method returns the value of the key to be removed.
	 * If the key given as parameter was not found in the list of buckets
	 * then the method returns null.
	 * 
	 */
	@Override
	public V remove(K key) {
		int hkey=translate(key);
		MyBucket<K,V> bucket=H.get(hkey);
		LinkedList<MyEntry<K,V>> entries=(LinkedList<MyHashMapImpl<K, V>.MyEntry<K, V>>) bucket.getEntries();
		V last_value = null;
		int nr=0;
		for (MyEntry<K,V> i: entries) {
			if (i.getKey()==key) {
				last_value=i.getValue();
				break;
			}
			nr++;
		}
		if (last_value!=null) {
			bucket.removeEntry(nr);
		}
		return last_value;
	}
	
	/**This method returns the maximum size of the list of buckets
	 * 
	 */
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return HMAX;
	}
	
	/**This method returns the list of buckets of the hashmap
	 * 
	 */
	@Override
	public List<? extends MyHashMap.Bucket<K, V>> getBuckets() {
		// TODO Auto-generated method stub
		return H;
	}
	
	/**This internal class represents an entry in the list of buckets.
	 * An entry is made of a key and its associated value.
	 * 
	 * @author Diana
	 *
	 * @param <K>
	 * @param <V>
	 */
	public class MyEntry<K,V> implements Entry <K,V> {
		K key;
		V value;
		
		public MyEntry() {
			
		}
		
		public MyEntry (K key,V value) {
			this.key=key;
			this.value=value;
		}
		
		public void setKey (K key) {
			this.key=key;
		}
		
		public void setValue (V value) {
			this.value=value;
		}
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
	}
	
	/**This internal class represents a Bucket which is 
	 * formed of a list of entries.
	 * 
	 * @author Diana
	 *
	 * @param <K>
	 * @param <V>
	 */
	public class MyBucket<K,V> implements Bucket<K,V> {
		private LinkedList<MyEntry<K, V>> list;
		
		public MyBucket () {
			list=new LinkedList<MyEntry<K,V>> ();
		}
		
		public void addEntry(MyEntry<K,V> entry) {
			list.add(entry);
		}
		
		public void removeEntry(int nr) {
			list.remove(list.get(nr));
		}
		@Override
		public List<? extends MyHashMap.Entry<K, V>> getEntries() {
			return list;
		}
		
	}

}
