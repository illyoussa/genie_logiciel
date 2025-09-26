package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;
    private Float __real_money__;


    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }

        this.playerName = playerName;
        Avatar_name = avatar_name;
        AvatarClass = avatarClass;
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);
    }

    public String getAvatarClass () {
        return AvatarClass;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money = Integer.parseInt(money.toString()) - amount;
    }
    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
    }
    public int retrieveLevel() {
        // Calcul dynamique des paliers de niveaux
        int maxLevel = 5; // Peut être augmenté si besoin
        int[] thresholds = new int[maxLevel + 1];
        thresholds[1] = 0;
        for (int lvl = 2; lvl <= maxLevel; lvl++) {
            int prevXp = thresholds[lvl - 1];
            thresholds[lvl] = (lvl - 1) * 10 + Math.round((lvl * prevXp) / 4.0f);
        }

        for (int lvl = maxLevel; lvl >= 2; lvl--) {
            if (xp >= thresholds[lvl]) {
                return lvl;
            }
        }
        return 1;
    }

    public int getXp() {
        return this.xp;
    }

    /*
    Ингредиенты:
        Для теста:

            250 г муки
            125 г сливочного масла (холодное)
            70 г сахара
            1 яйцо
            1 щепотка соли
     */

}