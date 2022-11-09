package Model.ADT;

import Model.Exceptions.ListException;

import java.util.ArrayList;

public interface IList<T> {
    void add(T elem);
    ArrayList<T> getList();
    T getElemAtIndex(int index) throws ListException;
    int size();
}
