package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

    public static int HEALTH = 100;

    private int greenValue = 255;

    private int score = 0;
    private int level = 1;

    int i = 0;

    public void tick() {
        // HEALTH--;

        HEALTH = Game.clamp(HEALTH, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = HEALTH * 2;

        i++;
        if (i > Spawn.scoreSpeed) {
            score++;
            i = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(16, 16, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(16, 16, HEALTH * 2, 32);
        g.setColor(Color.lightGray);
        g.drawRect(16, 16, 200, 32);

        g.drawString(Game.v, Game.WIDTH - 80, 16);
        g.drawString("Score: " + score, 16, 64);
        g.drawString("Level: " + level, 16, 80);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
