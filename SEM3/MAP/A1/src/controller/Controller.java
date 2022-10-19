package controller;

import exception.RepositoryFull;
import model.Car;
import model.Motorcycle;
import model.Truck;
import model.Vehicle;
import repository.Repository;
import exception.VehicleError;


public class Controller {

    private Repository repo;

    public Controller(){
        this.repo = new Repository();
    }

    public Controller(Repository new_repo){
        this.repo = new_repo;
    }

    public void add(String vehicle_type, int cost, String model){
        Vehicle new_vehicle;
        if (vehicle_type.equals("car")) {
            new_vehicle = new Car(cost, model);
        }
        else if (vehicle_type.equals("truck")) {
            new_vehicle = new Truck(cost, model);
        }
        else if (vehicle_type.equals("motorcycle")) {
            new_vehicle = new Motorcycle(cost, model);
        }
        else {
            throw new VehicleError();
        }
        try {
            this.repo.add(new_vehicle);
        }
        catch(RepositoryFull e){
            System.out.println(e.getMessage());
        }
    }

    public void remove(int pos){
        this.repo.remove(pos);
    }

    public Vehicle[] getAll(){
        return this.repo.getAll();
    }

    public int getSize(){
        return this.repo.getSize();
    }

}
