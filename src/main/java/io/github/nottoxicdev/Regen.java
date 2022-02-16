package io.github.nottoxicdev;

public class Regen extends Upgrade {

    public Regen(UpgradeID ID, int level) {
        super(ID, level);

    }

    public void tick() {
        if (HUD.HEALTH > 10) {
            if (level == 1) {
                HUD.HEALTH += 0.01f;
            } else if (level == 2) {
                HUD.HEALTH += 0.1f;
            } else if (level == 3) {
                HUD.HEALTH += 1f;
            }
        }

    }

}
