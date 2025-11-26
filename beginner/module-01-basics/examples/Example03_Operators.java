
/**
 * Example 3: Операторы в Java
 *
 * Демонстрирует все виды операторов
 */
public class Example03_Operators {

    public static void main(String[] args) {
        System.out.println("=== АРИФМЕТИЧЕСКИЕ ОПЕРАТОРЫ ===\n");

        int a = 10;
        int b = 3;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));    // Сложение
        System.out.println("a - b = " + (a - b));    // Вычитание
        System.out.println("a * b = " + (a * b));    // Умножение
        System.out.println("a / b = " + (a / b));    // Деление (целочисленное)
        System.out.println("a % b = " + (a % b));    // Остаток от деления
        
        // Деление с дробными числами
        double c = 10.0;
        double d = 3.0;
        System.out.println("\nc = " + c + ", d = " + d);
        System.out.println("c / d = " + (c / d));    // Дробное деление
        
        // ИНКРЕМЕНТ И ДЕКРЕМЕНТ
        System.out.println("\n=== ИНКРЕМЕНТ И ДЕКРЕМЕНТ ===\n");
        
        int x = 5;
        System.out.println("x = " + x);
        System.out.println("x++ = " + (x++));  // Постфиксный (сначала использует, потом увеличивает)
        System.out.println("x = " + x);
        System.out.println("++x = " + (++x));  // Префиксный (сначала увеличивает, потом использует)
        System.out.println("x = " + x);
        
        int y = 5;
        System.out.println("\ny = " + y);
        System.out.println("y-- = " + (y--));  // Постфиксный декремент
        System.out.println("y = " + y);
        System.out.println("--y = " + (--y));  // Префиксный декремент
        System.out.println("y = " + y);
        
        // ОПЕРАТОРЫ СРАВНЕНИЯ
        System.out.println("\n=== ОПЕРАТОРЫ СРАВНЕНИЯ ===\n");
        
        int num1 = 10;
        int num2 = 20;
        
        System.out.println("num1 = " + num1 + ", num2 = " + num2);
        System.out.println("num1 == num2: " + (num1 == num2));  // Равно
        System.out.println("num1 != num2: " + (num1 != num2));  // Не равно
        System.out.println("num1 > num2: " + (num1 > num2));    // Больше
        System.out.println("num1 < num2: " + (num1 < num2));    // Меньше
        System.out.println("num1 >= num2: " + (num1 >= num2));  // Больше или равно
        System.out.println("num1 <= num2: " + (num1 <= num2));  // Меньше или равно
        
        // ЛОГИЧЕСКИЕ ОПЕРАТОРЫ
        System.out.println("\n=== ЛОГИЧЕСКИЕ ОПЕРАТОРЫ ===\n");
        
        boolean t = true;
        boolean f = false;
        
        System.out.println("t = " + t + ", f = " + f);
        System.out.println("t && f: " + (t && f));   // И (AND)
        System.out.println("t || f: " + (t || f));   // ИЛИ (OR)
        System.out.println("!t: " + (!t));           // НЕ (NOT)
        System.out.println("!f: " + (!f));
        
        // Короткое замыкание
        System.out.println("\nКороткое замыкание:");
        int z = 0;
        boolean result1 = (z != 0) && (10 / z > 1);  // Второе условие не выполнится
        System.out.println("(z != 0) && (10 / z > 1): " + result1);
        
        // ПОБИТОВЫЕ ОПЕРАТОРЫ
        System.out.println("\n=== ПОБИТОВЫЕ ОПЕРАТОРЫ ===\n");
        
        int p = 5;   // 0101 в двоичной системе
        int q = 3;   // 0011 в двоичной системе
        
        System.out.println("p = " + p + " (binary: " + Integer.toBinaryString(p) + ")");
        System.out.println("q = " + q + " (binary: " + Integer.toBinaryString(q) + ")");
        System.out.println("p & q = " + (p & q) + " (AND)");    // 0001 = 1
        System.out.println("p | q = " + (p | q) + " (OR)");     // 0111 = 7
        System.out.println("p ^ q = " + (p ^ q) + " (XOR)");    // 0110 = 6
        System.out.println("~p = " + (~p) + " (NOT)");          // Инверсия битов
        System.out.println("p << 1 = " + (p << 1) + " (сдвиг влево)");   // 1010 = 10
        System.out.println("p >> 1 = " + (p >> 1) + " (сдвиг вправо)");  // 0010 = 2
        
        // ОПЕРАТОРЫ ПРИСВАИВАНИЯ
        System.out.println("\n=== ОПЕРАТОРЫ ПРИСВАИВАНИЯ ===\n");
        
        int n = 10;
        System.out.println("n = " + n);
        
        n += 5;  // n = n + 5
        System.out.println("n += 5: " + n);
        
        n -= 3;  // n = n - 3
        System.out.println("n -= 3: " + n);
        
        n *= 2;  // n = n * 2
        System.out.println("n *= 2: " + n);
        
        n /= 4;  // n = n / 4
        System.out.println("n /= 4: " + n);
        
        n %= 5;  // n = n % 5
        System.out.println("n %= 5: " + n);
        
        // ТЕРНАРНЫЙ ОПЕРАТОР
        System.out.println("\n=== ТЕРНАРНЫЙ ОПЕРАТОР ===\n");
        
        int age = 18;
        String status = (age >= 18) ? "Совершеннолетний" : "Несовершеннолетний";
        System.out.println("Возраст: " + age);
        System.out.println("Статус: " + status);
        
        int max = (a > b) ? a : b;
        System.out.println("\nМаксимум из " + a + " и " + b + ": " + max);
        
        // ПРИОРИТЕТ ОПЕРАТОРОВ
        System.out.println("\n=== ПРИОРИТЕТ ОПЕРАТОРОВ ===\n");
        
        int result = 2 + 3 * 4;
        System.out.println("2 + 3 * 4 = " + result + " (умножение выполняется первым)");
        
        result = (2 + 3) * 4;
        System.out.println("(2 + 3) * 4 = " + result + " (скобки имеют высший приоритет)");
        
        result = 10 + 5 * 2 - 3;
        System.out.println("10 + 5 * 2 - 3 = " + result);
    }
}

/*
ВЫВОД ПРОГРАММЫ:
================
=== АРИФМЕТИЧЕСКИЕ ОПЕРАТОРЫ ===

a = 10, b = 3
a + b = 13
a - b = 7
a * b = 30
a / b = 3
a % b = 1

c = 10.0, d = 3.0
c / d = 3.3333333333333335

=== ИНКРЕМЕНТ И ДЕКРЕМЕНТ ===

x = 5
x++ = 5
x = 6
++x = 7
x = 7

y = 5
y-- = 5
y = 4
--y = 3
y = 3

=== ОПЕРАТОРЫ СРАВНЕНИЯ ===

num1 = 10, num2 = 20
num1 == num2: false
num1 != num2: true
num1 > num2: false
num1 < num2: true
num1 >= num2: false
num1 <= num2: true

=== ЛОГИЧЕСКИЕ ОПЕРАТОРЫ ===

t = true, f = false
t && f: false
t || f: true
!t: false
!f: true

Короткое замыкание:
(z != 0) && (10 / z > 1): false

=== ПОБИТОВЫЕ ОПЕРАТОРЫ ===

p = 5 (binary: 101)
q = 3 (binary: 11)
p & q = 1 (AND)
p | q = 7 (OR)
p ^ q = 6 (XOR)
~p = -6 (NOT)
p << 1 = 10 (сдвиг влево)
p >> 1 = 2 (сдвиг вправо)

=== ОПЕРАТОРЫ ПРИСВАИВАНИЯ ===

n = 10
n += 5: 15
n -= 3: 12
n *= 2: 24
n /= 4: 6
n %= 5: 1

=== ТЕРНАРНЫЙ ОПЕРАТОР ===

Возраст: 18
Статус: Совершеннолетний

Максимум из 10 и 3: 10

=== ПРИОРИТЕТ ОПЕРАТОРОВ ===

2 + 3 * 4 = 14 (умножение выполняется первым)
(2 + 3) * 4 = 20 (скобки имеют высший приоритет)
10 + 5 * 2 - 3 = 17
*/
