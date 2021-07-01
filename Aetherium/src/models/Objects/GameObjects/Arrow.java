package models.Objects.GameObjects;

import models.Enums.ID;
import models.Objects.GameObject;
import models.Objects.Handler;
import models.window.SpriteSheet;

import java.awt.*;

public class Arrow extends GameObject {

    private Handler handler;
    private Player player;
    //public int countdown_2 = 0;


    public Arrow(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) {
        super(x, y, id, ss);
        //this.handler = handler;
        //handler.countdown_2 = 100;
        //if(handler.countdown_2 == 0) {

        handler.removeObject(this);

        //super(x, y, id, ss);
        this.handler = handler;


        velX = (mx - x) / 10; //10 ist die geschwindikeit
        velY = (my - y) / 10;
        //}
    }


    public void tick() {
        x += velX;
        y += velY;

        //if(handler.countdown_2 > 0) {
        //    handler.removeObject(this);
        //d}


        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            //handler.countdown_2
            if (tempObject.getId() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {

                    handler.removeObject(this);
                }
            }
        }




    }


    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 8, 8);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
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
