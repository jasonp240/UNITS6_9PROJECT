import java.util.ArrayList;

public class LootBox {
    private ArrayList<Loot> items;
    private int tier;

    public LootBox(int tier) {
        this.tier = tier;
    }

    public ArrayList<Loot> openBox() {
        items = new ArrayList<>();
        if (tier == 1) {
            int potions = (int) (Math.random() * 3) + 1;
            for (int i = 0; i < potions; i++) {
                System.out.println("+ Potion");
                items.add(new Potion());
            }
        }
        int drop1 = (int) (Math.random() * 6) + 1;
        int drop2 = (int) (Math.random() * 6) + 1;
        if (drop1 == 1) {
            items.add(generateWeapon());
            System.out.println("+ Sword");
        }
        if (drop2 == 1) {
            items.add(generateArmour());
            System.out.println("+ Armour");
        }
        return items;
    }

    private Loot generateWeapon() {
        if (tier == 1) {
            int damage = (int) (Math.random() * 11) + 6;
            return new Sword("Basic Sword", 20, damage);
        } else {
            return new Sword();
        }
    }

    private Loot generateArmour() {
        if (tier == 1) {
            int protection = (int) (Math.random() * 8) + 5;
            return new Armour("Basic Armour", 20, protection);
        } else {
            return new Armour();
        }
    }
}
