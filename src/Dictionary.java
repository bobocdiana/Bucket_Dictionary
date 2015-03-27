import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Dictionary extends MyHashMapImpl<String, String> {
	DisjointSets<String> synonyms;
	
	public Dictionary(int HMAX) {
		super(HMAX);
		synonyms=new DisjointSets<String> (HMAX);
	}
	
	/**This method overrides the method put from MyHashMapImpl.
	 * It differs from the other classical implementation because in this 
	 * case a key is allowed to have more than one value associated.Thus,
	 * I have given the possibility for a word to have more then one
	 * definition.
	 * In brief, only if the key and the value given are already found in the
	 * list of buckets, then the method will return the key's value and do nothing
	 * in rest.In other cases, the method will return null and add the pair given as
	 * parameters to the list of buckets.
	 * 
	 */
	public String put(String key, String value) {
		
		MyEntry<String, String> new_entry = new MyEntry<String, String>(key,
				value);
		int hkey = translate(key);
		MyBucket<String, String> bucket = (MyHashMapImpl<String, String>.MyBucket<String, String>) getBuckets()
				.get(hkey);
		LinkedList<MyEntry<String, String>> entries = (LinkedList<MyHashMapImpl<String, String>.MyEntry<String, String>>) bucket
				.getEntries();
		String last_value = null;

		for (MyEntry<String, String> i : entries) {
			if (i.getKey().equals(key) && i.getValue().equals(value)) {
				last_value = i.getValue();
				break;
			}
		}
		if (last_value==null) bucket.addEntry(new_entry);
		return last_value;
	}
	
	/**This method adds a word and its definition to the dictionary and its
	 * hashmap.
	 * Also, the word is added to its correspondent set (+ the array elements
	 * and the hashmap indexes)  
	 * 
	 * @param word is the word to be added (aka the key)
	 * @param definition is the word definition (aka the key's value)
	 */
	public void addWord(String word, String definition) {
		String last_definition;
		last_definition = put(word, definition);
		synonyms.addElement(word);
	}
	
	/**This method adds the pair of synonyms formed of word1 and word2 
	 * 
	 * @param word1 a synonym 
	 * @param word2 a synonym
	 */
	public void addSynonym(String word1, String word2) {
		synonyms.mergeSetsOf(word1, word2);
	}
	
	/**This method sorts the array given as parameter
	 * and returns the new sorted array
	 * 
	 * @param syn is the array to be sorted
	 * @return the sorted array
	 */
	public String[] sort( ArrayList<String> syn) {
		String [] sorted=new String [syn.size()];
		for (int i=0;i<syn.size();i++) {
			sorted[i]=syn.get(i);
		}
		Arrays.sort(sorted);
		return sorted;
	}
	
	/**This method returns the synonyms of the word given as a parameter
	 * 
	 * @param word
	 * @return the word's synonyms
	 */
	public String[] getSynonyms (String word) {
		return sort(synonyms.setOf(word));
	}
	
	/**This method returns the number of definitions of the word
	 * given as parameter
	 * 
	 * @param word
	 * @return the word's number of definitions
	 */
	public int getNumberOfDefinitions(String word) {
		int hkey = translate(word);
		MyBucket<String, String> bucket = (MyHashMapImpl<String, String>.MyBucket<String, String>) getBuckets()
				.get(hkey);
		LinkedList<MyEntry<String, String>> entries = (LinkedList<MyHashMapImpl<String, String>.MyEntry<String, String>>) bucket
				.getEntries();
		int nr=0;
		for (MyEntry<String, String> e:entries) {
			if (e.getKey().equals(word)) {
				nr++;
			}
		}
		return nr;
	}
}
