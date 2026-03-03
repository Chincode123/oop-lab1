package Simulation;


import cars.Saab95;
import cars.Scania;
import cars.Volvo240;
import other.DrawPanel;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawableCarFactory {
    private static BufferedImage getImage(String path) {
        try {
            return ImageIO.read(DrawableCarFactory.class.getResourceAsStream(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getVolvoCarImage() {
        return getImage("../pics/Volvo240.jpg");
    }

    private static BufferedImage getSaabCarImage() {
        return getImage("../pics/Saab95.jpg");
    }

    private static BufferedImage getScaniaCarImage() {
        return getImage("../pics/Scania.jpg");
    }

    public static DrawableCar createDrawableVolvo240(Point2D point) {
        return new DrawableCar(new Volvo240((int)point.getX(), (int)point.getY()), getVolvoCarImage());
    }

    public static DrawableCar createDrawableSaab95(Point2D point) {
        return new DrawableCar(new Saab95((int)point.getX(), (int)point.getY()), getSaabCarImage());
    }

    public static DrawableCar createDrawableScania(Point2D point) {
        return new DrawableCar(new Scania((int)point.getX(), (int)point.getY()), getScaniaCarImage());
    }
}
