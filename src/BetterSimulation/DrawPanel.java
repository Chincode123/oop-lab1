package BetterSimulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DrawPanel extends JPanel implements SpriteHandler {
    private Set<DrawablePositionable> panels = new HashSet<>();

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

    @Override
    public void addSprite(DrawablePositionable drawable) {
        panels.add(drawable);
    }

    @Override
    public void removeSprite(DrawablePositionable drawable) {
        panels.remove(drawable);
    }
}
