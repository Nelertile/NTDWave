package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss_LV20 extends GameObject {
    private Handler handler;
    Random r = new Random();

    private int timer = 30;
    private int timer2 = 50;
    private int timer3 = 1000;
    private int timerDeath = 100;

    public EnemyBoss_LV20(Float x, Float y, ID id, GroupID gid, Handler handler) {
        super(x, y, id, gid);

        this.handler = handler;

        velX = 0;
        velY = 5;
    }

    public void tick() {
        x += velX;
        y += velY;

        if (timer <= 0) {
            velY = 0;
            timer2--;
        } else {
            timer--;
        }
        if (timer2 <= 0) {
            if (velX == 0)
                velX = 10;
            if (velX > 0)
                velX += 0.01f;
            else if (velX < 0)
                velX -= 0.01f;
            if (timer3 <= 0) {
                int spawn = r.nextInt(5);
                if (spawn == 0)
                    handler.addObject(new EnemyBossBullet(x + 32, y + 256, ID.BasicEnemy, GroupID.Enemy, handler));
                timerDeath--;
                if (timerDeath <= 0) {
                    // death
                    Spawn.boss = false;
                    handler.remObject(this);
                }
            } else {
                int spawn = r.nextInt(10);
                if (spawn == 0)
                    handler.addObject(new EnemyBossBullet(x + 32, y + 256, ID.BasicEnemy, GroupID.Enemy, handler));
                timer3--;
            }

        }
        // if (y <= 0 || y >= Game.HEIGHT - 59) {
        // velY *= -1;
        // }
        if (x <= 0 || x >= Game.WIDTH - 40) {
            velX *= -1;
        }

        // handler.addObject(new Trail(x + 8, y, ID.Trail, GroupID.Effect,
        // Color.MAGENTA, 48, 48, 0.01f, handler));
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.MAGENTA);
        g.fillRect((int) x, (int) y, 64, 256);
        if (Game.showCollisionBoxes) {
            g.setColor(Color.RED);
            g2d.fill(getBounds());
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 256);
    }

}
