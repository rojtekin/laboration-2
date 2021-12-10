package model;

import view.DrawPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A specific subclass of car representing a Scania truck
 */
public class Scania extends Car {

    /**
     * A platform is mounted on the back.
     * A fully lowered platform has the angle 0 while a fully raised has the angle 70
     */
    private double platform;

    /**
     * Creates a Scania truck
     */
    public Scania(double X, double Y){
        super(X, Y,2,
                Color.white,
                100,
                "Scania",
                false);
        platform = 0;
    }

    /**
     * Gets the platforms angle
     * @return Returns the platforms angle
     */
    public double getPlatform(){
        return platform;
    }

    /**
     * Raises the platform, can not be raises to more than 70 degrees.
     * Is only possible while stationary
     * @param amount how many degrees the platform will be raised
     */
    public void raisePlatform(double amount){
        if (getCurrentSpeed() == 0) {
            platform = Math.min(70, getPlatform() + amount);
        }
        else{
            System.out.println("Platform cant be lowered during motion");
        }
    }

    /**
     * Lowers the platform, cannot be lowered to less than 0 degrees.
     * Is only possible while stationary
     * @param amount how much you want the platform to be lowered
     */
    public void lowerPlatform(double amount){
            platform = Math.max(0, getPlatform() - amount);
    }

    /**
     * Accelerates the car, is only possible if the platform is raised to 0 degrees
     * @param amount Decides how hard (between 0 and 1) the gas paddle is pressed
     */
    @Override
    public void gas(double amount){
        if (platform == 0) {
            super.gas(amount);
        }
        else{
            System.out.println("Platform must be completely raised first!");
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
