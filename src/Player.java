import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private Sword sword;
    private Armour armour;
    private int numPotions;
    private final Scanner scan;
    private int health;
    private int maxHealth;
    private boolean isDead;
    private boolean win;

    public Player() {
        scan = new Scanner(System.in);
        numPotions = 5;
        sword = new Sword();
        armour = new Armour();
        health = 10;
        maxHealth = 10;
        win = false;
    }

    public void addLoot(ArrayList<Loot> loot) {
        addPotions(loot);
        if (checkSword(loot) != null) {
            Sword newSword = (Sword) checkSword(loot);
            System.out.println("There is a sword in the loot!");
            System.out.println("-----------------");
            System.out.println("Current Sword:");
            System.out.println("Name: " + sword.getName());
            System.out.println("Durability: " + sword.getCurDurability() + "/" + sword.getMaxDurability());
            System.out.println("Damage: " + sword.getDamage());
            System.out.println("-----------------");
            System.out.println("New Sword: ");
            System.out.println("Name: " + newSword.getName());
            System.out.println("Durability: " + newSword.getCurDurability() + "/" + newSword.getMaxDurability());
            System.out.println("Damage: " + newSword.getDamage());
            System.out.println("-----------------");
            System.out.print("Would you like to replace the sword (y/n): ");
            String userInput = scan.nextLine();
            while (!userInput.equals("y") && !userInput.equals("n")) {
                System.out.println("Invalid input!");
                System.out.print("Would you like to replace the sword (y/n): ");
                userInput = scan.nextLine();
            }
            if (userInput.equals("y")) {
                sword = newSword;
                System.out.println("Sword Updated!");
            }
        }
        if (checkArmour(loot) != null) {
            Armour newArmour = (Armour) checkArmour(loot);
            System.out.println("There is Armour in the loot!");
            System.out.println("-----------------");
            System.out.println("Current Armour:");
            System.out.println("Name: " + armour.getName());
            System.out.println("Durability: " + armour.getCurDurability() + "/" + armour.getMaxDurability());
            System.out.println("Protection: " + armour.getProtection());
            System.out.println("-----------------");
            System.out.println("New Armour: ");
            System.out.println("Name: " + newArmour.getName());
            System.out.println("Durability: " + newArmour.getCurDurability() + "/" + newArmour.getMaxDurability());
            System.out.println("Protection: " + newArmour.getProtection());
            System.out.println("-----------------");
            System.out.print("Would you like to replace the armour (y/n): ");
            String userInput = scan.nextLine();
            while (!userInput.equals("y") && !userInput.equals("n")) {
                System.out.println("Invalid input!");
                System.out.print("Would you like to replace the armour (y/n): ");
                userInput = scan.nextLine();
            }
            if (userInput.equals("y")) {
                armour = newArmour;
                System.out.println("Armour Updated!");
            }
        }
    }

    public void printStatus() {
        System.out.println("-----------------");
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("-----------------");
        System.out.println("Potions: " + numPotions);
        System.out.println("-----------------");
        System.out.println("Sword:");
        System.out.println("Name: " + sword.getName());
        System.out.println("Durability: " + sword.getCurDurability() + "/" + sword.getMaxDurability());
        System.out.println("Damage: " + sword.getDamage());
        System.out.println("-----------------");
        System.out.println("Armour:");
        System.out.println("Name: " + armour.getName());
        System.out.println("Durability: " + armour.getCurDurability() + "/" + armour.getMaxDurability());
        System.out.println("Protection: " + armour.getProtection());
        System.out.println("-----------------");
    }

    public void increaseMaxHealth() {
        maxHealth++;
        System.out.println("You have won the battle your Max HP increases by 1!");
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            isDead = true;
        }
    }

    public Sword getSword() {
        return sword;
    }

    public Armour getArmour() {
        return armour;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getNumPotions() {
        return numPotions;
    }

    public void drinkPotion() {
        numPotions--;
        health += (int) Math.round(maxHealth * .4);
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void reset() {
        health = maxHealth;
        isDead = false;
    }

    public void setPotion5() {
        if (numPotions < 5) {
            numPotions = 5;
        }
    }

    public boolean isWin() {
        return win;
    }

    public void winSet() {
        win = true;
    }

    public void death() {
        if (maxHealth >= 12) {
            maxHealth -= 2;
            System.out.println("Max Health -2");
        } else {
            if (maxHealth == 11) {
                System.out.println("Max Health -1");
                maxHealth--;
            }
        }
    }

    private void addPotions(ArrayList<Loot> loot) {
        for (Loot item : loot) {
            if (item instanceof Potion) {
                numPotions++;
            }
        }
    }

    private Loot checkSword(ArrayList<Loot> loot) {
        for (Loot item : loot) {
            if (item instanceof Sword) {
                return item;
            }
        }
        return null;
    }

    private Loot checkArmour(ArrayList<Loot> loot) {
        for (Loot item : loot) {
            if (item instanceof Armour) {
                return item;
            }
        }
        return null;
    }

}
