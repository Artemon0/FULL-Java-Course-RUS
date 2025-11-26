
/**
 * Example 1: Наследование - создание иерархии классов
 */

// Родительский класс
class Animal {

    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " ест");
    }

    public void sleep() {
        System.out.println(name + " спит");
    }

    public void makeSound() {
        System.out.println(name + " издаёт звук");
    }

    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
    }
}

// Дочерний класс Dog
class Dog extends Animal {

    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " лает: Гав-гав!");
    }

    public void fetch() {
        System.out.println(name + " приносит палку");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Порода: " + breed);
    }
}

// Дочерний класс Cat
class Cat extends Animal {

    private String color;

    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " мяукает: Мяу!");
    }

    public void scratch() {
        System.out.println(name + " точит когти");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Цвет: " + color);
    }
}

// Дочерний класс Bird
class Bird extends Animal {

    private double wingSpan;

    public Bird(String name, int age, double wingSpan) {
        super(name, age);
        this.wingSpan = wingSpan;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " поёт: Чирик-чирик!");
    }

    public void fly() {
        System.out.println(name + " летит");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Размах крыльев: %.1f см%n", wingSpan);
    }
}

public class Example01_Inheritance {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ ОБЪЕКТОВ ===\n");

        Dog dog = new Dog("Бобик", 3, "Овчарка");
        Cat cat = new Cat("Мурка", 2, "Рыжий");
        Bird bird = new Bird("Кеша", 1, 25.5);

        System.out.println("Собака:");
        dog.displayInfo();
        System.out.println();

        System.out.println("Кошка:");
        cat.displayInfo();
        System.out.println();

        System.out.println("Птица:");
        bird.displayInfo();
        System.out.println();

        System.out.println("=== ОБЩИЕ МЕТОДЫ ===\n");

        dog.eat();
        dog.sleep();

        cat.eat();
        cat.sleep();

        bird.eat();
        bird.sleep();

        System.out.println();

        System.out.println("=== ПЕРЕОПРЕДЕЛЁННЫЕ МЕТОДЫ ===\n");

        dog.makeSound();
        cat.makeSound();
        bird.makeSound();

        System.out.println();

        System.out.println("=== УНИКАЛЬНЫЕ МЕТОДЫ ===\n");

        dog.fetch();
        cat.scratch();
        bird.fly();

        System.out.println();

        System.out.println("=== ПОЛИМОРФИЗМ ===\n");

        Animal[] animals = {dog, cat, bird};

        System.out.println("Все животные издают звуки:");
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}
