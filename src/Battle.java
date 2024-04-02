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

    private String displayMobHealth() {
        int health = mob.getHealth();
        int maxHealth = mob.getMaxHealth();
        double barValue = (double) maxHealth / 10;
        int barAmount = (int) Math.round(health / barValue);
        if (health < barValue) {
            return "[ + x x x x x x x x x ]";
        }
        String returnStr = "[ ";
        for (int i = 1; i <= barAmount; i++) {
            returnStr += "+ ";
        }
        for (int i = 1; i <= 10 - barAmount; i++) {
            returnStr += "x ";
        }
        return returnStr + "]";
    }

    private String displayPlayerHealth() {
        int health = player.getHealth();
        int maxHealth = player.getMaxHealth();
        double barValue = (double) maxHealth / 10;
        int barAmount = (int) Math.round(health / barValue);
        if (health < barValue) {
            return "[ + x x x x x x x x x ]";
        }
        String returnStr = "[ ";
        for (int i = 1; i <= barAmount; i++) {
            returnStr += "+ ";
        }
        for (int i = 1; i <= 10 - barAmount; i++) {
            returnStr += "x ";
        }
        return returnStr + "]";
    }
}
