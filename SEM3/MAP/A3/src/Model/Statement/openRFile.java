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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt {
    private IExp exp;

    public openRFile(IExp e) {
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState currentState) throws StatementException, ExpressionException, DictionaryException, FileException {
        IValue val = this.exp.eval(currentState.getSymbolTable());
        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            if (currentState.getFileTable().isDefined(stringValue)) {
                throw new StatementException("The given string value already is a key in the file table.");
            }
            try {
                BufferedReader fileDescriptor = new BufferedReader(new FileReader(stringValue.getValue()));
                currentState.getFileTable().addKeyValuePair(stringValue, fileDescriptor);
            } catch (FileNotFoundException e) {
                throw new FileException("The given file (" + stringValue.getValue() + ") was not found");
            }
        }
        else {
            throw new StatementException("The given expression is not evaluated to a string");
        }
        return currentState;
    }

    @Override
    public String toString() {
        return "openFile " + this.exp.toString();
    }

    @Override
    public IStmt deepCopy() {
        return new openRFile(this.exp.deepCopy());
    }
}
