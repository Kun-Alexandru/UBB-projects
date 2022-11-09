package Model.ADT;

import Model.Exceptions.ListException;

import java.util.ArrayList;

public class MyList<T> implements IList<T> {
    private ArrayList<T> list;

    public MyList() {
        this.list = new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        this.list.add(elem);
    }

    @Override
    public ArrayList<T> getList() {
        return this.list;
    }

    @Override
    public T getElemAtIndex(int index) throws ListException {
        if (index >= this.list.size()) {
            throw new ListException("Error : given index is not in list.");
        }
        return this.list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for(T txt: list)
            text.append(txt).append("\n");
        return text.toString();
    }
}