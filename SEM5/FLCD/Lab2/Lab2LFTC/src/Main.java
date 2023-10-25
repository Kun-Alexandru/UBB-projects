public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(13);

        symbolTable.add("a");
        symbolTable.add("b");
        symbolTable.add("ab");
        symbolTable.add("dddd");
        symbolTable.add("ba");

        System.out.println(symbolTable.getHashTable());
    }
}