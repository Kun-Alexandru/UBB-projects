package repository;
import model.Vehicle;
import exception.RepositoryFull;

public class Repository implements RepoInterface{
    private Vehicle[] vehicles;
    private int index;
    private int capacity = 10;

    public Repository(){
        this.vehicles = new Vehicle[capacity];
        this.index = 0;
    }

    @Override
    public void add(Vehicle a){
        if(this.index >= this.capacity)
            throw new RepositoryFull();
        else
            this.vehicles[this.index++] = a;
    }

    @Override
    public void remove(int pos){
        for(int i = pos; i < index - 1; i++)
            this.vehicles[i] = this.vehicles[i+1];
        this.index--;
    }

    @Override
    public Vehicle[] getAll(){
        return this.vehicles;
    }

    @Override
    public int getSize(){
        return this.index;
    }
}
