import java.awt.*;
import java.util.ArrayList;

/**
 * A type of vehicle that can transport cars
 */
public class CarTransporter extends Car implements Load{

    /**
     * CarTransporter has a platform
     * It can be in 2 positions, up or down
     */
    private boolean rampUp;

    /**
     * a flatbed can carry cars
     */
    private ArrayList<Car> flatbed;

    /**
     * a flatbed has a max capacity
     */
    private int max_capacity;


    /**
     * creates a CarTransporter
     * @param max_capacity the maximum amount of cars the transporter can carry.
     */
    public CarTransporter(int max_capacity){
        super(2,
                Color.GRAY,
                100,
                "UltimateCarCarryService",
                false);
        rampUp = true;
        flatbed = new ArrayList<>();
        this.max_capacity = max_capacity;
    }

    /**
     * Gets if platform is raised
     * @return Returns true if raised, false if lowered
     */
    public boolean getRampUp(){
        return rampUp;
    }
    private int getMax_capacity(){
        return max_capacity;
    }

    /**
     * Raises the ramp
     */
    public void raiseRamp(){
        rampUp = true;
    }

    /**
     * lowers the ramp
     * only possible while stationary
     */
    public void lowerRamp(){
        if(getCurrentSpeed() == 0){
            rampUp = false;
        }
        else{
            System.out.println("Can't Lower ramp while in motion");
        }
    }

    /**
     * for checking if transporter is at max capacity
     */
    private boolean TransporterFull() {
        return flatbed.size() == getMax_capacity();
    }

    /**
     * Restriction to se if a car is close enough to be loaded
     * @param model Car in question
     * @return Returns true if it is 10 or less close
     */
    @Override
    public boolean isCloseForLoad(Car model){
        double proximityX = getPositionX() - model.getPositionX();
        double proximityY = getPositionY() - model.getPositionY();
        return Math.abs(proximityX) <= 10 && Math.abs(proximityY) <= 10;
    }

    /**
     * Loads a loadable car onto the flatbed if all conditions are fulfilled
     * @param model car in question
     */
    @Override
    public void loadCar(Car model){
        if (TransporterFull()){
            throw new IllegalStateException("The transporter is full");
        }
        if (!model.isLoadable()){
            throw new IllegalStateException("The car isnt loadable");
        }
        if (rampUp){
            throw new IllegalStateException("The ramp isnt down");
        }
        if (!isCloseForLoad(model)){
            throw new IllegalStateException("The requested car isnt close enough");
        }
        if (model.isLoadable() && !rampUp && isCloseForLoad(model)){
            model.setPositionX(getPositionX());
            model.setPositionY(getPositionY());
            model.setRotationX(getRotationX());
            model.setRotationY(getRotationY());
            flatbed.add(model);
        }
    }
    /**
     * unloads the last loaded car from the flatbed
     */
    @Override
    public void unloadCar() {
        if (rampUp){
            throw new IllegalStateException("The ramp must first be down!");
        }
        if (!rampUp && flatbed.size() > 0) {
            Car car = flatbed.get(flatbed.size() - 1);
            car.setPositionY(getPositionY()-1);
            car.setPositionX(getPositionX()-1);
            flatbed.remove(flatbed.size()-1);
        }
        else {
            throw new IllegalStateException("There's no cars on the flatbed.");
        }
    }
    /**
     * Moves the CarTransporter
     */
    @Override
    public void move(){
        super.move();
        double truckPosX = getPositionX();
        double truckPosY = getPositionY();
        for (Car i : flatbed) {
            i.setPositionX(truckPosX);
            i.setPositionY(truckPosY);
        }
    }

    /**
     * Accelerates the car, is only possible if the platform is raised
     * @param amount Decides how hard (between 0 and 1) the gas paddle is pressed
     */
    @Override
    public void gas(double amount){
        if (getRampUp()) {
            super.gas(amount);
        }
        else{
            System.out.println("The platform should first be raised!");
        }
    }
    /**
     * The specific speed capacity
     * @return Returns the speedFactor
     */
    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

}
