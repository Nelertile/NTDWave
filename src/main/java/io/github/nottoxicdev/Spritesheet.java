package io.github.nottoxicdev;

import java.awt.image.BufferedImage;

public class Spritesheet {
    private BufferedImage sprite;

    public Spritesheet(BufferedImage ss) {
        this.sprite = ss;
    }

    public BufferedImage grabSprite(int row, int col, int width, int height) {
        BufferedImage img = sprite.getSubimage((col * 32 - 32), (row * 32 - 32), width, height);
        return img;
    }
}
