package BetterSimulation;

import cars.Car;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableCar implements DrawablePositionable {
    private final Car car;

    private final BufferedImage image;

    public DrawableCar(Car car, BufferedImage image) {
        this.car = car;
        this.image = image;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Point2D getPosition() {
        return car.getPosition();
    }
}
