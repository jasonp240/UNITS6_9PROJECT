public class Map {
    private Space playerSpace;
    private Space grassSpace;
    private Space walkSpace;
    private Space lootSpace;
    private Space treeSpace;
    private Space[][] curMap;
    private int curMapNum;
    private int playerX;
    private int playerY;
    private Space prev;

    private Player player;

    public Map(Player player) {
        playerSpace = new Space("\uD83E\uDDD1");
        grassSpace = new Space("\uD83D\uDFE9");
        walkSpace = new Space("\uD83D\uDFEB");
        lootSpace = new Space("\uD83D\uDCE6");
        treeSpace = new Space("\uD83C\uDF33");
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
                  {treeSpace, treeSpace, treeSpace, walkSpace, playerSpace, walkSpace, treeSpace, treeSpace, treeSpace}
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
        if (curMapNum == 1) {
            curMap = new Space[][]{
                    {treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, grassSpace, grassSpace, grassSpace, grassSpace},
                    {treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, treeSpace, grassSpace},
                    {treeSpace, treeSpace, treeSpace, treeSpace, lootSpace, treeSpace, treeSpace, treeSpace, grassSpace},
                    {treeSpace, grassSpace, grassSpace, grassSpace, grassSpace, grassSpace, grassSpace, treeSpace, grassSpace},
                    {treeSpace, grassSpace, treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                    {treeSpace, grassSpace, grassSpace, grassSpace, lootSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                    {treeSpace, grassSpace, treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                    {treeSpace, grassSpace, grassSpace, grassSpace, grassSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                    {treeSpace, treeSpace, treeSpace, treeSpace, grassSpace, treeSpace, grassSpace, treeSpace, grassSpace},
                    {treeSpace, treeSpace, treeSpace, treeSpace, playerSpace, treeSpace, grassSpace, grassSpace, grassSpace}
            };
            prev = grassSpace;
        }
    }

    public void move(String input) {

        if (input.equals("W")){
            if (playerY == 0) {
                playerX = 4;
                playerY = 9;
                nextMap();
            } else {
                if (curMap[playerY - 1][playerX] != treeSpace) {
                    curMap[playerY][playerX] = prev;
                    if (curMap[playerY - 1][playerX] == lootSpace) {
                        prev = grassSpace;
                        LootBox lootBox = new LootBox(1);
                        player.addLoot(lootBox.openBox());
                    } else {
                        prev = curMap[playerY - 1][playerX];
                    }
                    if (curMap[playerY - 1][playerX] == grassSpace) {
                        int randomAttack = (int) (Math.random() * 4);
                        if (randomAttack == 3) {
                            System.out.println("You have run into a mob!");
                            Battle battle = new Battle(new Mob(curMapNum), player);
                            battle.fight();
                        }
                    }
                    curMap[playerY - 1][playerX] = playerSpace;
                    playerY--;
                } else {
                    System.out.println("Theres a tree in the way!");
                }
            }
        } else if (input.equals("S") && playerY < 9) {
            if (curMap[playerY + 1][playerX] != treeSpace) {
                curMap[playerY][playerX] = prev;
                if (curMap[playerY + 1][playerX] == lootSpace) {
                    prev = grassSpace;
                    LootBox lootBox = new LootBox(1);
                    player.addLoot(lootBox.openBox());
                } else {
                    prev = curMap[playerY + 1][playerX];
                }
                if (curMap[playerY + 1][playerX] == grassSpace) {
                    int randomAttack = (int) (Math.random() * 4);
                    if (randomAttack == 3) {
                        System.out.println("You have run into a mob!");
                        Battle battle = new Battle(new Mob(curMapNum), player);
                        battle.fight();
                    }
                }
                curMap[playerY + 1][playerX] = playerSpace;
                playerY++;
            } else {
                System.out.println("Theres a tree in the way!");
            }
        } else if (input.equals("D") && playerX < 8) {
            if (curMap[playerY][playerX + 1] != treeSpace) {
                curMap[playerY][playerX] = prev;
                if (curMap[playerY][playerX + 1] == lootSpace) {
                    prev = grassSpace;
                    LootBox lootBox = new LootBox(1);
                    player.addLoot(lootBox.openBox());
                } else {
                    prev = curMap[playerY][playerX + 1];
                }
                if (curMap[playerY][playerX + 1] == grassSpace) {
                    int randomAttack = (int) (Math.random() * 4);
                    if (randomAttack == 3) {
                        System.out.println("You have run into a mob!");
                        Battle battle = new Battle(new Mob(curMapNum), player);
                        battle.fight();
                    }
                }
                curMap[playerY][playerX + 1] = playerSpace;
                playerX++;
            } else {
            System.out.println("Theres a tree in the way!");
        }

        } else if (input.equals("A") && playerX > 0) {
            if (curMap[playerY][playerX - 1] != treeSpace) {
                curMap[playerY][playerX] = prev;
                if (curMap[playerY][playerX - 1] == lootSpace) {
                    prev = grassSpace;
                    LootBox lootBox = new LootBox(1);
                    player.addLoot(lootBox.openBox());
                } else {
                    prev = curMap[playerY][playerX - 1];
                }
                if (curMap[playerY][playerX - 1] == grassSpace) {
                    int randomAttack = (int) (Math.random() * 4);
                    if (randomAttack == 3) {
                        System.out.println("You have run into a mob!");
                        Battle battle = new Battle(new Mob(curMapNum), player);
                        battle.fight();
                    }
                }
                curMap[playerY][playerX - 1] = playerSpace;
                playerX--;
            } else {
                System.out.println("Theres a tree in the way!");
            }
        } else {
            System.out.println("You will go out of bound!");
        }
    }

    public int getCurMapNum() {
        return curMapNum;
    }
}
