package BetterSimulation;

import cars.Positionable;

import java.awt.image.BufferedImage;

public interface DrawablePositionable extends Positionable {
    BufferedImage getImage();
}
