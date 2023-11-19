package FA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FiniteAutomata {
    private List<String> setOfStates;
    private List<String> alphabet;
    private List<Transition> transitions;
    private String initialState;
    private List<String> setOfFinalStates;
    private String filename;

    public FiniteAutomata(String filename){
        this.filename = filename;
        setOfStates = new ArrayList<>();
        alphabet = new ArrayList<>();
        transitions = new ArrayList<>();
        initialState = "";
        setOfFinalStates = new ArrayList<>();
    }

    public List<String> getSetOfStates() {
        return setOfStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getSetOfFinalStates() {
        return setOfFinalStates;
    }

    public void readSet(String line, List<String> set){
        String[] tokens = line.split(",");
        set.addAll(Arrays.asList(tokens));
    }

    public void readTransition(String line) throws Exception {
        String[] tokens = line.split(",");
        Transition transition = new Transition();
        transition.setStartState(tokens[0]);
        transition.setValue(tokens[1]);
        transition.setEndState(new ArrayList<>(Arrays.asList(tokens).subList(2, tokens.length)));
        transitions.add(transition);
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
                    readSet(line, setOfStates);
                    countLine++;
                }
                else if(countLine == 1){
                    readSet(line, alphabet);
                    countLine++;
                }
                else if(countLine == 2){
                    initialState = line;
                    countLine++;
                }
                else if(countLine == 3){
                    readSet(line, setOfFinalStates);
                    countLine++;
                }
                else if(countLine == 4){
                    readTransition(line);
                }
            }
            fr.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isDFA(){
        for(int i = 0; i < transitions.size(); i++){
            for(int j = 0; j < transitions.size(); j++)
            {
                if(transitions.get(i).getStartState().equals(transitions.get(j).getStartState()) && transitions.get(i).getValue().equals(transitions.get(j).getValue()) && i != j)
                    return false;
            }
        }
        return true;

    }

    public String getNextState(String startState, String value){
        /*
         * Get list of endStates based on given startState and value from the Transitions of the FA
         */
        List<String> endStates = new ArrayList<>();
        for(Transition transition: transitions){
            if(Objects.equals(transition.getStartState(), startState) &&
                    Objects.equals(transition.getValue(), value)){
                endStates.addAll(transition.getEndState());
            }
        }
        if(endStates.size() == 1){
            return endStates.get(0);
        }
        return "false";
    }

    public boolean isAccepted(String sequence){
        /*
         *
         */
        String startState = initialState;
        String lastEndState = initialState;
        String[] seqElements = sequence.split("");
        for(String value: seqElements){
            String endState = getNextState(startState,value);
            if(Objects.equals(endState, "false")){
                return false;
            }
            startState = endState;
            lastEndState = endState;
        }
        // check if last endState is from the set of finalStates
        return setOfFinalStates.contains(lastEndState);
    }

    @Override
    public String toString(){
        String fa = "";
        fa += "set of states: "+setOfStates.toString()+"\n";
        fa += "alphabet: "+alphabet.toString()+"\n";
        fa += "initial state: "+initialState+"\n";
        fa += "set of final states: "+setOfFinalStates.toString()+"\n";
        fa += "transitions: \n"+transitions.toString();
        return fa;
    }

}
