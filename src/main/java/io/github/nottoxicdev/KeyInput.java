package io.github.nottoxicdev;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(1);
            }

            if (tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_F3) {
                    if (Game.showCollisionBoxes) {
                        Game.showCollisionBoxes = false;
                    } else {
                        Game.showCollisionBoxes = true;

                    }
                }
                // key events for player 1
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-6f);
                    keyDown[0] = true;
                } else if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(6f);
                    keyDown[1] = true;

                } else if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(6f);
                    keyDown[2] = true;

                } else if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-6f);
                    keyDown[3] = true;

                }
            }

        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                // key events for player 1
                if (key == KeyEvent.VK_W) {
                    // tempObject.setVelY(0);
                    keyDown[0] = false;
                } else if (key == KeyEvent.VK_S) {
                    // tempObject.setVelY(0);
                    keyDown[1] = false;

                } else if (key == KeyEvent.VK_D) {
                    // tempObject.setVelX(0);
                    keyDown[2] = false;

                } else if (key == KeyEvent.VK_A) {
                    // tempObject.setVelX(0);
                    keyDown[3] = false;

                }

                if (!keyDown[0] && !keyDown[1])
                    tempObject.setVelY(0f);
                if (!keyDown[2] && !keyDown[3])
                    tempObject.setVelX(0f);
            }

        }
    }

}
