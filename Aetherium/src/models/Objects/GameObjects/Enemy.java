package models.Objects.GameObjects;

import models.Enums.ID;
import models.Objects.GameObject;
import models.Objects.Handler;
import models.window.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {


    private Handler handler;
    private BufferedImage enemy = null;
    Random random = new Random();
    int choose = 0;
    int health = 20;

    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;

        enemy = ss.grabImage(1,  4, 64, 128);
    }

    @Override
    public void tick() {

        if (handler.pausenmnue == false) {
            x += velX;
            y += velY;

            choose = random.nextInt(50);

            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Block) {
                    if (getBoundsBig().intersects(tempObject.getBounds())) {
                        x += (velX * 2) * -1;
                        y += (velY * 2) * -1;
                    } else if (choose == 0) {
                        velX = (random.nextInt(4 - -4) + -4);
                        velY = (random.nextInt(4 - -4) + -4);
                    }
                }
                if (tempObject.getId() == ID.Arrow) {
                    if (getBoundsBig().intersects(tempObject.getBounds())) {
                        if (health <= 0) {
                            //handler.get10xp();
                            handler.getamountofxp(13);
                            handler.gold = handler.gold + 1;
                            handler.removeObject(this);
                        } else if (health >= 1) {
                            int schaden = 4 * handler.xp_Stufe;
                            health = health - schaden;

                        }
                    }
                }
                if (tempObject.getId() == ID.Player) {
                    if (handler.issword() == true) {        //&& handler.countdown_2 == 0

                        if (getBoundsBig().intersects(tempObject.getBounds())) {
                            if (health <= 0) {

                                handler.get10xp();
                                handler.gold = handler.gold + 1;

                                //handler.xp = handler.xp + 10;
                                handler.removeObject(this);

                            } else if (health >= 1) {
                                health = health - 8;
                                //handler.countdown_2 = 50;

                            }
                        }
                    }
                }

            }
        }
        else {

        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(enemy, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 64);
    }

    public Rectangle getBoundsBig(){
        return new Rectangle(x-16, y-16,64,96);
    }

    public Rectangle outerRectangle(){return new Rectangle(x - 16, y - 16,192,192);};



   // public int giveGold(){
     //   int probability = random.nextInt(1);

//        return
  //  }
}
