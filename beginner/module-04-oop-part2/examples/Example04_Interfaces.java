
/**
 * Example 4: Интерфейсы - контракты для классов
 */

interface Flyable {

    void fly();

    void land();
}

interface Swimmable {

    void swim();
}

interface Walkable {

    void walk();
}

class Duck implements Flyable, Swimmable, Walkable {

    private String name;

    public Duck(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " летит");
    }

    @Override
    public void land() {
        System.out.println(name + " приземляется");
    }

    @Override
    public void swim() {
        System.out.println(name + " плывёт");
    }

    @Override
    public void walk() {
        System.out.println(name + " ходит");
    }
}

class Airplane implements Flyable {

    private String model;

    public Airplane(String model) {
        this.model = model;
    }

    @Override
    public void fly() {
        System.out.println("Самолёт " + model + " летит на высоте 10000м");
    }

    @Override
    public void land() {
        System.out.println("Самолёт " + model + " приземляется");
    }
}

class Fish implements Swimmable {

    private String species;

    public Fish(String species) {
        this.species = species;
    }

    @Override
    public void swim() {
        System.out.println(species + " плывёт под водой");
    }
}

public class Example04_Interfaces {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ ОБЪЕКТОВ ===\n");

        Duck duck = new Duck("Утка Дональд");
        Airplane plane = new Airplane("Boeing 747");
        Fish fish = new Fish("Золотая рыбка");

        System.out.println("=== УТКА (все интерфейсы) ===\n");
        duck.walk();
        duck.fly();
        duck.land();
        duck.swim();

        System.out.println("\n=== САМОЛЁТ (Flyable) ===\n");
        plane.fly();
        plane.land();

        System.out.println("\n=== РЫБА (Swimmable) ===\n");
        fish.swim();

        System.out.println("\n=== ПОЛИМОРФИЗМ С ИНТЕРФЕЙСАМИ ===\n");

        Flyable[] flyingObjects = {duck, plane};
        System.out.println("Все летающие объекты:");
        for (Flyable obj : flyingObjects) {
            obj.fly();
        }

        System.out.println();

        Swimmable[] swimmingObjects = {duck, fish};
        System.out.println("Все плавающие объекты:");
        for (Swimmable obj : swimmingObjects) {
            obj.swim();
        }
    }
}
