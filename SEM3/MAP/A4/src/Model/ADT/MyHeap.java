package Model.ADT;

import Model.Exceptions.HeapException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<V> implements IHeap<V> {
    private HashMap<Integer, V> elems;
    private int nextFreeLocation;

    public MyHeap() {
        this.elems = new HashMap<Integer, V>();
        this.nextFreeLocation = 1;
    }

    @Override
    public int addNewHeapEntry(V value) {
        this.elems.put(this.nextFreeLocation, value);
        this.nextFreeLocation = this.nextFreeLocation + 1;
        return this.nextFreeLocation - 1;   // returns the address on which the value was stored
    }

    @Override
    public V getHeapValue(int address) throws HeapException {
        if (!this.elems.containsKey(address)) {
            throw new HeapException("ERROR: Could not return heap value. The given address is not a key in the heap table.");
        }
        else {
            return this.elems.get(address);   // if we get here, the function call will always succeed
        }
    }

    @Override
    public void updateHeapEntry(int address, V newValue) throws HeapException {
        if (!this.elems.containsKey(address)) {
            throw new HeapException("ERROR: Could not update heap entry. The given address is not a key in the heap table");
        }
        else {
            this.elems.put(address, newValue);
        }
    }

    @Override
    public boolean isDefined(int address) {
        return this.elems.containsKey(address);
    }

    @Override
    public void setContent(Map<Integer, V> newContent) {
        this.elems.clear();
        this.elems.putAll(newContent);
    }

    @Override
    public Map<Integer, V> getContent() {
        return this.elems;
    }

    @Override
    public String toString() {
        StringBuilder elemsInString = new StringBuilder();
        int i = 0;
        for (Map.Entry<Integer, V> entry: this.elems.entrySet()) {
            int key = entry.getKey();
            V value = entry.getValue();
            elemsInString.append(key);
            elemsInString.append(" --> ");
            elemsInString.append(value.toString());
            if (i < this.elems.size() - 1) {
                elemsInString.append("\n");
            }
            i += 1;
        }
        return elemsInString.toString();
    }
}
