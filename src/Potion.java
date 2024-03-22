public class Potion extends Loot {
    public Potion() {
        super("Health Potion", 1);
    }

    @Override
    public void fix() {
        System.out.println("Can not be fixed!");
    }
}
