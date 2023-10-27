import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.Objects;

public class HashTable {
    private final ArrayList<ArrayList<String>> elements;
    private final int size;

    public HashTable(int size) {
        this.elements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.elements.add(new ArrayList<>());
        }
        this.size = size;
    }

    private int hash(String key) {
        int sumOfAscii = 0;
        for (int i = 0; i < key.length(); i++) {
            sumOfAscii += key.charAt(i);
        }
        return sumOfAscii % this.size;
    }

    private boolean isKey(int hashPosition, String key) {
        return elements.get(hashPosition).contains(key);
    }

    public AbstractMap.SimpleEntry<Integer, Integer> addSymbol(String symbol) {
        int hashPosition = hash(symbol);

        if (isKey(hashPosition, symbol)) {
            int listPosition = elements.get(hashPosition).indexOf(symbol);
            return new AbstractMap.SimpleEntry<>(hashPosition, listPosition);
        } else {
            elements.get(hashPosition).add(symbol);
            return new AbstractMap.SimpleEntry<>(hashPosition, elements.get(hashPosition).size() - 1);
        }
    }

    @Override
    public String toString() {
        String stString = "SymbolTable (implemented as a hashTable)\n";
        for (int i = 0; i < size; i++) {
            stString += "( ";
            for (String item : elements.get(i)) {
                stString += item + ", ";
            }
            stString += " )\n";
        }
        return stString;
    }
}