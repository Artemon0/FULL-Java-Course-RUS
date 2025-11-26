
import java.util.Scanner;

/**
 * МИНИ-ПРОЕКТ: Консольный калькулятор
 *
 * Функции: - Базовые операции (+, -, *, /) - История вычислений - Работа с
 * дробными числами - Обработка ошибок
 *
 * Компиляция: javac Calculator.java Запуск: java Calculator
 */
public class Calculator {

    // История вычислений
    private static String[] history = new String[10];
    private static int historyCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printWelcome();

        while (running) {
            printMenu();
            int choice = getIntInput(scanner, "Ваш выбор: ");

            switch (choice) {
                case 1:
                    performCalculation(scanner);
                    break;
                case 2:
                    showHistory();
                    break;
                case 3:
                    clearHistory();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nСпасибо за использование калькулятора!");
                    break;
                default:
                    System.out.println("\n❌ Неверный выбор! Попробуйте снова.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Печатает приветствие
     */
    private static void printWelcome() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     КОНСОЛЬНЫЙ КАЛЬКУЛЯТОР             ║");
        System.out.println("║     Версия 1.0                         ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    /**
     * Печатает меню
     */
    private static void printMenu() {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│ МЕНЮ:                                  │");
        System.out.println("│ 1. Выполнить вычисление                │");
        System.out.println("│ 2. Показать историю                    │");
        System.out.println("│ 3. Очистить историю                    │");
        System.out.println("│ 0. Выход                               │");
        System.out.println("└────────────────────────────────────────┘");
    }
    
    /**
     * Выполняет вычисление
     */
    private static void performCalculation(Scanner scanner) {
        System.out.println("\n=== ВЫЧИСЛЕНИЕ ===");
        
        double num1 = getDoubleInput(scanner, "Введите первое число: ");
        
        System.out.print("Введите операцию (+, -, *, /): ");
        String operation = scanner.next();
        
        double num2 = getDoubleInput(scanner, "Введите второе число: ");
        
        double result = 0;
        boolean validOperation = true;
        
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("\n❌ ОШИБКА: Деление на ноль!");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("\n❌ ОШИБКА: Неизвестная операция!");
                validOperation = false;
        }
        
        if (validOperation) {
            System.out.println("\n┌────────────────────────────────────────┐");
            System.out.printf("│ Результат: %.2f %s %.2f = %.2f%n", num1, operation, num2, result);
            System.out.println("└────────────────────────────────────────┘");
            
            // Добавляем в историю
            addToHistory(String.format("%.2f %s %.2f = %.2f", num1, operation, num2, result));
        }
    }
    
    /**
     * Показывает историю вычислений
     */
    private static void showHistory() {
        System.out.println("\n=== ИСТОРИЯ ВЫЧИСЛЕНИЙ ===");
        
        if (historyCount == 0) {
            System.out.println("История пуста");
            return;
        }
        
        for (int i = 0; i < historyCount; i++) {
            System.out.printf("%d. %s%n", i + 1, history[i]);
        }
    }
    
    /**
     * Очищает историю
     */
    private static void clearHistory() {
        historyCount = 0;
        System.out.println("\n✓ История очищена");
    }
    
    /**
     * Добавляет запись в историю
     */
    private static void addToHistory(String record) {
        if (historyCount < history.length) {
            history[historyCount] = record;
            historyCount++;
        } else {
            // Сдвигаем историю
            for (int i = 0; i < history.length - 1; i++) {
                history[i] = history[i + 1];
            }
            history[history.length - 1] = record;
        }
    }
    
    /**
     * Получает целое число от пользователя
     */
    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Ошибка! Введите целое число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    /**
     * Получает дробное число от пользователя
     */
    private static double getDoubleInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("❌ Ошибка! Введите число: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}

/*
ПРИМЕР РАБОТЫ ПРОГРАММЫ:
=========================

╔════════════════════════════════════════╗
║     КОНСОЛЬНЫЙ КАЛЬКУЛЯТОР             ║
║     Версия 1.0                         ║
╚════════════════════════════════════════╝

┌────────────────────────────────────────┐
│ МЕНЮ:                                  │
│ 1. Выполнить вычисление                │
│ 2. Показать историю                    │
│ 3. Очистить историю                    │
│ 0. Выход                               │
└────────────────────────────────────────┘
Ваш выбор: 1

=== ВЫЧИСЛЕНИЕ ===
Введите первое число: 15.5
Введите операцию (+, -, *, /): *
Введите второе число: 2

┌────────────────────────────────────────┐
│ Результат: 15.50 * 2.00 = 31.00
└────────────────────────────────────────┘

┌────────────────────────────────────────┐
│ МЕНЮ:                                  │
│ 1. Выполнить вычисление                │
│ 2. Показать историю                    │
│ 3. Очистить историю                    │
│ 0. Выход                               │
└────────────────────────────────────────┘
Ваш выбор: 1

=== ВЫЧИСЛЕНИЕ ===
Введите первое число: 100
Введите операцию (+, -, *, /): /
Введите второе число: 4

┌────────────────────────────────────────┐
│ Результат: 100.00 / 4.00 = 25.00
└────────────────────────────────────────┘

┌────────────────────────────────────────┐
│ МЕНЮ:                                  │
│ 1. Выполнить вычисление                │
│ 2. Показать историю                    │
│ 3. Очистить историю                    │
│ 0. Выход                               │
└────────────────────────────────────────┘
Ваш выбор: 2

=== ИСТОРИЯ ВЫЧИСЛЕНИЙ ===
1. 15.50 * 2.00 = 31.00
2. 100.00 / 4.00 = 25.00

┌────────────────────────────────────────┐
│ МЕНЮ:                                  │
│ 1. Выполнить вычисление                │
│ 2. Показать историю                    │
│ 3. Очистить историю                    │
│ 0. Выход                               │
└────────────────────────────────────────┘
Ваш выбор: 0

Спасибо за использование калькулятора!
*/
