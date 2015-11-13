import java.util.LinkedList;

public class HashTable<K, V> {
  protected final static int DEFAULT_CAPACITY = 101;
  protected int capacity;
  protected int size;
  protected LinkedList<Entry>[] table;

  protected class Entry {
    public K key;
    public V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public HashTable() {
    this(DEFAULT_CAPACITY);
  }

  public HashTable(int initialCapacity) {
    size = 0;
    table = (LinkedList<Entry>[])new LinkedList[initialCapacity];
    capacity = initialCapacity;
    for (int i = 0; i < initialCapacity; i++) {
      table[i] = new LinkedList<Entry>();
    }
  }

  protected int compressionFunction(int hashCode) {
    return hashCode % capacity;
  }

  public int size() {
    return size;
  }

  public void put(K key, V value) {
    int hashVal = key == null ? 0 : compressionFunction(key.hashCode());
    LinkedList<Entry> chain = table[hashVal];
    for (Entry e : chain) {
      if ((key == null && key == e.key) || (key != null && key.equals(e.key))) {
        e.value = value;
        return;
      }
    }
    chain.add(new Entry(key, value));
    size++;
  }

  public V get(K key) {
    int hashVal = key == null ? 0 : compressionFunction(key.hashCode());
    LinkedList<Entry> chain = table[hashVal];
    for (Entry e : chain) {
      if ((key == null && key == e.key) || (key != null && key.equals(e.key))) {
        return e.value;
      }
    }
    return null;
  }

  public V remove(K key) {
    int hashVal = key == null ? 0 : compressionFunction(key.hashCode());
    LinkedList<Entry> chain = table[hashVal];
    int removeIndex = 0;
    for (Entry e : (LinkedList<Entry>)chain.clone()) {
      if ((key == null && key == e.key) || (key != null && key.equals(e.key))) {
        size--;
        return chain.remove(removeIndex).value;
      }
      removeIndex++;
    }
    return null;
  }

  public static void main(String[] args) {
    HashTable<Integer, String> h = new HashTable<Integer, String>();

    System.out.println("PUTTING [null, 'NULL'] into table:");
    h.put(null, "NULL");
    System.out.println("h.get(null) should be 'NULL': " + h.get(null));
    System.out.println("h.size() should be 1: " + Integer.toString(h.size()));
    System.out.println("h.remove(null) should be 'NULL': " + h.remove(null));
    System.out.println("h.get(null) == null should be true: " + Boolean.toString(h.get(null) == null));
    System.out.println("h.remove(null) == null should be true: " + Boolean.toString(h.remove(null) == null));
    System.out.println("h.size() should be 0: " + Integer.toString(h.size()));
    System.out.println("");

    System.out.println("PUTTING [1, 'One'] into table:");
    h.put(1, "One");
    System.out.println("h.get(1) should be 'One': " + h.get(1));
    System.out.println("h.size() should be 1: " + Integer.toString(h.size()));
    System.out.println("h.remove(1) should be 'NULL': " + h.remove(1));
    System.out.println("h.get(1) == null should be true: " + Boolean.toString(h.remove(1) == null));
    System.out.println("h.remove(1) == null should be true: " + Boolean.toString(h.remove(1) == null));
    System.out.println("h.size() should be 0: " + Integer.toString(h.size()));
  }
}
