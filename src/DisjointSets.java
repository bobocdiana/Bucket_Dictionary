import java.util.ArrayList;

public class DisjointSets<T> {
	private ArrayList<Entry<T>> elements;
	private MyHashMapImpl<T, Integer> indexes;

	/**This class symbolizes the tuplet element(a word in our case) and the index
	 * of the set to which it belongs 
	 * 
	 * @author Diana
	 *
	 * @param <T>
	 */
	private class Entry<T> {
		public T element;
		public int setIndex;

		public Entry(T element) {
			this.element = element;
			setIndex = elements.size();
		}
	}

	/**This method receives as parameter an element(a word in our case)
	 * and based on it, it is made a new Entry object and it is added 
	 * to the elements array and to the indexes hashmap with its set's
	 * index 
	 * 
	 * @param elem is the element(word in our case) to be added to the array elements
	 * and to the hashmap indexes
	 */
	public void addElement(T elem) {
		Entry e = new Entry(elem);
		elements.add(e);
		indexes.put(elem, e.setIndex);
	}

	/**This method merges the sets of each element received as parameter based on 
	 * their index as follows: every element which belongs to the set with the bigger
	 * index will be moved to the set with the smaller index; this move implies that the
	 * element and its new index will have to be put once again in the indexes hashmap
	 * in order to keep their new association. 
	 * 
	 * @param e1 is one of the element which will merge its set with the other parameter's set
	 * @param e2 is the other element which will merge its set with the other's set
	 */
	public void mergeSetsOf(T e1, T e2) {
		int m1 = indexes.get(e1);
		int m2 = indexes.get(e2);
		for (Entry<T> e : elements) {
			if (e.setIndex == Math.max(m1, m2)) {
				e.setIndex = Math.min(m1, m2);
				indexes.put(e.element, e.setIndex);
			}
		}
	}
	
	/**This method will return an array with all the elements that belong to the
	 * same set as the element given as parameter
	 * 
	 * @param elem is the element for which the method return its entire set
	 * @return an array which contains the set of the element given as parameter
	 */
	public ArrayList<T> setOf(T elem) {
		int i = indexes.get(elem);
		ArrayList<T> list = new ArrayList<T>();
		for (Entry<T> e : elements) {
			if (e.setIndex == i && e.element.equals(elem)==false) {
				list.add(e.element);
			}
		}
		return list;
	}
	
	/**Is the constructor of this class which receives as parameter the
	 * maximum size of the array elements and of the list of buckets of the 
	 * hasmap indexes.
	 * 
	 * @param HMAX represents the maximum size
	 */
	public DisjointSets(int HMAX) {
		elements = new ArrayList<Entry<T>>(HMAX);
		indexes = new MyHashMapImpl<T, Integer>(HMAX);
	}
}