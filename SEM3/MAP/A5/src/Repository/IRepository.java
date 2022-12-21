package Repository;

import Model.Exceptions.FileException;
import Model.ProgramState.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> newProgramStates);
    void addProgramState(ProgramState newProgramState);
    void logPrgStateExec(ProgramState programState) throws FileException;
}
