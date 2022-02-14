package io.github.nottoxicdev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
    Handler handler;

    private BufferedImage player_image;

    public Player(Float x, Float y, ID id, Handler handler) {
        super(x, y, id, null);
        this.handler = handler;

        Spritesheet ss = new Spritesheet(Game.sprite_sheet);

        if (Menu.selectedSkin == 0) {
            player_image = ss.grabSprite(1, 1, 32, 32);
        } else if (Menu.selectedSkin == 1) {
            player_image = ss.grabSprite(1, 2, 32, 32);
        } else if (Menu.selectedSkin == 2) {
            player_image = ss.grabSprite(1, 3, 32, 32);
        } else if (Menu.selectedSkin == 3) {
            player_image = ss.grabSprite(1, 4, 32, 32);
        } else if (Menu.selectedSkin == 4) {
            player_image = ss.grabSprite(2, 1, 32, 32);
        } else if (Menu.selectedSkin == 5) {
            player_image = ss.grabSprite(2, 2, 32, 32);
        } else if (Menu.selectedSkin == 6) {
            player_image = ss.grabSprite(2, 3, 32, 32);
        } else if (Menu.selectedSkin == 7) {
            player_image = ss.grabSprite(2, 4, 32, 32);
        } else if (Menu.selectedSkin == 8) {
            player_image = ss.grabSprite(3, 1, 32, 32);
        } else if (Menu.selectedSkin == 9) {
            player_image = ss.grabSprite(3, 2, 32, 32);
        } else if (Menu.selectedSkin == 10) {
            player_image = ss.grabSprite(3, 3, 32, 32);
        } else if (Menu.selectedSkin == 11) {
            player_image = ss.grabSprite(3, 4, 32, 32);
        } else if (Menu.selectedSkin == 12) {
            player_image = ss.grabSprite(4, 1, 32, 32);
        } else {
            player_image = ss.grabSprite(4, 4, 32, 32);
        }

    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0f, (float) Game.WIDTH - 48);
        y = Game.clamp(y, 0f, (float) Game.HEIGHT - 70);
        handler.addObject(
                new Trail(x, y, ID.Trail, GroupID.Effect, null, 32, 32, 0.2f, true, player_image, handler));

        collision();

        if (Game.gameState == Game.STATE.End) {
            handler.remObject(this);
        }
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // collision code
                    HUD.HEALTH -= 1000;
                }
            } else if (tempObject.getGID() == GroupID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // collision code
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (Game.showCollisionBoxes) {
            g.setColor(Color.GREEN);
            g2d.draw(getBounds());
        }

        // g.setColor(Color.GRAY);
        // g.fillRect((int) x, (int) y, 32, 32);
        g.drawImage(player_image, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

}
