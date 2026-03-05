package BetterSimulation;

import cars.CarWorkshop;
import cars.Volvo240;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawableCarWorkshopFactory {
    private static BufferedImage getImage(String path) {
        try {
            return ImageIO.read(DrawableCarFactory.class.getResourceAsStream(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getVolvoBrandImage() {
        return getImage("../pics/VolvoBrand.jpg");
    }

    public static DrawableCarWorkshop createDrawableVolvo240Workshop(Point2D point) {
        return new DrawableCarWorkshop(new CarWorkshop<Volvo240>(1, (int)point.getX(), (int)point.getY()), getVolvoBrandImage());
    }
}
