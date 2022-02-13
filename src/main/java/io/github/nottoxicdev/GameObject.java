package io.github.nottoxicdev;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected float x, y;
    protected ID id;
    protected GroupID gid;
    protected float velX, velY;

    public GameObject(float x, float y, ID id, GroupID gid) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.gid = gid;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    // set & get pos
    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    // set & get id
    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    // set & get gid
    public void setGID(GroupID gid) {
        this.gid = gid;
    }

    public GroupID getGID() {
        return gid;
    }

    // set & get velocity
    public void setVelX(Float velX) {
        this.velX = velX;
    }

    public void setVelY(Float velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }
}
