package Model.Expression;

import Model.ADT.IDictionary;
import Model.Exceptions.DictionaryException;
import Model.Value.IValue;

public class VarExp implements IExp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symbolTable) throws DictionaryException {
        return symbolTable.lookUp(this.id);
    }

    @Override
    public IExp deepCopy() {
        return new VarExp(this.id);
    }

    @Override
    public String toString() {
        return this.id;
    }
}
