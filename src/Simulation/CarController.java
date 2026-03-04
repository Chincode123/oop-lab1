package Simulation;

import cars.*;

import java.util.ArrayList;
import java.util.Objects;

public class CarController {
    private final ArrayList<DrawableCar> cars = new ArrayList<>();

    private final ArrayList<DrawableCarWorkshop> workshops = new ArrayList<>();

    private final int screenWidth, screenHeight;

    public CarController(int screenWidth, int screenHeight) {
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
                car.getCar().gas(gasAmount);
            } catch (RuntimeException e) {
                if (!("can't gas with non-zero bed angle".equals(e.getMessage()))) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void brake(double brakeAmount) {
        for (DrawableCar car : cars) {
            car.getCar().brake(brakeAmount);
        }
    }

    public void start() {
        for (DrawableCar car : cars) {
            car.getCar().startEngine();
        }
    }

    public void stop() {
        for (DrawableCar car : cars) {
            car.getCar().stopEngine();
        }
    }

    public void raiseBed() {
        for (DrawableCar car : cars) {
            if(car.getCar() instanceof Scania)
                ((Scania) car.getCar()).raiseBed(1);
        }
    }

    public void lowerBed() {
        for (DrawableCar car : cars) {
            if(car.getCar() instanceof Scania)
                ((Scania) car.getCar()).lowerBed(1);
        }
    }

    public void turboOn() {
        for (DrawableCar car : cars) {
            if(car.getCar() instanceof Saab95)
                ((Saab95) car.getCar()).setTurboOn();
        }
    }

    public void turboOff() {
        for (DrawableCar car : cars) {
            if(car.getCar() instanceof Saab95)
                ((Saab95) car.getCar()).setTurboOff();
        }
    }

    public void update() {
        for (DrawableCar car : cars) {
            car.getCar().move();

            int x = (int) Math.round(car.getPosition().getX());
            int y = (int) Math.round(car.getPosition().getY());

            if(x < 0 || x > screenWidth - 100 || y < 0 || y > screenHeight - 60) {
                // turn the car 180 degrees
                double totalAmount = Math.PI / car.getCar().getRotationSpeed();

                int fullSteps = (int)Math.floor(totalAmount);
                double remainder = totalAmount - fullSteps;

                for (int i = 0; i < fullSteps; i++) {
                    car.getCar().turn(1);
                }

                car.getCar().turn(remainder);
            }

            final int workshop_half_size = 50;
            for (DrawableCarWorkshop workshop : workshops) {
                if (car.getPosition().distance(workshop.getPosition()) < workshop_half_size) {
                    try {
                        Volvo240 tCar = (Volvo240)car.getCar();
                        CarWorkshop<Volvo240> tWorkshop = (CarWorkshop<Volvo240>) workshop.getCarWorkshop();
                        if (!tWorkshop.isLoaded(tCar)) {
                            try {
                                tWorkshop.load(tCar);
                            } catch(RuntimeException error) {
                                error.printStackTrace();
                            }
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }


                    try {
                        Saab95 tCar = (Saab95) car.getCar();
                        CarWorkshop<Saab95> tWorkshop = (CarWorkshop<Saab95>) workshop.getCarWorkshop();
                        if (!tWorkshop.isLoaded(tCar)) {
                            try {
                                tWorkshop.load(tCar);
                            } catch(RuntimeException error) {
                                error.printStackTrace();
                            }
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }


                    try {
                        Scania tCar = (Scania) car.getCar();
                        CarWorkshop<Scania> tWorkshop = (CarWorkshop<Scania>) workshop.getCarWorkshop();
                        if (!tWorkshop.isLoaded(tCar)) {
                            try {
                                tWorkshop.load(tCar);
                            } catch(RuntimeException error) {
                                error.printStackTrace();
                            }
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
