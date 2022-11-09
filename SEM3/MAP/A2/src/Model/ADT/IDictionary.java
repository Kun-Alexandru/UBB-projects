package Model.ADT;

import Model.Exceptions.DictionaryException;

public interface IDictionary<K, V> {
    void put(K Key, V Value);
    V lookUp(K key) throws DictionaryException;

    void update(K Key, V Val) throws DictionaryException;
    boolean isVarDef(K key);
}
