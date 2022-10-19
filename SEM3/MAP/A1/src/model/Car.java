package model;

public class Car implements Vehicle{
    private int cost;
    private String typeOfVehicle = "car";
    private String modelOfVehicle;

    public Car() {
        this.cost = 0;
        this.modelOfVehicle = "";
    }

    public Car(int cost,String model){
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
        return "Car " + this.modelOfVehicle + " cost : " + this.cost;
    }
}
