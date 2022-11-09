package Model.Statement;

import Model.ADT.IStack;
import Model.ProgramState.PrgState;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt stmt1, IStmt stmt2) {
        this.first = stmt1;
        this.second = stmt2;
    }

    @Override
    public PrgState execute(PrgState state) {
        IStack<IStmt> executionStack = state.getExeStack();
        executionStack.push(this.second);
        executionStack.push(this.first);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(this.first.deepCopy(), this.second.deepCopy());
    }

    @Override
    public String toString() {
        return "(" + this.first.toString() + "; " + this.second.toString() + ")";
    }
}
