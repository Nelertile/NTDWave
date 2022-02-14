package io.github.nottoxicdev;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Rectangle;

public class Trail extends GameObject {
    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    private float life;
    private boolean hasSprite;
    private BufferedImage sprite;

    public Trail(float x, float y, ID id, GroupID gid, Color color, int width, int height, float life,
            Boolean hasSprite, BufferedImage sprite,
            Handler handler) {
        super(x, y, id, gid);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        this.hasSprite = hasSprite;
        this.sprite = sprite;
    }

    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.001f;
        } else {
            handler.remObject(this);
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(makeTrasparent(alpha));
        if (!hasSprite) {
            g.setColor(color);
            g.fillRect((int) x, (int) y, width, height);
        } else {
            g.drawImage(sprite, (int) x, (int) y, null);
        }

        g2d.setComposite(makeTrasparent(1));

    }

    private AlphaComposite makeTrasparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public Rectangle getBounds() {
        return null;
    }

}
