package BetterSimulation;

import cars.Car;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableCar implements DrawablePositionable {
    private final SimulationCar car;

    private final BufferedImage image;

    public DrawableCar(Car car, BufferedImage image) {
        this.car = new SimulationCar(car, car.getClass());
        this.image = image;
    }

    public SimulationCar getCar() {
        return car;
    }

    public Car getBaseCar() {
        return car.getCar();
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Point2D getPosition() {
        return car.getCar().getPosition();
    }
}
