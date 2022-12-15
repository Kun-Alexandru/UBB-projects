package Controller;

import Model.ADT.IList;
import Model.ADT.IStack;
import Model.Exceptions.*;
import Model.ProgramState.ProgramState;
import Model.Statement.IStatement;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    private boolean displayFlag;

    public Controller(IRepository repo) {
        this.repo = repo;
        this.displayFlag = false;
    }

    public void addProgramState(ProgramState prg) {
        this.repo.addProgramState(prg);
    }

    public ProgramState oneStep(ProgramState currentState) throws ControllerException, StackException, StatementException, ExpressionException, DictionaryException, FileException, HeapException {
        IStack<IStatement> executionStack = currentState.getExecutionStack();
        if (executionStack.isEmpty()) {
            throw new ControllerException("Program state's execution stack is empty.");
        }
        IStatement topStatement = executionStack.pop();
        return topStatement.execute(currentState);
    }

    public void allSteps() throws ControllerException, StatementException, StackException, ExpressionException, DictionaryException, ListException, FileException, IOException, HeapException {
        ProgramState programState = this.repo.getCurrentProgramState();
        if (programState.getExecutionStack().isEmpty()) {
            throw new ControllerException("ERROR: The program was already executed. The execution stack is empty.");
        }
        this.repo.logPrgStateExec();
        if (this.displayFlag) {
            System.out.println("Program execution started:");
            System.out.print(programState + "\n");
        }
        int outputListSize = 0;
        IList<IValue> output;
        while (!programState.getExecutionStack().isEmpty()) {
            this.oneStep(programState);
            this.repo.logPrgStateExec();

            Map<Integer, IValue> heapContent = programState.getHeapTable().getContent();
            List<Integer> symbolTableAddresses = this.getAddrFromSymTable(programState.getSymbolTable().getContent().values());
            List<Integer> allReferencedAddresses = this.allAddresses(symbolTableAddresses, heapContent);
            programState.getHeapTable().setContent(this.garbageCollector(allReferencedAddresses, heapContent));

            if (this.displayFlag) {
                System.out.println(programState);
            } else {
                output = programState.getOutput();
                if (outputListSize != output.size()) {
                    outputListSize = output.size();
                    System.out.println(output.getElemAtIndex(output.size() - 1).toString());
                }
            }
        }
    }

    private List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    private List<Integer> allAddresses(List<Integer> addressesFromSymbolTable, Map<Integer, IValue> heap) {
        boolean isRunning = true;
        List<Integer> newAddressList = new ArrayList<>(addressesFromSymbolTable);

        while (isRunning) {
            List<Integer> appendingList;
            isRunning = false;

            appendingList = heap.entrySet().stream()
                    .filter(e -> e.getValue() instanceof RefValue)
                    .filter(e -> newAddressList.contains(e.getKey()))
                    .map(e -> ((RefValue) e.getValue()).getAddress())
                    .filter(e -> !newAddressList.contains(e))
                    .collect(Collectors.toList());
            if (!appendingList.isEmpty()) {
                isRunning = true;
                newAddressList.addAll(appendingList);
            }
        }
        return newAddressList;
    }

    private Map<Integer, IValue> garbageCollector(List<Integer> referencedAddresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> referencedAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    void turnDisplayFlagOn() {
        this.displayFlag = true;
    }

    void turnDisplayFlagOff() {
        this.displayFlag = false;
    }
}
