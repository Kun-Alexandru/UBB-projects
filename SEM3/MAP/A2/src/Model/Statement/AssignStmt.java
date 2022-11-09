package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.MyStack;
import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Exceptions.StatementException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

public class AssignStmt implements IStmt {
    private String id;
    private IExp exp;

    public AssignStmt(String id, IExp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, DictionaryException {
        MyStack<IStmt> stack = state.getExeStack();
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        if (symbolTable.isVarDef(this.id)) {
            IValue val = this.exp.eval(symbolTable);
            IType type = symbolTable.lookUp(this.id).getType();
            if (val.getType().equals(type)) {
                symbolTable.put(this.id, val);
            } else {
                throw new StatementException("Declared type of variable " + id + " and type of the assigned expression do not match.");
            }
        } else {
            throw new StatementException("The used variable " + id + " was not previously declared.");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(this.id, this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return this.id + " = " + this.exp.toString();
    }
}
