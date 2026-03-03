package Simulation;

import cars.Car;
import cars.CarWorkshop;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableCarWorkshop implements DrawablePositionable {
    private final CarWorkshop<? extends Car> workshop;

    private final BufferedImage image;

    public DrawableCarWorkshop(CarWorkshop<? extends Car> workshop, BufferedImage image) {
        this.workshop = workshop;
        this.image = image;
    }

    public CarWorkshop<? extends Car> getCarWorkshop() {
        return workshop;
    }

    @Override
    public Point2D getPosition() {
        return workshop.getPosition();
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
