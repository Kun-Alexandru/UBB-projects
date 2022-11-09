package Model.ADT;

import Model.Exceptions.DictionaryException;

import java.util.HashMap;;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    private HashMap<K, V> map;

    public MyDictionary() {
        this.map = new HashMap<K, V>();
    }

    @Override
    public void put(K Key, V Value) {
        this.map.put(Key, Value);   // returns the previous V value if K was used, null otherwise
    }

    public void update(K Key, V Value) throws DictionaryException {
        if (!this.map.containsKey(Key)) {
            throw new DictionaryException("Error : given key not in dictionary.");
        }
        map.put(Key,Value);
    }
    @Override
    public V lookUp(K key) throws DictionaryException {
        if (!this.map.containsKey(key)) {
            throw new DictionaryException("Error : given key not in dictionary.");
        }
        return this.map.get(key);
    }

    @Override
    public boolean isVarDef(K key) {
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