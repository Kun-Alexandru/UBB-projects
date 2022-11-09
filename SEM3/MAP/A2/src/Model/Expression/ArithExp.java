package Model.Expression;

import Model.ADT.IDictionary;
import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class ArithExp implements IExp {
    private IExp exp1;
    private IExp exp2;
    private char operation;

    public ArithExp(IExp e1, IExp e2, char op) {
        this.exp1 = e1;
        this.exp2 = e2;
        this.operation = op;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symbolTable) throws ExpressionException, DictionaryException {
        IValue val1, val2;
        val1 = exp1.eval(symbolTable);
        if (val1.getType().equals(new IntType())) {
            val2 = exp2.eval(symbolTable);
            if (val2.getType().equals(new IntType())) {
                IntValue Val1 = (IntValue) val1;
                IntValue Val2 = (IntValue) val2;
                int firstNumber, secondNumber;
                firstNumber = Val1.getValue();
                secondNumber = Val2.getValue();
                if(this.operation == '+')
                    return new IntValue(firstNumber + secondNumber);
                else if(this.operation == '-')
                    return new IntValue(firstNumber - secondNumber);
                else if(this.operation == '*')
                    return new IntValue(firstNumber * secondNumber);
                else if(this.operation == '/')
                    if (secondNumber == 0) {
                        throw new ExpressionException("Error : division by 0.");
                    }
                    else {
                        return new IntValue(firstNumber / secondNumber);
                    }
                else
                    throw new ExpressionException("Error : given operation is not (+ - * /).");
                }
            else
            {
                throw new ExpressionException("Error : number2 is not int.");
            }
        }
        else
        {
            throw new ExpressionException("Error : number1 is not int.");
        }
    }

    @Override
    public IExp deepCopy() {
        return new ArithExp(this.exp1.deepCopy(), this.exp2.deepCopy(), this.operation);
    }

    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.operation + " " + this.exp2.toString();
    }
}
