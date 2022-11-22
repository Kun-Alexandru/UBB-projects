package Model.Statement;

import Model.ADT.IDict;
import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Exceptions.StatementException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

public class AssignStmt implements IStmt {
    private String id;
    private IExp expression;

    public AssignStmt(String id, IExp expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState currentState) throws StatementException, ExpressionException, DictionaryException {
        IDict<String, IValue> symbolTable = currentState.getSymbolTable();
        if (symbolTable.isDefined(this.id)) {
            IValue val = this.expression.eval(symbolTable);
            IType type = symbolTable.lookUp(this.id).getType();
            if (val.getType().equals(type)) {
                symbolTable.addKeyValuePair(this.id, val);
            } else {
                throw new StatementException("Declared type of variable " + id + " and type of the assigned expression do not match.");
            }
        } else {
            throw new StatementException("Variable " + id + " was not previously declared.");
        }
        return currentState;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(this.id, this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return this.id + " = " + this.expression.toString();
    }
}
