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

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;

    {
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage saab95Image;
    {
        try {
            saab95Image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage scaniaImage;
    {
        try {
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;
    // To keep track of a singel cars position

    ArrayList<ObjektToDraw> allObjektsToDraw = new ArrayList<>();

    // TODO: Make this general for all cars
    public void moveit(int x, int y, BufferedImage image) { // när vi lyckas koppla bilarnas objekt med deras bilder
        // i världen kan man göra en bra generaliserad version av detta.
        ObjektToDraw draw = new ObjektToDraw(new Point(x,y), image);
        allObjektsToDraw.add(draw);


    }
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        // You can remove the "view.pics" part if running outside of IntelliJ and
        // everything is in the same main folder.
        // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

        // Remember to right-click src New -> Package -> name: view.pics -> MOVE *.jpg to view.pics.
        // if you are starting in IntelliJ.

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        while(allObjektsToDraw.size() > i){
            BufferedImage image = allObjektsToDraw.get(i).getImage();
            int x = allObjektsToDraw.get(i).getPoint().x;
            int y = allObjektsToDraw.get(i).getPoint().y;

            g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
            i++;
        }
        allObjektsToDraw.clear();
    }
}
