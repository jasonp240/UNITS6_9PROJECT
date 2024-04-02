public class Sword extends Loot {
    private int upgrade;
    private int damage;
    public Sword(String name, int durability, int damage) {
        super(name, durability);
        upgrade = 0;
        this.damage = damage;
    }

    public Sword() {
        super("Starter Sword", 10);
        upgrade = 0;
        damage = 5;
    }

    public boolean upgrade() {
        if (upgrade < 10) {
            return false;
        } else {
            upgrade++;
            return true;
        }
    }

    public int getDamage() {
        return damage;
    }

}
