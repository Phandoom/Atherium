package models.Objects.GameObjects;

import models.Enums.ID;
import models.Objects.GameObject;

import java.awt.*;

public class Block extends GameObject {
    public Block(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 32, 32);
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
