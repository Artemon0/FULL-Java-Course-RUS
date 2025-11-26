
import java.util.Scanner;

/**
 * ФИНАЛЬНЫЙ ПРОЕКТ BEGINNER: Текстовая RPG игра
 *
 * Полная реализация с: - Системой персонажей - Боевой системой - Инвентарём -
 * Врагами - Прогрессией
 */
public class Game {

    private static Scanner scanner = new Scanner(System.in);
    private static Player player;

    public static void main(String[] args) {
        printWelcome();
        createCharacter();
        gameLoop();
    }

    private static void printWelcome() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║              ТЕКСТОВАЯ RPG ИГРА                            ║");
        System.out.println("║              Приключение начинается!                       ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void createCharacter() {
        System.out.println("=== СОЗДАНИЕ ПЕРСОНАЖА ===\n");

        System.out.print("Введите имя героя: ");
        String name = scanner.nextLine();

        System.out.println("\nВыберите класс:");
        System.out.println("1. Воин - Высокое здоровье и защита");
        System.out.println("2. Маг - Мощная магия");
        System.out.println("3. Лучник - Быстрые атаки");

        System.out.print("\nВаш выбор: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String characterClass = "";
        switch (choice) {
            case 1:
                characterClass = "warrior";
                break;
            case 2:
                characterClass = "mage";
                break;
            case 3:
                characterClass = "archer";
                break;
            default:
                characterClass = "warrior";
        }

        player = new Player(name, characterClass);

        // Добавляем начальные предметы
        player.getInventory().addItem(new HealthPotion());
        player.getInventory().addItem(new HealthPotion());

        System.out.println("\nПерсонаж создан!");
        player.displayStats();
    }

    private static void gameLoop() {
        boolean playing = true;

        while (playing) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    explore();
                    break;
                case 2:
                    player.getInventory().displayItems();
                    break;
                case 3:
                    player.displayStats();
                    break;
                case 4:
                    rest();
                    break;
                case 0:
                    playing = false;
                    System.out.println("Спасибо за игру!");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║              МЕНЮ                      ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. Исследовать                         ║");
        System.out.println("║ 2. Инвентарь                           ║");
        System.out.println("║ 3. Характеристики                      ║");
        System.out.println("║ 4. Отдохнуть                           ║");
        System.out.println("║ 0. Выход                               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Выбор: ");
    }

    private static void explore() {
        System.out.println("\nВы отправляетесь на исследование...");

        // Случайная встреча с врагом
        if (Math.random() < 0.7) {
            Enemy enemy = generateEnemy();
            CombatSystem combat = new CombatSystem(player, enemy);
            combat.startCombat();
        } else {
            System.out.println("Вы нашли сундук с сокровищами!");
            int gold = (int) (Math.random() * 50) + 10;
            player.addGold(gold);
            System.out.println("Получено " + gold + " золота!");

            if (Math.random() < 0.5) {
                player.getInventory().addItem(new HealthPotion());
                System.out.println("Найдено зелье здоровья!");
            }
        }
    }

    private static Enemy generateEnemy() {
        String[] enemyNames = {"Гоблин", "Орк", "Скелет", "Волк", "Бандит"};
        String name = enemyNames[(int) (Math.random() * enemyNames.length)];
        int level = player.level + (int) (Math.random() * 2);
        return new Enemy(name, level);
    }

    private static void rest() {
        System.out.println("\nВы отдыхаете...");
        player.heal(player.getMaxHealth());
        System.out.println("Здоровье полностью восстановлено!");
    }
}
