package re.forestier.edu.rpg;

import java.util.Map;

// "Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
public class Item {
    private String name;
    private String description;
    private double weight;
    private double value;

    public static final Map<String, Item> ITEMS = Map.of(
        "Lookout Ring",        new Item("Lookout Ring", "Prevents surprise attacks", 0.1, 80),
        "Scroll of Stupidity", new Item("Scroll of Stupidity", "INT -2 when applied to an enemy", 0.2, 40),
        "Draupnir",            new Item("Draupnir", "Increases XP gained by 100%", 0.5, 250),
        "Magic Charm",         new Item("Magic Charm", "Magic +10 for 5 rounds", 0.1, 120),
        "Rune Staff of Curse", new Item("Rune Staff of Curse", "May burn your enemies... Or yourself", 4.0, 300),
        "Combat Edge",         new Item("Combat Edge", "Well, that's an edge", 2.0, 90),
        "Holy Elixir",         new Item("Holy Elixir", "Recover your HP", 0.3, 60)
    );

    public Item(String name, String description, double weight, double value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
        this.quantity = 1;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public SellItem(Item item, int quantity) {
        this.item = getName();
        this.quantity = quantity;
        this.valuePerItem = getValue();

        this.totalValue = quantity * valuePerItem;
        player.addMoney(this.totalValue);

        System.out.println("Vendu " + quantity + " " + item.getName() + "(s) pour " + this.totalValue + " pièces d'or.");
        this.totalweight = this.totalweight - (quantity * item.getWeight());
    }

    public BuyItem(Item item, int quantity) {
        this.item = getName();
        this.quantity = quantity;
        this.valuePerItem = getValue() * 1.2;

        this.totalValue = quantity * valuePerItem;
        player.subtractMoney(this.totalValue);

        System.out.println("Acheté " + quantity + " " + item.getName() + "(s) pour " + this.totalValue + " pièces d'or.");

        if (this.totalweight + (quantity * item.getWeight()) > player.this.weightlimit) {
            System.out.println("Impossible d'acheter " + quantity + " " + item.getName() + "(s). Limite de poids dépassée.");
            return;
        }
        this.totalweight = this.totalweight + (quantity * item.getWeight());
    }
}