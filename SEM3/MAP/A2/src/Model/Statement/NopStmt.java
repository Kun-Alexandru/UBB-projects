package Model.Statement;

import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Exceptions.StatementException;
import Model.ProgramState.PrgState;

public class NopStmt implements IStmt {

    public NopStmt() {}

    @Override
    public PrgState execute(PrgState currentState) throws StatementException, ExpressionException, DictionaryException {
        return currentState;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public String toString() {
        return "NoOp Statement";
    }
}
