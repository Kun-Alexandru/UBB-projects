package Model.Statement;

import Model.Exceptions.DictionaryException;
import Model.Exceptions.ExpressionException;
import Model.Exceptions.FileException;
import Model.Exceptions.StatementException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    private IExp expression;

    public closeRFile(IExp expr) {
        this.expression = expr;
    }

    @Override
    public PrgState execute(PrgState currentState) throws StatementException, ExpressionException, DictionaryException, FileException {
        IValue val =  this.expression.eval(currentState.getSymbolTable());
        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            BufferedReader fileDescriptor = currentState.getFileTable().lookUp(stringValue);
            try {
                fileDescriptor.close();
            } catch (IOException e) {
                throw new FileException("Failed to close the file " + stringValue);
            }
            currentState.getFileTable().removeByKey(stringValue);
        }
        else {
            throw new StatementException("The given expression (" + this.expression.toString() + ") is not a String");
        }
        return currentState;
    }

    @Override
    public String toString() {
        return "closeFile " + this.expression.toString();
    }

    @Override
    public IStmt deepCopy() {
        return new closeRFile(this.expression);
    }
}
