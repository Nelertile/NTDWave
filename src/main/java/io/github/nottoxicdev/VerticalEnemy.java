package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class VerticalEnemy extends GameObject {
    private Handler handler;

    public VerticalEnemy(float f, float g, ID id, GroupID gid, Handler handler) {
        super(f, g, id, gid);

        this.handler = handler;

        velY = 10;
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 59) {
            velY *= -1;
        }
        if (x <= 0 || x >= Game.WIDTH - 40) {
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, GroupID.Effect, Color.CYAN, 24, 24, 0.1f, false, null, handler));
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.CYAN);
        g.fillRect((int) x, (int) y, 24, 24);
        if (Game.showCollisionBoxes) {
            g.setColor(Color.RED);
            g2d.fill(getBounds());
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 24, 24);
    }

}
