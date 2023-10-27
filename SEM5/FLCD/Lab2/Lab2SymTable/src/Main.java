public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(13);
        symbolTable.addSymbol("ded");
        symbolTable.addSymbol("cccc");
        symbolTable.addSymbol("dde");
        symbolTable.addSymbol("a");
        symbolTable.addSymbol("adwawa");
        symbolTable.addSymbol("eee");

        System.out.println(symbolTable.toString());
    }
}