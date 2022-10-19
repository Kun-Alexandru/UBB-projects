package view;
import controller.Controller;
import model.Vehicle;

import java.util.Scanner;

public class UI {

    private Controller controller;

    public UI(Controller new_controller){
        this.controller = new_controller;
        this.run_menu();
    }

    private void print_menu(){
        System.out.println("1. Add vehicle ");
        System.out.println("2. Remove vehicle ");
        System.out.println("3. Show all vehicles ");
        System.out.println("4. Show all vehicles with the repair cost greater than 1000 Ron ");
        System.out.println("5. Exit");
    }

    private void initialise_list(){
        this.controller.add("car", 700,"Honda Civic");
        this.controller.add("motorcycle",1200,"Moto1");
        this.controller.add("truck",2500,"Truck1");
        this.controller.add("car",650,"Skoda Octavia");
        this.controller.add("motorcycle",1999,"Moto2");
        this.controller.add("truck",999,"Truck2");
    }

    private void add(){
        System.out.println("Select : car, truck, motorcycle");
        String type;
        Scanner input = new Scanner(System.in);
        type = input.next();
        System.out.println("What's the cost of repair?");
        int cost;
        cost = input.nextInt();
        System.out.println("What's the model of the vehicle?");
        String model;
        model = input.next();
        try{
            this.controller.add(type, cost,model);
        }
        catch(Exception e){
            System.out.println("INVALID VEHICLE TYPEEEE");
        }
    }

    private void remove(){
        int pos;
        Scanner input = new Scanner(System.in);
        System.out.println("What's the position you want to remove? It starts from 0.");
        try{
            pos = input.nextInt();
            this.controller.remove(pos);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void print_all(){
        Vehicle[] vehicles = this.controller.getAll();
        for (int i = 0; i < this.controller.getSize(); i++)
            System.out.println(vehicles[i].toString());
    }

    private void print_higher_1000(){
        Vehicle[] vehicles = this.controller.getAll();
        for (int i = 0; i < this.controller.getSize(); i++)
            if(vehicles[i].getCost() > 1000)
                System.out.println(vehicles[i].toString());
    }

    private void run_menu(){
        this.initialise_list();
        String answer;
        int choice;
        Scanner input = new Scanner(System.in);
        int isRunning = 1;
        while(isRunning == 1){
            this.print_menu();
            answer = input.next();
            try{
                choice = Integer.parseInt(answer);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            if(choice == 1)
                this.add();
            else if (choice == 2)
                this.remove();
            else if(choice == 3)
                this.print_all();
            else if(choice == 4)
                this.print_higher_1000();
            else if(choice == 5)
                isRunning = 0;
            else
                System.out.println("Invalid selection!!");
            }
        }
    }

