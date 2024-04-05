public class Mob {
    private String name;
    private int health;
    private int maxHealth;
    private int damage;
    private boolean isDead;

    public Mob(int mapNum) {
        int mobNameGen = (int) (Math.random() * 4);
        if (mapNum == 3) {
            name = "Goblin King";
        } else if (mobNameGen == 0) {
            name = "Orc";
        } else if (mobNameGen == 1) {
            name = "Goblin";
        } else if (mobNameGen == 2) {
            name = "Skeleton";
        } else {
            name = "Zombie";
        }
        if (mapNum != 3) {
            health = (int) (Math.random() * (6 + mapNum)) + 7 + mapNum;
            maxHealth = health;
            damage = (int) (Math.random() * (3 + mapNum)) + 5 + mapNum;
        }
        if (mapNum == 0) {
            health = 8;
            maxHealth = 8;
            damage = 4;
        }
        if (mapNum == 3) {
            health = 100;
            maxHealth = 100;
            damage = 13;
        }
        isDead = false;
    }


    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int damageTaken) {
        health-= damageTaken;
        if (health <= 0) {
            isDead = true;
            health = 0;
        }
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public boolean isDead() {
        return isDead;
    }
}
