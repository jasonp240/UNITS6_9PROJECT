public class Sword extends Loot {
    private int upgrade;
    public Sword(String name, int durability) {
        super(name, durability);
        upgrade = 0;
    }

    public boolean upgrade() {
        if (upgrade < 10) {
            return false;
        } else {
            upgrade++;
            return true;
        }
    }
}
