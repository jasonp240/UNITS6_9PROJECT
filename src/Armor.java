public class Armor extends Loot {
    private int protection;
    public Armor(String name, int durability, int protection) {
        super(name, durability);
        this.protection = protection;
    }

    public Armor() {
        super("Starter Armor", 10);
        protection = 2;
    }

    @Override
    public void fix() {
        super.fix();
        System.out.println("Armor fixed!");
    }

    public int getProtection() {
        if (super.isBroken()) {
            return 0;
        }
        return protection;
    }
}
