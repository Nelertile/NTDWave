package io.github.nottoxicdev;

public class Heal extends Upgrade {

    public static boolean healing = false;
    private int timerval = 5000;

    private int timer = 1000000;

    public Heal(UpgradeID ID, int level) {
        super(ID, level);

        if (level == 1) {
            timerval = 5000;
        } else if (level == 2) {
            timerval = 4000;
        } else if (level == 3) {
            timerval = 3000;
        } else if (level == 4) {
            timerval = 2000;
        } else if (level == 5) {
            timerval = 1000;
        } else if (level == 6) {
            timerval = 750;
        } else if (level >= 7) {
            timerval = 500;
        }

        timer = timerval;

    }

    public void tick() {

        if (timer <= 0) {
            HUD.showHeal = true;
            if (healing) {
                heal();
                HUD.showHeal = false;
                healing = false;
                timer = timerval;

                System.out.println(timer);
            }
        } else {
            timer--;
        }

    }

    public void heal() {
        HUD.HEALTH += 10 * level;
    }

}
