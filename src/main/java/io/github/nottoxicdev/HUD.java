package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class HUD {

    public static float HEALTH = 100;

    public static Font font = new Font("Comfortaa", 1, 50);
    public static Font mfont = new Font("Comfortaa", 1, 30);
    public static Font sfont = new Font("Comfortaa", 0, 15);

    private float greenValue = 255f;

    private int score = 0;
    private int level = 1;

    int i = 0;

    public void tick() {
        // HEALTH--;

        HEALTH = Game.clamp(HEALTH, 0f, 100f);
        greenValue = (int) Game.clamp((float) greenValue, (float) 0, (float) 255);

        greenValue = (int) (HEALTH * 2);

        i++;
        if (i > Spawn.scoreSpeed) {
            score++;
            i = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(16, 16, 200, 32);
        g.setColor(new Color(75, (int) greenValue, 0));
        g.fillRect((int) 16, (int) 16, (int) HEALTH * 2, 32);
        g.setColor(Color.lightGray);
        g.drawRect(16, 16, 200, 32);

        g.setFont(sfont);
        g.drawString(Game.v, 0, Game.HEIGHT - 48);
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
