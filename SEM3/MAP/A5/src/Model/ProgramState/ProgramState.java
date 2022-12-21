package Model.ProgramState;

import Model.ADT.IDictionary;
import Model.ADT.IHeapTable;
import Model.ADT.IList;
import Model.ADT.IStack;
import Model.Exceptions.*;
import Model.Statement.IStatement;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgramState {
    private IStack<IStatement> executionStack;
    private IDictionary<String, IValue> symbolTable;
    private IList<IValue> output;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private IHeapTable<IValue> heapTable;
    private IStatement originalProgram;
    private int id;
    private static int baseId;

    public ProgramState(IStack<IStatement> exeStack, IDictionary<String, IValue> symTable, IList<IValue> out,
                        IDictionary<StringValue, BufferedReader> fileTable, IHeapTable<IValue> heapTable, IStatement origPrg) {
        this.executionStack = exeStack;
        this.symbolTable = symTable;
        this.output = out;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.originalProgram = origPrg.deepCopy();
        this.executionStack.push(this.originalProgram);
        this.incrementBaseId();
        this.id = this.getBaseId();
    }

    private synchronized int getBaseId() {
        return baseId;
    }

    private synchronized void incrementBaseId() {
        baseId = baseId + 1;
    }

    public IStack<IStatement> getExecutionStack() {
        return this.executionStack;
    }

    public IDictionary<String, IValue> getSymbolTable() {
        return this.symbolTable;
    }

    public IList<IValue> getOutput() {
        return this.output;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() { return this.fileTable; }

    public IHeapTable<IValue> getHeapTable() { return this.heapTable; }

    public IStatement getOriginalProgram() {
        return this.originalProgram;
    }

    public boolean isNotCompleted() {
        return !this.executionStack.isEmpty();
    }

    public ProgramState oneStep() throws ControllerException, StackException, StatementException, HeapException, ExpressionException, DictionaryException {
        IStack<IStatement> executionStack = this.executionStack;
        if (executionStack.isEmpty()) {
            throw new ControllerException("Program state's execution stack is empty.");
        }
        IStatement topStatement = executionStack.pop();
        return topStatement.execute(this);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return "ID: " + this.id + "\n" +
               dtf.format(now) + "\n" +
               "Execution Stack:\n" +
               this.executionStack.toString() + "\n" +
               "Heap:\n" +
               this.heapTable.toString() + "\n" +
               "Symbol Table:\n" +
               this.symbolTable.toString() + "\n" +
               "Output:\n" +
               this.output.toString() + "\n" +
               "-".repeat(50);
    }
}
