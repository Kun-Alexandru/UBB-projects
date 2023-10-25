public class SymbolTable {

    private Integer size;
    private HashTable hashTable;


    public SymbolTable(Integer size){
        hashTable = new HashTable(size);
    }

    public String findByPos(Pair pos){
        return hashTable.findByPos(pos);
    }

    public HashTable getHashTable(){
        return hashTable;
    }

    public Integer getSize(){
        return hashTable.getSize();
    }

    public Pair findPosOfElement(String term){
        return hashTable.findPositionOfTerm(term);
    }

    public boolean containsElement(String term){
        return hashTable.contains(term);
    }

    public boolean add(String term){
        return hashTable.add(term);
    }

}