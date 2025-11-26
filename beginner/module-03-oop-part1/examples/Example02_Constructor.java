
/**
 * Example 2: Конструкторы - инициализация объектов
 */

class Car {

    String brand;
    String model;
    int year;
    String color;
    double price;

    // Конструктор по умолчанию
    Car() {
        brand = "Unknown";
        model = "Unknown";
        year = 2020;
        color = "White";
        price = 0.0;
    }

    // Конструктор с параметрами
    Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = "White";
        this.price = 0.0;
    }

    // Конструктор со всеми параметрами
    Car(String brand, String model, int year, String color, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    void displayInfo() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("  Марка: " + brand);
        System.out.println("  Модель: " + model);
        System.out.println("  Год: " + year);
        System.out.println("  Цвет: " + color);
        System.out.printf("  Цена: %.2f руб%n", price);
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
    }

    void start() {
        System.out.println(brand + " " + model + " заведён. Врум-врум!");
    }

    void stop() {
        System.out.println(brand + " " + model + " остановлен.");
    }

    int getAge() {
        return 2024 - year;
    }
}

public class Example02_Constructor {

    public static void main(String[] args) {
        System.out.println("=== КОНСТРУКТОР ПО УМОЛЧАНИЮ ===\n");

        Car car1 = new Car();
        car1.displayInfo();

        System.out.println("=== КОНСТРУКТОР С ПАРАМЕТРАМИ ===\n");

        Car car2 = new Car("Toyota", "Camry", 2022);
        car2.displayInfo();

        System.out.println("=== ПОЛНЫЙ КОНСТРУКТОР ===\n");

        Car car3 = new Car("BMW", "X5", 2023, "Чёрный", 5500000.0);
        car3.displayInfo();

        Car car4 = new Car("Mercedes", "E-Class", 2021, "Серебристый", 4800000.0);
        car4.displayInfo();

        System.out.println("=== РАБОТА С ОБЪЕКТАМИ ===\n");

        car3.start();
        System.out.println("Возраст автомобиля: " + car3.getAge() + " лет");
        car3.stop();

        System.out.println();

        car4.start();
        System.out.println("Возраст автомобиля: " + car4.getAge() + " лет");
        car4.stop();
    }
}
