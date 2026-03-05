package BetterSimulation;

import cars.*;

import java.util.ArrayList;
import java.util.HashSet;

public class SimulationModel {
    private final ArrayList<DrawableCar> cars = new ArrayList<>();

    private final ArrayList<DrawableCarWorkshop> workshops = new ArrayList<>();

    private final HashSet<SpriteHandler> spriteHandlers = new HashSet<>();

    private final int screenWidth, screenHeight;

    public SimulationModel(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
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
                        workshop.getCarWorkshop().tryLoad(car.getCar());
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
}
