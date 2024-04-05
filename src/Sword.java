public class Sword extends Loot {
    private int damage;
    public Sword(String name, int durability, int damage) {
        super(name, durability);
        this.damage = damage;
    }

    public Sword() {
        super("Starter Sword", 10);
        damage = 2;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void fix() {
        super.fix();
        System.out.println("Sword fixed!");
    }

}
