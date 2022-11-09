package Model.ProgramState;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Statement.IStmt;
import Model.Value.IValue;

public class PrgState {
    private MyStack<IStmt> exeStack;
    private MyDictionary<String, IValue> symTable;
    private MyList<IValue> output;
    private IStmt originalProgram;

    public PrgState(MyStack<IStmt> exeStack, MyDictionary<String, IValue> symTable, MyList<IValue> out, IStmt origPrg) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.output = out;
        this.originalProgram = origPrg.deepCopy();
        this.exeStack.push(this.originalProgram);
    }

    public MyStack<IStmt> getExeStack() {
        return this.exeStack;
    }

    public MyDictionary<String, IValue> getSymTable() {
        return this.symTable;
    }

    public MyList<IValue> getOutput() {
        return this.output;
    }

    public IStmt getOriginalProgram() {
        return this.originalProgram;
    }

    @Override
    public String toString() {
        return "Execution Stack: " + this.exeStack.toString() + "\n" + "Symbol Table: " + "\n" + this.symTable.toString() + "\n" + "Output: " + this.output.toString() + "\n";
    }
}
