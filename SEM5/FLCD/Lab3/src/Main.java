
public class Main {
    public static void main(String[] args) {

        SymbolTable identifST1 = new SymbolTable(13);
        SymbolTable constST1 = new SymbolTable(13);
        PIF pifTable1 = new PIF();

        SymbolTable identifST2 = new SymbolTable(13);
        SymbolTable constST2 = new SymbolTable(13);
        PIF pifTable2 = new PIF();

        SymbolTable identifST3 = new SymbolTable(13);
        SymbolTable constST3 = new SymbolTable(13);
        PIF pifTable3 = new PIF();

        SymbolTable identifSTErr = new SymbolTable(13);
        SymbolTable constSTErr = new SymbolTable(13);
        PIF pifTableErr = new PIF();

        MyScanner scanner1 = new MyScanner(identifST1,constST1,pifTable1,"src/programs/in/p1.txt");
        MyScanner scanner2 = new MyScanner(identifST2,constST2,pifTable2,"src/programs/in/p2.txt");
        MyScanner scanner3 = new MyScanner(identifST3,constST3,pifTable3,"src/programs/in/p3.txt");
        MyScanner scannerErr = new MyScanner(identifSTErr,constSTErr,pifTableErr,"src/programs/in/p1err.txt");

        scanner1.scan();
        scanner1.outputScanner("src/programs/out/p1.out");

        scanner2.scan();
        scanner2.outputScanner("src/programs/out/p2.out");

        scanner3.scan();
        scanner3.outputScanner("src/programs/out/p3.out");

        scannerErr.scan();
        scannerErr.outputScanner("src/programs/out/p1err.out");
    }
}