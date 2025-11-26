
/**
 * Example 2: Переменные и типы данных
 *
 * Демонстрирует все примитивные типы данных в Java
 */
public class Example02_Variables {

    public static void main(String[] args) {
        System.out.println("=== ПРИМИТИВНЫЕ ТИПЫ ДАННЫХ ===\n");

        // ЦЕЛЫЕ ЧИСЛА
        byte byteVar = 127;                    // 8 бит: -128 до 127
        short shortVar = 32000;                // 16 бит: -32,768 до 32,767
        int intVar = 2_000_000_000;           // 32 бит: -2^31 до 2^31-1
        long longVar = 9_000_000_000_000L;    // 64 бит: -2^63 до 2^63-1

        System.out.println("ЦЕЛЫЕ ЧИСЛА:");
        System.out.println("byte: " + byteVar + " (размер: " + Byte.BYTES + " байт)");
        System.out.println("short: " + shortVar + " (размер: " + Short.BYTES + " байт)");
        System.out.println("int: " + intVar + " (размер: " + Integer.BYTES + " байт)");
        System.out.println("long: " + longVar + " (размер: " + Long.BYTES + " байт)");

        // ДРОБНЫЕ ЧИСЛА
        float floatVar = 3.14159f;            // 32 бит, ~7 знаков точности
        double doubleVar = 3.141592653589793; // 64 бит, ~15 знаков точности

        System.out.println("\nДРОБНЫЕ ЧИСЛА:");
        System.out.println("float: " + floatVar + " (размер: " + Float.BYTES + " байт)");
        System.out.println("double: " + doubleVar + " (размер: " + Double.BYTES + " байт)");

        // СИМВОЛЫ И ЛОГИЧЕСКИЕ
        char charVar = 'A';                   // 16 бит, Unicode символ
        boolean boolVar = true;               // true или false

        System.out.println("\nДРУГИЕ ТИПЫ:");
        System.out.println("char: " + charVar + " (код: " + (int) charVar + ")");
        System.out.println("boolean: " + boolVar);

        // СТРОКИ (ссылочный тип)
        String stringVar = "Привет, Java!";

        System.out.println("\nСТРОКИ:");
        System.out.println("String: " + stringVar);
        System.out.println("Длина: " + stringVar.length());

        // КОНСТАНТЫ
        final double PI = 3.14159;
        final int MAX_USERS = 100;

        System.out.println("\nКОНСТАНТЫ:");
        System.out.println("PI = " + PI);
        System.out.println("MAX_USERS = " + MAX_USERS);

        // АВТОМАТИЧЕСКОЕ ОПРЕДЕЛЕНИЕ ТИПА (Java 10+)
        var autoInt = 42;                     // int
        var autoString = "Автоопределение";   // String
        var autoDouble = 3.14;                // double

        System.out.println("\nАВТООПРЕДЕЛЕНИЕ ТИПА (var):");
        System.out.println("autoInt: " + autoInt + " (тип: int)");
        System.out.println("autoString: " + autoString + " (тип: String)");
        System.out.println("autoDouble: " + autoDouble + " (тип: double)");

        // ПРЕДЕЛЫ ТИПОВ
        System.out.println("\n=== ПРЕДЕЛЫ ТИПОВ ===");
        System.out.println("byte: от " + Byte.MIN_VALUE + " до " + Byte.MAX_VALUE);
        System.out.println("short: от " + Short.MIN_VALUE + " до " + Short.MAX_VALUE);
        System.out.println("int: от " + Integer.MIN_VALUE + " до " + Integer.MAX_VALUE);
        System.out.println("long: от " + Long.MIN_VALUE + " до " + Long.MAX_VALUE);
        System.out.println("float: от " + Float.MIN_VALUE + " до " + Float.MAX_VALUE);
        System.out.println("double: от " + Double.MIN_VALUE + " до " + Double.MAX_VALUE);
    }
}

/*
ВЫВОД ПРОГРАММЫ:
================
=== ПРИМИТИВНЫЕ ТИПЫ ДАННЫХ ===

ЦЕЛЫЕ ЧИСЛА:
byte: 127 (размер: 1 байт)
short: 32000 (размер: 2 байт)
int: 2000000000 (размер: 4 байт)
long: 9000000000000 (размер: 8 байт)

ДРОБНЫЕ ЧИСЛА:
float: 3.14159 (размер: 4 байт)
double: 3.141592653589793 (размер: 8 байт)

ДРУГИЕ ТИПЫ:
char: A (код: 65)
boolean: true

СТРОКИ:
String: Привет, Java!
Длина: 13

КОНСТАНТЫ:
PI = 3.14159
MAX_USERS = 100

АВТООПРЕДЕЛЕНИЕ ТИПА (var):
autoInt: 42 (тип: int)
autoString: Автоопределение (тип: String)
autoDouble: 3.14 (тип: double)

=== ПРЕДЕЛЫ ТИПОВ ===
byte: от -128 до 127
short: от -32768 до 32767
int: от -2147483648 до 2147483647
long: от -9223372036854775808 до 9223372036854775807
float: от 1.4E-45 до 3.4028235E38
double: от 4.9E-324 до 1.7976931348623157E308
 */
