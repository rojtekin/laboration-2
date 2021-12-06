package view;

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
    public void moveit(int x, int y) {
        for (Point point : allPoints) {
            point.x = x;
            point.y = y;
        }
    }
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        addCar(volvoPoint,volvoImage);
        addCar(saab95Point,saab95Image);
        addCar(scaniaPoint,scaniaImage);
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
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }
}
