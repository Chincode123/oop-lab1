package Simulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private final ArrayList<DrawablePositionable> panels = new ArrayList<>();

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void addDrawable(DrawablePositionable drawable) {
        panels.add(drawable);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DrawablePositionable panel : panels) {
            g.drawImage(panel.getImage(), (int)panel.getPosition().getX(), (int)panel.getPosition().getY(), null);
        }
    }
}
