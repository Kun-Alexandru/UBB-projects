package Model.Statement;

import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Value.IValue;

public class PrintStmt implements IStmt {
    private IExp expression;

    public PrintStmt(IExp e) {
        this.expression = e;
    }

    @Override
    public PrgState execute(PrgState currentState) throws ExpressionException, DictionaryException {
        IValue expressionValue = this.expression.eval(currentState.getSymbolTable());
        currentState.getOutput().add(expressionValue);
        return currentState;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "print(" + this.expression.toString() + ")";
    }
}
