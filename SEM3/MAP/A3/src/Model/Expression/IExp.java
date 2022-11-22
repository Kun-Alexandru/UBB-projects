package Model.Expression;

import Model.ADT.IDict;
import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Value.IValue;

public interface IExp {
    IValue eval(IDict<String, IValue> symbolTable) throws ExpressionException, DictionaryException;
    IExp deepCopy();
}
