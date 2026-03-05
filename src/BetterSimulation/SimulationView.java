package BetterSimulation;

import BetterSimulation.DrawablePositionable;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame {

    private final DrawPanel drawPanel = new DrawPanel();

    private final JPanel controlPanel = new JPanel();

    public SimulationView(String framename, int screenWidth, int screenHeight) {
        final int controlPanelHeight = 240;

        this.setTitle(framename);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        drawPanel.setDoubleBuffered(true);
        drawPanel.setPreferredSize(new Dimension(screenWidth, screenHeight - controlPanelHeight));
        drawPanel.setBackground(Color.green);
        this.add(drawPanel);

        controlPanel.setPreferredSize(new Dimension(screenWidth, controlPanelHeight));
        controlPanel.setBackground(Color.CYAN);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(controlPanel);
    }

    public void update() {
        drawPanel.repaint();
    }

    public SpriteHandler getSpriteHandler() {
        return drawPanel;
    }

    public void attachButtons(Buttons buttons) {
        buttons.attach(controlPanel);
    }

    public void display() {
        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
