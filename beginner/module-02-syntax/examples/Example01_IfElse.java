
/**
 * Example 1: Условные операторы if-else
 *
 * Демонстрирует все варианты использования if-else
 */
public class Example01_IfElse {

    public static void main(String[] args) {
        System.out.println("=== ПРОСТОЙ IF ===\n");

        int age = 18;
        if (age >= 18) {
            System.out.println("Вы совершеннолетний");
        }

        System.out.println("\n=== IF-ELSE ===\n");

        int temperature = 25;
        if (temperature > 30) {
            System.out.println("Жарко!");
        } else {
            System.out.println("Нормальная температура");
        }

        System.out.println("\n=== IF-ELSE IF-ELSE ===\n");

        int score = 85;
        if (score >= 90) {
            System.out.println("Оценка: Отлично (A)");
        } else if (score >= 80) {
            System.out.println("Оценка: Хорошо (B)");
        } else if (score >= 70) {
            System.out.println("Оценка: Удовлетворительно (C)");
        } else if (score >= 60) {
            System.out.println("Оценка: Посредственно (D)");
        } else {
            System.out.println("Оценка: Неудовлетворительно (F)");
        }

        System.out.println("\n=== ВЛОЖЕННЫЕ IF ===\n");

        boolean hasLicense = true;
        int drivingAge = 20;

        if (hasLicense) {
            if (drivingAge >= 18) {
                System.out.println("Можете водить машину");
            } else {
                System.out.println("Слишком молоды для вождения");
            }
        } else {
            System.out.println("Сначала получите права");
        }

        System.out.println("\n=== ЛОГИЧЕСКИЕ ОПЕРАТОРЫ В УСЛОВИЯХ ===\n");

        int hour = 14;
        boolean isWeekend = false;

        // AND (&&)
        if (hour >= 9 && hour <= 18 && !isWeekend) {
            System.out.println("Рабочее время");
        }

        // OR (||)
        if (hour < 9 || hour > 18 || isWeekend) {
            System.out.println("Нерабочее время");
        }

        System.out.println("\n=== ТЕРНАРНЫЙ ОПЕРАТОР ===\n");

        int number = 15;
        String result = (number % 2 == 0) ? "Чётное" : "Нечётное";
        System.out.println(number + " - " + result);

        // Вложенный тернарный оператор
        int value = 0;
        String sign = (value > 0) ? "Положительное"
                : (value < 0) ? "Отрицательное" : "Ноль";
        System.out.println(value + " - " + sign);

        System.out.println("\n=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===\n");

        // Пример 1: Проверка високосного года
        int year = 2024;
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println(year + " - " + (isLeapYear ? "Високосный год" : "Обычный год"));

        // Пример 2: Определение времени суток
        int currentHour = 15;
        String timeOfDay;
        if (currentHour >= 5 && currentHour < 12) {
            timeOfDay = "Утро";
        } else if (currentHour >= 12 && currentHour < 17) {
            timeOfDay = "День";
        } else if (currentHour >= 17 && currentHour < 21) {
            timeOfDay = "Вечер";
        } else {
            timeOfDay = "Ночь";
        }
        System.out.println(currentHour + ":00 - " + timeOfDay);

        // Пример 3: Категория возраста
        int personAge = 35;
        String category;
        if (personAge < 13) {
            category = "Ребёнок";
        } else if (personAge < 20) {
            category = "Подросток";
        } else if (personAge < 60) {
            category = "Взрослый";
        } else {
            category = "Пожилой";
        }
        System.out.println("Возраст " + personAge + " - " + category);

        // Пример 4: Проверка треугольника
        int a = 3, b = 4, c = 5;
        if (a + b > c && a + c > b && b + c > a) {
            System.out.println("Стороны " + a + ", " + b + ", " + c + " образуют треугольник");

            if (a == b && b == c) {
                System.out.println("Это равносторонний треугольник");
            } else if (a == b || b == c || a == c) {
                System.out.println("Это равнобедренный треугольник");
            } else {
                System.out.println("Это разносторонний треугольник");
            }

            if (a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a) {
                System.out.println("Это прямоугольный треугольник");
            }
        } else {
            System.out.println("Стороны " + a + ", " + b + ", " + c + " НЕ образуют треугольник");
        }
    }
}

/*
ВЫВОД ПРОГРАММЫ:
================
=== ПРОСТОЙ IF ===

Вы совершеннолетний

=== IF-ELSE ===

Нормальная температура

=== IF-ELSE IF-ELSE ===

Оценка: Хорошо (B)

=== ВЛОЖЕННЫЕ IF ===

Можете водить машину

=== ЛОГИЧЕСКИЕ ОПЕРАТОРЫ В УСЛОВИЯХ ===

Рабочее время

=== ТЕРНАРНЫЙ ОПЕРАТОР ===

15 - Нечётное
0 - Ноль

=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===

2024 - Високосный год
15:00 - День
Возраст 35 - Взрослый
Стороны 3, 4, 5 образуют треугольник
Это разносторонний треугольник
Это прямоугольный треугольник
 */
