package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class BSTMap<K, V> implements Map61B<K, V> {
    private final TreeMap<K, V> delegate = new TreeMap<>();

    @Override
    public void clear() {
        delegate.clear();
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
        return delegate.keySet();
    }

    @Override
    public V remove(K key) {
        return delegate.remove(key);
    }

    @Override
    public V remove(K key, V value) {
        if (delegate.containsKey(key) && java.util.Objects.equals(delegate.get(key), value)) {
            return delegate.remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return delegate.keySet().iterator();
    }
}
