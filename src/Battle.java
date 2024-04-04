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
        while (player.getHealth() > 0 && mob.getHealth() > 0) {
            System.out.println("-----------------");
            System.out.println(mob.getName() + "'s Health");
            System.out.println(displayMobHealth());
            System.out.println("-----------------");
            System.out.println("Player's Health:");
            System.out.println(displayMobHealth());
            System.out.println("Amount of potions: " + player.getNumPotions());
            System.out.println("-----------------");
            System.out.println("Attack(1) or Drink Potion(2): ");
            String userInput = scan.nextLine();
            while (!userInput.equals("1") && !userInput.equals("2")) {
                System.out.println("Invalid Input");
                System.out.println("Attack(1) or Drink Potion(2): ");
                userInput = scan.nextLine();
            }
            
        }
        return true;
    }

    private String displayMobHealth() {
        int health = mob.getHealth();
        int maxHealth = mob.getMaxHealth();
        double barValue = (double) maxHealth / 10;
        int barAmount = (int) Math.round(health / barValue);
        if (health < barValue) {
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
        if (health < barValue) {
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
