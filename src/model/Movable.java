package model;

/**
 * A class with this interface should have a move, turn left and turn right method
 */
public interface Movable {
    /**
     * Move should change the position based on speed and direction
     */
    void move();

    /**
     * turnLeft should change the direction of the car to 90 degrees left
     */
    void turnLeft();

    /**
     * turnRight should change the direction of the car to 90 degrees right
     */
    void turnRight();

}
