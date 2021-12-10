package view;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjektToDraw {
    private BufferedImage image;
    private Point point;

    public ObjektToDraw(Point point, BufferedImage image){
        this.image = image;
        this.point = point;
    }


    public Point getPoint() {
        return point;
    }

    public BufferedImage getImage() {
        return image;
    }
}
