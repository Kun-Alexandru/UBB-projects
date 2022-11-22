package Model.ADT;

import Model.Exceptions.StackException;

import java.util.Stack;

public class MyStack<T> implements IStack<T>{
    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public void push(T newElem) {
        this.stack.push(newElem);
    }

    @Override
    public T pop() throws StackException {
        if (this.stack.empty()) {
            throw new StackException("Failed to pop element: Full stack.");
        }
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.empty();
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for(T txt: stack)
            text.append(txt).append("\n");
        return text.toString();
    }
}
