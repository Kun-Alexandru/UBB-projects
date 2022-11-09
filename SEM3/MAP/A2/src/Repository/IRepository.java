package Repository;

import Model.ProgramState.PrgState;

public interface IRepository {
    PrgState getCurrentProgramState();
    void addProgramState(PrgState newPrgState);
}
