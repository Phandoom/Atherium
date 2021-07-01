package models.Objects.GameObjects.Water;

import models.Enums.ID;
import models.Objects.GameObject;
import models.window.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Water_left extends GameObject {

    private BufferedImage water = null;

    public Water_left(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);

        water = ss.grabImage(3, 3, 32, 32);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(water, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public Rectangle outerRectangle() {
        return null;
    }

    @Override
    public Rectangle getBoundsBig() {
        return null;
    }
}
