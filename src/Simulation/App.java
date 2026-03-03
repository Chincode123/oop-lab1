package Simulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private final CarController controller;

    private final DrawPanel view;

    private final CarView input;

    private Timer timer;

    public App() {
        controller = new CarController();

        view = new DrawPanel();

        input = new CarView();

        // 50 hz corresponds to 20 updates per second.
        final int initialTimerDelay = 50;
        setTimerDelay(initialTimerDelay);
    }

    public void run() {
        timer.start();
        while (true);
    }

    public void addCar(DrawableCar car) {

    }

    public void addWorkshop(DrawableCarWorkshop workshop) {

    }

    private void setTimerDelay(int delay) {
        timer = new Timer(delay, new Update());
    }

    private class Update implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            input.update();
            view.update();
        }
    }
}
