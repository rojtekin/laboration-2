package model;

import java.util.ArrayList;

/**
 * A representation of a car repair shop
 * @param <T> Parameter T is a Car or one of its subclasses
 */
public class CarRepairShop <T extends Car> implements Load<T>{
    /**
     * Maximum capacity of cars that can be accepted
     */
    private int max_capacity;
    /**
     * An arraylist containing the cars in the workshop
     */
    private ArrayList<T> cars_in_workshop;
    /**
     * The X-axis position of the carRepairShop
     */
    private final double positionX;
    /**
     * The Y-axis position of the carRepairShop
     */
    private final double positionY;

    /**
     * Creates a CarRepairShop
     * @param max_capacity Maximum capacity of cars that can be accepted
     * @param positionX The X-axis position
     * @param positionY The Y-axis position
     */
    public CarRepairShop(int max_capacity, double positionX, double positionY){
        this.max_capacity = max_capacity;
        cars_in_workshop = new ArrayList<T>();
        this.positionX = positionX;
        this.positionY = positionY;

    }

    /**
     * Gets the amount of cars
     * @return Returns the amount of cars
     */
    public int getAmountOfCarsInWorkshop(){
        return cars_in_workshop.size();
    }

    /**
     * Loads the car onto the workshop
     * @param car The car that gets loaded
     */
    @Override
    public void loadCar(T car) {

        if(cars_in_workshop.size() >= this.max_capacity){
            throw new IllegalStateException("The workshop is already at max capacity");
        }
        if (cars_in_workshop.size() < max_capacity && this.isCloseForLoad(car)) {
            cars_in_workshop.add((T) car);
            car.setPositionX(getPositionX());
            car.setPositionY(getPositionY());
        }
        if (!isCloseForLoad(car)){
            throw new IllegalStateException("The car is too far away");
        }
    }

    /**
     * Unloads the car from the workshop
     */
    @Override
    public void unloadCar() {
        if (cars_in_workshop.size() == 0) {
            throw new IllegalStateException("There's no cars in the workshop to unload");
        }
        if (cars_in_workshop.size() > 0){
            Car car = cars_in_workshop.get(cars_in_workshop.size()-1);
            car.setPositionY(getPositionY()-1);
            car.setPositionX(getPositionX()-1);
            cars_in_workshop.remove(cars_in_workshop.size()-1);
        }
    }

    /**
     * checks the X-axis position
     * @return Returns the X-axis position
     */
    private double getPositionX() {
        return positionX;
    }

    /**
     * checks the Y-axis position
     * @return Returns the Y-axis position
     */
    private double getPositionY() {
        return positionY;
    }

    /**
     * Checks if the car is close enough to be loaded
     * @param car The vehicle you want to load
     * @return Returns true if its close enough to be loaded
     */
    @Override
    public boolean isCloseForLoad(Car car) {
        double proximityX = getPositionX() - car.getPositionX();
        double proximityY = getPositionY() - car.getPositionY();
        return Math.abs(proximityX) <= 5 && Math.abs(proximityY) <= 5;
    }
}
