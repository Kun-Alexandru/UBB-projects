
import java.util.Scanner;

public class Main {

    private static void displayMenu()
    {
        System.out.println("\n");
        System.out.println("1. Print set of NonTerminals");
        System.out.println("2. Print set of terminals");
        System.out.println("3. Print set of productions");
        System.out.println("4. Print productions for a given NonTerminal");
        System.out.println("5. Context Free Grammar check ");
        System.out.println("6. Print starting symbol");
        System.out.println("0. Exit ");
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Grammar gr = new Grammar("src/g1.txt");
        //Grammar gr = new Grammar("src/g2.txt");

        gr.readFromFile();

        Scanner read = new Scanner(System.in);
        while(true) {
            displayMenu();
            System.out.println("Enter option: ");
            String option = read.nextLine();

            switch (option) {
                case "1":
                    System.out.println("NonTerminals:");
                    System.out.println(gr.getSetOfNonTerminals());
                    break;
                case "2":
                    System.out.println("Terminals: ");
                    System.out.println(gr.getSetOfTerminals());
                    break;
                case "3":
                    System.out.println("Productions:");
                    System.out.println(gr.getListOfProductions());
                    break;
                case "4":
                    System.out.println("Give NonTerminal:");
                    String nonTerminal = read.nextLine();
                    try {
                        System.out.println("Productions of a given NonTerminal:");
                        System.out.println(gr.getProductionsForNonTerminal(nonTerminal));
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "5":
                    System.out.println("Context Free grammar check");
                    System.out.println(gr.isCFG());
                    break;
                case "6":
                    System.out.println("Starting symbol");
                    System.out.println(gr.getStartingSymbol());
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong option!");
            }
        }
    }
}