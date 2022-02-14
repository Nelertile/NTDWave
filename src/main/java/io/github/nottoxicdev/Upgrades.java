package io.github.nottoxicdev;

import java.util.LinkedList;

public class Upgrades {

    LinkedList<Upgrade> object = new LinkedList<Upgrade>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            Upgrade tempUpgrade = object.get(i);

            tempUpgrade.tick();
        }
    }

    public void addUpgrade(Upgrade upgrade) {
        this.object.add(upgrade);
    }

    public void remUpgrade(Upgrade upgrade) {
        this.object.remove(upgrade);
    }
}
