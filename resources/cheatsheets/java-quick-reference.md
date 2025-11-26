# Java Quick Reference - Шпаргалка

```
╔════════════════════════════════════════════════════════════╗
║  JAVA QUICK REFERENCE                                      ║
║  Быстрая справка по Java                                   ║
╚════════════════════════════════════════════════════════════╝
```

## 📝 Базовый синтаксис

### Типы данных
```java
// Примитивные типы
byte    b = 127;           // 8 бит  (-128 до 127)
short   s = 32767;         // 16 бит (-32,768 до 32,767)
int     i = 2147483647;    // 32 бит
long    l = 9223372036854775807L;  // 64 бит
float   f = 3.14f;         // 32 бит
double  d = 3.14159;       // 64 бит
char    c = 'A';           // 16 бит (Unicode)
boolean bool = true;       // true или false

// Ссылочные типы
String str = "Hello";
Integer num = 42;
```

### Операторы
```java
// Арифметические
+ - * / %

// Сравнения
== != > < >= <=

// Логические
&& || !

// Присваивания
= += -= *= /= %=

// Инкремент/декремент
++ --

// Тернарный
условие ? значение1 : значение2
```

### Управление потоком
```java
// if-else
if (условие) {
    // код
} else if (другое_условие) {
    // код
} else {
    // код
}

// switch
switch (переменная) {
    case значение1:
        // код
        break;
    case значение2:
        // код
        break;
    default:
        // код
}

// for
for (int i = 0; i < 10; i++) {
    // код
}

// for-each
for (Type item : collection) {
    // код
}

// while
while (условие) {
    // код
}

// do-while
do {
    // код
} while (условие);
```

## 🎯 ООП

### Класс
```java
public class Person {
    // Поля
    private String name;
    private int age;
    
    // Конструктор
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    // Методы
    public void introduce() {
        System.out.println("Я " + name);
    }
}
```

### Наследование
```java
public class Student extends Person {
    private String university;
    
    public Student(String name, int age, String university) {
        super(name, age);
        this.university = university;
    }
    
    @Override
    public void introduce() {
        super.introduce();
        System.out.println("Учусь в " + university);
    }
}
```

### Интерфейс
```java
public interface Drawable {
    void draw();  // абстрактный метод
    
    default void display() {  // default метод
        System.out.println("Displaying...");
    }
}

public class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}
```

### Абстрактный класс
```java
public abstract class Shape {
    protected String color;
    
    public abstract double getArea();
    
    public void setColor(String color) {
        this.color = color;
    }
}
```

## 📦 Коллекции

### List
```java
// ArrayList
List<String> list = new ArrayList<>();
list.add("элемент");
list.get(0);
list.remove(0);
list.size();
list.contains("элемент");

// LinkedList
LinkedList<Integer> linkedList = new LinkedList<>();
linkedList.addFirst(1);
linkedList.addLast(3);
linkedList.getFirst();
linkedList.getLast();
```

### Set
```java
// HashSet
Set<String> set = new HashSet<>();
set.add("элемент");
set.remove("элемент");
set.contains("элемент");

// TreeSet (отсортированный)
TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.add(5);
treeSet.first();
treeSet.last();
```

### Map
```java
// HashMap
Map<String, Integer> map = new HashMap<>();
map.put("ключ", 123);
map.get("ключ");
map.remove("ключ");
map.containsKey("ключ");
map.containsValue(123);

// Итерация
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
}
```

## 🌊 Stream API

### Создание Stream
```java
Stream<String> stream = list.stream();
Stream<String> stream = Stream.of("a", "b", "c");
Stream<Integer> stream = Arrays.stream(array);
```

### Промежуточные операции
```java
stream.filter(x -> x > 5)           // фильтрация
      .map(x -> x * 2)              // преобразование
      .distinct()                   // уникальные
      .sorted()                     // сортировка
      .limit(10)                    // ограничение
      .skip(5)                      // пропуск
```

### Терминальные операции
```java
.forEach(System.out::println)       // для каждого
.collect(Collectors.toList())       // в коллекцию
.reduce(0, (a, b) -> a + b)        // свёртка
.count()                            // количество
.anyMatch(x -> x > 5)              // есть ли
.allMatch(x -> x > 0)              // все ли
.findFirst()                        // первый
.min(Comparator.naturalOrder())     // минимум
.max(Comparator.naturalOrder())     // максимум
```

### Collectors
```java
// В List
.collect(Collectors.toList())

// В Set
.collect(Collectors.toSet())

// В Map
.collect(Collectors.toMap(k -> k, v -> v))

// Группировка
.collect(Collectors.groupingBy(Person::getCity))

// Объединение в строку
.collect(Collectors.joining(", "))

// Подсчёт
.collect(Collectors.counting())

// Сумма
.collect(Collectors.summingInt(Person::getAge))
```

## 🧵 Многопоточность

### Создание потока
```java
// Extends Thread
class MyThread extends Thread {
    public void run() {
        // код потока
    }
}
new MyThread().start();

// Implements Runnable
class MyRunnable implements Runnable {
    public void run() {
        // код потока
    }
}
new Thread(new MyRunnable()).start();

// Lambda
new Thread(() -> {
    // код потока
}).start();
```

### Синхронизация
```java
// Synchronized метод
public synchronized void method() {
    // код
}

// Synchronized блок
synchronized(object) {
    // код
}

// Lock
Lock lock = new ReentrantLock();
lock.lock();
try {
    // код
} finally {
    lock.unlock();
}
```

### ExecutorService
```java
ExecutorService executor = Executors.newFixedThreadPool(5);

executor.submit(() -> {
    // задача
});

executor.shutdown();
```

## 📁 Работа с файлами

### Чтение файла
```java
// Построчно
List<String> lines = Files.readAllLines(Paths.get("file.txt"));

// Весь файл
String content = Files.readString(Paths.get("file.txt"));

// BufferedReader
try (BufferedReader reader = new BufferedReader(
        new FileReader("file.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

### Запись в файл
```java
// Простая запись
Files.writeString(Paths.get("file.txt"), "content");

// Список строк
List<String> lines = Arrays.asList("line1", "line2");
Files.write(Paths.get("file.txt"), lines);

// BufferedWriter
try (BufferedWriter writer = new BufferedWriter(
        new FileWriter("file.txt"))) {
    writer.write("content");
}
```

## 🌱 Spring Boot

### Аннотации
```java
@SpringBootApplication  // Главный класс
@RestController        // REST контроллер
@Service              // Сервис
@Repository           // Репозиторий
@Component            // Компонент
@Configuration        // Конфигурация
@Bean                 // Бин

@Autowired            // Внедрение зависимости
@Value("${prop}")     // Значение из properties

@GetMapping           // GET запрос
@PostMapping          // POST запрос
@PutMapping           // PUT запрос
@DeleteMapping        // DELETE запрос
@RequestMapping       // Общий маппинг

@PathVariable         // Переменная из пути
@RequestParam         // Параметр запроса
@RequestBody          // Тело запроса
```

### REST Controller
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }
    
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, 
                      @RequestBody User user) {
        return userService.update(id, user);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

## 🧪 JUnit

### Тестовый класс
```java
@Test
public void testMethod() {
    // Arrange
    int a = 5;
    int b = 3;
    
    // Act
    int result = calculator.add(a, b);
    
    // Assert
    assertEquals(8, result);
}

// Другие assertions
assertTrue(condition);
assertFalse(condition);
assertNull(object);
assertNotNull(object);
assertThrows(Exception.class, () -> method());
```

### Lifecycle
```java
@BeforeAll
static void setupAll() { }

@BeforeEach
void setup() { }

@Test
void test() { }

@AfterEach
void tearDown() { }

@AfterAll
static void tearDownAll() { }
```

## 💡 Полезные паттерны

### Singleton
```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### Builder
```java
public class Person {
    private String name;
    private int age;
    
    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }
    
    public static class Builder {
        private String name;
        private int age;
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Person build() {
            return new Person(this);
        }
    }
}

// Использование
Person person = new Person.Builder()
    .name("Иван")
    .age(25)
    .build();
```

### Factory
```java
public interface Animal {
    void makeSound();
}

public class AnimalFactory {
    public static Animal createAnimal(String type) {
        switch (type) {
            case "dog": return new Dog();
            case "cat": return new Cat();
            default: throw new IllegalArgumentException();
        }
    }
}
```

---

## 🔗 Полезные ссылки

- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Spring Framework](https://spring.io/)
- [Baeldung](https://www.baeldung.com/)
- [JUnit 5](https://junit.org/junit5/)

---

**Сохраните эту шпаргалку для быстрого доступа! 📚**
