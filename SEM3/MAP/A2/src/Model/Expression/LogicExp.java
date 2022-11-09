package Model.Expression;

import Model.ADT.IDictionary;
import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class LogicExp implements IExp {
    private IExp exp1;
    private IExp exp2;
    private String operation;

    public LogicExp(IExp e1, IExp e2, String op) {
        this.exp1 = e1;
        this.exp2 = e2;
        this.operation = op;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symbolTable) throws ExpressionException, DictionaryException {
        IValue val1, val2;
        val1 = exp1.eval(symbolTable);
        if (val1.getType().equals(new BoolType())) {
            val2 = exp2.eval(symbolTable);
            if (val2.getType().equals(new BoolType())) {
                BoolValue boolVal1 = (BoolValue) val1;
                BoolValue boolVal2 = (BoolValue) val2;
                boolean bool1, bool2;
                bool1 = boolVal1.getValue();
                bool2 = boolVal2.getValue();
                if(this.operation.equals("&&"))
                    return new BoolValue(bool1 && bool2);
                else if(this.operation.equals("||"))
                    return new BoolValue(bool1 || bool2);
                else
                    throw new ExpressionException("Error : given operation is not (&& ||)");
            } else {
                throw new ExpressionException("Error : bool2 is not bool.");
            }
        } else {
            throw new ExpressionException("Error : bool1 is not bool.");
        }
    }

    @Override
    public IExp deepCopy() {
        return new LogicExp(this.exp1.deepCopy(), this.exp2.deepCopy(), this.operation);
    }

    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.operation + " " + this.exp2.toString();
    }
}
