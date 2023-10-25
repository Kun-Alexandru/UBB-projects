public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(13);

        symbolTable.add("a3a");
        symbolTable.add("3aa");
        symbolTable.add("aa3");
        symbolTable.add("dddd");
        symbolTable.add("ba");

        System.out.println(symbolTable.getHashTable());
    }
}
