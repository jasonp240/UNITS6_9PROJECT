public class Armour extends Loot {
    private int upgrade;
    private int protection;
    public Armour(String name, int durability, int protection) {
        super(name, durability);
        upgrade = 0;
        this.protection = protection;
    }

    public Armour() {
        super("Starter Armour", 10);
        upgrade = 0;
        protection = 3;
    }

    public boolean upgrade() {
        if (upgrade < 10) {
            return false;
        } else {
            upgrade++;
            return true;
        }
    }

    public int getProtection() {
        return protection;
    }
}
