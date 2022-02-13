package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
    Handler handler;

    public Player(Float x, Float y, ID id, Handler handler) {
        super(x, y, id, null);
        this.handler = handler;

    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0f, (float) Game.WIDTH - 48);
        y = Game.clamp(y, 0f, (float) Game.HEIGHT - 70);
        handler.addObject(new Trail(x, y, ID.Trail, GroupID.Effect, Color.GRAY, 32, 32, 0.2f, handler));

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getGID() == GroupID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // collision code
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (Game.showCollisionBoxes) {
            g.setColor(Color.GREEN);
            g2d.draw(getBounds());
        }

        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

}
