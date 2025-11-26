
import java.util.Scanner;
import java.util.Random;

/**
 * МИНИ-ПРОЕКТ: Игра "Угадай число"
 *
 * Полная игра с: - Генерацией случайного числа - Подсказками (больше/меньше) -
 * Ограничением попыток - Статистикой - Уровнями сложности
 */
public class NumberGuessingGame {

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int gamesPlayed = 0;
    private static int gamesWon = 0;

    public static void main(String[] args) {
        printWelcome();

        boolean playAgain = true;

        while (playAgain) {
            playGame();
            playAgain = askPlayAgain();
        }

        printStatistics();
        System.out.println("\nСпасибо за игру! До встречи!");
        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║                  ИГРА 'УГАДАЙ ЧИСЛО'                       ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Я загадаю число, а ты попробуй его угадать!               ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void playGame() {
        gamesPlayed++;

        // Выбор уровня сложности
        int difficulty = chooseDifficulty();
        int maxNumber = 0;
        int maxAttempts = 0;

        switch (difficulty) {
            case 1: // Лёгкий
                maxNumber = 50;
                maxAttempts = 10;
                break;
            case 2: // Средний
                maxNumber = 100;
                maxAttempts = 7;
                break;
            case 3: // Сложный
                maxNumber = 200;
                maxAttempts = 5;
                break;
        }

        int secretNumber = random.nextInt(maxNumber) + 1;
        int attempts = 0;
        boolean won = false;

        System.out.println("\n┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ Я загадал число от 1 до " + maxNumber);
        System.out.println("│ У тебя " + maxAttempts + " попыток");
        System.out.println("└────────────────────────────────────────────────────────────┘\n");

        while (attempts < maxAttempts && !won) {
            attempts++;
            System.out.print("Попытка " + attempts + "/" + maxAttempts + ": ");

            int guess = getIntInput();

            if (guess == secretNumber) {
                won = true;
                printVictory(attempts, maxAttempts);
            } else if (guess < secretNumber) {
                System.out.println("❌ Моё число БОЛЬШЕ");
                giveHint(guess, secretNumber, maxNumber);
            } else {
                System.out.println("❌ Моё число МЕНЬШЕ");
                giveHint(guess, secretNumber, maxNumber);
            }

            if (!won && attempts < maxAttempts) {
                System.out.println("Осталось попыток: " + (maxAttempts - attempts) + "\n");
            }
        }

        if (!won) {
            printDefeat(secretNumber);
        } else {
            gamesWon++;
        }
    }

    private static int chooseDifficulty() {
        System.out.println("Выбери уровень сложности:");
        System.out.println("1. Лёгкий   (1-50, 10 попыток)");
        System.out.println("2. Средний  (1-100, 7 попыток)");
        System.out.println("3. Сложный  (1-200, 5 попыток)");
        System.out.print("\nТвой выбор (1-3): ");

        int choice = getIntInput();
        while (choice < 1 || choice > 3) {
            System.out.print("Неверный выбор! Введи 1, 2 или 3: ");
            choice = getIntInput();
        }

        return choice;
    }

    private static void giveHint(int guess, int secret, int maxNumber) {
        int difference = Math.abs(guess - secret);

        if (difference <= maxNumber * 0.05) {
            System.out.println("🔥 ОЧЕНЬ ГОРЯЧО!");
        } else if (difference <= maxNumber * 0.1) {
            System.out.println("🌡️  Горячо!");
        } else if (difference <= maxNumber * 0.2) {
            System.out.println("☀️  Тепло");
        } else if (difference <= maxNumber * 0.3) {
            System.out.println("🌤️  Прохладно");
        } else {
            System.out.println("❄️  Холодно");
        }
    }

    private static void printVictory(int attempts, int maxAttempts) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║                    🎉 ПОЗДРАВЛЯЮ! 🎉                       ║");
        System.out.println("║                                                            ║");
        System.out.println("║                  Ты угадал число!                          ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        System.out.println("\n📊 Статистика попытки:");
        System.out.println("   Использовано попыток: " + attempts + " из " + maxAttempts);

        double efficiency = (double) (maxAttempts - attempts + 1) / maxAttempts * 100;
        System.out.printf("   Эффективность: %.1f%%%n", efficiency);

        if (attempts == 1) {
            System.out.println("   🏆 НЕВЕРОЯТНО! Угадал с первой попытки!");
        } else if (attempts <= maxAttempts / 3) {
            System.out.println("   ⭐ ОТЛИЧНО! Очень быстро!");
        } else if (attempts <= maxAttempts / 2) {
            System.out.println("   ✨ ХОРОШО! Неплохой результат!");
        } else {
            System.out.println("   👍 Молодец! Справился!");
        }
        System.out.println();
    }

    private static void printDefeat(int secretNumber) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║                  😢 ПОПЫТКИ ЗАКОНЧИЛИСЬ                    ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\nЗагаданное число было: " + secretNumber);
        System.out.println("Не расстраивайся! Попробуй ещё раз!\n");
    }

    private static boolean askPlayAgain() {
        System.out.print("Хочешь сыграть ещё раз? (да/нет): ");
        String answer = scanner.next().toLowerCase();
        System.out.println();
        return answer.equals("да") || answer.equals("yes") || answer.equals("д") || answer.equals("y");
    }

    private static void printStatistics() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ОБЩАЯ СТАТИСТИКА                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\n📊 Всего игр сыграно: " + gamesPlayed);
        System.out.println("🏆 Побед: " + gamesWon);
        System.out.println("😢 Поражений: " + (gamesPlayed - gamesWon));

        if (gamesPlayed > 0) {
            double winRate = (double) gamesWon / gamesPlayed * 100;
            System.out.printf("📈 Процент побед: %.1f%%%n", winRate);
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Ошибка! Введи число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

/*
ПРИМЕР ИГРЫ:
============

╔════════════════════════════════════════════════════════════╗
║                                                            ║
║                  ИГРА 'УГАДАЙ ЧИСЛО'                       ║
║                                                            ║
║  Я загадаю число, а ты попробуй его угадать!              ║
║                                                            ║
╚════════════════════════════════════════════════════════════╝

Выбери уровень сложности:
1. Лёгкий   (1-50, 10 попыток)
2. Средний  (1-100, 7 попыток)
3. Сложный  (1-200, 5 попыток)

Твой выбор (1-3): 2

┌────────────────────────────────────────────────────────────┐
│ Я загадал число от 1 до 100
│ У тебя 7 попыток
└────────────────────────────────────────────────────────────┘

Попытка 1/7: 50
❌ Моё число БОЛЬШЕ
🌤️  Прохладно
Осталось попыток: 6

Попытка 2/7: 75
❌ Моё число МЕНЬШЕ
☀️  Тепло
Осталось попыток: 5

Попытка 3/7: 65
❌ Моё число БОЛЬШЕ
🌡️  Горячо!
Осталось попыток: 4

Попытка 4/7: 70
❌ Моё число МЕНЬШЕ
🔥 ОЧЕНЬ ГОРЯЧО!
Осталось попыток: 3

Попытка 5/7: 68

╔════════════════════════════════════════════════════════════╗
║                                                            ║
║                    🎉 ПОЗДРАВЛЯЮ! 🎉                       ║
║                                                            ║
║                  Ты угадал число!                          ║
║                                                            ║
╚════════════════════════════════════════════════════════════╝

📊 Статистика попытки:
   Использовано попыток: 5 из 7
   Эффективность: 42.9%
   👍 Молодец! Справился!

Хочешь сыграть ещё раз? (да/нет): нет

╔════════════════════════════════════════════════════════════╗
║                  ОБЩАЯ СТАТИСТИКА                          ║
╚════════════════════════════════════════════════════════════╝

📊 Всего игр сыграно: 1
🏆 Побед: 1
😢 Поражений: 0
📈 Процент побед: 100.0%

Спасибо за игру! До встречи!
 */
