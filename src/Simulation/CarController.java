package Simulation;

import cars.Car;

import java.util.ArrayList;

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
            car.getCar().gas(gasAmount);
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

    }

    public void lowerBed() {

    }

    public void turboOn() {

    }

    public void turboOff() {

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
        }
    }
}
