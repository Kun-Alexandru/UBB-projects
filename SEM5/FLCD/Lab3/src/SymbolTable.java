import java.util.AbstractMap;

public class SymbolTable {
    private final HashTable hashTable;

    public SymbolTable(int size) {
        hashTable = new HashTable(size);
    }

    public AbstractMap.SimpleEntry<Integer, Integer> addSymbol(String symbol) {
        return hashTable.addSymbol(symbol);
    }

    @Override
    public String toString() {
        return hashTable.toString();
    }
}