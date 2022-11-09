package Model.ADT;

import Model.Exceptions.StackException;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public void push(T elem) {
        this.stack.push(elem);
    }

    @Override
    public T pop() throws StackException {
        T e = stack.pop();
        if (e == null)
            throw new StackException("Error : stack empty, can't pop.");
        return e;
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