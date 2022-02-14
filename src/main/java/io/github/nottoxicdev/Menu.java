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
    private HUD hud;
    private Handler handler;

    public Menu(Handler handler, HUD hud) {
        this.hud = hud;
        this.handler = handler;

    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == STATE.Menu) {
            // play
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 128, 256, 80)) {
                Game.gameState = STATE.Game;
                handler.addObject(
                        new Player((float) Game.WIDTH / 2 - 32, (float) Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemies();

            }
            // bugs button
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 256, 256, 80)) {
                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(new URI("https://github.com/NotToxicDev/NTDWave/issues"));
                } catch (IOException | URISyntaxException e2) {
                    e2.printStackTrace();
                }
            }

            // exit
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 384, 256, 80)) {
                System.exit(1);
            }
        } else if (Game.gameState == STATE.End) {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 384, 256, 80)) {
                hud.setLevel(1);
                hud.setScore(0);
                Spawn.start = true;
                Game.gameState = STATE.Menu;
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
        // Game.gameState = STATE.End;
        if (Game.gameState == STATE.End) {
            g.setFont(HUD.font);
            g.setColor(Color.lightGray);
            g.drawString("Game over", Game.WIDTH / 2 - 132, 48);
            g.setFont(HUD.mfont);
            g.drawString("You lost", Game.WIDTH / 2 - 64, 128);
            g.drawString("Score: " + hud.getScore(), Game.WIDTH / 2 - 64, 256);
            g.drawString("Level: " + hud.getLevel(), Game.WIDTH / 2 - 64, 320);

            g.setColor(Color.darkGray);
            g.fillRect(Game.WIDTH / 2 - 128, 384, 256, 80);
            g.setColor(Color.lightGray);
            g.drawRect(Game.WIDTH / 2 - 128, 384, 256, 80);
            g.drawString("Try again", Game.WIDTH / 2 - 75, 448);

        } else { // menu
            g.setFont(HUD.font);
            g.setColor(Color.lightGray);
            g.drawString(Game.v, 0, 48);
            if (Game.m != "") {
                g.drawString(Game.m, 0, Game.HEIGHT - 48);
            }
            g.setColor(Color.darkGray);

            g.fillRect(Game.WIDTH / 2 - 128, 128, 256, 80);
            g.fillRect(Game.WIDTH / 2 - 128, 256, 256, 80);
            g.fillRect(Game.WIDTH / 2 - 128, 384, 256, 80);
            g.setColor(Color.lightGray);
            g.drawRect(Game.WIDTH / 2 - 128, 128, 256, 80);
            g.drawRect(Game.WIDTH / 2 - 128, 256, 256, 80);
            g.drawRect(Game.WIDTH / 2 - 128, 384, 256, 80);
            g.setFont(HUD.font);
            g.drawString("Play", Game.WIDTH / 2 - 64, 192);
            g.drawString("Bugs", Game.WIDTH / 2 - 70, 320);
            g.drawString("Quit", Game.WIDTH / 2 - 64, 448);
        }

    }
}
