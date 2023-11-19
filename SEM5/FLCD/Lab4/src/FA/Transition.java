package FA;

import java.util.List;

public class Transition {
    private String startState;
    private String value;
    private List<String> endState;

    public Transition(){

    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getEndState() {
        return endState;
    }

    public void setEndState(List<String> endState) {
        this.endState = endState;
    }

    @Override
    public String toString(){
        return "δ("+startState+", "+value+") = "+endState.toString()+"\n";
    }
}
