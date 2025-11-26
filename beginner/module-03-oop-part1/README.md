# Module 3: ООП - Часть 1 (Классы и объекты)

```
╔════════════════════════════════════════════════════════════╗
║  MODULE 3: ООП - ЧАСТЬ 1                                   ║
║  Уровень: Beginner                                         ║
║  Время: 2-3 недели                                         ║
╚════════════════════════════════════════════════════════════╝
```

## 📖 Теория

### 3.1 Что такое ООП?

**Объектно-ориентированное программирование (ООП)** - это парадигма программирования, основанная на концепции "объектов", которые содержат данные и код для их обработки.

**Четыре столпа ООП:**
```
┌─────────────────────────────────────────────┐
│  1. ИНКАПСУЛЯЦИЯ (Encapsulation)            │
│     Сокрытие данных и реализации            │
│                                             │
│  2. НАСЛЕДОВАНИЕ (Inheritance)              │
│     Создание новых классов на основе        │
│     существующих                            │
│                                             │
│  3. ПОЛИМОРФИЗМ (Polymorphism)              │
│     Один интерфейс - разные реализации      │
│                                             │
│  4. АБСТРАКЦИЯ (Abstraction)                │
│     Выделение главного, скрытие деталей     │
└─────────────────────────────────────────────┘
```

### 3.2 Классы и объекты

**Класс** - это шаблон (чертёж) для создания объектов.
**Объект** - это экземпляр класса.

```
┌──────────────────┐
│   Класс: Car     │  ← Шаблон
├──────────────────┤
│ - brand          │
│ - model          │
│ - year           │
│ - speed          │
├──────────────────┤
│ + start()        │
│ + stop()         │
│ + accelerate()   │
└──────────────────┘
        │
        │ создание объектов
        ▼
┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│   myCar     │  │  yourCar    │  │  hisCar     │
├─────────────┤  ├─────────────┤  ├─────────────┤
│ Toyota      │  │ BMW         │  │ Mercedes    │
│ Camry       │  │ X5          │  │ E-Class     │
│ 2020        │  │ 2022        │  │ 2021        │
└─────────────┘  └─────────────┘  └─────────────┘
```

### 3.3 Структура класса

```java
[модификатор] class ИмяКласса {
    // Поля (переменные класса)
    тип имяПоля;
    
    // Конструктор
    public ИмяКласса(параметры) {
        // инициализация
    }
    
    // Методы
    [модификатор] тип_возврата имяМетода(параметры) {
        // тело метода
        return значение;
    }
}
```

### 3.4 Модификаторы доступа

```
┌──────────────┬─────────┬─────────┬────────────┬─────────┐
│ Модификатор  │ Класс   │ Пакет   │ Подкласс   │ Мир     │
├──────────────┼─────────┼─────────┼────────────┼─────────┤
│ public       │   ✓     │   ✓     │     ✓      │   ✓     │
│ protected    │   ✓     │   ✓     │     ✓      │   ✗     │
│ default      │   ✓     │   ✓     │     ✗      │   ✗     │
│ private      │   ✓     │   ✗     │     ✗      │   ✗     │
└──────────────┴─────────┴─────────┴────────────┴─────────┘
```

### 3.5 Конструкторы

**Конструктор** - специальный метод для инициализации объекта.

```java
public class Person {
    // Конструктор по умолчанию
    public Person() {
        // инициализация
    }
    
    // Конструктор с параметрами
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

### 3.6 Ключевое слово this

`this` - ссылка на текущий объект.

```java
public class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;  // this.name - поле класса
                           // name - параметр конструктора
    }
}
```

### 3.7 Геттеры и сеттеры

```java
private String name;

// Геттер - получение значения
public String getName() {
    return name;
}

// Сеттер - установка значения
public void setName(String name) {
    this.name = name;
}
```

---

## 💻 Примеры кода

### Пример 1: Простой класс
```java
public class Person {
    // Поля
    String name;
    int age;
    
    // Метод
    void introduce() {
        System.out.println("Привет! Меня зовут " + name + ", мне " + age + " лет.");
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Иван";
        person.age = 25;
        person.introduce();
    }
}
```

### Пример 2: Класс с конструктором
```java
public class Car {
    private String brand;
    private String model;
    private int year;
    
    // Конструктор
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    public void displayInfo() {
        System.out.println(year + " " + brand + " " + model);
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2020);
        Car car2 = new Car("BMW", "X5", 2022);
        
        car1.displayInfo();
        car2.displayInfo();
    }
}
```

### Пример 3: Инкапсуляция
```java
public class BankAccount {
    private String accountNumber;
    private double balance;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Геттеры
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Методы для работы с балансом
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Пополнено: " + amount);
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Снято: " + amount);
            return true;
        }
        System.out.println("Недостаточно средств");
        return false;
    }
}
```

### Пример 4: Перегрузка конструкторов
```java
public class Rectangle {
    private double width;
    private double height;
    
    // Конструктор по умолчанию
    public Rectangle() {
        this.width = 1.0;
        this.height = 1.0;
    }
    
    // Конструктор для квадрата
    public Rectangle(double side) {
        this.width = side;
        this.height = side;
    }
    
    // Конструктор для прямоугольника
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public double getArea() {
        return width * height;
    }
    
    public double getPerimeter() {
        return 2 * (width + height);
    }
}
```

### Пример 5: Перегрузка методов
```java
public class Calculator {
    // Сложение двух чисел
    public int add(int a, int b) {
        return a + b;
    }
    
    // Сложение трёх чисел
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Сложение дробных чисел
    public double add(double a, double b) {
        return a + b;
    }
    
    // Сложение массива чисел
    public int add(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}
```

### Пример 6: Статические члены класса
```java
public class Counter {
    private static int totalCount = 0;  // Статическое поле
    private int instanceCount;           // Поле экземпляра
    
    public Counter() {
        totalCount++;
        instanceCount = totalCount;
    }
    
    public int getInstanceCount() {
        return instanceCount;
    }
    
    // Статический метод
    public static int getTotalCount() {
        return totalCount;
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        
        System.out.println("Всего создано: " + Counter.getTotalCount());
        System.out.println("c1: " + c1.getInstanceCount());
        System.out.println("c2: " + c2.getInstanceCount());
    }
}
```

### Пример 7: Класс с валидацией
```java
public class Student {
    private String name;
    private int age;
    private double gpa;
    
    public Student(String name, int age, double gpa) {
        setName(name);
        setAge(age);
        setGpa(gpa);
    }
    
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
    }
    
    public void setAge(int age) {
        if (age >= 16 && age <= 100) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Возраст должен быть от 16 до 100");
        }
    }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 5.0) {
            this.gpa = gpa;
        } else {
            throw new IllegalArgumentException("GPA должен быть от 0.0 до 5.0");
        }
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
}
```

### Пример 8: Композиция объектов
```java
public class Address {
    private String street;
    private String city;
    private String zipCode;
    
    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    
    @Override
    public String toString() {
        return street + ", " + city + ", " + zipCode;
    }
}

public class Employee {
    private String name;
    private Address address;  // Композиция
    
    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    public void displayInfo() {
        System.out.println("Сотрудник: " + name);
        System.out.println("Адрес: " + address);
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Address addr = new Address("Ленина 10", "Москва", "123456");
        Employee emp = new Employee("Иван Иванов", addr);
        emp.displayInfo();
    }
}
```

### Пример 9: toString() и equals()
```java
public class Book {
    private String title;
    private String author;
    private int year;
    
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
    
    @Override
    public String toString() {
        return "\"" + title + "\" - " + author + " (" + year + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Book book = (Book) obj;
        return year == book.year &&
               title.equals(book.title) &&
               author.equals(book.author);
    }
}
```

### Пример 10: Полный пример - класс Product
```java
public class Product {
    private static int nextId = 1;
    
    private int id;
    private String name;
    private double price;
    private int quantity;
    
    public Product(String name, double price, int quantity) {
        this.id = nextId++;
        this.name = name;
        setPrice(price);
        setQuantity(quantity);
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Количество не может быть отрицательным");
        }
    }
    
    public double getTotalValue() {
        return price * quantity;
    }
    
    public void addStock(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }
    
    public boolean removeStock(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | %s | Цена: %.2f руб | Количество: %d | Всего: %.2f руб",
                           id, name, price, quantity, getTotalValue());
    }
}
```

---

## ✏️ Мини-задания

### Задание 1: Класс Circle
Создайте класс Circle с полем radius и методами для вычисления площади и длины окружности.

### Задание 2: Класс Temperature
Создайте класс для работы с температурой, который хранит значение в Цельсиях и имеет методы для конвертации в Фаренгейт и Кельвин.

### Задание 3: Класс Time
Создайте класс Time с полями hours, minutes, seconds и методами для добавления времени и вывода в формате HH:MM:SS.

### Задание 4: Класс Counter
Создайте класс счётчика с методами increment(), decrement(), reset() и getValue().

### Задание 5: Класс Point
Создайте класс Point2D с координатами x и y, и методом для вычисления расстояния до другой точки.

---

## 🔨 Практические упражнения

### Упражнение 1: Класс Library Book
Создайте класс для книги в библиотеке:
- Поля: title, author, ISBN, isAvailable
- Методы: borrow(), returnBook(), displayInfo()
- Статическое поле для подсчёта общего количества книг

### Упражнение 2: Класс Date
Создайте класс для работы с датами:
- Поля: day, month, year
- Валидация корректности даты
- Методы: isLeapYear(), getDaysInMonth(), addDays()
- Метод toString() для красивого вывода

### Упражнение 3: Класс ShoppingCart
Создайте класс корзины покупок:
- Массив товаров
- Методы: addItem(), removeItem(), getTotalPrice()
- Применение скидки
- Вывод чека

### Упражнение 4: Класс Fraction
Создайте класс для работы с дробями:
- Поля: numerator, denominator
- Методы: add(), subtract(), multiply(), divide()
- Упрощение дроби
- Преобразование в double

### Упражнение 5: Класс Password
Создайте класс для работы с паролями:
- Хранение зашифрованного пароля
- Проверка сложности пароля
- Методы: validate(), changePassword(), checkStrength()

---

## 🎨 Мини-проект: Система управления студентами

Создайте систему для управления информацией о студентах.

**Требования:**

1. **Класс Student:**
   - Поля: id, name, age, course, gpa
   - Конструкторы (с параметрами и без)
   - Геттеры и сеттеры с валидацией
   - Методы: displayInfo(), isExcellent() (GPA >= 4.5)

2. **Класс StudentDatabase:**
   - Массив студентов
   - Методы:
     - addStudent()
     - removeStudent()
     - findStudentById()
     - findStudentsByName()
     - getExcellentStudents()
     - getAverageGpa()
     - displayAllStudents()

3. **Класс Main:**
   - Меню для взаимодействия
   - Добавление/удаление студентов
   - Поиск и фильтрация
   - Статистика

**Пример работы:**
```
╔════════════════════════════════════════╗
║   СИСТЕМА УПРАВЛЕНИЯ СТУДЕНТАМИ        ║
╚════════════════════════════════════════╝

1. Добавить студента
2. Удалить студента
3. Найти студента по ID
4. Показать всех студентов
5. Показать отличников
6. Средний GPA
0. Выход

Выбор: 1

Введите имя: Иван Петров
Введите возраст: 20
Введите курс: 2
Введите GPA: 4.7

✓ Студент добавлен! ID: 1
```

---

## ➡️ Следующий модуль

После завершения переходите к [Module 4: ООП - Часть 2](../module-04-oop-part2/README.md)

