package Repository;

import Model.ProgramState.PrgState;

import java.util.ArrayList;

public class Repository implements IRepository {
    private ArrayList<PrgState> listOfPrgState;

    public Repository(PrgState prgState) {
        this.listOfPrgState = new ArrayList<PrgState>();
        this.listOfPrgState.add(prgState);
    }

    @Override
    public PrgState getCurrentProgramState() {
        return this.listOfPrgState.get(0);
    }

    @Override
    public void addProgramState(PrgState newPrgState) {
        this.listOfPrgState.set(0, newPrgState);
    }
}
