package Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private final CarController controller;

    private final DrawPanel view;

    private final CarView input;

    private Timer timer;

    public App(int updateRate) {
        int X = 800;
        int Y = 800;

        controller = new CarController(X, Y);

        view = new DrawPanel(X, Y - 240);

        input = new CarView(controller);
        input.initComponents("Car simulation ++(1.0)", view, X, Y);

        setTimerDelay(updateRate);
    }

    public void run() {
        while (true);
    }

    public void addCar(DrawableCar car) {
        controller.addCar(car);
        view.addDrawable(car);
    }

    public void addWorkshop(DrawableCarWorkshop workshop) {
        controller.addWorkshop(workshop);
        view.addDrawable(workshop);
    }

    private void setTimerDelay(int delay) {
        timer = new Timer(delay, new Update());
        timer.start();
    }

    private class Update implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controller.update();
            view.repaint();
        }
    }
}
