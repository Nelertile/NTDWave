package io.github.nottoxicdev;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import io.github.nottoxicdev.Game.STATE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

public class Menu extends MouseAdapter {
    private HUD hud;
    private Handler handler;

    private BufferedImage skin1_1;
    private BufferedImage skin1_2;
    private BufferedImage skin1_3;
    private BufferedImage skin1_4;
    private BufferedImage skin2_1;
    private BufferedImage skin2_2;
    private BufferedImage skin2_3;
    private BufferedImage skin2_4;
    private BufferedImage skin3_1;
    private BufferedImage skin3_2;
    private BufferedImage skin3_3;
    private BufferedImage skin3_4;
    private BufferedImage skin4_1;
    private BufferedImage skin4_4;

    private int skinshopmargin = 4;

    public static int selectedSkin = 0;

    public Menu(Handler handler, HUD hud) {
        this.hud = hud;
        this.handler = handler;

        Spritesheet ss = new Spritesheet(Game.sprite_sheet);
        skin1_1 = ss.grabSprite(1, 1, 32, 32);
        skin1_2 = ss.grabSprite(1, 2, 32, 32);
        skin1_3 = ss.grabSprite(1, 3, 32, 32);
        skin1_4 = ss.grabSprite(1, 4, 32, 32);
        skin2_1 = ss.grabSprite(2, 1, 32, 32);
        skin2_2 = ss.grabSprite(2, 2, 32, 32);
        skin2_3 = ss.grabSprite(2, 3, 32, 32);
        skin2_4 = ss.grabSprite(2, 4, 32, 32);
        skin3_1 = ss.grabSprite(3, 1, 32, 32);
        skin3_2 = ss.grabSprite(3, 2, 32, 32);
        skin3_3 = ss.grabSprite(3, 3, 32, 32);
        skin3_4 = ss.grabSprite(3, 4, 32, 32);
        skin4_1 = ss.grabSprite(4, 1, 32, 32);
        skin4_4 = ss.grabSprite(4, 4, 32, 32);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == STATE.Game && Game.paused == true) {
            Game.paused = false;
            Game.gameState = STATE.Game;
            HUD.HEALTH = 0;
        }
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

            // skinsel
            if (mouseOver(mx, my, 100, 200, 32, 32)) {
                selectedSkin = 0;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 136, 200, 32, 32)) {
                selectedSkin = 1;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 172, 200, 32, 32)) {
                selectedSkin = 2;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 208, 200, 32, 32)) {
                selectedSkin = 3;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 100, 236, 32, 32)) {
                selectedSkin = 4;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 136, 236, 32, 32)) {
                selectedSkin = 5;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 172, 236, 32, 32)) {
                selectedSkin = 6;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 208, 236, 32, 32)) {
                selectedSkin = 7;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 100, 274, 32, 32)) {
                selectedSkin = 8;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 136, 274, 32, 32)) {
                selectedSkin = 9;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 172, 274, 32, 32)) {
                selectedSkin = 10;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 208, 274, 32, 32)) {
                selectedSkin = 11;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 100, 296, 32, 32)) {
                selectedSkin = 12;
                Game.prefs.putInt("skin", selectedSkin);
            } else if (mouseOver(mx, my, 136, 296, 32, 32)) {
                selectedSkin = 13;
                Game.prefs.putInt("skin", selectedSkin);
            }

        } else if (Game.gameState == STATE.End) {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 128, 384, 256, 80)) {
                hud.setLevel(1);
                hud.setScore(0);
                Spawn.start = true;
                Game.hasWon = false;
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
        // Game.gameState = Game.STATE.End;
        if (Game.gameState == STATE.End) {
            if (!Game.hasWon) {
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

            } else {
                g.setFont(HUD.font);
                g.setColor(Color.lightGray);
                g.drawString("Game done?", Game.WIDTH / 2 - 132, 48);
                g.setFont(HUD.mfont);
                g.drawString("You won", Game.WIDTH / 2 - 64, 128);
                g.drawString("Score: " + hud.getScore(), Game.WIDTH / 2 - 64, 256);
                g.drawString("Level: " + hud.getLevel(), Game.WIDTH / 2 - 64, 320);

                g.setColor(Color.darkGray);
                g.fillRect(Game.WIDTH / 2 - 128, 384, 256, 80);
                g.setColor(Color.lightGray);
                g.drawRect(Game.WIDTH / 2 - 128, 384, 256, 80);
                g.drawString("Exit to menu", Game.WIDTH / 2 - 75, 448);
            }

        }

        else { // menu
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

            int rowPos_1 = 200;
            int rowPos_2 = 232 + skinshopmargin;
            int rowPos_3 = 264 + skinshopmargin * 2;
            int rowPos_4 = 296 + skinshopmargin * 3;
            g.setColor(Color.darkGray);

            g.fillRect(96, 132, 148, 216);
            g.setColor(Color.lightGray);
            g.drawRect(96, 132, 148, 216);

            g.drawString("Skins", 98, 180);
            g.drawImage(skin1_1, 100, rowPos_1, null);
            g.drawImage(skin1_2, 132 + skinshopmargin, rowPos_1, null);
            g.drawImage(skin1_3, 164 + skinshopmargin * 2, rowPos_1, null);
            g.drawImage(skin1_4, 196 + skinshopmargin * 3, rowPos_1, null);

            g.drawImage(skin2_1, 100, rowPos_2, null);
            g.drawImage(skin2_2, 132 + skinshopmargin, rowPos_2, null);
            g.drawImage(skin2_3, 164 + skinshopmargin * 2, rowPos_2, null);
            g.drawImage(skin2_4, 196 + skinshopmargin * 3, rowPos_2, null);

            g.drawImage(skin3_1, 100, rowPos_3, null);
            g.drawImage(skin3_2, 132 + skinshopmargin, rowPos_3, null);
            g.drawImage(skin3_3, 164 + skinshopmargin * 2, rowPos_3, null);
            g.drawImage(skin3_4, 196 + skinshopmargin * 3, rowPos_3, null);

            g.drawImage(skin4_1, 100, rowPos_4, null);
            g.drawImage(skin4_4, 132 + skinshopmargin, rowPos_4, null);

            int selXpos, selYpos, selX, selY;
            selXpos = 99;
            selYpos = rowPos_1 - 1;
            selX = 33;
            selY = 33;
            if (selectedSkin == 1) {
                selXpos = 135;
                selYpos = rowPos_1 - 1;
            } else if (selectedSkin == 2) {
                selXpos = 171;
                selYpos = rowPos_1 - 1;
            } else if (selectedSkin == 3) {
                selXpos = 207;
                selYpos = rowPos_1 - 1;
            } else if (selectedSkin == 4) {
                selXpos = 99;
                selYpos = rowPos_2 - 1;
            } else if (selectedSkin == 5) {
                selXpos = 135;
                selYpos = rowPos_2 - 1;
            } else if (selectedSkin == 6) {
                selXpos = 171;
                selYpos = rowPos_2 - 1;
            } else if (selectedSkin == 7) {
                selXpos = 207;
                selYpos = rowPos_2 - 1;
            } else if (selectedSkin == 8) {
                selXpos = 99;
                selYpos = rowPos_3 - 1;
            } else if (selectedSkin == 9) {
                selXpos = 135;
                selYpos = rowPos_3 - 1;
            } else if (selectedSkin == 10) {
                selXpos = 171;
                selYpos = rowPos_3 - 1;
            } else if (selectedSkin == 11) {
                selXpos = 207;
                selYpos = rowPos_3 - 1;
            } else if (selectedSkin == 12) {
                selXpos = 99;
                selYpos = rowPos_4 - 1;
            } else if (selectedSkin == 13) {
                selXpos = 135;
                selYpos = rowPos_4 - 1;
            }
            g.drawRect(selXpos, selYpos, selX, selY);
        }

    }
}
