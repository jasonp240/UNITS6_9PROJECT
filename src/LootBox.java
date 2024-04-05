import java.util.ArrayList;

public class LootBox {
    private ArrayList<Loot> items;
    private int mapNum;

    public LootBox(int mapNum) {
        this.mapNum = mapNum;
    }

    public ArrayList<Loot> openBox() {
        items = new ArrayList<>();
        int potions = (int) (Math.random() * 3 + mapNum) + 1;
        if (potions > 5) {
            potions = 5;
        }
        for (int i = 0; i < potions; i++) {
            System.out.println("+ Potion");
            items.add(new Potion());
        }
        // TEST MODE
//        int drop1 = (int) (Math.random() * 6) + 1;
//        int drop2 = (int) (Math.random() * 6) + 1;
//        if (drop1 == 1) {
            items.add(generateWeapon());
            System.out.println("+ Sword");
//        }
//        if (drop2 == 1) {
            items.add(generateArmor());
            System.out.println("+ Armor");
//        }
        return items;
    }

    private Loot generateWeapon() {
        int damage = (int) (Math.random() * (3 + mapNum)) + 3 + mapNum;
        return new Sword("Basic Sword", 10, damage);
    }

    private Loot generateArmor() {
        int protection = (int) (Math.random() * (3 + mapNum)) + 4 + mapNum;
        if (mapNum == 0) {
            protection = 5;
        }
        return new Armor("Basic Armor", 10, protection);
    }
}
