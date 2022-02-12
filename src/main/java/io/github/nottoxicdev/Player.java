package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);

    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
    }

    public void render(Graphics g) {
        if (id == ID.Player) {
            g.setColor(Color.DARK_GRAY);
        }
        g.fillRect(x, y, 32, 32);
    }

}
