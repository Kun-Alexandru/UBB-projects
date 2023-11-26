import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Grammar {
    private List<String> setOfTerminals;
    private List<String> setOfNonTerminals;
    private List<Production> listOfProductions;
    private String startingSymbol;
    private String filename;

    public Grammar(String filename){
        this.filename = filename;
        this.setOfNonTerminals = new ArrayList<>();
        this.setOfTerminals = new ArrayList<>();
        this.listOfProductions = new ArrayList<>();
        this.startingSymbol = "";
    }

    public List<String> getSetOfTerminals() {
        return setOfTerminals;
    }

    public List<String> getSetOfNonTerminals() {
        return setOfNonTerminals;
    }

    public List<Production> getListOfProductions() {
        return listOfProductions;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public List<String> getProductionsForNonTerminal(String nonTerminal) throws Exception {
        if(!setOfNonTerminals.contains(nonTerminal)){
            throw new Exception("Given nonTerminal does not exist");
        }
        List<String> prodValues = new ArrayList<>();
        for(Production production: listOfProductions){
            if(Objects.equals(production.getKey(), nonTerminal)){
                prodValues.addAll(production.getValues());
            }
        }
        return prodValues;
    }

    public List<String> getSymbolsOfRHS(String productionRHS) {
        String[] tokens = productionRHS.split(" ");
        return Arrays.asList(tokens);
    }

    public void readSet(String line, List<String> set) {
        String[] tokens = line.split(" ");
        set.addAll(Arrays.asList(tokens));
    }

    public void readProductions(String line) {
        String[] prodSplit = line.split(" -> ");
        Production production = new Production();
        production.setKey(prodSplit[0]);
        String[] statesSplit = prodSplit[1].split(" \\| ");
        production.setValues(new ArrayList<>(Arrays.asList(statesSplit)));
        listOfProductions.add(production);
    }

    public void readFromFile(){
        File file=new File(filename);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int countLine = 0;
            while ((line = br.readLine()) != null) {
                if(countLine == 0){
                    readSet(line, setOfNonTerminals);
                    countLine++;
                }
                else if(countLine == 1){
                    readSet(line, setOfTerminals);
                    countLine++;
                }
                else if(countLine == 2){
                    startingSymbol = line;
                    countLine++;
                }
                else if(countLine == 3){
                    readProductions(line);
                }
            }
            fr.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isCFG() {
        try {
            // Check each production for validity
            for (Production production : listOfProductions) {
                String key = production.getKey();

                // Check if the key is a single non-terminal
                if (key.split(" ").length > 1) {
                    System.out.println("Error: Production key contains more than one symbol.");
                    return false;
                }

                // Check if the key is in the set of non-terminals
                if (!setOfNonTerminals.contains(key)) {
                    System.out.println("Error: Production key is not in the set of non-terminals.");
                    return false;
                }

                // Check each value in the production
                for (String value : production.getValues()) {
                    // Split the value into symbols
                    List<String> symbols = getSymbolsOfRHS(value);

                    // Check if each symbol is in the set of terminals or non-terminals
                    for (String symbol : symbols) {
                        if (!setOfTerminals.contains(symbol) && !setOfNonTerminals.contains(symbol)) {
                            System.out.println("Error: Symbol '" + symbol + "' is not in the set of terminals or non-terminals.");
                            return false;
                        }
                    }
                }
            }

            // Check if the starting symbol is in the set of non-terminals
            if (!setOfNonTerminals.contains(startingSymbol)) {
                System.out.println("Error: Starting symbol is not in the set of non-terminals.");
                return false;
            }

            // Check for left recursion
            for (Production production : listOfProductions) {
                String key = production.getKey();
                for (String value : production.getValues()) {
                    List<String> symbols = getSymbolsOfRHS(value);
                    if (symbols.get(0).equals(key)) {
                        System.out.println("Error: Left recursion detected in production " + production);
                        return false;
                    }
                }
            }

            // Add more checks as needed

            // If all checks pass, the grammar is a context-free grammar
            return true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Grammar{" +
                "setOfTerminals=" + setOfTerminals +
                ", setOfNonTerminals=" + setOfNonTerminals +
                ", listOfProductions=" + listOfProductions +
                ", startingSymbol='" + startingSymbol + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
