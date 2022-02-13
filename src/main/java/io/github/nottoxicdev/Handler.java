package io.github.nottoxicdev;

import java.util.LinkedList;

import java.awt.Graphics;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void remObject(GameObject object) {
        this.object.remove(object);
    }

    public void clearEnemies() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.getID() == ID.Player) {
                GameObject tempPlayer = tempObject;
                object.clear();
                addObject(tempPlayer);
            }
        }
    }
}
