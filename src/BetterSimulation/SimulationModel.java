package BetterSimulation;

import cars.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class SimulationModel {
    private final ArrayList<DrawableCar> cars = new ArrayList<>();

    private final ArrayList<DrawableCarWorkshop> workshops = new ArrayList<>();

    private final HashSet<SpriteHandler> spriteHandlers = new HashSet<>();

    private final int screenWidth, screenHeight;

    private final int maxCars;

    public SimulationModel(int screenWidth, int screenHeight, int maxCars) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.maxCars = maxCars;
    }

    public void addCar(DrawableCar car) {
        cars.add(car);
    }

    public void addWorkshop(DrawableCarWorkshop workshop) {
        workshops.add(workshop);
    }

    public void gas(double gasAmount) {
        for (DrawableCar car : cars) {
            try {
                car.getBaseCar().gas(gasAmount);
            } catch (RuntimeException e) {
                if (!("can't gas with non-zero bed angle".equals(e.getMessage()))) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void brake(double brakeAmount) {
        for (DrawableCar car : cars) {
            car.getBaseCar().brake(brakeAmount);
        }
    }

    public void start() {
        for (DrawableCar car : cars) {
            car.getBaseCar().startEngine();
        }
    }

    public void stop() {
        for (DrawableCar car : cars) {
            car.getBaseCar().stopEngine();
        }
    }

    public void raiseBed() {
        for (DrawableCar car : cars) {
            car.getCar().tryRaiseBed(1);
        }
    }

    public void lowerBed() {
        for (DrawableCar car : cars) {
            car.getCar().tryLowerBed(1);
        }
    }

    public void turboOn() {
        for (DrawableCar car : cars) {
            car.getCar().tryTurboOn();
        }
    }

    public void turboOff() {
        for (DrawableCar car : cars) {
            car.getCar().tryTurboOff();
        }
    }

    public void update() {
        for (DrawableCar car : cars) {
            car.getBaseCar().move();

            int x = (int) Math.round(car.getPosition().getX());
            int y = (int) Math.round(car.getPosition().getY());

            if(x < 0 || x > screenWidth - 100 || y < 0 || y > screenHeight - 60) {
                // turn the car 180 degrees
                double totalAmount = Math.PI / car.getBaseCar().getRotationSpeed();

                int fullSteps = (int)Math.floor(totalAmount);
                double remainder = totalAmount - fullSteps;

                for (int i = 0; i < fullSteps; i++) {
                    car.getBaseCar().turn(1);
                }

                car.getBaseCar().turn(remainder);
            }


            final int workshop_half_size = 50;
            for (DrawableCarWorkshop workshop : workshops) {
                if (car.getPosition().distance(workshop.getPosition()) < workshop_half_size) {
                    if (!workshop.getCarWorkshop().isLoaded(car.getCar())) {
                        try {
                            workshop.getCarWorkshop().tryLoad(car.getCar());
                        } catch (RuntimeException e) {
                            if (!("CarWorkshop full".equals(e.getMessage()))) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }

            notifyHandlers(car);
        }

        for (DrawableCarWorkshop workshop : workshops) {
            notifyHandlers(workshop);
        }
    }

    public void addSpriteHandler(SpriteHandler handler) {
        spriteHandlers.add(handler);
    }

    public void removeSpriteHandler(SpriteHandler handler) {
        spriteHandlers.remove(handler);
    }

    private void notifyHandlers(DrawablePositionable drawable) {
        for (SpriteHandler spriteHandler : spriteHandlers) {
            spriteHandler.updateSprite(drawable);
        }
    }

    public void createRandomCar() {
        if (cars.size() > maxCars) return;

        Random random = new Random();

        switch (random.nextInt(3)) {
            case 0:
                addCar(DrawableCarFactory.createDrawableSaab95(new Point(random.nextInt(screenWidth - 100), random.nextInt(screenHeight - 300))));
                break;
            case 1:
                addCar(DrawableCarFactory.createDrawableScania(new Point(random.nextInt(screenWidth - 100), random.nextInt(screenHeight - 300))));
                break;
            case 2:
                addCar(DrawableCarFactory.createDrawableVolvo240(new Point(random.nextInt(screenWidth - 100), random.nextInt(screenHeight - 300))));
                break;
        };
    }

    public void removeRandomCar() {
        if (cars.size() <= 0) return;

        Random random = new Random();

        cars.remove(random.nextInt(cars.size()));
    }
}
