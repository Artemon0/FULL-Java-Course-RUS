
/**
 * Класс врага
 */
public class Enemy extends Character {

    private int experienceReward;
    private int goldReward;

    public Enemy(String name, int level) {
        super(name, level);
        initializeStats();
    }

    private void initializeStats() {
        maxHealth = 50 + (level * 20);
        health = maxHealth;
        attack = 5 + (level * 3);
        defense = 2 + (level * 2);
        experienceReward = 50 * level;
        goldReward = 20 * level;
    }

    @Override
    public void specialAbility() {
        System.out.println(name + " использует тёмную магию!");
    }

    @Override
    public String getClassName() {
        return "Враг";
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public int getGoldReward() {
        return goldReward;
    }
}
