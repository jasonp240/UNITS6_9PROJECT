import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Scanner scan = new Scanner(System.in);
        Map i = new Map(player);
        i.setupMap();
        int x = 0;
        while (x == 0) {
            player.printStatus();
            i.printMap();
            System.out.print(": ");
            i.move(scan.nextLine().toUpperCase(Locale.ROOT));
        }
    }
}
