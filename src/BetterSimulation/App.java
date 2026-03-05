package BetterSimulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private final SimulationModel simulation;

    private final SimulationView view;

    private final Buttons input = new Buttons();

    public App(int updateRate, int windowWidth, int windowHeight) {
        simulation = new SimulationModel(windowWidth, windowHeight);
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
            System.out.println();
        }
    }
}
