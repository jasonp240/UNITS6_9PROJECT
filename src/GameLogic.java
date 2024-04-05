import java.util.Locale;
import java.util.Scanner;

public class GameLogic {
    public GameLogic() {
        System.out.println("Welcome to Dungeon Raid!");
        System.out.println("Your goal is to collect loot boxes \uD83D\uDCE6 to get better gear and defeat the boss!");
        System.out.println("Rules:");
        System.out.println("- You can not walk into trees \uD83C\uDF33");
        System.out.println("- Grass \uD83D\uDFE9 has a random chance of making you fight a monster");
        System.out.println("- WorkBenches \uD83D\uDEE0\uFE0F allow you to fix your items");
        System.out.println("- In order to interact with the game use w, a, s, d, 1, and 2");
        System.out.println("Have fun!");
        Player player = new Player();
        Scanner scan = new Scanner(System.in);
        Map i = new Map(player);
        i.setupMap();
        while (!player.isWin()) {
            player.printStatus();
            i.printMap();
            System.out.print(": ");
            i.move(scan.nextLine().toUpperCase(Locale.ROOT));
        }
        System.out.println("You have won!!!");
        System.out.println("                                ████          \n" +
                "                                ██  ██        \n" +
                "                              ▓▓    ██        \n" +
                "                              ▓▓    ██        \n" +
                "                            ██    ██          \n" +
                "                            ██    ██          \n" +
                "                          ██      ██          \n" +
                "                          ██    ██            \n" +
                "  ██████████            ██      ██            \n" +
                "██░░▒▒▒▒░░▒▒██    ██████        ██████████████\n" +
                "██░░░░░░░░▒▒██  ██                          ██\n" +
                "██▒▒▒▒▒▒░░▒▒██  ██                          ██\n" +
                "██▓▓▓▓▓▓▓▓▓▓██  ██                ██████████  \n" +
                "██▓▓▓▓▒▒▓▓▓▓██  ██                        ██  \n" +
                "██▓▓▓▓▒▒▒▒▒▒██  ▓▓                ░░░░    ██  \n" +
                "██▓▓▓▓▓▓▓▓▓▓██  ██                ████████    \n" +
                "██▓▓▓▓▓▓▓▓▓▓██  ██                      ██    \n" +
                "██▓▓▓▓▓▓▓▓▓▓██  ██                      ██    \n" +
                "██▓▓▓▓▓▓▓▓▓▓██  ██                ██████      \n" +
                "██▓▓▓▓▓▓  ▓▓██  ██                    ██      \n" +
                "██▓▓▓▓▓▓▓▓▓▓██    ████████████████████        \n" +
                "  ██████████                                  ");
    }
}
