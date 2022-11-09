package Controller;

import Model.ADT.IStack;
import Model.Exceptions.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Repository.IRepository;

public class Controller {
    private final IRepository repo;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void addProgramState(PrgState prg) {
        this.repo.addProgramState(prg);
    }

    public PrgState oneStep(PrgState currentState) throws ControllerException, StackException, StatementException, ExpressionException, DictionaryException {
        IStack<IStmt> executionStack = currentState.getExeStack();
        if (executionStack.isEmpty()) {
            throw new ControllerException("Error : exe stack is empty.");
        }
        IStmt topStatement = executionStack.pop();
        return topStatement.execute(currentState);
    }

    public void allSteps() throws ControllerException, StatementException, StackException, ExpressionException, DictionaryException, ListException {
        PrgState prgState = this.repo.getCurrentProgramState();
        while (!prgState.getExeStack().isEmpty()) {
            this.oneStep(prgState);
            System.out.println("========================");
            System.out.println(prgState);
        }
    }
}
