package Model.ADT;

import Model.Exceptions.DictionaryException;

import java.util.HashMap;

public class MyDict<K, V> implements IDict<K, V> {
    private HashMap<K, V> map;

    public MyDict() {
        this.map = new HashMap<K, V>();
    }

    @Override
    public void addKeyValuePair(K newKey, V newValue) {
        this.map.put(newKey, newValue);   // returns the previous V value if K was used, null otherwise
    }

    @Override
    public V lookUp(K key) throws DictionaryException {
        if (!this.map.containsKey(key)) {
            throw new DictionaryException("Failed to get value: the given key is not in the dictionary.");
        }
        return this.map.get(key);
    }

    @Override
    public V removeByKey(K key) throws DictionaryException {
        V value = this.map.remove(key);
        if (value != null) {
            return value;
        }
        else {
            throw new DictionaryException("Failed to remove key-value pair: The given key is not in the dictionary");
        }
    }

    @Override
    public boolean isDefined(K key) {
        return this.map.containsKey(key);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for(K key: map.keySet())
            text.append(key).append(": ").append(map.get(key)).append("\n");
        return text.toString();
    }
}
