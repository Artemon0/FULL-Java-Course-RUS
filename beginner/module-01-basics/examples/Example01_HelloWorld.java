/**
 * Example 1: Hello World - Самая простая программа на Java
 * 
 * Компиляция: javac Example01_HelloWorld.java
 * Запуск: java Example01_HelloWorld
 */
public class Example01_HelloWorld {
    public static void main(String[] args) {
        // Вывод текста в консоль
        System.out.println("Hello, World!");
        System.out.println("Добро пожаловать в Java!");
        System.out.println("Это моя первая программа");
        
        // Вывод без перевода строки
        System.out.print("Java ");
        System.out.print("это ");
        System.out.print("круто!");
        System.out.println(); // Перевод строки
        
        // Форматированный вывод
        System.out.printf("Меня зовут %s, мне %d лет%n", "Иван", 25);
    }
}

/*
ВЫВОД ПРОГРАММЫ:
================
Hello, World!
Добро пожаловать в Java!
Это моя первая программа
Java это круто!
Меня зовут Иван, мне 25 лет
*/
