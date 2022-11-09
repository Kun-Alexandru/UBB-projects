package Model.Statement;

import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Value.IValue;

public class PrintStmt implements IStmt {
    private IExp exp;

    public PrintStmt(IExp e) {
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws ExpressionException, DictionaryException {
        IValue expressionValue = this.exp.eval(state.getSymTable());
        state.getOutput().add(expressionValue);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "print(" + this.exp.toString() + ")";
    }
}
