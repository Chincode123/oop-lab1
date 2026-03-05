package BetterSimulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements SpriteHandler {
    private ArrayList<DrawablePositionable> panels = new ArrayList<>();

    public void addDrawable(DrawablePositionable drawable) {
        panels.add(drawable);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DrawablePositionable panel : panels) {
            g.drawImage(panel.getImage(), (int)panel.getPosition().getX(), (int)panel.getPosition().getY(), null);
        }
        refresh();
    }

    @Override
    public void updateSprite(DrawablePositionable drawable) {
        panels.add(drawable);
    }

    public void refresh() {
        panels = new ArrayList<>();
    }
}
