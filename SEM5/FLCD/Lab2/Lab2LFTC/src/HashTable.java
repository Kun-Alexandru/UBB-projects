import java.util.ArrayList;

public class HashTable {
    private Integer size;
    private ArrayList<ArrayList<String>> hashTable;

    public HashTable(Integer size){
        this.size = size;
        this.hashTable = new ArrayList<>();
        for(int i = 0; i < size; i++){
            this.hashTable.add(new ArrayList<>());
        }
    }

    public String findByPos(Pair pos){
        if(this.hashTable.size() <= pos.getFirst() || this.hashTable.get(pos.getFirst()).size() <= pos.getSecond()){
            throw new IndexOutOfBoundsException("Invalid position");
        }

        return this.hashTable.get(pos.getFirst()).get(pos.getSecond());
    }

    public Integer getSize(){
        return size;
    }

    public Pair findPositionOfTerm(String term){
        int pos = hash(term);

        if(!hashTable.get(pos).isEmpty()){
            ArrayList<String> elems = this.hashTable.get(pos);
            for(int i = 0; i < elems.size(); i++){
                if(elems.get(i).equals(term)){
                    return new Pair(pos, i);
                }
            }
        }

        return null;
    }

    private Integer hash(String key){
        int sum_chars = 0;
        char[] key_characters = key.toCharArray();
        for(char c: key_characters){
            sum_chars += c;
        }
        return sum_chars % size;
    }

    public boolean contains(String term){
        return this.findPositionOfTerm(term) != null;
    }

    public boolean add(String term){
        if(contains(term)){
            return false;
        }

        Integer pos = hash(term);

        ArrayList<String> elems = this.hashTable.get(pos);
        elems.add(term);

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(i).append(" = ").append(hashTable.get(i)).append("\n");
        }
        return sb.toString();
    }

}
