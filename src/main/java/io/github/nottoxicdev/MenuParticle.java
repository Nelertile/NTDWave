package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
    private Handler handler;
    Random r = new Random();

    private Color col;
    private int size;

    public MenuParticle(Float x, Float y, ID id, GroupID gid, Handler handler) {
        super(x, y, id, gid);

        this.handler = handler;

        velX = r.nextInt((15 - -15) + -15);

        velY = r.nextInt((15 - -15) + -15);

        size = r.nextInt(24) + 24;

        if (velY == 0 && velX == 0) {
            velX = 1;
            velY = 1;
        }

        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
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

        handler.addObject(new Trail(x, y, ID.Trail, GroupID.Effect, col, size, size, 0.05f, handler));
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(col);
        g.fillRect((int) x, (int) y, size, size);
        if (Game.showCollisionBoxes) {
            g.setColor(Color.RED);
            g2d.fill(getBounds());
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, size, size);
    }

}
