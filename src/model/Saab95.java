package model;

import java.awt.*;

/**
 * A specific subclass of car representing Saab95
 */
public class Saab95 extends Car {

    /**
     * Saab95 has a turbo which can increase its speed
     */
    private boolean turboOn;

    /**
     * Creates a Saab95
     */
    public Saab95(double X, double Y) {
        super(X, Y,2,
                Color.red,
                125,
                "Saab95",
                true);
        turboOn = false;
    }

    /**
     * Turns on the turbo
     */
    public void setTurboOn() { turboOn = true; }

    /**
     * Turns off the turbo
     */
    public void setTurboOff() { turboOn = false; }

    /**
     * The specific speed capacity
     * @return Returns the speedFactor
     */
    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
