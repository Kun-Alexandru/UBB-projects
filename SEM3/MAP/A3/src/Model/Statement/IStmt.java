package Model.Statement;

import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Exceptions.FileException;
import Model.Exceptions.StatementException;
import Model.ProgramState.PrgState;

public interface IStmt {
    PrgState execute(PrgState currentState) throws StatementException, ExpressionException, DictionaryException, FileException;
    IStmt deepCopy();
}
