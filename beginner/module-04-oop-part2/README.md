# Module 4: ООП - Часть 2 (Наследование и полиморфизм)

```
╔════════════════════════════════════════════════════════════╗
║  MODULE 4: ООП - ЧАСТЬ 2                                   ║
║  Уровень: Beginner                                         ║
║  Время: 2-3 недели                                         ║
╚════════════════════════════════════════════════════════════╝
```

## 📖 Теория

### 4.1 Наследование (Inheritance)

**Наследование** - механизм создания нового класса на основе существующего.

```
        ┌──────────────┐
        │   Animal     │  ← Родительский класс (суперкласс)
        ├──────────────┤
        │ + name       │
        │ + age        │
        │ + eat()      │
        │ + sleep()    │
        └───────┬──────┘
                │
        ┌───────┴───────┐
        │               │
   ┌────▼────┐    ┌────▼────┐
   │   Dog   │    │   Cat   │  ← Дочерние классы (подклассы)
   ├─────────┤    ├─────────┤
   │ + bark()│    │ + meow()│
   └─────────┘    └─────────┘
```

**Синтаксис:**
```java
public class ДочернийКласс extends РодительскийКласс {
    // дополнительные поля и методы
}
```

### 4.2 Ключевое слово super

`super` - ссылка на родительский класс.

```java
super.метод()        // вызов метода родителя
super(параметры)     // вызов конструктора родителя
```

### 4.3 Переопределение методов (Override)

```java
@Override  // аннотация (рекомендуется)
public void метод() {
    // новая реализация
}
```

### 4.4 Полиморфизм (Polymorphism)

**Полиморфизм** - способность объекта принимать множество форм.

```
Animal animal1 = new Dog();   // Dog IS-A Animal
Animal animal2 = new Cat();   // Cat IS-A Animal

animal1.makeSound();  // Гав!
animal2.makeSound();  // Мяу!
```

### 4.5 Абстрактные классы

**Абстрактный класс** - класс, который не может быть инстанцирован.

```java
public abstract class Shape {
    abstract double getArea();  // абстрактный метод
    
    void display() {            // обычный метод
        System.out.println("Площадь: " + getArea());
    }
}
```

### 4.6 Интерфейсы

**Интерфейс** - контракт, который класс обязуется выполнить.

```java
public interface Drawable {
    void draw();  // все методы public abstract по умолчанию
}

public class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Рисую круг");
    }
}
```

**Множественная реализация:**
```java
public class SmartPhone implements Callable, Photographable, Browsable {
    // реализация всех методов интерфейсов
}
```

### 4.7 Модификатор final

```
final переменная  - константа
final метод       - нельзя переопределить
final класс       - нельзя наследовать
```

### 4.8 Иерархия классов

```
           ┌─────────────┐
           │   Object    │  ← Корень всех классов
           └──────┬──────┘
                  │
           ┌──────▼──────┐
           │   Vehicle   │
           └──────┬──────┘
                  │
        ┌─────────┴─────────┐
        │                   │
   ┌────▼────┐        ┌────▼────┐
   │   Car   │        │  Bike   │
   └────┬────┘        └─────────┘
        │
   ┌────▼────┐
   │  Tesla  │
   └─────────┘
```

---

## 💻 Примеры кода

### Пример 1: Простое наследование
```java
// Родительский класс
public class Animal {
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
}

// Дочерний класс
public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // вызов конструктора родителя
        this.breed = breed;
    }
    
    public void bark() {
        System.out.println(name + " лает: Гав-гав!");
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Бобик", 3, "Овчарка");
        dog.eat();    // метод из Animal
        dog.sleep();  // метод из Animal
        dog.bark();   // метод из Dog
    }
}
```

### Пример 2: Переопределение методов
```java
public class Animal {
    public void makeSound() {
        System.out.println("Животное издаёт звук");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Гав-гав!");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Мяу!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();
        Cat cat = new Cat();
        
        animal.makeSound();  // Животное издаёт звук
        dog.makeSound();     // Гав-гав!
        cat.makeSound();     // Мяу!
    }
}
```

### Пример 3: Полиморфизм
```java
public class Main {
    public static void main(String[] args) {
        // Полиморфизм - один тип, разные объекты
        Animal[] animals = new Animal[3];
        animals[0] = new Dog("Бобик", 3, "Овчарка");
        animals[1] = new Cat("Мурка", 2, "Сиамская");
        animals[2] = new Animal("Неизвестное", 1);
        
        // Вызов метода для каждого животного
        for (Animal animal : animals) {
            animal.makeSound();  // Разные реализации!
        }
    }
}
```

### Пример 4: Абстрактный класс
```java
public abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Абстрактные методы
    public abstract double getArea();
    public abstract double getPerimeter();
    
    // Обычный метод
    public void displayInfo() {
        System.out.println("Цвет: " + color);
        System.out.println("Площадь: " + getArea());
        System.out.println("Периметр: " + getPerimeter());
    }
}

public class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double getArea() {
        return width * height;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}
```

### Пример 5: Интерфейсы
```java
public interface Flyable {
    void fly();
    void land();
}

public interface Swimmable {
    void swim();
}

public class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Утка летит");
    }
    
    @Override
    public void land() {
        System.out.println("Утка приземляется");
    }
    
    @Override
    public void swim() {
        System.out.println("Утка плывёт");
    }
}

public class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("Самолёт летит");
    }
    
    @Override
    public void land() {
        System.out.println("Самолёт приземляется");
    }
}
```

### Пример 6: Иерархия сотрудников
```java
public class Employee {
    protected String name;
    protected int id;
    protected double baseSalary;
    
    public Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }
    
    public double calculateSalary() {
        return baseSalary;
    }
    
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Имя: " + name);
        System.out.println("Зарплата: " + calculateSalary());
    }
}

public class Manager extends Employee {
    private double bonus;
    
    public Manager(String name, int id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
}

public class Developer extends Employee {
    private int projectsCompleted;
    private double projectBonus;
    
    public Developer(String name, int id, double baseSalary, int projectsCompleted) {
        super(name, id, baseSalary);
        this.projectsCompleted = projectsCompleted;
        this.projectBonus = 5000;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + (projectsCompleted * projectBonus);
    }
}
```

### Пример 7: Интерфейсы с default методами (Java 8+)
```java
public interface Payment {
    void processPayment(double amount);
    
    // Default метод
    default void printReceipt(double amount) {
        System.out.println("═══════════════════");
        System.out.println("ЧЕК");
        System.out.println("Сумма: " + amount + " руб");
        System.out.println("═══════════════════");
    }
}

public class CreditCardPayment implements Payment {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата картой " + cardNumber);
        System.out.println("Сумма: " + amount);
        printReceipt(amount);  // использование default метода
    }
}
```

### Пример 8: Instanceof и приведение типов
```java
public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
            new Dog("Бобик", 3, "Овчарка"),
            new Cat("Мурка", 2, "Сиамская"),
            new Animal("Хомяк", 1)
        };
        
        for (Animal animal : animals) {
            animal.makeSound();
            
            // Проверка типа
            if (animal instanceof Dog) {
                Dog dog = (Dog) animal;  // приведение типа
                dog.bark();
            } else if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                cat.meow();
            }
            
            System.out.println("---");
        }
    }
}
```

### Пример 9: Композиция vs Наследование
```java
// Наследование (IS-A отношение)
public class Car extends Vehicle {
    // Car IS-A Vehicle
}

// Композиция (HAS-A отношение)
public class Car {
    private Engine engine;  // Car HAS-A Engine
    private Wheel[] wheels; // Car HAS-A Wheels
    
    public Car() {
        this.engine = new Engine();
        this.wheels = new Wheel[4];
    }
}
```

### Пример 10: Полная иерархия - банковские счета
```java
public abstract class BankAccount {
    protected String accountNumber;
    protected String ownerName;
    protected double balance;
    
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Пополнено: " + amount);
        }
    }
    
    public abstract boolean withdraw(double amount);
    
    public double getBalance() {
        return balance;
    }
    
    public void displayInfo() {
        System.out.println("Счёт: " + accountNumber);
        System.out.println("Владелец: " + ownerName);
        System.out.println("Баланс: " + balance);
    }
}

public class SavingsAccount extends BankAccount {
    private double interestRate;
    
    public SavingsAccount(String accountNumber, String ownerName, 
                         double initialBalance, double interestRate) {
        super(accountNumber, ownerName, initialBalance);
        this.interestRate = interestRate;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Снято: " + amount);
            return true;
        }
        System.out.println("Недостаточно средств");
        return false;
    }
    
    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Начислены проценты: " + interest);
    }
}

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    
    public CheckingAccount(String accountNumber, String ownerName,
                          double initialBalance, double overdraftLimit) {
        super(accountNumber, ownerName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            System.out.println("Снято: " + amount);
            if (balance < 0) {
                System.out.println("Внимание! Овердрафт: " + Math.abs(balance));
            }
            return true;
        }
        System.out.println("Превышен лимит овердрафта");
        return false;
    }
}
```

---

## ✏️ Мини-задания

### Задание 1: Иерархия транспорта
Создайте классы Vehicle, Car, Motorcycle с соответствующими полями и методами.

### Задание 2: Геометрические фигуры
Создайте абстрактный класс Shape и классы Circle, Rectangle, Triangle.

### Задание 3: Интерфейс Comparable
Создайте класс Person, реализующий интерфейс Comparable для сортировки по возрасту.

### Задание 4: Животные
Создайте иерархию: Animal → Mammal, Bird, Fish с уникальными методами.

### Задание 5: Электроника
Создайте интерфейс Chargeable и классы Phone, Laptop, Tablet.

---

## 🔨 Практические упражнения

### Упражнение 1: Система оплаты
Создайте интерфейс PaymentMethod и классы:
- CreditCard
- DebitCard
- PayPal
- Cash

Каждый должен реализовать метод pay() по-своему.

### Упражнение 2: Зоопарк
Создайте систему зоопарка:
- Абстрактный класс Animal
- Классы: Lion, Elephant, Monkey, Penguin
- Интерфейсы: Flyable, Swimmable, Climbable
- Метод для кормления всех животных

### Упражнение 3: Медиа плеер
Создайте иерархию медиа файлов:
- Абстрактный класс MediaFile
- Классы: AudioFile, VideoFile, ImageFile
- Методы: play(), pause(), stop()
- Уникальные методы для каждого типа

### Упражнение 4: Учебное заведение
Создайте систему:
- Класс Person
- Классы Student, Teacher, Administrator (наследуют Person)
- Интерфейсы: Teachable, Learnable
- Методы для взаимодействия

### Упражнение 5: Игровые персонажи
Создайте RPG систему:
- Абстрактный класс Character
- Классы: Warrior, Mage, Archer
- Интерфейсы: Attackable, Defendable, Healable
- Система боя

---

## 🎨 Мини-проект: Система управления библиотекой

Создайте полноценную систему управления библиотекой с использованием ООП.

**Требования:**

1. **Абстрактный класс LibraryItem:**
   - id, title, author, year
   - Абстрактные методы: getType(), getMaxBorrowDays()
   - Методы: displayInfo()

2. **Классы-наследники:**
   - Book (жанр, количество страниц)
   - Magazine (номер выпуска, месяц)
   - DVD (продолжительность, жанр)

3. **Класс Member:**
   - id, name, membershipType (обычный/премиум)
   - Список взятых предметов
   - Методы: borrowItem(), returnItem()

4. **Класс Library:**
   - Коллекция предметов
   - Коллекция членов
   - Методы управления

5. **Интерфейсы:**
   - Borrowable (методы для займа)
   - Searchable (методы поиска)

**Функционал:**
- Добавление/удаление предметов
- Регистрация членов
- Займ и возврат предметов
- Поиск по различным критериям
- Просмотр истории
- Расчёт штрафов за просрочку

---

## ➡️ Финальный проект уровня Beginner

Поздравляем! Вы завершили все модули уровня Beginner.
Переходите к [Финальному проекту: Текстовое приключение](../project-text-adventure/README.md)

