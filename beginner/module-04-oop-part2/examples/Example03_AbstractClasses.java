
/**
 * Example 3: Абстрактные классы
 */

abstract class Vehicle {

    protected String brand;
    protected String model;
    protected int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Абстрактные методы
    public abstract void start();

    public abstract void stop();

    public abstract double calculateFuelConsumption();

    // Обычный метод
    public void displayInfo() {
        System.out.println("Марка: " + brand);
        System.out.println("Модель: " + model);
        System.out.println("Год: " + year);
    }
}

class Car extends Vehicle {

    private int numberOfDoors;

    public Car(String brand, String model, int year, int numberOfDoors) {
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void start() {
        System.out.println("Автомобиль " + brand + " " + model + " заведён");
    }

    @Override
    public void stop() {
        System.out.println("Автомобиль " + brand + " " + model + " остановлен");
    }

    @Override
    public double calculateFuelConsumption() {
        return 8.5; // л/100км
    }

    @Override
    public void displayInfo() {
        System.out.println("Тип: Автомобиль");
        super.displayInfo();
        System.out.println("Количество дверей: " + numberOfDoors);
        System.out.printf("Расход топлива: %.1f л/100км%n", calculateFuelConsumption());
    }
}

class Motorcycle extends Vehicle {

    private boolean hasSidecar;

    public Motorcycle(String brand, String model, int year, boolean hasSidecar) {
        super(brand, model, year);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void start() {
        System.out.println("Мотоцикл " + brand + " " + model + " заведён");
    }

    @Override
    public void stop() {
        System.out.println("Мотоцикл " + brand + " " + model + " остановлен");
    }

    @Override
    public double calculateFuelConsumption() {
        return hasSidecar ? 5.0 : 3.5; // л/100км
    }

    @Override
    public void displayInfo() {
        System.out.println("Тип: Мотоцикл");
        super.displayInfo();
        System.out.println("Коляска: " + (hasSidecar ? "Да" : "Нет"));
        System.out.printf("Расход топлива: %.1f л/100км%n", calculateFuelConsumption());
    }
}

class Truck extends Vehicle {

    private double loadCapacity;

    public Truck(String brand, String model, int year, double loadCapacity) {
        super(brand, model, year);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void start() {
        System.out.println("Грузовик " + brand + " " + model + " заведён");
    }

    @Override
    public void stop() {
        System.out.println("Грузовик " + brand + " " + model + " остановлен");
    }

    @Override
    public double calculateFuelConsumption() {
        return 15.0 + (loadCapacity * 0.5); // л/100км
    }

    @Override
    public void displayInfo() {
        System.out.println("Тип: Грузовик");
        super.displayInfo();
        System.out.printf("Грузоподъёмность: %.1f тонн%n", loadCapacity);
        System.out.printf("Расход топлива: %.1f л/100км%n", calculateFuelConsumption());
    }
}

public class Example03_AbstractClasses {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ ТРАНСПОРТНЫХ СРЕДСТВ ===\n");

        Car car = new Car("Toyota", "Camry", 2022, 4);
        Motorcycle motorcycle = new Motorcycle("Harley-Davidson", "Street 750", 2021, false);
        Truck truck = new Truck("Volvo", "FH16", 2023, 20.0);

        car.displayInfo();
        System.out.println();

        motorcycle.displayInfo();
        System.out.println();

        truck.displayInfo();
        System.out.println();

        System.out.println("=== РАБОТА С ТРАНСПОРТОМ ===\n");

        Vehicle[] vehicles = {car, motorcycle, truck};

        for (Vehicle vehicle : vehicles) {
            vehicle.start();
        }

        System.out.println();

        double totalConsumption = 0;
        for (Vehicle vehicle : vehicles) {
            totalConsumption += vehicle.calculateFuelConsumption();
        }

        System.out.printf("Общий расход топлива: %.1f л/100км%n", totalConsumption);

        System.out.println();

        for (Vehicle vehicle : vehicles) {
            vehicle.stop();
        }
    }
}
