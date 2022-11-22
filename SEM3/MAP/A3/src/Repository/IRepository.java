package Repository;

import Model.Exceptions.FileException;
import Model.ProgramState.PrgState;

import java.io.IOException;

public interface IRepository {
    PrgState getCurrentProgramState();
    void addProgramState(PrgState newPrgState);
    void logPrgStateExec() throws IOException, FileException;
}
