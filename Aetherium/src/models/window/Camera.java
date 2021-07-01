package models.window;

import models.Objects.GameObject;

public class Camera {

    private float x, y;

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object){

        x += ((object.getX() - x) - 800/2) * 0.05f;
        y += ((object.getY() - y) - 600/2) * 0.05f;

        if (x <= 0) x = 0;
        if (x >= 1264) x = 1264;
        if (y <= -6) y = -6;
        if (y >= 1480) y = 1480;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
