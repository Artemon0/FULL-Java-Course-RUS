
/**
 * Example 3: Цикл for - все варианты использования
 *
 * Компиляция: javac Example03_ForLoop.java
 * Запуск: java Example03_ForLoop
 */
public class Example03_ForLoop {

    public static void main(String[] args) {
        System.out.println("=== БАЗОВЫЙ ЦИКЛ FOR ===\n");

        // Простой цикл от 1 до 10
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
// Обратный отсчёт
        System.out.println("Обратный отсчёт:");
        for (int i = 10; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println("Пуск!\n");
        
        // Шаг 2
        System.out.println("Чётные числа от 0 до 20:");
        for (int i = 0; i <= 20; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        
        // Шаг 3
        System.out.println("Числа кратные 3 от 0 до 30:");
        for (int i = 0; i <= 30; i += 3) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        
        System.out.println("=== ТАБЛИЦА УМНОЖЕНИЯ ===\n");
        
        int number = 7;
        System.out.println("Таблица умножения на " + number + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d × %d = %d%n", number, i, number * i);
        }
        System.out.println();
        
        System.out.println("=== ВЛОЖЕННЫЕ ЦИКЛЫ ===\n");
        
        // Таблица умножения 5x5
        System.out.println("Таблица умножения 5×5:");
        System.out.print("   ");
        for (int i = 1; i <= 5; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println("\n   " + "----".repeat(5));
        
        for (int i = 1; i <= 5; i++) {
            System.out.printf("%d |", i);
            for (int j = 1; j <= 5; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
        System.out.println();
        
        // Треугольник из звёздочек
        System.out.println("Треугольник из звёздочек:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
        
        // Перевёрнутый треугольник
        System.out.println("Перевёрнутый треугольник:");
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
        
        // Пирамида
        System.out.println("Пирамида:");
        for (int i = 1; i <= 5; i++) {
            // Пробелы
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            // Звёздочки
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        
        // Ромб
        System.out.println("Ромб:");
        int size = 5;
        // Верхняя часть
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // Нижняя часть
        for (int i = size - 1; i >= 1; i--) {
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        
        System.out.println("=== ЦИКЛ FOR С НЕСКОЛЬКИМИ ПЕРЕМЕННЫМИ ===\n");
        
        for (int i = 0, j = 10; i < j; i++, j--) {
            System.out.printf("i = %d, j = %d%n", i, j);
        }
        System.out.println();
        
        System.out.println("=== БЕСКОНЕЧНЫЙ ЦИКЛ (с break) ===\n");
        
        int counter = 0;
        for (;;) {
            System.out.println("Итерация " + counter);
            counter++;
            if (counter >= 5) {
                break;
            }
        }
        System.out.println();
        
        System.out.println("=== FOR С CONTINUE ===\n");
        
        System.out.println("Числа от 1 до 10, кроме 5:");
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println("\n");
        
        System.out.println("Нечётные числа от 1 до 20:");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println("\n");
        
        System.out.println("=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===\n");
        
        // Пример 1: Сумма чисел от 1 до 100
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("Сумма чисел от 1 до 100: " + sum);
        
        // Пример 2: Факториал
        int n = 5;
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);
        
        // Пример 3: Степени числа 2
        System.out.println("\nСтепени числа 2:");
        for (int i = 0; i <= 10; i++) {
            System.out.printf("2^%d = %d%n", i, (int)Math.pow(2, i));
        }
        
        // Пример 4: Числа Фибоначчи
        System.out.println("\nПервые 10 чисел Фибоначчи:");
        int a = 0, b = 1;
        for (int i = 0; i < 10; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
        System.out.println("\n");
        
        // Пример 5: Простые числа до 50
        System.out.println("Простые числа до 50:");
        for (int num = 2; num <= 50; num++) {
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(num + " ");
            }
        }
        System.out.println("\n");
        
        // Пример 6: Шахматная доска
        System.out.println("Шахматная доска 8×8:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("■ ");
                } else {
                    System.out.print("□ ");
                }
            }
            System.out.println();
        }
        System.out.println();
        
        // Пример 7: Цифровые часы (симуляция)
        System.out.println("Первые 10 секунд дня:");
        for (int h = 0; h < 1; h++) {
            for (int m = 0; m < 1; m++) {
                for (int s = 0; s < 10; s++) {
                    System.out.printf("%02d:%02d:%02d%n", h, m, s);
                }
            }
        }
        System.out.println();
        
        // Пример 8: Таблица ASCII
        System.out.println("Таблица ASCII (символы 65-90):");
        for (int i = 65; i <= 90; i++) {
            System.out.printf("%d = %c  ", i, (char)i);
            if ((i - 64) % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
        
        // Пример 9: Сумма цифр числа
        int num = 12345;
        int digitSum = 0;
        int temp = num;
        System.out.println("Сумма цифр числа " + num + ":");
        for (; temp > 0; temp /= 10) {
            int digit = temp % 10;
            System.out.print(digit + " ");
            digitSum += digit;
        }
        System.out.println("= " + digitSum + "\n");
        
        // Пример 10: Перевод числа в двоичную систему
        int decimal = 42;
        System.out.println("Число " + decimal + " в двоичной системе:");
        String binary = "";
        for (int i = decimal; i > 0; i /= 2) {
            binary = (i % 2) + binary;
        }
        System.out.println(binary);
    }
}

/*
ВЫВОД ПРОГРАММЫ:
================
=== БАЗОВЫЙ ЦИКЛ FOR ===

1 2 3 4 5 6 7 8 9 10 

Обратный отсчёт:
10 9 8 7 6 5 4 3 2 1 Пуск!

Чётные числа от 0 до 20:
0 2 4 6 8 10 12 14 16 18 20 

Числа кратные 3 от 0 до 30:
0 3 6 9 12 15 18 21 24 27 30 

=== ТАБЛИЦА УМНОЖЕНИЯ ===

Таблица умножения на 7:
7 × 1 = 7
7 × 2 = 14
7 × 3 = 21
7 × 4 = 28
7 × 5 = 35
7 × 6 = 42
7 × 7 = 49
7 × 8 = 56
7 × 9 = 63
7 × 10 = 70

=== ВЛОЖЕННЫЕ ЦИКЛЫ ===

Таблица умножения 5×5:
      1   2   3   4   5
   --------------------
1 |   1   2   3   4   5
2 |   2   4   6   8  10
3 |   3   6   9  12  15
4 |   4   8  12  16  20
5 |   5  10  15  20  25

Треугольник из звёздочек:
* 
* * 
* * * 
* * * * 
* * * * * 

Перевёрнутый треугольник:
* * * * * 
* * * * 
* * * 
* * 
* 

Пирамида:
    *
   ***
  *****
 *******
*********

Ромб:
    *
   ***
  *****
 *******
*********
 *******
  *****
   ***
    *

=== ЦИКЛ FOR С НЕСКОЛЬКИМИ ПЕРЕМЕННЫМИ ===

i = 0, j = 10
i = 1, j = 9
i = 2, j = 8
i = 3, j = 7
i = 4, j = 6

=== БЕСКОНЕЧНЫЙ ЦИКЛ (с break) ===

Итерация 0
Итерация 1
Итерация 2
Итерация 3
Итерация 4

=== FOR С CONTINUE ===

Числа от 1 до 10, кроме 5:
1 2 3 4 6 7 8 9 10 

Нечётные числа от 1 до 20:
1 3 5 7 9 11 13 15 17 19 

=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===

Сумма чисел от 1 до 100: 5050
5! = 120

Степени числа 2:
2^0 = 1
2^1 = 2
2^2 = 4
2^3 = 8
2^4 = 16
2^5 = 32
2^6 = 64
2^7 = 128
2^8 = 256
2^9 = 512
2^10 = 1024

Первые 10 чисел Фибоначчи:
0 1 1 2 3 5 8 13 21 34 

Простые числа до 50:
2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 

Шахматная доска 8×8:
■ □ ■ □ ■ □ ■ □ 
□ ■ □ ■ □ ■ □ ■ 
■ □ ■ □ ■ □ ■ □ 
□ ■ □ ■ □ ■ □ ■ 
■ □ ■ □ ■ □ ■ □ 
□ ■ □ ■ □ ■ □ ■ 
■ □ ■ □ ■ □ ■ □ 
□ ■ □ ■ □ ■ □ ■ 

Первые 10 секунд дня:
00:00:00
00:00:01
00:00:02
00:00:03
00:00:04
00:00:05
00:00:06
00:00:07
00:00:08
00:00:09

Таблица ASCII (символы 65-90):
65 = A  66 = B  67 = C  68 = D  69 = E  
70 = F  71 = G  72 = H  73 = I  74 = J  
75 = K  76 = L  77 = M  78 = N  79 = O  
80 = P  81 = Q  82 = R  83 = S  84 = T  
85 = U  86 = V  87 = W  88 = X  89 = Y  
90 = Z  

Сумма цифр числа 12345:
5 4 3 2 1 = 15

Число 42 в двоичной системе:
101010
*/
