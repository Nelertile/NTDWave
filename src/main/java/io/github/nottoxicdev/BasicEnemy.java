package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends GameObject {

    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);

        velX = 10;
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
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 24, 24);
    }

}
