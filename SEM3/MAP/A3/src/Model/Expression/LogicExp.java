package Model.Expression;

import Model.ADT.IDict;
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
    public IValue eval(IDict<String, IValue> symbolTable) throws ExpressionException, DictionaryException {
        IValue val1, val2;
        val1 = exp1.eval(symbolTable);
        if (val1.getType().equals(new BoolType())) {
            val2 = exp2.eval(symbolTable);
            if (val2.getType().equals(new BoolType())) {
                BoolValue boolVal1 = (BoolValue) val1;
                BoolValue boolVal2 = (BoolValue) val2;
                boolean b1, b2;
                b1 = boolVal1.getValue();
                b2 = boolVal2.getValue();
                return switch (this.operation) {
                    case "&&" -> new BoolValue(b1 && b2);
                    case "||" -> new BoolValue(b1 || b2);
                    case "^" -> new BoolValue(b1 ^ b2);
                    default -> throw new ExpressionException("Invalid logical operation given.");
                };
            } else {
                throw new ExpressionException("Second operand is not a bool.");
            }
        } else {
            throw new ExpressionException("First operand is not a bool.");
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
