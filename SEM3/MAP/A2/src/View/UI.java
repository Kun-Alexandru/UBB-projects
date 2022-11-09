package View;

import Controller.Controller;
import Model.ADT.*;
import Model.Exceptions.*;
import Model.Expression.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;

import java.util.Scanner;

public class UI {
    private Controller controller;

    public UI(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Scanner keyboard = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("1. int v;v=2;Print(v)\n" + "2. int a;int b; a=2+3*5;b=a+1;Print(b)\n" + "3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)" + "\n4.Quit.");
            System.out.print("\n>>> ");
            int input = keyboard.nextInt();
            try {
                if(input == 1) {
                    IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
                    PrgState prg = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, IValue>(), new MyList<IValue>(), ex1);
                    this.controller.addProgramState(prg);
                    this.controller.allSteps();
                }
                else if(input == 2) {
                    IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                                new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)),
                                new ValueExp(new IntValue(5)), '*'), '+')), new CompStmt(new AssignStmt("b",
                                new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));
                    PrgState prg = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, IValue>(), new MyList<IValue>(), ex2);
                    this.controller.addProgramState(prg);
                    this.controller.allSteps();
                }
                else if(input == 3){
                    IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                                new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
                    PrgState prg = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, IValue>(), new MyList<IValue>(), ex3);
                    this.controller.addProgramState(prg);
                    this.controller.allSteps();
                }
                else if(input == 4)
                    isRunning = false;
                else
                    System.out.println("Try again, invalid input.");
            } catch (ControllerException | StatementException | StackException | ExpressionException |
                     DictionaryException | ListException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }
}

