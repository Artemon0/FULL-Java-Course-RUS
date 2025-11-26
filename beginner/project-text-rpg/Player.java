
/**
 * Класс игрока
 */
public class Player extends Character {

    private int experience;
    private int experienceToNextLevel;
    private Inventory inventory;

    public Player(String name, String characterClass) {
        super(name, 1);
        this.experience = 0;
        this.experienceToNextLevel = 100;
        this.inventory = new Inventory(20);
        this.gold = 100;

        initializeStats(characterClass);
    }

    private void initializeStats(String characterClass) {
        switch (characterClass.toLowerCase()) {
            case "warrior":
                maxHealth = 150;
                maxMana = 50;
                attack = 15;
                defense = 10;
                break;
            case "mage":
                maxHealth = 80;
                maxMana = 150;
                attack = 20;
                defense = 5;
                break;
            case "archer":
                maxHealth = 100;
                maxMana = 80;
                attack = 18;
                defense = 7;
                break;
            default:
                maxHealth = 100;
                maxMana = 100;
                attack = 10;
                defense = 5;
        }
        health = maxHealth;
        mana = maxMana;
    }

    @Override
    public void specialAbility() {
        System.out.println(name + " использует специальную способность!");
    }

    @Override
    public String getClassName() {
        return "Герой";
    }

    public void gainExperience(int exp) {
        experience += exp;
        System.out.println("Получено опыта: " + exp);

        while (experience >= experienceToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        experience -= experienceToNextLevel;
        experienceToNextLevel = (int) (experienceToNextLevel * 1.5);

        maxHealth += 20;
        health = maxHealth;
        maxMana += 10;
        mana = maxMana;
        attack += 5;
        defense += 3;

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("  ПОВЫШЕНИЕ УРОВНЯ!");
        System.out.println("  Новый уровень: " + level);
        System.out.println("╚════════════════════════════════════════╝");
    }

    public Inventory getInventory() {
        return inventory;
    }
}
