package Model.Statement;

import Model.ADT.IStack;
import Model.ProgramState.PrgState;

public class CompStmt implements IStmt {
    private IStmt statement1;
    private IStmt statement2;

    public CompStmt(IStmt s1, IStmt s2) {
        this.statement1 = s1;
        this.statement2 = s2;
    }

    @Override
    public PrgState execute(PrgState currentState) {
        IStack<IStmt> executionStack = currentState.getExecutionStack();
        executionStack.push(this.statement2);
        executionStack.push(this.statement1);
        return currentState;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(this.statement1.deepCopy(), this.statement2.deepCopy());
    }

    @Override
    public String toString() {
        return "(" + this.statement1.toString() + "; " + this.statement2.toString() + ")";
    }
}
