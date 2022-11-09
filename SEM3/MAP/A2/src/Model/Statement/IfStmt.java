package Model.Statement;

import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Exceptions.StatementException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class IfStmt implements IStmt {
    private IExp expression;
    private IStmt thenStatement;
    private IStmt elseStatement;

    public IfStmt(IExp expression, IStmt thenStatement, IStmt elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public PrgState execute(PrgState currentState) throws StatementException, ExpressionException, DictionaryException {
        IValue conditional = this.expression.eval(currentState.getSymTable());
        if (conditional.getType().equals(new BoolType())) {
            BoolValue boolConditional = (BoolValue) conditional;
            if (boolConditional.getValue()) {
                currentState.getExeStack().push(this.thenStatement);
            } else {
                currentState.getExeStack().push(this.elseStatement);
            }
        } else {
            throw new StatementException("Conditional expression is not a boolean.");
        }
        return currentState;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }

    @Override
    public String toString() {
        return "IF (" + this.expression.toString() + ") " +
                "THEN (" + this.thenStatement.toString() + ") " +
                "ELSE (" + this.elseStatement.toString() + ")";
    }
}
