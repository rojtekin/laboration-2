package controllers;

import model.*;
import view.CarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Saab95> Saab95Cars = new ArrayList<>();
    ArrayList<Scania> ScaniaCars = new ArrayList<>();
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Volvo240 volvo240 = new Volvo240();
        cc.cars.add(volvo240);

        Saab95 saabCar = new Saab95();
        saabCar.setPositionX(100);
        cc.cars.add(saabCar);
        cc.Saab95Cars.add(saabCar);

        Scania scaniaCar = new Scania();
        scaniaCar.setPositionX(200);
        cc.ScaniaCars.add(scaniaCar);
        cc.cars.add(scaniaCar);

    //    for (Car car : cc.cars){
    //        double i = 0;
    //        car.setPositionX(100*i);      // this doesnt work as intended
    //        i = i+1;
    //    }
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getPositionX());
                int y = (int) Math.round(car.getPositionY());
                if (car.getPositionY() > CarView.getWorldY_max() - 240
                        || car.getPositionY() < 0
                        ||car.getPositionX() > CarView.getWorldX_max()
                        || car.getPositionX() < 0){
                    car.turnLeft();
                    car.turnLeft();
                }
                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

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
    // Calls setTurboOn on all Saab95 cars
    public void setTurboOn(){
        for (Saab95 car : Saab95Cars){
            car.setTurboOn();
        }
    }
    // Calls setTurboOff on all Saab95 cars
    public void setTurboOff(){
        for (Saab95 car : Saab95Cars){
            car.setTurboOff();
        }
    }
    // Calls raisePlatform on all Scania cars
    public void raisePlatform(){
        for (Scania car : ScaniaCars){
            car.raisePlatform(10);
        }
    }
    // Calls raisePlatform on all Scania cars
    public void lowerPlatform(){
        for (Scania car : ScaniaCars){
            car.lowerPlatform(10);
        }
    }
}