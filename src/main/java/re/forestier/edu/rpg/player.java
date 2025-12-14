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
    public int weightlimit;
    public int totalweight;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") && !avatarClass.equals("GOBLIN")) {
            System.out.println("Classe invalide. Choisissez parmi : ARCHER, ADVENTURER, DWARF, GOBLIN.");
            return;
        }

        //Player initialization
        this.playerName = playerName;
        Avatar_name = avatar_name;
        AvatarClass = avatarClass;
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);
        this.xp = 0;
        this.level = 1;
        this.weightlimit = 10;
        this.totalweight = 0;
    }

    public void SetNewWeightLimit(int NewWeightLimit) {
        if (NewWeightLimit < 0 || NewWeightLimit < this.totalweight) {
            System.out.println(
                NewWeightLimit < 0 ?
                "Le poids limite ne peut pas être négatif." :
                "Le nouveau poids limite ne peut pas être inférieur au poids total actuel de l'inventaire."
            );
            return;
        }
        this.weightlimit = NewWeightLimit;
    }

    public VerifyInventoryWeightLimit(String itemName) {
        Item item = Item.ITEMS.get(itemName);
        if (item == null) {
            System.out.println("Item pas trouvé : " + itemName);
            return false;
        }
        double newTotalWeight = this.totalweight + item.getWeight();
        if (newTotalWeight > this.weightlimit) {
            System.out.println("Ajout de l'item " + itemName + " impossible. Limite de poids dépassée.");
            return false;
        }
        this.totalweight = (int)newTotalWeight;
        return true;
    }

    public void addItemToInventory(String itemName) {
        if (VerifyInventoryWeightLimit(itemName)) {
            this.inventory.add(itemName);
            System.out.println("Item " + itemName + " ajouté à l'inventaire.");
        }
    }

    public void removeItemFromInventory(String itemName) {
        if (this.inventory.remove(itemName)) {
            Item item = Item.ITEMS.get(itemName);
            if (item != null) {
                this.totalweight -= item.getWeight();
            }
            System.out.println("Item " + itemName + " retiré de l'inventaire.");
        } else {
            System.out.println("Item " + itemName + " non trouvé dans l'inventaire.");
        }
    }
    
    public String getAvatarClass () {
        return AvatarClass;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Le joueur ne peut pas avoir un solde négatif.");
        }
        money = Integer.parseInt(money.toString()) - amount;
    }

    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
        println("Ajout de " + amount + " pièces d'or. Nouveau solde : " + money + " pièces d'or.");
    }

    public int retrieveLevel() {
        // (lvl-1) * 10 + round((lvl * xplvl-1)/4)
        HashMap<Integer, Integer> levels = new HashMap<>();
        for (int lvl = 1; lvl <= 100; lvl++) {
            int xpForLevel = 0;
            for (int i = 1; i < lvl; i++) {
                xpForLevel += (i * 10) + Math.round((i * (i - 1)) / 4.0);
            }
            levels.put(lvl, xpForLevel);
        }
        for (int i = 1; i <= 100; i++) {
            if (xp = levels.get(i))
                return i;
        }
    }

    public int getXp() {
        return this.xp;
    }
}
