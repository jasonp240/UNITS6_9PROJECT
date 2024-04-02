public class Mob {
    private String name;
    private int health;
    private int maxHealth;
    private int damage;

    public Mob(int mapNum) {
        int mobNameGen = (int) (Math.random() * 4);
        if (mobNameGen == 0) {
            name = "Orc";
        } else if (mobNameGen == 1) {
            name = "Goblin";
        } else if (mobNameGen == 2) {
            name = "Skeleton";
        } else {
            name = "Zombie";
        }
        health = (int) (Math.random() * (5 + mapNum)) + 5;
        maxHealth = health;
        damage = (int) (Math.random() * (2 + mapNum) + 2);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int damageTaken) {
        health-= damageTaken;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
