
/**
 * Базовый класс для всех персонажей
 */
public abstract class Character {

    protected String name;
    protected int level;
    protected int health;
    protected int maxHealth;
    protected int mana;
    protected int maxMana;
    protected int attack;
    protected int defense;
    protected int gold;

    public Character(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public abstract void specialAbility();

    public abstract String getClassName();

    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense);
        health -= actualDamage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " получает " + actualDamage + " урона!");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
        System.out.println(name + " восстанавливает " + amount + " HP");
    }

    public void displayStats() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("  " + name + " - " + getClassName());
        System.out.println("  Уровень: " + level);
        System.out.println("  HP: " + health + "/" + maxHealth);
        System.out.println("  MP: " + mana + "/" + maxMana);
        System.out.println("  Атака: " + attack);
        System.out.println("  Защита: " + defense);
        System.out.println("  Золото: " + gold);
        System.out.println("╚════════════════════════════════════════╝");
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }
}
