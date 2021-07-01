package models.Objects.GameObjects;

import DB.IRepositoryPlayer;
import DB.RepositoryPlayerDB;
import models.Enums.ID;
import models.Objects.GameObject;
import models.Objects.Handler;
import models.window.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;


public class NPC extends GameObject{

    private Handler handler;
    private BufferedImage npc = null;


    public NPC(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;

        npc = ss.grabImage(6,  1, 64, 64);
    }


    @Override
    public void tick() {




            //if (tempObject.getId() == ID.Player){
            //    if (handler.issword() == true && handler.countdown_2 == 0) {
            //
            //        if (getBoundsBig().intersects(tempObject.getBounds())) {
            //            if (health <= 0) {
            //                handler.removeObject(this);
            //            } else if (health >= 1) {
            //                health = health - 8;
            //                handler.countdown_2 = 50;
            //
            //            }
            //        }
            //    }
            //}

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(npc, x, y, null);
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(x+15, y, 20, 64);
    }

    public Rectangle getBoundsBig(){
        return new Rectangle(x-16, y-16,96,96);
    }

    public Rectangle outerRectangle(){
        return new Rectangle(x - 16, y - 16,192,192);
    }


}
