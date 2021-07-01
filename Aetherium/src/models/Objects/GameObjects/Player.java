package models.Objects.GameObjects;

import models.Enums.ID;
import models.Objects.GameObject;
import models.Objects.Handler;
import models.window.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Player extends GameObject {
static Random random = new Random();
    Handler handler;
    private BufferedImage player = null;
    int gold = 0;
    int exp = 0;
    int zufallstrefferWert;

    //int hp = 1000;
    //int countdown_1 = 0;
    //public int countdown_2 = 0;


    public Player(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        player = ss.grabImage(3, 1, 32, 32);
    }
    @Override
    public void tick() {


        if (handler.pausenmnue == false) {
            x += velX;
            y += velY;

            //BufferStrategy bs = this.getBufferStrategy();
            //if (bs == null){
            //    this.createBufferStrategy(3);
            //    return;
            //}
            //Graphics g = bs.getDrawGraphics();
            //Graphics2D g2D = (Graphics2D) g;


            //x -= -velX * -1;
            //y -= -velY * -1;

            //System.out.println("hp: " + handler.hp + "                                c2:" + handler.countdown_2);

            collision();

            if (handler.hp <= 0) {
                System.out.println("deat");

                //g.setColor(Color.black);
                //g.drawRect(5, 5, 200, 32);
            } else {

            }


            if (handler.isUp()) {
                velY = -5;
            } else if (!handler.isDown()) {
                velY = 0;
            }

            if (handler.isDown()) {
                velY = 5;
            } else if (!handler.isUp()) {
                velY = 0;
            }

            if (handler.isLeft()) {
                velX = -5;
            } else if (!handler.isRight()) {
                velX = 0;
            }

            if (handler.isRight()) {
                velX = 5;
            } else if (!handler.isLeft()) {
                velX = 0;
            }


            if (handler.isheal()) {
                if (handler.countdown_1 == 0) {

                    handler.countdown_1 = 1000;
                    handler.hp = handler.hp + 100;
                    if (handler.hp > 1000) {
                        handler.hp = 1000;
                    } else {

                    }
                } else {
                    handler.hp = handler.hp + 0;
                }

            } else if (!handler.isheal()) {
                handler.hp = handler.hp + 0;
            }

            if (handler.hp <= 0) {
                handler.removeObject(this);
                handler.isdeat = true;
            }

            if (handler.countdown_1 != 0) {
                handler.countdown_1 = handler.countdown_1 - 1;
            } else {

            }

            if (handler.countdown_2 != 0) {
                handler.countdown_2 = handler.countdown_2 - 1;
            } else {

            }

            if (handler.countdown_for_Gold_loosing != 0) {
                handler.countdown_for_Gold_loosing = handler.countdown_for_Gold_loosing - 1;
            } else {

            }

            if (handler.countdown_for_talking_1 != 0) {
                handler.countdown_for_talking_1 = handler.countdown_for_talking_1 - 1;
            } else {

            }

            //if (countdown_2 != 0){
            //  countdown_2 = countdown_2 - 1;
            //}
            //else {
            //
            //}


        }
        else {

        }

        if (handler.countdown_for_menuedot != 0) {
            handler.countdown_for_menuedot = handler.countdown_for_menuedot - 1;
        } else {

        }

    }

    private void collision(){
        int playerLevel = handler.getXp_Stufe();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block){
                if (getBounds().intersects(tempObject.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            if (tempObject.getId() == ID.Enemy) {
                if (this.getBoundsBig().intersects(tempObject.getBounds())) {
                    zufallstrefferWert = random.nextInt(100);

                    if (zufallstrefferWert < (90/playerLevel)){
                        handler.hp = handler.hp - 1;
                    }
                }
            }
            if (tempObject.getId() == ID.NPC){
                if (this.getBoundsBig().intersects(tempObject.getBoundsBig())) {
                    handler.ifplayeronnpc = true;
                }
                else {
                    handler.ifplayeronnpc = false;
                }
            }
            if (tempObject.getId() == ID.NPC){
                if (this.getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            else {
                //handler.ifplayeronnpc = false;
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(player, x, y,null);
        }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public Rectangle getBoundsBig(){
        return new Rectangle(x-16, y-16,64,96);
    }

    @Override
    public Rectangle outerRectangle(){return new Rectangle(x,y, 64,64);};

    public void addGold(int Gold){
        gold = gold + Gold;
    }

    public void addExp(int Exp){
        exp = exp + Exp;
    }
}
