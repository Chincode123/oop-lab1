package BetterSimulation;

import cars.Car;
import cars.CarWorkshop;

public class SimulationWorkshop<T extends Car> {
    private final CarWorkshop<T> workshop;
    private final Class<T> workshopType;

    public SimulationWorkshop(CarWorkshop<T> workshop, Class<T> workshopType) {
        this.workshop = workshop;
        this.workshopType = workshopType;
    }

    public CarWorkshop<T> getWorkshop() {
        return workshop;
    }

    public void tryLoad(SimulationCar car) {
        if (car.checkType((workshopType))) {
            workshop.load(workshopType.cast(car.getCar()));
        }
    }

    public boolean isLoaded(SimulationCar car) {
        if (car.checkType(workshopType)) {
            return workshop.isLoaded(workshopType.cast(car.getCar()));
        }
        return false;
    }

    public boolean isFull() {
        return workshop.isFull();
    }
}