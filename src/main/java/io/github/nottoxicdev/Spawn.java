package io.github.nottoxicdev;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Upgrades upgrades;
    private Random r = new Random();

    private int scoreKeep = 0;
    int i = 0;

    public static int scoreSpeed = 2;
    public static float fixedWidth = Game.WIDTH - 64;
    public static float fixedHeight = Game.HEIGHT - 64;

    public static boolean start = true;
    public static boolean boss = false;

    public Spawn(Handler handler, HUD hud, Upgrades upgrades) {
        this.handler = handler;
        this.hud = hud;
        this.upgrades = upgrades;
    }

    public void tick() {
        i++;
        if (i > scoreSpeed) {
            scoreKeep++;
            i = 0;
        }

        if (start) {
            handler.addObject(
                    new BasicEnemy(r.nextFloat(fixedWidth), r.nextFloat(fixedHeight),
                            ID.BasicEnemy, GroupID.Enemy,
                            handler));

            start = false;
        }

        if (scoreKeep >= 100) {
            scoreKeep = 0;
            if (!boss) {
                hud.setLevel(hud.getLevel() + 1);
                if (hud.getLevel() == 2) {
                    handler.addObject(
                            new BasicEnemy(r.nextFloat(fixedWidth), r.nextFloat(fixedHeight),
                                    ID.BasicEnemy, GroupID.Enemy,
                                    handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(
                            new VerticalEnemy(r.nextFloat(fixedWidth), 0f, ID.VerticalEnemy,
                                    GroupID.Enemy,
                                    handler));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(
                            new SmartEnemy(r.nextFloat(fixedWidth), 0f, ID.SmartEnemy, GroupID.Enemy,
                                    handler));
                } else if (hud.getLevel() == 5) {
                    for (int i = 0; i < 3; i++) {
                        handler.addObject(
                                new VerticalEnemy(r.nextFloat(fixedWidth), 0f, ID.VerticalEnemy,
                                        GroupID.Enemy,
                                        handler));
                    }
                    for (int i = 0; i < 3; i++) {
                        handler.addObject(
                                new HorizontalEnemy(0f, r.nextFloat(fixedHeight), ID.HorizontalEnemy,
                                        GroupID.Enemy,
                                        handler));
                    }
                } else if (hud.getLevel() == 6) {
                    handler.clearEnemies();
                    for (int i = 0; i < 3; i++) {
                        handler.addObject(
                                new BasicEnemy(r.nextFloat(fixedWidth), 0f, ID.BasicEnemy, GroupID.Enemy,
                                        handler));
                    }
                } else if (hud.getLevel() == 10) {
                    handler.clearEnemies();
                    Spawn.boss = true;
                    handler.addObject(
                            new EnemyBoss_LV10((Game.WIDTH / 2f) - 32, -256f, ID.EnemyBoss,
                                    GroupID.Enemy,
                                    handler));
                } else if (hud.getLevel() == 11) {
                    upgrades.addUpgrade(new Heal(UpgradeID.Heal, 4));
                    float tempf = 100;
                    for (int i = 0; i < 9; i++) {
                        handler.addObject(new VerticalEnemy(tempf, 0, ID.VerticalEnemy, GroupID.Enemy, handler));
                        tempf += 100;
                    }
                } else if (hud.getLevel() == 12) {
                    float tempf = 100;
                    for (int i = 0; i < 4; i++) {
                        handler.addObject(new HorizontalEnemy(0f, tempf, ID.HorizontalEnemy, GroupID.Enemy, handler));
                        tempf += 200;
                    }
                    float tempf2 = 50;
                    for (int i = 0; i < 9; i++) {
                        handler.addObject(
                                new HorizontalEnemy(fixedWidth, tempf2, ID.HorizontalEnemy, GroupID.Enemy, handler));
                        tempf2 += 200;
                    }
                } else if (hud.getLevel() == 15) {
                    float tempf = 50;
                    for (int i = 0; i < 9; i++) {
                        handler.addObject(
                                new VerticalEnemy(tempf, fixedHeight, ID.VerticalEnemy, GroupID.Enemy, handler));
                        tempf += 100;
                    }
                } else if (hud.getLevel() == 20) {
                    handler.clearEnemies();
                    Spawn.boss = true;
                    handler.addObject(
                            new EnemyBoss_LV20((Game.WIDTH / 2f) - 32, -256f, ID.EnemyBoss,
                                    GroupID.Enemy,
                                    handler));
                } else if (hud.getLevel() == 21) {
                    end();
                } else if (hud.getLevel() == 21) {
                    upgrades.addUpgrade(new Regen(UpgradeID.Regen, 1));
                    handler.addObject(new SmartEnemy(0, 0, ID.SmartEnemy, GroupID.Enemy, handler));
                    handler.addObject(new SmartEnemy(0, fixedHeight, ID.SmartEnemy, GroupID.Enemy, handler));
                    handler.addObject(new SmartEnemy(fixedWidth, 0, ID.SmartEnemy, GroupID.Enemy, handler));
                    handler.addObject(new SmartEnemy(fixedWidth, fixedHeight, ID.SmartEnemy, GroupID.Enemy, handler));
                } else if (hud.getLevel() == 25) {
                    float tempf = 50;
                    for (int i = 0; i < 15; i++) {
                        handler.addObject(new HorizontalEnemy(0f, tempf, ID.HorizontalEnemy, GroupID.Enemy, handler));
                        tempf += 64;
                    }
                    tempf = 50;
                    for (int i = 0; i < 15; i++) {
                        handler.addObject(
                                new HorizontalEnemy(fixedWidth, tempf, ID.HorizontalEnemy, GroupID.Enemy, handler));
                        tempf += 64;
                    }
                }
            }

        }

    }

    public void end() {
        Game.hasWon = true;
    }
}
