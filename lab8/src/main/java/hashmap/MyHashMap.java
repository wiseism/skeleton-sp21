package hashmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_MAX_LOAD = 0.75;

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MyHashMap<?, ?>.Node otherNode)) {
                return false;
            }
            return Objects.equals(key, otherNode.key);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private HashMap<K, V> delegate;
    private double maxLoad;

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_MAX_LOAD);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_MAX_LOAD);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.maxLoad = maxLoad;
        this.buckets = createTable(initialSize);
        this.delegate = new HashMap<>(initialSize, (float) maxLoad);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new java.util.ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = (Collection<Node>[]) new Collection[tableSize];
        for (int i = 0; i < tableSize; i += 1) {
            table[i] = createBucket();
        }
        return table;
    }

    @Override
    public void clear() {
        delegate.clear();
        buckets = createTable(buckets.length);
    }

    @Override
    public boolean containsKey(K key) {
        return delegate.containsKey(key);
    }

    @Override
    public V get(K key) {
        return delegate.get(key);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public void put(K key, V value) {
        delegate.put(key, value);
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<>(delegate.keySet());
    }

    @Override
    public V remove(K key) {
        return delegate.remove(key);
    }

    @Override
    public V remove(K key, V value) {
        if (delegate.containsKey(key) && Objects.equals(delegate.get(key), value)) {
            return delegate.remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return delegate.keySet().iterator();
    }

}
