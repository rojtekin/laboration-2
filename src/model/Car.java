package model;

import view.DrawPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A representation of a vehicle
 */
public abstract class Car implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double positionX;
    private double positionY;
    private double rotationX;
    private double rotationY;
    private boolean loadable;
    private BufferedImage image;

    /**
     * Creates a car from given attributes
     * @param nrDoors Number of doors
     * @param color Color of the car
     * @param enginePower How strong the engine is
     * @param modelName Name of the subclass
     * @param loadable If the vehicle is able to get loaded onto things
     */
    public Car(double positionX, double positionY,int nrDoors, Color color, int enginePower, String modelName, boolean loadable){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.loadable = loadable;
        this.positionY = positionY;
        this.positionX = positionX;
        rotationX = 0;
        rotationY = 1;
        setImage(loadImage(getModelName()));

    }

    /**
     * All subclasses require a way to decide its speed capacity
     * @return returns the speed of the car.
     */
    protected abstract double speedFactor();

    /**
     * gets X-axis rotation
     * @return Returns rotationX
     */
    public double getRotationX() {
        return rotationX;
    }

     void setImage(BufferedImage image){
        this.image = image;
    }

    /**
     * gets the image of the car
     * @return Returns the image
     */
    public BufferedImage getImage(){
        return image;
    }
    /**
     * gets Y-axis rotation
     * @return Returns rotationY
     */
    public double getRotationY() {
        return rotationY;
    }

    /**
     * sets X-axis position
     * @param positionX The new X-axis position
     */
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    /**
     * sets Y-axis position
     * @param positionY The new Y-axis position
     */
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    /**
     * sets X-axis rotation
     * @param rotationX The new X-axis rotation
     */
    public void setRotationX(double rotationX) {
        this.rotationX = rotationX;
    }

    /**
     * Sets Y-axis rotation
     * @param rotationY The new Y-axis rotation
     */
    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

    /**
     * Gets number of doors
     * @return Returns number of doors
     */
    public int getNrDoors(){ return nrDoors; }

    /**
     * checks if a car is loadable to a loading truck
     * @return Returns True
     */
    public boolean isLoadable() { // TODO: 2021-11-29 tests
        return loadable;
    }

    protected BufferedImage loadImage(String modelName){

        try {
            return ImageIO.read(DrawPanel.class.getResourceAsStream("/./view/pics/"+ modelName +".jpg")); // doesnt go to file location
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    String getModelName(){
        return modelName;
    }

    /**
     * Gets enginePower
     * @return Returns the engine power
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * Gives current speed
     * @return Returns current speed
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Sets the new color of the car
     * @param clr The new color of the car
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Gets the color of the car
     * @return Returns the color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Accelerates the car
     * @param amount Decides how hard (between 0 and 1) the gas paddle is pressed
     */
    public void gas(double amount) {
        if (amount <= 1 && amount >= 0)
        { currentSpeed = Math.max(getCurrentSpeed(), incrementSpeed(amount));}
        else
        {System.out.println("input value between [0,1]"); }
    }

    /**
     * Slows down the car
     * @param amount Decides how hard (between 0 and 1) the brake paddle is pressed
     */
    public void brake(double amount){
        if (amount <= 1 && amount >= 0)
        { currentSpeed = Math.min(getCurrentSpeed(), decrementSpeed(amount));}
        else
        {System.out.println("input value between [0,1]");}
    }

    /**
     * Increments speed
     * @param amount the factor of the increment
     * @return Returns the increased speed
     */
    public double incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
        return currentSpeed;
    }

    /**
     * Decrements speed
     * @param amount the factor of the decrement
     * @return Returns the reduced speed
     */
    public double decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        return currentSpeed;
    }

    /**
     * Sets current speed to 0.1
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets current speed to 0.1
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Gets the X-axis position
     * @return Returns the X-axis position
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * Gets the Y-axis position
     * @return Returns the X-axis position
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * Moves the cars coordinates
     */
    @Override
    public void move() {
        positionX += getCurrentSpeed() * getRotationX();
        positionY += getCurrentSpeed() * getRotationY();

    }

    /**
     * Turns it left
     */
    @Override
    public void turnLeft() {
        double oldX =  rotationX;
        double oldY =  rotationY;
        rotationX = -oldY;
        rotationY = oldX;
    }

    /**
     * Turns it right
     */
    @Override
    public void turnRight() {
        double oldX = rotationX;
        double oldY = rotationY;
        rotationX = oldY;
        rotationY = -oldX;

    }
}
