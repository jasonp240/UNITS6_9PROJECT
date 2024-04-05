import java.util.Scanner;

public class Battle {
    private Mob mob;
    private Player player;
    private Scanner scan;

    public Battle(Mob mob, Player player) {
        scan = new Scanner(System.in);
        this.mob = mob;
        this.player = player;
    }

    public boolean fight() {
        if (player.getArmor().getProtection() >= mob.getDamage()) {
            System.out.println("-----------------");
            System.out.println(mob.getName() + "'s Health");
            System.out.println(mob.getHealth() + "/" + mob.getMaxHealth());
            System.out.println(displayMobHealth());
            System.out.println("-----------------");
            System.out.println("Player's Health:");
            System.out.println(player.getHealth() + "/" + player.getMaxHealth());
            System.out.println(displayPlayerHealth());
            System.out.println("Amount of potions: " + player.getNumPotions());
            System.out.println("-----------------");
            System.out.println("The mob realizes that you are too strong and runs away!");
            return true;
        }
        if (player.getSword().isBroken()) {
            System.out.println("Sword is broken! You have been killed!");
            System.out.println("You have been reset! Repair your weapon!");
            player.death();
            return false;
        }
        while (player.getHealth() > 0 && mob.getHealth() > 0) {
            System.out.println("-----------------");
            System.out.println(mob.getName() + "'s Health");
            System.out.println(mob.getHealth() + "/" + mob.getMaxHealth());
            System.out.println(displayMobHealth());
            System.out.println("-----------------");
            System.out.println("Player's Health:");
            System.out.println(player.getHealth() + "/" + player.getMaxHealth());
            System.out.println(displayPlayerHealth());
            System.out.println("Amount of potions: " + player.getNumPotions());
            System.out.println("-----------------");
            System.out.print("Attack(1) or Drink Potion(2): ");
            String userInput = scan.nextLine();
            while (!userInput.equals("1") && !userInput.equals("2")) {
                System.out.println("Invalid Input");
                System.out.print("Attack(1) or Drink Potion(2): ");
                userInput = scan.nextLine();
            }
            if (userInput.equals("1")) {
                System.out.println("The player attacks the " + mob.getName() + " for " + player.getSword().getDamage() + " damage!");
                mob.takeDamage(player.getSword().getDamage());
                player.getSword().use();
            } else if (player.getNumPotions() > 0 && player.getHealth() != player.getMaxHealth()) {
                System.out.println("You drink a potion! and gain " + (int) Math.round(player.getMaxHealth() * .4) + " HP!");
                player.drinkPotion();
            } else if (player.getHealth() == player.getMaxHealth()) {
                System.out.println("You are already at max health!");
                System.out.println("The player attacks instead! for " + player.getSword().getDamage() + " damage!");
                mob.takeDamage(player.getSword().getDamage());
                player.getSword().use();
            } else {
                System.out.println("You don't have any more potions!");
                System.out.println("The player attacks instead! for " + player.getSword().getDamage() + " damage!");
                mob.takeDamage(player.getSword().getDamage());
                player.getSword().use();
            }
            if (!mob.isDead()) {
                System.out.println("The " + mob.getName() + " attacks the player for " + (mob.getDamage() - player.getArmor().getProtection()) + " damage!");
                player.takeDamage(mob.getDamage() - player.getArmor().getProtection());
                player.getArmor().use();
            }
            if (player.isDead()) {
                System.out.println("The player has been killed!");
                System.out.println("You have been reset!");
                player.death();
                return false;
            }
        }
        if (mob.getName().equals("Goblin King")) {
            player.winSet();
        }
        return true;
    }

    private String displayMobHealth() {
        int health = mob.getHealth();
        int maxHealth = mob.getMaxHealth();
        double barValue = (double) maxHealth / 10;
        int barAmount = (int) Math.round(health / barValue);
        if (health < barValue && health != 0) {
            return "[ ❤\uFE0F \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 ]";
        }
        String returnStr = "[ ";
        for (int i = 1; i <= barAmount; i++) {
            returnStr += "❤\uFE0F ";
        }
        for (int i = 1; i <= 10 - barAmount; i++) {
            returnStr += "\uD83D\uDDA4 ";
        }
        return returnStr + "]";
    }

    private String displayPlayerHealth() {
        int health = player.getHealth();
        int maxHealth = player.getMaxHealth();
        double barValue = (double) maxHealth / 10;
        int barAmount = (int) Math.round(health / barValue);
        if (health < barValue && health != 0) {
            return "[ ❤\uFE0F \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 \uD83D\uDDA4 ]";
        }
        String returnStr = "[ ";
        for (int i = 1; i <= barAmount; i++) {
            returnStr += "❤\uFE0F ";
        }
        for (int i = 1; i <= 10 - barAmount; i++) {
            returnStr += "\uD83D\uDDA4 ";
        }
        return returnStr + "]";
    }
}
