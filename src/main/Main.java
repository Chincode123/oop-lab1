package main;

import Simulation.App;
import Simulation.DrawableCarFactory;
import Simulation.DrawableCarWorkshopFactory;

import java.awt.*;

public class Main {
     public static void main(String[] args) {
        App app = new App();

        app.addCar(DrawableCarFactory.createDrawableVolvo240(new Point(0, 0)));
        app.addCar(DrawableCarFactory.createDrawableSaab95(new Point(0, 100)));
        app.addCar(DrawableCarFactory.createDrawableScania(new Point(0, 200)));

        app.addWorkshop(DrawableCarWorkshopFactory.createDrawableVolvo240Workshop(new Point(300, 0)));

        app.run();
    }
}
