
/**
 * Example 2: Switch-case оператор
 *
 * Демонстрирует различные способы использования switch
 */
public class Example02_Switch {

    public static void main(String[] args) {
        System.out.println("=== БАЗОВЫЙ SWITCH ===\n");

        int dayOfWeek = 3;
        String dayName;

        switch (dayOfWeek) {
            case 1:
                dayName = "Понедельник";
                break;
            case 2:
                dayName = "Вторник";
                break;
            case 3:
                dayName = "Среда";
                break;
            case 4:
                dayName = "Четверг";
                break;
            case 5:
                dayName = "Пятница";
                break;
            case 6:
                dayName = "Суббота";
                break;
            case 7:
                dayName = "Воскресенье";
                break;
            default:
                dayName = "Неверный день";
        }

        System.out.println("День " + dayOfWeek + ": " + dayName);

        System.out.println("\n=== SWITCH БЕЗ BREAK (FALL-THROUGH) ===\n");

        int month = 2;
        int days;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = 28; // или 29 в високосный год
                break;
            default:
                days = 0;
        }

        System.out.println("Месяц " + month + " имеет " + days + " дней");

        System.out.println("\n=== SWITCH С CHAR ===\n");

        char grade = 'B';
        String description;

        switch (grade) {
            case 'A':
                description = "Отлично! Превосходная работа!";
                break;
            case 'B':
                description = "Хорошо! Продолжайте в том же духе!";
                break;
            case 'C':
                description = "Удовлетворительно. Можно лучше.";
                break;
            case 'D':
                description = "Посредственно. Нужно подтянуться.";
                break;
            case 'F':
                description = "Неудовлетворительно. Требуется пересдача.";
                break;
            default:
                description = "Неверная оценка";
        }

        System.out.println("Оценка " + grade + ": " + description);

        System.out.println("\n=== SWITCH СО STRING ===\n");

        String command = "start";

        switch (command) {
            case "start":
                System.out.println("Запуск программы...");
                break;
            case "stop":
                System.out.println("Остановка программы...");
                break;
            case "pause":
                System.out.println("Пауза...");
                break;
            case "resume":
                System.out.println("Возобновление...");
                break;
            default:
                System.out.println("Неизвестная команда: " + command);
        }

        System.out.println("\n=== SWITCH EXPRESSIONS (Java 14+) ===\n");

        int day = 5;
        String dayType = switch (day) {
            case 1, 2, 3, 4, 5 ->
                "Будний день";
            case 6, 7 ->
                "Выходной";
            default ->
                "Неверный день";
        };

        System.out.println("День " + day + ": " + dayType);

        // Switch expression с блоком кода
        int monthNum = 12;
        String season = switch (monthNum) {
            case 12, 1, 2 -> {
                System.out.println("  (Холодное время года)");
                yield "Зима";
            }
            case 3, 4, 5 -> {
                System.out.println("  (Время цветения)");
                yield "Весна";
            }
            case 6, 7, 8 -> {
                System.out.println("  (Жаркое время года)");
                yield "Лето";
            }
            case 9, 10, 11 -> {
                System.out.println("  (Время листопада)");
                yield "Осень";
            }
            default ->
                "Неверный месяц";
        };

        System.out.println("Месяц " + monthNum + ": " + season);

        System.out.println("\n=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===\n");

        // Пример 1: Калькулятор
        char operation = '+';
        int a = 10, b = 5;
        int result;

        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                result = 0;
                System.out.println("Неизвестная операция");
        }

        System.out.println(a + " " + operation + " " + b + " = " + result);

        // Пример 2: Определение типа символа
        char ch = '5';
        String charType = switch (ch) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ->
                "Цифра";
            case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' ->
                "Строчная буква";
            case 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ->
                "Заглавная буква";
            case ' ', '\t', '\n' ->
                "Пробельный символ";
            default ->
                "Специальный символ";
        };

        System.out.println("Символ '" + ch + "': " + charType);

        // Пример 3: HTTP статус коды
        int statusCode = 404;
        String statusMessage = switch (statusCode) {
            case 200 ->
                "OK - Успешный запрос";
            case 201 ->
                "Created - Ресурс создан";
            case 400 ->
                "Bad Request - Неверный запрос";
            case 401 ->
                "Unauthorized - Требуется авторизация";
            case 403 ->
                "Forbidden - Доступ запрещён";
            case 404 ->
                "Not Found - Ресурс не найден";
            case 500 ->
                "Internal Server Error - Ошибка сервера";
            case 503 ->
                "Service Unavailable - Сервис недоступен";
            default ->
                "Unknown Status Code";
        };

        System.out.println("HTTP " + statusCode + ": " + statusMessage);
    }
}

/*
ВЫВОД ПРОГРАММЫ:
================
=== БАЗОВЫЙ SWITCH ===

День 3: Среда

=== SWITCH БЕЗ BREAK (FALL-THROUGH) ===

Месяц 2 имеет 28 дней

=== SWITCH С CHAR ===

Оценка B: Хорошо! Продолжайте в том же духе!

=== SWITCH СО STRING ===

Запуск программы...

=== SWITCH EXPRESSIONS (Java 14+) ===

День 5: Будний день
  (Холодное время года)
Месяц 12: Зима

=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===

10 + 5 = 15
Символ '5': Цифра
HTTP 404: Not Found - Ресурс не найден
 */
