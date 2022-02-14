package io.github.nottoxicdev;

public abstract class Upgrade {

    protected UpgradeID id;
    protected int level;

    public Upgrade(UpgradeID ID, int level) {
        this.id = ID;
        this.level = level;
    }

    public abstract void tick();

    public void setID(UpgradeID id) {
        this.id = id;
    }

    public UpgradeID getID() {
        return id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}
