package Controller;

import Model.ADT.IStack;
import Model.ADT.MyList;
import Model.Exceptions.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Value.IValue;
import Repository.IRepository;

import java.io.IOException;

public class Controller {
    private IRepository repo;
    private boolean displayFlag;

    public Controller(IRepository repo) {
        this.repo = repo;
        this.displayFlag = false;
    }

    public void addProgramState(PrgState prg) {
        this.repo.addProgramState(prg);
    }

    public PrgState oneStep(PrgState currentState) throws ControllerException, StackException, StatementException, ExpressionException, DictionaryException, FileException {
        IStack<IStmt> executionStack = currentState.getExecutionStack();
        if (executionStack.isEmpty()) {
            throw new ControllerException("Program state's execution stack is empty.");
        }
        IStmt topStatement = executionStack.pop();
        return topStatement.execute(currentState);
    }

    public void allSteps() throws ControllerException, StatementException, StackException, ExpressionException, DictionaryException, ListException, FileException, IOException {
        PrgState prgState = this.repo.getCurrentProgramState();
        if (prgState.getExecutionStack().isEmpty()) {
            throw new ControllerException("ERROR: The program was already executed. The execution stack is empty.");
        }
        this.repo.logPrgStateExec();
        if (this.displayFlag) {
            System.out.println("Program execution started:");
            System.out.print(prgState + "\n");
        }
        int outputListSize = 0;
        MyList<IValue > output;
        while (!prgState.getExecutionStack().isEmpty()) {
            this.oneStep(prgState);
            this.repo.logPrgStateExec();
            if (this.displayFlag) {
                System.out.println(prgState);
            } else {
                output = prgState.getOutput();
                if (outputListSize != output.size()) {
                    outputListSize = output.size();
                    System.out.println(output.getElemAtIndex(output.size() - 1).toString());
                }
            }
        }
    }

    void turnDisplayFlagOn() {
        this.displayFlag = true;
    }

    void turnDisplayFlagOff() {
        this.displayFlag = false;
    }
}
