package io.github.nottoxicdev;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends Canvas {
    public Window(int width, int height, String title, Game game) {
        Spritesheet ss = new Spritesheet(Game.sprite_sheet);
        BufferedImage ico = ss.grabSprite(4, 4, 32, 32);
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setIconImage(ico);
        frame.setVisible(true);
        game.start();
    }
}
