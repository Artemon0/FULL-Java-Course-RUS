
/**
 * Базовый класс предмета
 */
public abstract class Item {

    protected String name;
    protected String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void use(Player player);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

class HealthPotion extends Item {

    private final int healAmount;

    public HealthPotion() {
        super("Зелье здоровья", "Восстанавливает 50 HP");
        this.healAmount = 50;
    }

    @Override
    public void use(Player player) {
        player.heal(healAmount);
    }
}

class Weapon extends Item {

    private int attackBonus;

    public Weapon(String name, int attackBonus) {
        super(name, "Атака +" + attackBonus);
        this.attackBonus = attackBonus;
    }

    @Override
    public void use(Player player) {
        System.out.println("Экипировано: " + name);
    }
}
