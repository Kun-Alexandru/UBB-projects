package model;

public class Truck implements Vehicle{
    private int cost;
    private String typeOfVehicle = "truck";
    private String modelOfVehicle;

    public Truck(){
        this.cost = 0;
        this.modelOfVehicle = "";
    }

    public Truck(int cost, String model){
        this.cost = cost;
        this.modelOfVehicle = model;
    }

    @Override
    public String getType(){
        return this.typeOfVehicle;
    }

    @Override
    public int getCost(){
        return this.cost;
    }

    @Override
    public String toString(){
        return "Truck " + this.modelOfVehicle + " cost : " + this.cost;
    }
}
