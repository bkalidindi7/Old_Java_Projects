import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries.
     */
    public HashMap() {
        // Initialize your hashtable here.
        table = new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public V add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        if (size + 1 >= MAX_LOAD_FACTOR * table.length) {
            MapEntry<K, V>[] copy = new MapEntry[2 * table.length + 1];
            MapEntry<K, V>[] backing = table;
            table = copy;
            size = 0;
            for (int i = 0; i < backing.length; i++) {
                if (backing[i] != null && !backing[i].isRemoved()) {
                    add(backing[i].getKey(), backing[i].getValue());
                }
            }
        }
        V result;
        int hash = Math.abs(key.hashCode());
        int index = hash % table.length;
        int newIndex = index;
        if (table[newIndex] == null) {
            table[newIndex] = new MapEntry<K, V>(key, value);
            size++;
            return null;
        }
        for (int n = 0; n < table.length; n++) {
            newIndex = (index + n * n) % table.length;
            if (table[newIndex] == null) {
                n = table.length;
            } else if (table[newIndex].getKey().equals(key)
                    && !table[newIndex].isRemoved()) {
                result = table[newIndex].getValue();
                table[newIndex].setValue(value);
                return result;
            }
        }
        for (int n = 0; n < table.length; n++) {
            newIndex = (index + n * n) % table.length;
            if (table[newIndex] == null || table[newIndex].isRemoved()) {
                table[newIndex] = new MapEntry<K, V>(key, value);
                size++;
                return null;
            }
        }
        MapEntry<K, V>[] copy = new MapEntry[2 * table.length + 1];
        MapEntry<K, V>[] backing = table;
        table = copy;
        size = 0;
        for (int i = 0; i < backing.length; i++) {
            if (backing[i] != null && !backing[i].isRemoved()) {
                add(backing[i].getKey(), backing[i].getValue());
            }
        }
        return add(key, value);
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        V result;
        int hash = Math.abs(key.hashCode());
        int index = hash % table.length;
        if (table[index] == null) {
            throw new NoSuchElementException("Key not found");
        }
        if (table[index].getKey().equals(key) && !table[index].isRemoved()) {
            size--;
            table[index].setRemoved(true);
            return table[index].getValue();
        }
        int newIndex = index;
        for (int n = 1; n < table.length; n++) {
            newIndex = (index + n * n) % table.length;
            if (table[newIndex] == null) {
                n = table.length;
            } else if (!table[newIndex].isRemoved()
                    && table[newIndex].getKey().equals(key)) {
                result = table[newIndex].getValue();
                table[newIndex].setRemoved(true);
                size--;
                return result;
            }
        }
        throw new NoSuchElementException("Key not found");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        V result;
        int hash = Math.abs(key.hashCode());
        int index = hash % table.length;
        if (table[index] == null) {
            throw new NoSuchElementException("Key not found");
        }
        if (table[index].getKey().equals(key) && !table[index].isRemoved()) {
            return table[index].getValue();
        }
        int newIndex = index;
        for (int n = 0; n < table.length; n++) {
            newIndex = (index + n * n) % table.length;
            if (table[newIndex] == null) {
                n = table.length;
            } else if (table[newIndex].getKey().equals(key)
                    && !table[newIndex].isRemoved()) {
                result = table[newIndex].getValue();
                return result;
            }
        }
        throw new NoSuchElementException("Key not found");
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        V result;
        int hash = Math.abs(key.hashCode());
        int index = hash % table.length;
        if (table[index] == null) {
            return false;
        }
        if (table[index].getKey().equals(key) && !table[index].isRemoved()) {
            return true;
        }
        int newIndex = index;
        for (int n = 0; n < table.length; n++) {
            newIndex = (index + n * n) % table.length;
            if (table[newIndex] == null) {
                return false;
            } else if (table[newIndex].getKey().equals(key)
                    && !table[newIndex].isRemoved()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MapEntry<K, V>[] toArray() {
        return table;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>(size);
        for (MapEntry<K, V> val : table) {
            if (val != null && !val.isRemoved()) {
                keys.add(val.getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<V>(size);
        for (MapEntry<K, V> val : table) {
            if (val != null && !val.isRemoved()) {
                values.add(val.getValue());
            }
        }
        return values;
    }
}