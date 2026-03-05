package BetterSimulation;

import Simulation.DrawablePositionable;
import cars.Car;
import cars.Saab95;
import cars.Scania;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SimulationCar {
    private final Car car;
    private final Class<?> carType;

    public SimulationCar(Car car, Class<?> carType) {
        this.car = car;
        this.carType = carType;
    }

    public Car getCar() {
        return car;
    }

    public void tryTurboOn() {
        if (carType == Saab95.class) {
            ((Saab95)car).setTurboOn();
        }
    }

    public void tryTurboOff() {
        if (carType == Saab95.class) {
            ((Saab95)car).setTurboOff();
        }
    }

    public void tryRaiseBed(double amount) {
        if (carType == Scania.class) {
            ((Scania)car).raiseBed(amount);
        }
    }

    public void tryLowerBed(double amount) {
        if (carType == Scania.class) {
            ((Scania)car).lowerBed(amount);
        }
    }

    public boolean checkType(Class<?> type) {
        return carType == type;
    }
}
