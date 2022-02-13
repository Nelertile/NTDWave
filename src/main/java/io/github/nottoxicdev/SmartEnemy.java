package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
    private Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, GroupID gid, Handler handler) {
        super(x, y, id, gid);

        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player)
                player = handler.object.get(i);
        }

        velX = 10;
        velY = 10;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 24, 24);
    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 4.5f;
        float diffY = y - player.getY() - 4.5f;
        float distance = (float) Math
                .sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velX = (Float) ((-1 / distance) * diffX);
        velY = (Float) ((-1 / distance) * diffY);

        handler.addObject(new Trail(x, y, ID.Trail, GroupID.Effect, Color.RED, 24, 24, 0.01f, handler));
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (Game.showCollisionBoxes) {
            g.setColor(Color.MAGENTA);
            g2d.draw(getBounds());
        }
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, 24, 24);
    }

}
