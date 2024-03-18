public class Loot {
    private String name;
    private int maxDurability;
    private int curDurability;
    private boolean broken = false;

    public Loot(String name, int durability) {
        this.name = name;
        maxDurability = durability;
        curDurability = durability;
    }

    public String getName() {
        return name;
    }

    public void use() {
        if (curDurability != 0) {
            curDurability--;
        }
        if (curDurability == 0) {
            broken = true;
        }
    }

    public void fix() {
        broken = false;
        curDurability = maxDurability;
    }

    public boolean isBroken() {
        return broken;
    }
}
