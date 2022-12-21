package Model.ADT;

import Model.Exceptions.DictionaryException;

import java.util.Map;

public class MyFileTable<K, V> extends MyDictionary<K, V> {
    @Override
    public synchronized void addKeyValuePair(K newKey, V newValue) {
        super.addKeyValuePair(newKey, newValue);
    }

    @Override
    public synchronized V lookUp(K key) throws DictionaryException {
        return super.lookUp(key);
    }

    @Override
    public synchronized V removeByKey(K key) throws DictionaryException {
        return super.removeByKey(key);
    }

    @Override
    public synchronized boolean isDefined(K key) {
        return super.isDefined(key);
    }

    @Override
    public synchronized Map<K, V> getContent() {
        return super.getContent();
    }

    @Override
    public synchronized void setContent(Map<K, V> newContent) {
        super.setContent(newContent);
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }
}
