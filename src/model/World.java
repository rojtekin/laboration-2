package model;

import java.util.ArrayList;

public class World {

    private double worldX = 600;
    private double worldY = 600;

    // A list of cars, modify if needed
    ArrayList<Car> cars;
    ArrayList<Saab95> Saab95Cars;
    ArrayList<Scania> ScaniaCars;



    public World(ArrayList<Car> cars,ArrayList<Scania> ScaniaCars,ArrayList<Saab95> Saab95Cars){
        this.worldX = 600;
        this.worldY = 600;
        this.ScaniaCars = ScaniaCars;
        this.cars = cars;
        this.Saab95Cars = Saab95Cars;
    }

    public void wallBounce(Car car){
        if (car.getPositionY() > getWorldX() - 240
                || car.getPositionY() < 0
                ||car.getPositionX() > getWorldX()
                || car.getPositionX() < 0){
            car.turnLeft();
            car.turnLeft();
    }}

    public ArrayList<Car> getcars(){
        return cars;
    }

    public ArrayList<Saab95> getSaab95Cars() {
        return Saab95Cars;
    }

    public ArrayList<Scania> getScaniaCars() {
        return ScaniaCars;
    }

    public double getWorldX() {
        return worldX;
    }

    public double getWorldY() {
        return worldY;
    }
}
