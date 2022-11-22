package Model.ProgramState;
import Model.ADT.MyDict;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Statement.IStmt;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrgState {
    private MyStack<IStmt> executionStack;
    private MyDict<String, IValue> symbolTable;
    private MyList<IValue> output;
    private MyDict<StringValue, BufferedReader> fileTable;
    private IStmt originalProgram;

    public PrgState(MyStack<IStmt> exeStack, MyDict<String, IValue> symTable, MyList<IValue> out, MyDict<StringValue, BufferedReader> fileT, IStmt origPrg) {
        this.executionStack = exeStack;
        this.symbolTable = symTable;
        this.output = out;
        this.fileTable = fileT;
        this.originalProgram = origPrg.deepCopy();
        this.executionStack.push(this.originalProgram);
    }

    public MyStack<IStmt> getExecutionStack() {
        return this.executionStack;
    }

    public MyDict<String, IValue> getSymbolTable() {
        return this.symbolTable;
    }

    public MyList<IValue> getOutput() {
        return this.output;
    }

    public MyDict<StringValue, BufferedReader> getFileTable() { return this.fileTable; }

    public IStmt getOriginalProgram() {
        return this.originalProgram;
    }

    @Override
    public String toString() {
        return  "\nExecution Stack:\n" +
                this.executionStack.toString() +
                "\n" +
                "Symbol Table:\n" +
                this.symbolTable.toString() +
                "\n" +
                "Output:\n" +
                this.output.toString() +
                "\n" +
                "======================================================================";
    }
}
