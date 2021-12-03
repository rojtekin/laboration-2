import java.awt.*;

/**
 * A specific subclass of car representing a Volvo240
 */
public class Volvo240 extends Car {

    /**
     * A volvo is trimmed, this is the specific for Volvo240
     */
    private final static double trimFactor = 1.25;

    /**
     * Creates a Volvo240
     */
    public Volvo240() {
        super(4,
                Color.black,
                100,
                "Volvo240",
                true);
    }

    /**
     * The specific speed capacity
     * @return Returns the speedFactor
     */
    @Override
    public double speedFactor() { return getEnginePower() * 0.01 * trimFactor; }
}