package controllers;

import Observer.*;
import model.*;
import view.CarView;
import model.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController{
    // member fields:
    public World world;

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Saab95> saab95Cars = new ArrayList<>();
    ArrayList<Scania> scaniaCars = new ArrayList<>();

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    ArrayList<Observer> allObservers = new ArrayList<>();

    public static void main(String[] args) {
        // Instance of this class

        CarController cc = new CarController();
        //

        Volvo240 volvo240 = new Volvo240(0,0);
        cc.cars.add(volvo240);

        Saab95 saabCar = new Saab95(100,0);
        cc.cars.add(saabCar);
        cc.saab95Cars.add(saabCar);

        Scania scaniaCar = new Scania(200, 0);
        cc.scaniaCars.add(scaniaCar);
        cc.cars.add(scaniaCar);

        cc.world = new World();



        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc, cc.world);

        cc.allObservers.add(cc.frame);
        // Start the timer
        cc.timer.start();
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */

    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {

                int x = (int) Math.round(car.getPositionX());
                int y = (int) Math.round(car.getPositionY());
                car.move();
                world.wallBounce(car);
                BufferedImage image = car.getImage();
                frame.drawPanel.moveit(x, y, image);
                // repaint() calls the paintComponent method of the panel
            }
            notifyObserver();
        }
    }
    //methods:

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }
    // Calls the break method for each car once
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }
    public void startEngine(){
        for (Car car : cars){
            car.startEngine();
        }
    }
    public void stopEngine(){
        for (Car car : cars){
            car.stopEngine();
        }
    }
    // Calls setTurboOn on all Saab95 cars
    public void setTurboOn(){
        for (Saab95 car : saab95Cars){
            car.setTurboOn();
        }
    }
    // Calls setTurboOff on all Saab95 cars
    public void setTurboOff(){
        for (Saab95 car : saab95Cars){
            car.setTurboOff();
        }
    }
    // Calls raisePlatform on all Scania cars
    public void raisePlatform(){
        for (Scania car : scaniaCars){
            car.raisePlatform(10);
        }
    }
    // Calls raisePlatform on all Scania cars
    public void lowerPlatform(){
        for (Scania car : scaniaCars){
            car.lowerPlatform(10);
        }
    }

    public void notifyObserver() {
        for (Observer observer : allObservers){
            observer.update();
        }
    }
    private void addObserver(Observer observer){
        allObservers.add(observer);
    }
    private void removeObserver(Observer observer){
        allObservers.remove(observer);
    }
}
