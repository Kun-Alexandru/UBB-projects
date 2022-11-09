package Model.Statement;

import Model.ADT.IDictionary;
import Model.Exceptions.StatementException;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

public class VarDeclStmt implements IStmt {
    private String name;
    private IType type;

    public VarDeclStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState currentState) throws StatementException {
        IDictionary<String, IValue> symbolTable = currentState.getSymTable();
        if (symbolTable.isVarDef(this.name)) {
            throw new StatementException("The given variable " + this.name + " is already declared.");
        } else {
            symbolTable.put(this.name, this.type.getDefaultValue());
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(this.name, this.type.deepCopy());
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.name;
    }
}
