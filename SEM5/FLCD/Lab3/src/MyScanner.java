import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyScanner {
    private final PIF pifTable;
    private final SymbolTable constSymbolTable;
    private final SymbolTable identifSymbolTable;
    private final String programFile;
    private static final String ID = "id";
    private static final String CONST = "const";

    public MyScanner(SymbolTable identifSymbolTable, SymbolTable constSymbolTable, PIF pifTable, String programFile) {
        this.programFile = programFile;
        this.identifSymbolTable = identifSymbolTable;
        this.constSymbolTable = constSymbolTable;
        this.pifTable = pifTable;
    }

    public Boolean isIdentifier(String symbol){
        String identifierRegex = "^([a-zA-Z][a-zA-Z\\d]*)$";
        Pattern p = Pattern.compile(identifierRegex);
        if (symbol == null) {
            return false;
        }
        Matcher m = p.matcher(symbol);
        return m.matches();
    }

    public Boolean isConstant(String symbol){
        String identifierRegex = "^([a-zA-Z_][a-zA-Z_\\d]*)$";
        Pattern p;
        if (symbol == null) {
            return false;
        }
        // check if digit
        String digitRegex = "-?[1-9]\\d*|0";
        p = Pattern.compile(digitRegex);
        Matcher m1 = p.matcher(symbol);
        // check if letter
        String charRegex = "'[a-zA-z_\\d]'";
        p = Pattern.compile(charRegex);
        Matcher m2 = p.matcher(symbol);
        // check if string
        Matcher m3 = null;
        if(symbol.charAt(0) == '"' && symbol.charAt(symbol.length()-1) == '"'){
            StringBuffer sb= new StringBuffer(symbol);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
            p = Pattern.compile(identifierRegex);
            m3 = p.matcher(sb);
        }
        return m1.matches() || m2.matches() || m3!=null && m3.matches();
    }

    public boolean isToken(String symbol) {
        File file=new File("src/programs/in/token.in");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (Objects.equals(line, symbol)) {
                    fr.close();
                    return true;
                }
            }
            fr.close();
            return false;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void addST(String symbol) throws Exception {
        if (!this.isToken(symbol)) {
            if (symbol.startsWith("`")) {
                String identifier = symbol.substring(1);
                if (isIdentifier(identifier)) {
                    AbstractMap.SimpleEntry<Integer, Integer> positionST = identifSymbolTable.addSymbol(identifier);
                    pifTable.add(positionST, ID);
                }
                else {
                    throw new Exception("Lexical error " + "symbol: " + symbol);
                }
            } else if (this.isIdentifier(symbol)) {
                identifSymbolTable.addSymbol(symbol);
            } else if (this.isConstant(symbol)) {
                constSymbolTable.addSymbol(symbol);
            }
        }
    }

    public void addPIF(String symbol) throws Exception {
        if (symbol.startsWith("`")) {
            pifTable.add(new AbstractMap.SimpleEntry<>(-1, -1), "`");
            if (symbol.length() > 1) {
                // Add the identifier without the backtick to the identifier symbol table
                String identifier = symbol.substring(1);
                if (isIdentifier(identifier)) {
                    AbstractMap.SimpleEntry<Integer, Integer> positionST = identifSymbolTable.addSymbol(identifier);
                    pifTable.add(positionST, ID);
                }
                else {
                    throw new Exception("Lexical error " + "symbol: " + symbol);
                }
            }
        } else if (isToken(symbol)) {
            pifTable.add(new AbstractMap.SimpleEntry<>(-1, -1), symbol);
        } else if (isIdentifier(symbol)) {
            AbstractMap.SimpleEntry<Integer, Integer> positionST = identifSymbolTable.addSymbol(symbol);
            pifTable.add(positionST, ID);
        } else if (isConstant(symbol)) {
            AbstractMap.SimpleEntry<Integer, Integer> positionST = constSymbolTable.addSymbol(symbol);
            pifTable.add(positionST, CONST);
        } else if (symbol.matches("\"[^\"]*\"")) {
            //it's a string
            AbstractMap.SimpleEntry<Integer, Integer> positionST = constSymbolTable.addSymbol(symbol);
            pifTable.add(positionST, CONST);
        } else {
            throw new Exception("Lexical error " + "symbol: " + symbol);
        }
    }



    public void scanLine(String line) throws Exception {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean insideString = false;

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                insideString = !insideString; // Toggle inside/outside of a string
                currentToken.append(c);
            } else if (insideString) {
                currentToken.append(c);
            } else if (c == ' ' || c == ',' || c == ';') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                if (c != ' ') {
                    tokens.add(String.valueOf(c));
                }
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        for (String symbol : tokens) {
            if (!symbol.isEmpty()) {
                addST(symbol);
                addPIF(symbol);
            }
        }
    }

    public void scan(){
        BufferedReader reader;
        int lineNo = 1;
        List<String> errors = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(programFile));
            String line = reader.readLine();
            while (line != null) {
                try {
                    scanLine(line);
                } catch (Exception e) {
                    errors.add(e.getMessage() + " line: " + lineNo);
                }
                lineNo++;
                line = reader.readLine();
            }
            reader.close();
            if (errors.isEmpty()) {
                System.out.println("Lexically correct!");
            } else {
                for (String error : errors) {
                    System.out.println(error);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " line: " + lineNo);
        }
    }


    public void outputScanner(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename, false));
            writer.print("");
            writer.print(this.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String[] splitted = programFile.split("\\\\");
        String programName = splitted[splitted.length-1];
        String lexicallyScannedProgram = "Program " + programName +"\n";
        lexicallyScannedProgram += "--------------------------------------------------------------------------------\n";
        lexicallyScannedProgram += "Identifiers\n" + identifSymbolTable;
        lexicallyScannedProgram += "--------------------------------------------------------------------------------\n";
        lexicallyScannedProgram += "Constants\n" + constSymbolTable;
        lexicallyScannedProgram += "--------------------------------------------------------------------------------\n";
        lexicallyScannedProgram += pifTable;
        return lexicallyScannedProgram;
    }

}