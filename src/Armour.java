public class Armour extends Loot {
    private int protection;
    public Armour(String name, int durability, int protection) {
        super(name, durability);
        this.protection = protection;
    }

    public Armour() {
        super("Starter Armour", 10);
        protection = 2;
    }

    @Override
    public void fix() {
        super.fix();
    }

    public int getProtection() {
        if (super.isBroken()) {
            return 0;
        }
        return protection;
    }
}
