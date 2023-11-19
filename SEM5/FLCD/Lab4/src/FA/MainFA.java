package FA;

import java.util.Scanner;

public class MainFA {

    public static void menu(){
        System.out.println("1 - Show states");
        System.out.println("2 - Show alphabet");
        System.out.println("3 - Show transitions");
        System.out.println("4 - Show initial state");
        System.out.println("5 - Show final states");
        System.out.println("6 - Is DFA?");
        System.out.println("7 - Verify if a sequence is accepted by FA");
        System.out.println("0 - Exit \n");
    }

    public static void main(String[] args) {
        FiniteAutomata fa = new FiniteAutomata("FA.in");
        fa.readFromFile();
//        System.out.println(fa);
//        System.out.println(fa.isDFA());

        boolean stop = false;
        while(!stop){
            menu();
            Scanner in = new Scanner(System.in);
            int option = in.nextInt();
            switch (option){
                case 0:
                    stop = true;
                    break;
                case 1:
                    System.out.println(fa.getSetOfStates().toString());
                    break;
                case 2:
                    System.out.println(fa.getAlphabet().toString());
                    break;
                case 3:
                    System.out.println(fa.getTransitions().toString());
                    break;
                case 4:
                    System.out.println(fa.getInitialState());
                    break;
                case 5:
                    System.out.println(fa.getSetOfFinalStates().toString());
                    break;
                case 6:
                    System.out.println(fa.isDFA());
                    break;
                case 7:
                    System.out.println("Input sequence: ");
                    String sequence = in.next();
                    System.out.println(fa.isAccepted(sequence));
                    System.out.println(sequence);
            }
        }
    }

}
