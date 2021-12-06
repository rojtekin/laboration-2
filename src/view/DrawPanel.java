package view;

import model.Car;
import model.Saab95;
import model.Scania;
import model.Volvo240;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saab95Image;
    BufferedImage scaniaImage;
    // To keep track of a singel cars position
    Point volvoPoint = new Point();
    Point saab95Point = new Point();
    Point scaniaPoint = new Point();

    ArrayList<BufferedImage> allImages = new ArrayList<>();
    ArrayList<Point> allPoints = new ArrayList<>();

    public void addCar(Point carPoint, BufferedImage carImage){

        allPoints.add(carPoint);
        allImages.add(carImage);
    }
    // TODO: Make this general for all cars
    public void moveit(int x, int y, Car car) { // när vi lyckas koppla bilarnas objekt med deras bilder i världen kan man
        // göra en bra generaliserad version av detta.
        if (car instanceof Volvo240) {
            volvoPoint.x = x;
            volvoPoint.y = y;
        }
        if (car instanceof Saab95){
            saab95Point.x = x;
            saab95Point.y = y;
        }
        if (car instanceof Scania){
            scaniaPoint.x = x;
            scaniaPoint.y = y;
        }
    }
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "view.pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: view.pics -> MOVE *.jpg to view.pics.
            // if you are starting in IntelliJ.
            saab95Image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            addCar(volvoPoint,volvoImage);
            addCar(saab95Point,saab95Image);
            addCar(scaniaPoint,scaniaImage);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        while(allPoints.size() > i){
            BufferedImage image = allImages.get(i);
            int x = allPoints.get(i).x + 100*i;
            int y = allPoints.get(i).y;

            g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
            i++;
        }
    }
}
