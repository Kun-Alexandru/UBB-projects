package View;

import Controller.Controller;
import Model.ADT.MyDict;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Expression.ArithExp;
import Model.Expression.RelExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;

public class Interpreter {
    public static void main(String[] args) throws IOException {
        // example 1: int v; v=2; Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(new MyStack<IStmt>(), new MyDict<String, IValue>(),
                new MyList<IValue>(), new MyDict<StringValue, BufferedReader>(), ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller controller1 = new Controller(repo1);

        // example 2: int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),
                                new ArithExp(new ValueExp(new IntValue(3)),
                                        new ValueExp(new IntValue(5)), "*"), "+")),
                                new CompStmt(new AssignStmt("b",
                                        new ArithExp(new VarExp("a"),
                                                new ValueExp(new IntValue(1)), "+")),
                                        new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new MyStack<IStmt>(), new MyDict<String, IValue>(),
                new MyList<IValue>(), new MyDict<StringValue, BufferedReader>(), ex2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller controller2 = new Controller(repo2);

        // example 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        PrgState prg3 = new PrgState(new MyStack<IStmt>(), new MyDict<String, IValue>(),
                new MyList<IValue>(), new MyDict<StringValue, BufferedReader>(), ex3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller controller3 = new Controller(repo3);

        // example 4: string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
        PrgState prg4 = new PrgState(new MyStack<IStmt>(), new MyDict<String, IValue>(),
                new MyList<IValue>(), new MyDict<StringValue, BufferedReader>(), ex4);
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller controller4 = new Controller(repo4);

        // example 5: string varf; varf="test_empty.in"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)
        IStmt ex5 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test_empty.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, IValue>(),
                new MyList<IValue>(), new MyDict<StringValue, BufferedReader>(), ex5);
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller controller5 = new Controller(repo5);

        // example 6: print(5 * 3 < 10)
        IStmt ex6 = new PrintStmt(new RelExp(
                new ArithExp(new ValueExp(new IntValue(5)), new ValueExp(new IntValue(3)), "*"),
                new ValueExp(new IntValue(10)), "<"));
        PrgState prg6 = new PrgState(new MyStack<IStmt>(), new MyDict<String, IValue>(),
                new MyList<IValue>(), new MyDict<StringValue, BufferedReader>(), ex6);
        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller controller6 = new Controller(repo6);

        // example 7: print(51 - 3 == 15 + 33)
        IStmt ex7 = new PrintStmt(new RelExp(new ArithExp(
                new ValueExp(new IntValue(51)), new ValueExp(new IntValue(3)), "-"),
                new ArithExp(new ValueExp(new IntValue(15)), new ValueExp(new IntValue(33)), "+"),
                "=="));
        PrgState prg7 = new PrgState(new MyStack<IStmt>(), new MyDict<String, IValue>(),
                new MyList<IValue>(), new MyDict<StringValue, BufferedReader>(), ex7);
        IRepository repo7 = new Repository(prg7, "log7.txt");
        Controller controller7 = new Controller(repo7);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", "int v; v = 2; print(x)", controller1));
        menu.addCommand(new RunExample("2", "int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)", controller2));
        menu.addCommand(new RunExample("3", "bool a; int v; a = true; IF (a) THEN (v = 2) ELSE (v = 3); print(v)", controller3));
        menu.addCommand(new RunExample("4", "string varf; varf = \"test.in\"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)", controller4));
        menu.addCommand(new RunExample("5", "string varf; varf = \"test_empty.in\"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)", controller5));
        menu.addCommand(new RunExample("6", "print(5 * 3 < 10);", controller6));
        menu.addCommand(new RunExample("7", "print(51 - 3 == 15 + 33);", controller7));

        menu.show();
    }
}
