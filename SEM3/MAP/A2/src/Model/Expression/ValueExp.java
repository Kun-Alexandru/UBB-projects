package Model.Expression;

import Model.ADT.IDictionary;
import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Value.IValue;

public class ValueExp implements IExp {
    private IValue val;

    public ValueExp(IValue v) {
        this.val = v;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symbolTable) throws ExpressionException, DictionaryException {
        return this.val;
    }

    @Override
    public IExp deepCopy() {
        return new ValueExp(this.val.deepCopy());
    }

    @Override
    public String toString() {
        return this.val.toString();
    }
}
