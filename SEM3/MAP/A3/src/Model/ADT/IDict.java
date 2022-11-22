package Model.ADT;

import Model.Exceptions.DictionaryException;

public interface IDict<K, V> {
    void addKeyValuePair(K newKey, V newValue);
    V lookUp(K key) throws DictionaryException;
    V removeByKey(K key) throws DictionaryException;
    boolean isDefined(K key);
}
