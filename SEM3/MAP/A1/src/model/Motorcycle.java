package model;

public class Motorcycle implements Vehicle{
    private int cost;
    private String typeOfVehicle = "motorcycle";
    private String modelOfVehicle;

    public Motorcycle(){
        this.cost = 0;
        this.modelOfVehicle = "";
    }

    public Motorcycle(int cost,String model){
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
        return "Motorcycle " + this.modelOfVehicle + " cost : " + this.cost;
    }
}
