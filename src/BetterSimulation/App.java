package BetterSimulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private final SimulationModel controller;

    private final SimulationView view;

    private final Buttons input = new Buttons();

    public App(int updateRate, int windowWidth, int windowHeight) {
        controller = new SimulationModel(windowWidth, windowHeight);
        input.implement(controller);

        view = new SimulationView("Car simulation ++(++(1.0))", windowWidth, windowHeight);
        view.attachButtons(input);
        view.display();

        runLoop(updateRate);
    }

    public void addCar(DrawableCar car) {
        controller.addCar(car);
        view.addDrawable(car);
    }

    public void addWorkshop(DrawableCarWorkshop workshop) {
        controller.addWorkshop(workshop);
        view.addDrawable(workshop);
    }

    private void runLoop(int delay) {
        final Timer timer = new Timer(delay, new Update());
        timer.start();
    }

    private class Update implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controller.update();
            view.update();
        }
    }
}
