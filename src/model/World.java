package model;

import java.util.ArrayList;

public class World {

    private double worldX = 600;
    private double worldY = 600;

    public World(){
        this.worldX = 600;
        this.worldY = 600;
    }

    public void wallBounce(Car car){
        if (car.getPositionY() > getWorldX() - 240
                || car.getPositionY() < 0
                ||car.getPositionX() > getWorldX()
                || car.getPositionX() < 0){
            car.turnLeft();
            car.turnLeft();
    }}
    public double getWorldX() {
        return worldX;
    }

    public double getWorldY() {
        return worldY;
    }
}
