public class Map {
    private Space playerSpace;
    private Space grassSpace;
    private Space walkSpace;
    private Space lootSpace;
    private Space treeSpace;
    private Space fixSpace;
    private Space[][] curMap;
    private int curMapNum;
    private int playerX;
    private int playerY;
    private Space prev;
    private Player player;
    private boolean allow;
    private Space boss1Space;

    public Map(Player player) {
        playerSpace = new Space("\uD83E\uDDD1");
        grassSpace = new Space("\uD83D\uDFE9");
        walkSpace = new Space("\uD83D\uDFEB");
        lootSpace = new Space("\uD83D\uDCE6");
        treeSpace = new Space("\uD83C\uDF33");
        fixSpace = new Space("\uD83D\uDEE0\uFE0F");
        boss1Space = new Space("\uD83D\uDC79");
        curMapNum = 0;
        this.player = player;
        prev = walkSpace;
    }

    public void setupMap() {
        playerX = 4;
        playerY = 9;
          curMap = new Space[][]{
                  {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, treeSpace, treeSpace},
                  {lootSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, lootSpace},
                  {grassSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                  {grassSpace, grassSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                  {treeSpace, grassSpace, grassSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                  {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                  {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                  {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                  {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, grassSpace, grassSpace, treeSpace},
                  {treeSpace, treeSpace, treeSpace, walkSpace, playerSpace, fixSpace, treeSpace, treeSpace, treeSpace}
        };
    }

    public void printMap() {
        for (Space[] row : curMap) {
            for (Space element : row) {
                System.out.print(element.getSymbol());
            }
            System.out.println();
        }
    }

    public void nextMap() {
        curMapNum++;
        curMap = getMap(curMapNum);
        if (curMapNum == 3) {
            prev = walkSpace;
        } else {
            prev = grassSpace;
        }
    }

    public void reset() {
        playerX = 4;
        playerY = 9;
        curMap = getMap(curMapNum);
        player.reset();
        player.setPotion5();
        if (curMapNum == 0 || curMapNum == 3 || curMapNum == 2) {
            prev = walkSpace;
        } else {
            prev = grassSpace;
        }
    }

    public void reset(int num) {
        playerX = 4;
        playerY = 9;
        player.reset();
        player.increaseMaxHealth();
        player.increaseMaxHealth();
        player.setPotion5();
        curMap = getMap(curMapNum);
        if (curMapNum == 0 || curMapNum == 3 || curMapNum == 2) {
            prev = walkSpace;
        } else {
            prev = grassSpace;
        }
    }

    public void move(String input) {
        allow = true;
        if (input.equals("W")){
            if (playerY == 0) {
                playerX = 4;
                playerY = 9;
                nextMap();
            } else {
                if (curMap[playerY - 1][playerX] != treeSpace) {
                    curMap[playerY][playerX] = prev;
                    if (curMap[playerY - 1][playerX] == lootSpace) {
                        prev = walkSpace;
                        LootBox lootBox = new LootBox(curMapNum);
                        player.addLoot(lootBox.openBox());
                    } else {
                        prev = curMap[playerY - 1][playerX];
                    }
                    if (curMap[playerY - 1][playerX] == grassSpace) {
                        int randomAttack = (int) (Math.random() * 4);
                        if (randomAttack == 3) {
                            System.out.println("You have run into a mob!");
                            Battle battle = new Battle(new Mob(curMapNum), player);
                            if (battle.fight()) {
                                player.increaseMaxHealth();
                            } else {
                                reset();
                                allow = false;
                            }
                        }
                    }
                    if (curMap[playerY - 1][playerX] == boss1Space) {
                        Battle battle = new Battle(new Mob(curMapNum), player);
                        if (!battle.fight()) {
                            reset(1);
                            allow = false;
                        }
                    }
                    if (allow) {
                        curMap[playerY - 1][playerX] = playerSpace;
                        playerY--;
                    }
                } else {
                    System.out.println("Theres a tree in the way!");
                }
            }
        } else if (input.equals("S") && playerY < 9) {
            if (curMap[playerY + 1][playerX] == fixSpace) {
                System.out.println("Your armour and sword have been fixed!");
                player.getSword().fix();
                player.getArmour().fix();
                allow = false;
            } else {
                if (curMap[playerY + 1][playerX] != treeSpace) {
                    curMap[playerY][playerX] = prev;
                    if (curMap[playerY + 1][playerX] == lootSpace) {
                        prev = walkSpace;
                        LootBox lootBox = new LootBox(curMapNum);
                        player.addLoot(lootBox.openBox());
                    } else {
                        prev = curMap[playerY + 1][playerX];
                    }
                    if (curMap[playerY + 1][playerX] == grassSpace) {
                        int randomAttack = (int) (Math.random() * 4);
                        if (randomAttack == 3) {
                            System.out.println("You have run into a mob!");
                            Battle battle = new Battle(new Mob(curMapNum), player);
                            if (battle.fight()) {
                                player.increaseMaxHealth();
                            } else {
                                reset();
                                allow = false;
                            }
                        }
                    }
                    if (allow) {
                        curMap[playerY + 1][playerX] = playerSpace;
                        playerY++;
                    }
                } else {
                    System.out.println("Theres a tree in the way!");
                }
            }
        } else if (input.equals("D") && playerX < 8) {
            if (curMap[playerY][playerX + 1] == fixSpace) {
                System.out.println("Your armour and sword have been fixed!");
                player.getSword().fix();
                player.getArmour().fix();
                allow = false;
            } else {
                if (curMap[playerY][playerX + 1] != treeSpace) {
                    curMap[playerY][playerX] = prev;
                    if (curMap[playerY][playerX + 1] == lootSpace) {
                        prev = walkSpace;
                        LootBox lootBox = new LootBox(curMapNum);
                        player.addLoot(lootBox.openBox());
                    } else {
                        prev = curMap[playerY][playerX + 1];
                    }
                    if (curMap[playerY][playerX + 1] == grassSpace) {
                        int randomAttack = (int) (Math.random() * 4);
                        if (randomAttack == 3) {
                            System.out.println("You have run into a mob!");
                            Battle battle = new Battle(new Mob(curMapNum), player);
                            if (battle.fight()) {
                                player.increaseMaxHealth();
                            } else {
                                reset();
                                allow = false;
                            }
                        }
                    }
                    if (allow) {
                        curMap[playerY][playerX + 1] = playerSpace;
                        playerX++;
                    }
                } else {
                    System.out.println("Theres a tree in the way!");
                }
            }
        } else if (input.equals("A") && playerX > 0) {
            if (curMap[playerY][playerX - 1] == fixSpace) {
                System.out.println("Your armour and sword have been fixed!");
                player.getSword().fix();
                player.getArmour().fix();
                allow = false;
            } else {
                if (curMap[playerY][playerX - 1] != treeSpace) {
                    curMap[playerY][playerX] = prev;
                    if (curMap[playerY][playerX - 1] == lootSpace) {
                        prev = walkSpace;
                        LootBox lootBox = new LootBox(curMapNum);
                        player.addLoot(lootBox.openBox());
                    } else {
                        prev = curMap[playerY][playerX - 1];
                    }
                    if (curMap[playerY][playerX - 1] == grassSpace) {
                        int randomAttack = (int) (Math.random() * 4);
                        if (randomAttack == 3) {
                            System.out.println("You have run into a mob!");
                            Battle battle = new Battle(new Mob(curMapNum), player);
                            if (battle.fight()) {
                                player.increaseMaxHealth();
                            } else {
                                reset();
                                allow = false;
                            }
                        }
                    }
                    if (allow) {
                        curMap[playerY][playerX - 1] = playerSpace;
                        playerX--;
                    }
                } else {
                    System.out.println("Theres a tree in the way!");
                }
            }
        } else {
            System.out.println("You will go out of bound!");
        }
    }

    public Space[][] getMap(int num) {
        if (num == 2) {
            return new Space[][]{
                {treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, walkSpace},
                {treeSpace, grassSpace, walkSpace, grassSpace, treeSpace, grassSpace, walkSpace, grassSpace, walkSpace},
                {treeSpace, walkSpace, treeSpace, walkSpace, treeSpace, walkSpace, treeSpace, treeSpace, walkSpace},
                {treeSpace, grassSpace, treeSpace, grassSpace, treeSpace, grassSpace, treeSpace, treeSpace, walkSpace},
                {treeSpace, walkSpace, treeSpace, walkSpace, treeSpace, walkSpace, treeSpace, treeSpace, walkSpace},
                {treeSpace, grassSpace, treeSpace, grassSpace, treeSpace, grassSpace, treeSpace, treeSpace, walkSpace},
                {treeSpace, walkSpace, treeSpace, walkSpace, treeSpace, walkSpace, treeSpace, treeSpace, walkSpace},
                {treeSpace, grassSpace, treeSpace, grassSpace, walkSpace, grassSpace, treeSpace, treeSpace, walkSpace},
                {treeSpace, walkSpace, treeSpace, treeSpace, treeSpace, walkSpace, treeSpace, lootSpace, walkSpace},
                {treeSpace, grassSpace, walkSpace, grassSpace, playerSpace, fixSpace, treeSpace, treeSpace, lootSpace}
            };
        } else if (num == 1) {
            return new Space[][]{
                {treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, grassSpace, grassSpace, grassSpace, grassSpace},
                {treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, grassSpace},
                {treeSpace, treeSpace, treeSpace, treeSpace, lootSpace, treeSpace, treeSpace, treeSpace, grassSpace},
                {treeSpace, grassSpace, grassSpace, grassSpace, grassSpace, grassSpace, grassSpace, treeSpace, grassSpace},
                {treeSpace, grassSpace, treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                {treeSpace, grassSpace, grassSpace, grassSpace, lootSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                {treeSpace, grassSpace, treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                {treeSpace, grassSpace, grassSpace, grassSpace, grassSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                {treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                {treeSpace, treeSpace, treeSpace, treeSpace, playerSpace, fixSpace, grassSpace, grassSpace, grassSpace}
            };
        } else if (num == 3) {
            return new Space[][]{
                {treeSpace, treeSpace, treeSpace, treeSpace, walkSpace, treeSpace, treeSpace, treeSpace, treeSpace},
                {treeSpace, treeSpace, treeSpace, treeSpace, boss1Space, treeSpace, treeSpace, treeSpace, treeSpace},
                {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, treeSpace, treeSpace},
                {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, treeSpace, treeSpace},
                {treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, treeSpace, treeSpace},
                {treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, treeSpace, treeSpace},
                {treeSpace, lootSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, lootSpace, treeSpace},
                {treeSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, treeSpace},
                {walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace, walkSpace},
                {walkSpace, walkSpace, walkSpace, walkSpace, playerSpace, walkSpace, walkSpace, walkSpace, fixSpace}
            };
        } else {
            return new Space[][]{
                    {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, treeSpace, treeSpace},
                    {lootSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, lootSpace},
                    {grassSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                    {grassSpace, grassSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                    {treeSpace, grassSpace, grassSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                    {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                    {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                    {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, treeSpace, grassSpace, treeSpace},
                    {treeSpace, treeSpace, treeSpace, walkSpace, walkSpace, walkSpace, grassSpace, grassSpace, treeSpace},
                    {treeSpace, treeSpace, treeSpace, walkSpace, playerSpace, fixSpace, treeSpace, treeSpace, treeSpace}
            };
        }
    }
}
