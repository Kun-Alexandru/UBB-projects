package Model.Expression;

import Model.ADT.IDictionary;
import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Value.IValue;

public interface IExp {
    IValue eval(IDictionary<String, IValue> symbolTable) throws ExpressionException, DictionaryException;
    IExp deepCopy();
}
