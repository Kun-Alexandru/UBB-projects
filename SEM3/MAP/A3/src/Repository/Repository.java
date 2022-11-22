package Repository;
import Model.Exceptions.FileException;
import Model.ProgramState.PrgState;

import java.io.*;
import java.util.ArrayList;

public class Repository implements IRepository {
    private ArrayList<PrgState> elems;
    private String logFilePath;
    boolean firstTimeWriting;

    public Repository(PrgState prgState, String logFilePath) throws IOException {
        this.elems = new ArrayList<PrgState>();
        this.elems.add(prgState);
        this.logFilePath = logFilePath;
        this.firstTimeWriting = false;
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, false)));
        logFile.print("");
        logFile.flush();
        logFile.close();
    }

    @Override
    public PrgState getCurrentProgramState() {
        return this.elems.get(0);
    }

    @Override
    public void addProgramState(PrgState newPrgState) {
        this.elems.set(0, newPrgState);
    }

    @Override
    public void logPrgStateExec() throws FileException {
        PrintWriter logFile;
        try {
            if (this.firstTimeWriting) {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, false)));
                this.firstTimeWriting = false;
            }
            else {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            }
        }
        catch (IOException e) {
            throw new FileException("The file cannot be opened/created/doesn't exist.");
        }
        logFile.println(this.getCurrentProgramState().toString());
        logFile.flush();
        logFile.close();
    }
}
