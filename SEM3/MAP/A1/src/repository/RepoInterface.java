package repository;

import model.Vehicle;

public interface RepoInterface {

    public void add(Vehicle a);

    public void remove(int pos);

    public Vehicle[] getAll();

    public int getSize();

}
