package BetterSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class App {
    private final SimulationModel simulation;

    private final SimulationView view;

    private final Buttons input = new Buttons();

    public App(int updateRate, int windowWidth, int windowHeight) {
        final int maxCars = 10;
        simulation = new SimulationModel(windowWidth, windowHeight, maxCars);
        input.implement(simulation);

        view = new SimulationView("Car simulation ++(++(1.0))", windowWidth, windowHeight);
        view.attachButtons(input);
        view.display();

        simulation.addSpriteHandler(view.getSpriteHandler());

        runLoop(updateRate);
    }

    public void addCar(DrawableCar car) {
        simulation.addCar(car);
    }

    public void addWorkshop(DrawableCarWorkshop workshop) {
        simulation.addWorkshop(workshop);
    }

    private void runLoop(int delay) {
        final Timer timer = new Timer(delay, new Update());
        timer.start();
    }

    private class Update implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            simulation.update();
            view.update();
        }
    }

    public static void main(String[] args) {
        // 50 hz corresponds to 20 updates per second.
        final int updateRate = 50;
        App app = new App(updateRate, 800, 800);

        app.addCar(DrawableCarFactory.createDrawableVolvo240(new Point(0, 0)));
        app.addCar(DrawableCarFactory.createDrawableSaab95(new Point(0, 100)));
        app.addCar(DrawableCarFactory.createDrawableScania(new Point(0, 200)));

        app.addWorkshop(DrawableCarWorkshopFactory.createDrawableVolvo240Workshop(new Point(300, 0)));
    }
}
