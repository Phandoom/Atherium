package models.Objects;

import kotlin.jvm.internal.SpreadBuilder;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<>();

    public int countdown_2 = 0;
    public int countdown_1 = 0;
    public int countdown_for_Gold_loosing = 0;
    public int countdown_for_talking_1 = 0;
    public int countdown_for_menuedot = 0;


    public int hp = 1000;
    public boolean isdeat = false;

    public int gold = 0;

    public int getXp_Stufe() {
        return xp_Stufe;
    }

    public int xp_Stufe = 1;
    public int xp = 0;
    public int level = 1;

    public boolean ifplayeronnpc = false;
    public boolean playerwanttotalk = false;

    public boolean pausenmnue = false;
    public boolean two_desiton_dots = true;
    public boolean wanttosave = false;

    public int escpresst = 0;
    public boolean enterispresst = false;

    public int menuedotpostition = 1;



    private boolean up = false, down = false, left = false, right = false, WT = false, pause = false, swort = false, heal = false;


    public boolean isUp() {
        return up;
    }
    public void setUp(boolean up) {
        //this.up = up;

        if (pausenmnue == true && wanttosave == false){
            if (countdown_for_menuedot == 0) {
                menuedotpostition--;
                if (menuedotpostition < 1) {
                    menuedotpostition = 4;
                }
                countdown_for_menuedot = 10;
            }
        }
        else {
            this.up = up;
        }
    }

    public boolean isDown() {
        return down;
    }
    public void setDown(boolean down) {
        //this.down = down;

        if (pausenmnue == true && wanttosave == false){
            if (countdown_for_menuedot == 0) {
                menuedotpostition++;
                if (menuedotpostition > 4) {
                    menuedotpostition = 1;
                }
                countdown_for_menuedot = 10;
            }
        }
        else {
            this.down = down;
        }

    }

    public boolean isLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;


    }

    public boolean isRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;


    }

    public boolean isWT(){
        return true;
    }
    public void setWT(boolean WT){
            this.pausenmnue = WT;
    }

    public boolean issword(){
        return swort;
    }
    public void setsword(boolean setsword){
        this.swort = setsword;
    }

    public boolean isheal(){
        return heal;
    }
    public void setheal(boolean setheal){
        this.heal = setheal;
    }

    public void wanttotalk() {
        if (ifplayeronnpc == true){
            playerwanttotalk = true;
        }
    }

    public boolean ismenue(){
        return pausenmnue;
    }
    public void setmaue(boolean pausenmemue){
        if (pausenmemue == true){
            escpresst++;
        }

        if (escpresst == 1) {
            pausenmnue = true;
        }

        if (escpresst == 2){
            menuedotpostition = 1;
            pausenmnue = false;
            escpresst = 0;
        }





    }

    public void enterispresst(boolean ispressed){
        //this.enterispresst = enterispresst;

        if (pausenmnue == true){
            if (menuedotpostition == 1){
                pausenmnue = false;
                countdown_for_menuedot = 10;
            }
            else if (menuedotpostition == 2){
                wanttosave = true;
                countdown_for_menuedot = 100;
            }
            else if (menuedotpostition == 3){

            }
            else if (menuedotpostition == 4){

            }
        }


        if (pausenmnue == true && wanttosave == true && countdown_for_menuedot == 0){
            if (two_desiton_dots == true){
                wanttosave = false;
                pausenmnue = false;
            }
            if (two_desiton_dots == false){
                wanttosave = false;
                wanttosave = false;
            }
        }
    }


    //public boolean isplayeronenemy() {return isplayeronenemy();}


    public void get10xp() {
        if (xp > 50){
            xp = 50;
        }


        xp = xp + 10;

        if (xp == 50){
            xp_Stufe = xp_Stufe + 1;
            xp = 0;
        }
    }

    public void getamountofxp (int howmuch){
        int soll = xp;
        soll = soll + howmuch;

        if (soll >= 50){
            soll = soll - 50;
            xp_Stufe++;
            xp = soll;
        }
        else {
            xp = soll;
        }

    }



    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);



            tempObject.tick();

        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);



        }
    }

    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }
    public void removeObject(GameObject tempObject){
        object.remove(tempObject);
    }


}
