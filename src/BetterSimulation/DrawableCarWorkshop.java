package BetterSimulation;

import cars.Car;
import cars.CarWorkshop;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableCarWorkshop implements DrawablePositionable {
    private final SimulationWorkshop workshop;

    private final BufferedImage image;

    public DrawableCarWorkshop(CarWorkshop<? extends Car> workshop, Class<?> workshopType, BufferedImage image) {
        this.workshop = new SimulationWorkshop(workshop, workshopType);
        this.image = image;
    }

    public SimulationWorkshop getCarWorkshop() {
        return workshop;
    }

    @Override
    public Point2D getPosition() {
        return workshop.getWorkshop().getPosition();
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
