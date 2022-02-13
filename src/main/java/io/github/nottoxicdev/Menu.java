package io.github.nottoxicdev;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import io.github.nottoxicdev.Game.STATE;

import java.awt.Graphics;
import java.awt.Color;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;

    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (game.gameState == STATE.Menu) {
            // play
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 128, 256, 80)) {
                game.gameState = STATE.Game;
                handler.addObject(
                        new Player((float) Game.WIDTH / 2 - 32, (float) Game.HEIGHT / 2 - 32, ID.Player, handler));
            }
            // bugs button
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 256, 256, 80)) {
                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(new URI("https://github.com/NotToxicDev/java-game/issues"));
                } catch (IOException | URISyntaxException e2) {
                    e2.printStackTrace();
                }
            }

            // exit
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 384, 256, 80)) {
                System.exit(1);
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setFont(HUD.font);
        g.setColor(Color.lightGray);
        g.drawString(Game.title, 0, 64);
        g.drawRect(Game.WIDTH / 2 - 128, 128, 256, 80);
        g.drawRect(Game.WIDTH / 2 - 128, 256, 256, 80);
        g.drawRect(Game.WIDTH / 2 - 128, 384, 256, 80);
        g.setFont(HUD.font);
        g.drawString("Play", Game.WIDTH / 2 - 64, 192);
        g.drawString("Bugs", Game.WIDTH / 2 - 70, 320);
        g.drawString("Quit", Game.WIDTH / 2 - 64, 448);
    }
}
