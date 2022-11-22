package Model.Statement;

import Model.ADT.IDict;
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
        IDict<String, IValue> symbolTable = currentState.getSymbolTable();
        if (symbolTable.isDefined(this.name)) {
            throw new StatementException("Variable " + this.name + " is already declared.");
        } else {
            symbolTable.addKeyValuePair(this.name, this.type.getDefaultValue());
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
