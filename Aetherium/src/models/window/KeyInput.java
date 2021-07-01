package models.window;

import models.Enums.ID;
import models.Objects.GameObject;
import models.Objects.Handler;

import javax.management.StandardMBean;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_W) handler.setUp(true);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_S) handler.setDown(true);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_A) handler.setLeft(true);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_D) handler.setRight(true);
            }
            if (tempObject.getId() == ID.Player){
                //if(handler.countdown_2 == 0) {
                //    handler.countdown_2 = 50;
                    if (key == KeyEvent.VK_SPACE) {
                        if(handler.countdown_2 == 0) {
                            handler.countdown_2 = 50;
                            handler.setsword(true);
                        }
                    }
                    //System.out.println("Test");
                //}
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_Q) handler.setheal(true);
                //System.out.println("Test");
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_E) handler.wanttotalk();
                //System.out.println("Test");
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_ESCAPE) handler.setmaue(true);
                //System.out.println("Test");
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_ENTER) handler.enterispresst(true);
                //System.out.println("Test");
            }







            if (tempObject.getId() == ID.Player){

                if (key == KeyEvent.VK_R){
                    if (!handler.isWT()){
                        handler.setWT(true);
                    }
                    else if (handler.isWT()){
                        handler.setWT(false);
                    }
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_W) handler.setUp(false);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_S) handler.setDown(false);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_A) handler.setLeft(false);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_D) handler.setRight(false);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_SPACE) handler.setsword(false);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_Q) handler.setheal(false);
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_E) handler.playerwanttotalk = false;
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_ESCAPE) handler.setmaue(false);
                //System.out.println("Test");
            }
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_ENTER) handler.enterispresst(false);
                //System.out.println("Test");
            }


        }

    }
}
