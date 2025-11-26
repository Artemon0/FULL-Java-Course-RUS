
import java.util.Scanner;

/**
 * Система боя
 */
public class CombatSystem {

    private Player player;
    private Enemy enemy;
    private Scanner scanner;

    public CombatSystem(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = new Scanner(System.in);
    }

    public boolean startCombat() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("  БОЙ НАЧАЛСЯ!");
        System.out.println("  " + enemy.getName() + " появился!");
        System.out.println("╚════════════════════════════════════════╝\n");

        while (player.isAlive() && enemy.isAlive()) {
            displayCombatStatus();
            playerTurn();

            if (!enemy.isAlive()) {
                victory();
                return true;
            }

            enemyTurn();

            if (!player.isAlive()) {
                defeat();
                return false;
            }
        }

        return player.isAlive();
    }

    private void displayCombatStatus() {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.printf("│ %s HP: %d/%d%n", player.getName(),
                player.getHealth(), player.getMaxHealth());
        System.out.printf("│ %s HP: %d/%d%n", enemy.getName(),
                enemy.getHealth(), enemy.getMaxHealth());
        System.out.println("└────────────────────────────────────────┘");
    }

    private void playerTurn() {
        System.out.println("\n--- Ваш ход ---");
        System.out.println("1. Атаковать");
        System.out.println("2. Использовать предмет");
        System.out.println("3. Бежать");

        System.out.print("Выбор: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                attack();
                break;
            case 2:
                useItem();
                break;
            case 3:
                System.out.println("Вы сбежали из боя!");
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор!");
                playerTurn();
        }
    }

    private void attack() {
        int damage = player.getAttack();
        enemy.takeDamage(damage);
        System.out.println("Вы нанесли " + damage + " урона!");
    }

    private void useItem() {
        player.getInventory().displayItems();
        if (player.getInventory().getCount() == 0) {
            return;
        }

        System.out.print("Выберите предмет (0 - отмена): ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= player.getInventory().getCount()) {
            Item item = player.getInventory().getItem(choice - 1);
            item.use(player);
        }
    }

    private void enemyTurn() {
        System.out.println("\n--- Ход врага ---");
        int damage = enemy.getAttack();
        player.takeDamage(damage);
    }

    private void victory() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("  ПОБЕДА!");
        System.out.println("╚════════════════════════════════════════╝");

        int exp = enemy.getExperienceReward();
        int gold = enemy.getGoldReward();

        player.gainExperience(exp);
        player.addGold(gold);

        System.out.println("Получено золота: " + gold);
    }

    private void defeat() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("  ПОРАЖЕНИЕ!");
        System.out.println("╚════════════════════════════════════════╝");
    }
}
