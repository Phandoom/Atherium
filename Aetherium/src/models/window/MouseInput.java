package models.window;

import models.Enums.ID;
import models.Objects.GameObject;
import models.Objects.GameObjects.Arrow;
import models.Objects.GameObjects.Player;
import models.Objects.Handler;
import models.window.Camera;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Camera camera;
    private SpriteSheet ss;
    private Player player;
    //private int countown_schoot = 0;

    public MouseInput (Handler handler, Camera camera){
        this.handler = handler;
        this.camera = camera;
    }

    public void mousePressed(MouseEvent e){

        //if(handler.countdown_2 == 0) {

        if (handler.pausenmnue == false) {
            int mx = (int) (e.getX() + camera.getX());
            int my = (int) (e.getY() + camera.getY());


            //handler.removeObject(this);
            //  handler.countdown_2 = handler.countdown_2 + 1000;
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);


                if (tempObject.getId() == ID.Player) {
                    if (handler.countdown_2 == 0) {
                        handler.countdown_2 = 50;
                        handler.addObject(new Arrow(tempObject.getX() + 16, tempObject.getY() + 16, ID.Arrow, handler, mx, my, ss));
                    }
                }

            }

            // }

        }
        else {

        }

    }





}

